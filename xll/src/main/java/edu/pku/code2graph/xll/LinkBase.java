package edu.pku.code2graph.xll;

import edu.pku.code2graph.model.URIBase;

public class LinkBase<T extends URIBase<?>> {
  public T def;
  public T use;
  public final String name;
  public final boolean hidden;

  public LinkBase(final T def, final T use, final String name) {
    this(def, use, name, false);
  }

  public LinkBase(final T def, final T use, final String name, final boolean hidden) {
    this.def = def;
    this.use = use;
    this.name = name;
    this.hidden = hidden;
  }

  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    output.append("(");
    output.append(def.toString());
    output.append(", ");
    output.append(use.toString());
    output.append(")");
    return output.toString();
  }
}
