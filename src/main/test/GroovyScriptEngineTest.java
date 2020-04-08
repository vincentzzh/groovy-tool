import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.junit.Test;

import java.io.IOException;

public class GroovyScriptEngineTest {

    @Test
    public void test1() throws IOException, ResourceException, ScriptException {
        // GroovyScriptEngine的根路径，可以传字符串数组，说明支持多个根路径
        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/java");

        Binding binding = new Binding();
        binding.setVariable("x1", 2);
        binding.setVariable("x2", 3);

        Object result = engine.run("compute.groovy", binding);
        System.out.println(result);
    }

    @Test
    public void test2() throws IOException, ResourceException, ScriptException {

        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/java");

        Binding binding = new Binding();
        binding.setVariable("x1", 2);
        binding.setVariable("x2", 3);

        long start = System.currentTimeMillis();
        engine.run("compute.groovy", binding);

        long middle = System.currentTimeMillis();
        System.out.println("init cost : " + (middle - start));

        for(int i = 0; i < 100;i++)
            engine.run("compute.groovy", binding);

        long end = System.currentTimeMillis();
        System.out.println("later cost : " + ((end - middle)));

    }
}
