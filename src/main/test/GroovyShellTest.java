import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class GroovyShellTest {

    @Test
    public void test1() throws IOException {
        GroovyShell groovyShell = new GroovyShell();
        Object result = groovyShell.evaluate(new File("src/main/java/test_groo.groovy"));

        System.out.println(result.toString());
    }

    @Test
    public void test2(){
        GroovyShell groovyShell = new GroovyShell();
        groovyShell.evaluate("println 'Directly evaluate Groovy shell.'");
    }

    @Test
    public void test3() throws IOException {
        Binding binding = new Binding();
        binding.setProperty("name", "vincent");

        GroovyShell groovyShell = new GroovyShell(binding);
        Object result = groovyShell.evaluate(new File("src/main/java/test_groo2.groovy"));
        System.out.println(result.toString());
    }
}
