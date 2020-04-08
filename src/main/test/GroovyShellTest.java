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
        Object result = groovyShell.evaluate(new File("src/main/java/SayHelloScript.groovy"));

        System.out.println(result);
    }

    @Test
    public void test2(){
        GroovyShell groovyShell = new GroovyShell();
        System.out.println(groovyShell.evaluate("2+3"));
        groovyShell.evaluate("println 'Directly evaluate Groovy shell.'");
    }

    @Test
    public void test3() throws IOException {
        Binding binding = new Binding();
        binding.setProperty("x1", 1);
        binding.setProperty("x2", 2);

        GroovyShell groovyShell = new GroovyShell(binding);
        Object result = groovyShell.evaluate("x1 + x2");
        System.out.println(result.toString());
    }

    @Test
    public void test4(){
        GroovyShell groovyShell = new GroovyShell();
        Script script = groovyShell.parse("2 + 3");
        System.out.println(script.run());
    }

    @Test
    public void test5() throws IOException {
        GroovyShell groovyShell = new GroovyShell();
        Script script1 = groovyShell.parse("2 + 3");
        Script script2 = groovyShell.parse("2 + 3");
        assert script1 != script2; //true

        File file = new File("src/main/java/SayHelloScript.groovy");
        Script script3 = groovyShell.parse(file);
        Script script4 = groovyShell.parse(file);
        assert script3 != script4; //true
    }
}
