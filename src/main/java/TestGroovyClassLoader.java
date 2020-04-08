import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;

import java.io.File;
import java.util.ArrayList;

public class TestGroovyClassLoader {


    private static GroovyClassLoader groovyClassLoader = null;

    public static void initGroovyClassLoader() {
        SecureASTCustomizer customizer = new SecureASTCustomizer();
        //允许import的白名单列表，如果为空则不允许import
        customizer.setImportsWhitelist(new ArrayList<String>());
        //是否允许定义方法
        customizer.setMethodDefinitionAllowed(true);

        CompilerConfiguration config = new CompilerConfiguration();
        config.setSourceEncoding("UTF-8");
        config.addCompilationCustomizers(customizer);

        groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), config);
    }


    public static String invoke(String name, String sex, int age) {
        String result = "";
        File groovyFile = new File("src/main/java/MessCompute.groovy");
        if (!groovyFile.exists()) {
            return result;
        }

        try {
            // 获得加载后的class
            Class<?> groovyClass = groovyClassLoader.parseClass(groovyFile);
            // 获得实例
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            // 反射调用sayHello方法得到返回值
            Object methodResult = groovyObject.invokeMethod("compute", new Object[] {name, sex, age});
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
        System.out.println(invoke("张三", "男", 25));
    }

}