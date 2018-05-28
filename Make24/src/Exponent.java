public class Exponent implements Operator {
    @Override
    public double operate(double a, double b) {
        return Math.pow(a, b);
    }

    @Override
    public String stringRep() {
        return "^";
    }
}
