import edu.pku.code2graph.xll.*;
import org.junit.jupiter.api.Test;
import edu.pku.code2graph.model.URI;

import java.util.*;

public class LinkerTest {
  private Config config;

  LinkerTest() {
    Optional<Config> configOptional = new ConfigLoader().load("src/main/resources/config.yml");
    config = configOptional.get();
  }

  private void matchTest(int ruleIndex, int patternIndex, String source) {
    URI uri = new URI(source);
    URIPattern pattern = config.getRules().get(ruleIndex).getPattern(patternIndex);
    System.out.println(pattern);
    System.out.println(uri);
    System.out.println(pattern.match(uri));
  }

  @Test
  public void identifierTest() {
    matchTest(1, 0,"use:///Code2Graph/client/build/resources/test/android/butterknife/main/java/com/example/demo/MainActivity.java//R.id.button");
  }

  @Test
  public void inlineTest1() {
    matchTest(1, 1,"def://E:/code/Code2Graph/client/build/resources/test/android/butterknife/main/res/layout/activity_main.xml//RelativeLayout/Button/android:id//@+id\\/button");
  }

  @Test
  public void inlineTest2() {
    matchTest(2, 1,"use://blog/new.html//html/body/form/data-th-object//${postForm}");
  }

  @Test
  public void inlineTest3() {
    matchTest(2, 0,"def://BlogAdminController.java//.addAttribute//postForm");
  }

  @Test
  public void generalTest() {
    Optional<Config> config = new ConfigLoader().load("src/main/resources/config.yml");
    config.ifPresent(value -> {
      Rule rule = value.getRules().get(0);
      URIPattern left = rule.getLeft();
      URI uri1 = new URI("def://foo/bar.java//getFooBar");
      System.out.println(left);
      System.out.println(uri1);
      System.out.println(left.match(uri1));
      System.out.println();

      URIPattern right = rule.getRight();
      URI uri2 = new URI("def://foo/baz.java//Select//#{FooBar}");
      System.out.println(right);
      System.out.println(uri2);
      System.out.println(right.match(uri2));
      System.out.println();

      List<Rule> subrules = rule.getSubRules();
      System.out.println(subrules);

//      List<URI> uris = new ArrayList<>();
//      uris.add(uri1);
//      uris.add(uri2);
//      Map<Language, List<URI>> uriMap = new HashMap<>();
//      uriMap.put(Language.JAVA, uris);
//      System.out.println("Total links: " + new Detector(uriMap).link(rule));
    });
  }
}
