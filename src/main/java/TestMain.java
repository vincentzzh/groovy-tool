import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.codehaus.groovy.control.CompilationFailedException;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @since 1.6+
 */
public class TestMain {

    public static void main(String[] args) throws CompilationFailedException, IOException, ResourceException, ScriptException {
        // TODO Auto-generated method stub
        
        //11111
        
//        GroovyShell groovyShell = new GroovyShell();  
//        Object result = groovyShell.evaluate(new File("src/main/java/test_groo.groovy"));  
//        System.out.println(result.toString());   

        //2222222
//        GroovyShell groovyShell = new GroovyShell();  
//        groovyShell.evaluate("println 'My First Groovy shell.'"); 
        
        
        //33333
//        Binding binding = new Binding();
//        binding.setProperty("name", "Juxinli");
//
//        GroovyShell groovyShell = new GroovyShell(binding);
//        Object result = groovyShell.evaluate(new File("src/main/java/test_groo2.groovy"));
//        System.out.println(result.toString());


        // GroovyScriptEngine的根路径，如果参数是字符串数组，说明有多个根路径
        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/java");

        Binding binding = new Binding();
        binding.setVariable("name", "juxinli");

        Object result1 = engine.run("test_groo2.groovy", binding);
        System.out.println(result1);

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println(s);

        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());


    }

}
