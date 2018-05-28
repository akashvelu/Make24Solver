import java.util.ArrayList;

/**
 * Created by Akash Velu, 5/23/18
 * Class containing algorithms which find a way to mathematically combine a list of operations
 * using basic math operations (i.e. +,-,*,/) to reach a target number.
 * Created with purpose of being a solver for the game Make24, in which the goal is to form the
 * number 24 by combining 4 integers.
 */

public class Solver {


    //Solver method: nums contains given numbers to form the target integer;
    //Operations arg is array of math operations available (typically +,-,*,/)
    public static ArrayList<String> solve (ArrayList<Double> nums, double target, Operator[] operations, ArrayList<String> sequence) {
        //stores results from calling Helper which each helper
        if (nums.size() == 0) {
            if (nums.get(0) == target) {
                return sequence;
            } else {
                return null;
            }
        }
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for (Double i : nums) {
            ArrayList<Double> usableNums = new ArrayList<>(nums);
            usableNums.remove(i);
            ArrayList<String> start = new ArrayList<>(sequence);
            start.add("   ");
            start.add(String.valueOf(i));
            results.add(solveHelper(i, target, usableNums, operations, start));
        }
        //if all results are null, there is no solution
        ArrayList<String> result = new ArrayList<>();
        boolean returnNull = true;
        for (ArrayList<String> b : results) {
            if (b != null) {
                for (String s : b) {
                    result.add(s);
                    returnNull = false;
                }
            }
        }
        if (returnNull) {
            return null;
        } else {
            return result;
        }
    }

    //helper method: currNum is the current considered number, sequence is the combo of numbers
    //that has resulted in the currNum
    public static ArrayList<String> solveHelper (double currNum, double target, ArrayList<Double> nums, Operator[] operations, ArrayList<String> sequence) {
        //true base case
        if (currNum == target && nums.size() == 0) {
            return sequence;
        } else if (nums.size() == 0) {
            return null; //false base case
        } else {
            //stores results from each recursive result of iteration
            ArrayList<ArrayList<String>> results = new ArrayList<>();
            for (Operator o : operations) {
                for (Double i : nums) {
                    //applying present operation to combine currNum and i
                    double newNum = o.operate(currNum, i);
                    ArrayList<Double> newNums = new ArrayList<>(nums);
                    //removing used number
                    newNums.remove(i);
                    //adding new number
                    newNums.add(newNum);
                    ArrayList<String> newSeq = new ArrayList<>(sequence);
                    //updating sequence to have operation just used
                    newSeq.add(o.stringRep());
                    newSeq.add(String.valueOf(i));
                    newSeq.add("=");
                    newSeq.add(String.valueOf(newNum));
                    //recursive call back to solve function
                    results.add(solve(newNums, target, operations, newSeq));


                }
            }

            ArrayList<String> result = new ArrayList<>();
            Boolean returnNull = true;
            for (ArrayList<String> b : results) {
                if (b != null) {
                     returnNull = false;

                     //fixes formatting issue of output
                     if (b.contains("=")) {
                         result.add(stringify(b));
                     } else {
                         for (String s : b) {
                             result.add(s);
                         }
                     }

                }
            }
            if (!returnNull) {
                return result;
            } else {
                return null;
            }
        }
    }

    //concatenates an ArrayList of strings into one big string
    public static String stringify(ArrayList<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString();
    }

}
