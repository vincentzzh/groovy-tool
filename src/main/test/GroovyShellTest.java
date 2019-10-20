import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class GroovyShellTest {

    @Test
    public void test1() throws IOException {
        GroovyShell groovyShell = new GroovyShell();
        Object result = groovyShell.evaluate(new File("src/main/java/test_groo.groovy"));

        System.out.println(result);
    }

    @Test
    public void test2(){
        GroovyShell groovyShell = new GroovyShell();
        System.out.println(groovyShell.evaluate("2 + 3"));
    }

    @Test
    public void test3() throws IOException {
        Binding binding = new Binding();
        binding.setProperty("name", "vincent");

        GroovyShell groovyShell = new GroovyShell(binding);
        Object result = groovyShell.evaluate(new File("src/main/java/test_groo2.groovy"));
        System.out.println(result);
    }

    @Test
    public void test4(){
        GroovyShell groovyShell = new GroovyShell();
        Script script = groovyShell.parse("println 2 + 3");
        script.run();
    }

    @Test
    public void test5() throws IOException {
        GroovyShell groovyShell = new GroovyShell();
        Script script1 = groovyShell.parse("2 + 3");
        Script script2 = groovyShell.parse("2 + 3");
        assert script1 != script2;

        File file = new File("src/main/java/test_groo.groovy");
        Script script3 = groovyShell.parse(file);
        Script script4 = groovyShell.parse(file);
        assert script3 != script4;
    }
}
