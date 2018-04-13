/**
 * Created by Carlos on 14/04/2018.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Calc {

    JFrame frame;
    private JTextField tfDisplay;
    private double result = 0;
    private boolean calculating = true;
    private String operator = "=";

    // Constructor to setup the UI components and event handlers
    public Calc() {

        frame = new JFrame("Calculadora");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(300, 200);
        tfDisplay = new JTextField("0");
        tfDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(tfDisplay, BorderLayout.NORTH);

        JPanel botones = new JPanel();

        GridLayout layout = new GridLayout(4, 4);
        layout.setHgap(10);
        layout.setVgap(10);

        botones.setLayout(layout);

        String buttonLabels = "789+456-123*c0=/";
        for (int i = 0; i < buttonLabels.length(); i++) {
            JButton b = new JButton(buttonLabels.substring(i, i + 1));
            botones.add(b);
            b.addActionListener(evt -> {
                teclaPulsada(evt);
            });

        }

        frame.add(botones, BorderLayout.CENTER);
        // Muestra la ventana
        frame.setVisible(true);
    }



    public void teclaPulsada(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9' || cmd.equals(".")) {
            if (calculating)
                tfDisplay.setText(cmd);
            else
                tfDisplay.setText(tfDisplay.getText() + cmd);
            calculating = false;
        } else {
            if (calculating) {
                if (cmd.equals("-")) {
                    tfDisplay.setText(cmd);
                    calculating = false;
                } else
                    operator = cmd;
            } else {
                double x = Double.parseDouble(tfDisplay.getText());
                calculate(x);
                operator = cmd;
                calculating = true;
            }
        }


    }

    private void calculate(double n) {
        if (operator.equals("+"))
            result += n;
        else if (operator.equals("-"))
            result -= n;
        else if (operator.equals("*"))
            result *= n;
        else if (operator.equals("/"))
            result /= n;
        else if (operator.equals("="))
            result = n;
        tfDisplay.setText("" + result);
    }

    public static void main(String[] args) {
        Calc cal = new Calc();
    }
}
