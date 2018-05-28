import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton solveButton;

    public GUI() {
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Operator[] operators = {new Add(), new Subtract(), new Multiply(), new Divide()};
                try {
                    ArrayList<String> result = Solver.solve(getNumbers(textField1.getText()), Double.valueOf(textField2.getText()), operators, new ArrayList<String>());
                    JTextArea textArea = new JTextArea(10, 25);
                    textArea.setText(convertAL(result));
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JOptionPane.showMessageDialog(null, scrollPane);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "invalid input");
                }
            }
        });
    }

    public static ArrayList<Double> getNumbers (String vals) {
        int index = 0;
        ArrayList<Double> result = new ArrayList<>();
        while (index < vals.length()) {
            StringBuilder sb = new StringBuilder();
            while (index < vals.length() && vals.charAt(index) != ',') {
                sb.append(vals.charAt(index));
                index += 1;
            }
            index += 1;
            try {
                result.add(Double.valueOf(sb.toString()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "invalid input");
            }
        }
        return result;
    }

    public static String convertAL (ArrayList<String> a) {
        StringBuilder sb = new StringBuilder();
        for (String s : a) {
            sb.append(s);
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main (String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }



}

