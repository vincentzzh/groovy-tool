import static Staticconfig.TEST_STATIC_INT

class GroovyShell_2 {

    public String sayHello(String name, String sex, int age) {
        println 'GroovyShell_2 的sayHello(String name, String sex, int age)方法';
        def a = Math.exp(age)

        if(a instanceof Double)
            println("Double :" + a)

        def b = String.format("#.2f", a)
        println(b)

        a=2/3

        //进行小数的取三位小数运算.
        if(a instanceof BigDecimal)
            println("BigDecimal")
        b = a.setScale(3,BigDecimal.ROUND_HALF_UP)
        println(Math.exp(age))
        println(b)

        return "name: " + name + ", sex: " + sex + ", age: " + age;
    }
}
