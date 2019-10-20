import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.junit.Test;

import java.io.IOException;

public class GroovyScriptEngineTest {

    @Test
    public void test1() throws IOException, ResourceException, ScriptException {
        // GroovyScriptEngine的根路径，可以传字符串数组，说明有多个根路径
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


        Object result1 = engine.run("compute.groovy", binding);

        long middle = System.currentTimeMillis();
        System.out.println("mid : " + (middle - start));

        for(int i = 0; i < 100;i++)
            result1 = engine.run("compute.groovy", binding);

        long end = System.currentTimeMillis();
        System.out.println("cost : " + ((end - middle)));

    }
}
