import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;

public class TestGroovyShellEngine {

    public static void main(String[] args) throws IOException, ResourceException, ScriptException {

        System.out.println("start ---");
        long start = System.currentTimeMillis();

        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/java");

        Binding binding = new Binding();
        binding.setVariable("x1", 2);
        binding.setVariable("x2", 3);


        Object result1 = engine.run("compute.groovy", binding);

        long middle = System.currentTimeMillis();

        for(int i = 0; i < 20;i++)
            result1 = engine.run("compute.groovy", binding);

        long end = System.currentTimeMillis();
        long cost = end - start;

        System.out.println("end ---");

        System.out.println("mid : " + (middle - start));
        System.out.println("cost : " + ((end - middle)));

    }
}
