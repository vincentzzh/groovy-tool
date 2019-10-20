import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;

import java.io.File;
import java.util.ArrayList;

public class TestGroovyClassLoader {


    private static GroovyClassLoader groovyClassLoader = null;

    public static void initGroovyClassLoader() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setSourceEncoding("UTF-8");

        SecureASTCustomizer customizer = new SecureASTCustomizer();
        customizer.setImportsWhitelist(new ArrayList<String>());
        customizer.setStarImportsWhitelist(new ArrayList<String>());
        customizer.setStaticImportsWhitelist(new ArrayList<String>());

        config.addCompilationCustomizers(customizer);

        // 设置该GroovyClassLoader的父ClassLoader为当前线程的加载器(默认)
        groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), config);
    }

    /**
     * 通过GroovyClassLoader加载test_groo3.groovy，并反射调用其sayHello方法
     */
    public static String invokeSayHello(String name, String sex, int age) {
        String result = "";

        File groovyFile = new File("src/main/java/test_groo3.groovy");
        if (!groovyFile.exists()) {
            return result;
        }

        try {
            // 获得加载后的class
            Class<?> groovyClass = groovyClassLoader.parseClass(groovyFile);
            // 获得实例
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            // 反射调用sayHello方法得到返回值
            Object methodResult = groovyObject.invokeMethod("sayHello", new Object[] {name, sex, age});
            if (methodResult != null) {
                result = methodResult.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) throws Exception {

        initGroovyClassLoader();
        System.out.println(invokeSayHello("张三", "男", 25));
    }

}