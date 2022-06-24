package edu.pku.code2graph.client;

import edu.pku.code2graph.exception.NonexistPathException;
import edu.pku.code2graph.io.GraphVizExporter;
import edu.pku.code2graph.model.Edge;
import edu.pku.code2graph.model.Language;
import edu.pku.code2graph.model.Node;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.log4j.BasicConfigurator;
import org.jgrapht.Graph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
  private static String tempDir = System.getProperty("user.home") + "/coding/data/temp/c2g";

  public static void main(String[] args) {
    // config the logger with log4j
    //    System.out.println(System.getProperty("user.dir"));
    //        System.setProperty("logs.dir", System.getProperty("user.dir"));
    //    PropertyConfigurator.configure("log4j.properties"); // Note that this could lead to
    // log4j.properties in jar dependencies
    //    // use basic configuration when packaging
    BasicConfigurator.configure();
    //    org.apache.log4j.Logger.getRootLogger().setLevel(Level.ERROR);
    try {
      //      testDiff();
      testFiles();
    } catch (NonexistPathException e) {
      e.printStackTrace();
    }
  }

  private static void testDiff() throws NonexistPathException {
    Code2Graph client =
        new Code2Graph("cxf", System.getProperty("user.home") + "/coding/data/repos/cxf");

    // TODO: create a root project node if necessary
    client.compareGraphs(tempDir, "ed4faad");
  }

  public static final String WORK_PATH = System.getProperty("user.home")
          + File.separator
          + "Desktop"
          + File.separator
          + "fdse"
          + File.separator
          + "atomic-code-change";
  public static final String REPO_BASE_PATH = System.getProperty("user.home")
          + File.separator
          + "Desktop"
          + File.separator
          + "fdse"
          + File.separator
          + "repository";
  public static final String C2G_TEMP_BASE_DIR = WORK_PATH
          + File.separator
          + "c2g"
          + File.separator
          + "temp";
  public static final String PDG_BASE_DIR = WORK_PATH
          + File.separator
          + "pdg-temp";

  private static void testFiles() throws NonexistPathException {
//    Code2Graph client = new Code2Graph("Code2Graph", System.getProperty("user.dir"));
    String REPO_NAME = "fastjson";
    String REPO_PATH = REPO_BASE_PATH
            + File.separator + REPO_NAME;
    Code2Graph client = new Code2Graph(REPO_NAME, REPO_PATH);

    client.addSupportedLanguage(Language.JAVA);

    // specify
    //    Generator generator = new JdtGenerator();

    // from files
//    List<String> filePaths = new ArrayList<>();
//    filePaths.add(
//        client.getRepoPath()
//            + File.separator
//            + "src/main/java/com/alibaba/fastjson/serializer/FieldSerializer.java");
//    Graph<Node, Edge> graph = client.generateGraph(filePaths);
    Graph<Node, Edge> graph = client.generateGraphWithExclude("src/test");

//    for (Node node : graph.vertexSet()) {
//      System.out.println(node.getAttribute("filePath"));
//    }

//    GraphVizExporter.printAsDot(graph);
//    GraphVizExporter.exportAsDot(graph);
    GraphVizExporter.saveAsDot(graph, C2G_TEMP_BASE_DIR + File.separator + "fastjson.dt");
  }
}
