/*
 * This file is part of GumTree.
 *
 * GumTree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GumTree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GumTree.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2011-2015 Jean-Rémy Falleri <jr.falleri@gmail.com>
 * Copyright 2011-2015 Floréal Morandat <florealm@gmail.com>
 */

package edu.pku.code2graph.gen;

import edu.pku.code2graph.model.Edge;
import edu.pku.code2graph.model.Node;
import org.jgrapht.Graph;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/** Registry of tree generators, using a singleton pattern. */
public class Generators extends Registry<String, Generator, Register> {

  private static Generators registry;

  /** Return the tree generators registry instance (singleton pattern) */
  public static Generators getInstance() {
    if (registry == null) registry = new Generators();
    return registry;
  }

  /**
   * Automatically search a tree generator for the given file path, and use it to parse it
   *
   * @param filePath the absolute file path
   * @return the TreeContext of the file
   * @throws UnsupportedOperationException if no suitable generator is found
   */
  public Graph<Node, Edge> generateFrom(String filePath)
      throws UnsupportedOperationException, IOException {
    Generator p = get(filePath);
    if (p == null) {
      throw new UnsupportedOperationException("No generator found for file: " + filePath);
    }
    return p.generateFrom().file(filePath);
  }

  /**
   * Notice that currently only files in a same and single language are supported
   * @param filePaths
   * @return
   * @throws UnsupportedOperationException
   * @throws IOException
   */
  public Graph<Node, Edge> generateFrom(List<String> filePaths)
      throws UnsupportedOperationException, IOException {
    if (filePaths.size() == 0) {
      throw new UnsupportedOperationException("The given file paths are empty");
    }
    Generator p = get(filePaths.get(0));
    if (p == null) {
      throw new UnsupportedOperationException("No generator found for file: " + filePaths.get(0));
    }
    return p.generateFrom().files(filePaths);
  }

  /**
   * Use the tree generator with the supplied name to parse the file at the given path to parse it
   *
   * @param generator the tree generator's name. if null, fallbacks to @see getTree(String)
   * @throws UnsupportedOperationException if no suitable generator is found
   */
  public Graph<Node, Edge> generateFrom(String filePath, String generator)
      throws UnsupportedOperationException, IOException {
    if (generator == null) return generateFrom(filePath);

    for (Entry e : entries)
      if (e.id.equals(generator)) return e.instantiate(null).generateFrom().file(filePath);

    throw new UnsupportedOperationException("No generator \"" + generator + "\" found.");
  }

  public boolean has(String generator) {
    return this.findById(generator) != null;
  }

  /** Indicate whether or not the given file path has a related tree generator */
  public boolean hasGeneratorForFile(String file) {
    return get(file) != null;
  }

  @Override
  protected Entry newEntry(Class<? extends Generator> clazz, Register annotation) {
    return new Entry(annotation.id(), clazz, defaultFactory(clazz), annotation.priority()) {
      final Pattern[] accept;

      {
        String[] accept = annotation.accept();
        this.accept = new Pattern[accept.length];
        for (int i = 0; i < accept.length; i++) this.accept[i] = Pattern.compile(accept[i]);
      }

      @Override
      protected boolean handle(String key) {
        for (Pattern pattern : accept) if (pattern.matcher(key).find()) return true;
        return false;
      }

      @Override
      public String toString() {
        return String.format(
            "%d\t%s\t%s: %s", priority, id, Arrays.toString(accept), clazz.getCanonicalName());
      }
    };
  }
}
