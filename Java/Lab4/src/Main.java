public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // add
        System.out.println("===== Test for add functions of Static Polymorphism =====");
        System.out.println("[Add]Two int primitives: 10 + 20 = " + calc.add(10, 20));
        System.out.println("[Add]Three int primitives: 10 + 20 + 30 = " + calc.add(10, 20, 30));
        System.out.println("[Add]A String representing a number and an int primitive: '50' + 60 = " + calc.add("50", 60));
        System.out.println();

        // subtract
        System.out.println("===== Test for subtract functions of Static Polymorphism =====");
        System.out.println("[Subtract]Two int primitives: 20 - 10 = " + calc.subtract(20, 10));
        System.out.println("[Subtract]A String representing a number and oa float primitive: '101.04', 60.53 = " + calc.subtract("101.04f", 60.53f));
        System.out.println();

        // multiply
        System.out.println("===== Test for multiply functions of Static Polymorphism =====");
        System.out.println("[Multiply]Two int primitives: 10 * 20 = " + calc.multiply(10, 20));
        System.out.println("[Multiply]An int primitive, A String representing a number and an int primitive: 10 * '50.43f' * 60.55f = " + calc.multiply(10, "50.43f", 60.55f));
    }
}
