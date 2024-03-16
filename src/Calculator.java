import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Calculator implements ActionListener {
    boolean isOperatorClicked = false;
    String operator = "";
    String oldValue;
    JLabel displayLabel;

    public Calculator() {
        JFrame jf = createFrame();
        jf.add(createLabel());
        // First row of buttons 7 8 9
        jf.add(createButton( "7", 30, 130, 40));
        jf.add(createButton( "8", 130, 130, 40));
        jf.add(createButton("9", 230, 130, 40));
        jf.add(createButton( "Clear", 430, 130, 10));
        // Second row of buttons 4 5 6
        jf.add(createButton( "4", 30, 230, 40));
        jf.add(createButton( "5", 130, 230, 40));
        jf.add(createButton("6", 230, 230, 40));
        // Third row of buttons 1 2 3
        jf.add(createButton("1", 30, 330, 40));
        jf.add(createButton("2", 130, 330, 40));
        jf.add(createButton( "3", 230, 330, 40));
        // Fourth row of buttons 7 8 9
        jf.add(createButton(".", 30, 430, 40));
        jf.add(createButton( "0", 130, 430, 40));
        jf.add(createButton( "=", 230, 430, 40));
        //Operations Column (/ * - +)
        jf.add(createButton("/", 330, 130, 40));
        jf.add(createButton("*", 330, 230, 40));
        jf.add(createButton( "-", 330, 330, 40));
        jf.add(createButton( "+", 330, 430, 40));
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JFrame createFrame() {
        JFrame jf = new JFrame("Calculator");
        jf.setLayout(null);
        jf.setSize(600, 600);
        jf.setLocation(300, 150);
        return jf;
    }

    private JLabel createLabel() {
        JLabel displayLabel = new JLabel();
        displayLabel.setBounds(30, 50, 540, 70);
        displayLabel.setBackground(Color.gray);
        displayLabel.setOpaque(true);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setForeground(Color.white);
        return displayLabel;
    }

    private JButton createButton(String text, int x, int y, int size) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 80, 80);
        button.setFont(new Font("Arial", Font.PLAIN, size));
        button.addActionListener(this);
        return button;
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = ((JButton) e.getSource()).getText();
        if (Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".").contains(text)) {
            if (isOperatorClicked) {
                displayLabel.setText(text);
                isOperatorClicked = false;
            } else {
                displayLabel.setText(displayLabel.getText() + text);
            }
        } else if (Arrays.asList("+", "-", "*", "/").contains(text)) {
            isOperatorClicked = true;
            oldValue = displayLabel.getText();
            operator = text;
        } else if (text.equals("Clear")) {
            displayLabel.setText("");
        } else if (text.equals("=")) {
            Float oldValueF = Float.parseFloat(oldValue);
            Float newValueF = Float.parseFloat(displayLabel.getText());
            Float result = calculate(oldValueF ,newValueF, operator);
            displayLabel.setText(result + "");
        }
    }

    private Float calculate(Float oldValue, Float newValue, String operator) {
        return switch (operator) {
            case "+" -> oldValue + newValue;
            case "-" -> oldValue - newValue;
            case "/" -> oldValue / newValue;
            case "*" -> oldValue * newValue;
            default -> throw new RuntimeException("Invalid Operator");
        };
    }
}
