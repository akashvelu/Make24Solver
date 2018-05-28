public class Add implements Operator{
    @Override
    public double operate(double a, double b) {
        return a + b;
    }

    @Override
    public String stringRep() {
        return "+";
    }
}
