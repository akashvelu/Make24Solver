import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        Operator[] operators = {new Add(), new Subtract(), new Multiply(), new Divide(), new Exponent()};
        ArrayList<Double> nums = new ArrayList<>();
        nums.add(2.0);
        nums.add(3.0);
        nums.add(5.0);
        nums.add(7.0);
        nums.add(11.0);

        ArrayList<String> result = Solver.solve(nums, 36
                , operators, new ArrayList<String>());
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println("done");



    }
}
