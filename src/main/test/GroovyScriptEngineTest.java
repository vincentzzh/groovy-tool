import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.junit.Test;

import java.io.IOException;

public class GroovyScriptEngineTest {

    @Test
    public void test1() throws IOException, ResourceException, ScriptException {
        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/java");

        Binding binding = new Binding();
        binding.setVariable("name", "vincent");

        Object result1 = engine.run("test_groo2.groovy", binding);
        System.out.println(result1);

    }

    @Test
    public void test2() throws IOException, ResourceException, ScriptException {

        long start = System.currentTimeMillis();

        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/java");

        Binding binding = new Binding();
        binding.setVariable("x1", 2);
        binding.setVariable("x2", 3);


        engine.run("compute.groovy", binding);

        long middle = System.currentTimeMillis();
        System.out.println("first run cost : " + (middle - start));

        for(int i = 1; i <= 10000;i++) {
            binding.setVariable("x1", i);
            engine.run("compute.groovy", binding);
        }

        long end = System.currentTimeMillis();
        System.out.println("later cost : " + ((end - middle)));

    }

    @Test
    public void testFileChange() throws IOException, ResourceException, ScriptException, InterruptedException {
        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/java");

        Binding binding = new Binding();
        binding.setVariable("name", "vincent");

        while (true) {
            Object result1 = engine.run("test_groo2.groovy", binding);
            System.out.println(result1);
            Thread.sleep(1000);
            //now change the test_groo2.groovy file content without interrupt
        }
    }
}
