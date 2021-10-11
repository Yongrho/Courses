public class Calculator {
    // Create a Calculator class. Within your class should be the following:
    // An overloaded add method which is capable of accepting the following arguments:

    // Two int primitives
    public int add(int a, int b) {
        return a + b;
    }

    // Three int primitives
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // A String representing a number and an int primitive
    public int add(String a, int b) {
        return Integer.parseInt(a) + b;
    }

    // An overloaded subtract method which is capable of accepting the following arguments:

    // Two int primitives
    public int subtract(int a, int b) {
        return a - b;
    }

    // A String representing a number and a float primitive
    public float subtract(String a, float b) {
        return Float.parseFloat(a) - b;
    }

    // An overloaded multiply method which is capable of accepting the following arguments:

    // Two int primitives
    public int multiply(int a, int b) {
        return a * b;
    }

    // An int primitive, a String representing a number, and a float primitive
    public float multiply(int a, String b, float c) {
        return a * Float.parseFloat(b) * c;
    }
}
