import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sin;

public class Form1 extends JFrame {
    private static String answer;
    private JButton buttonOK;
    private JPanel contentPanel;
    private JList list1;
    private JTextField textFieldX;
    private JLabel labelx;
    private JLabel secondYlabel;
    private JLabel firstYlabel;

    public Form1() {
        setContentPane(contentPanel);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, 500, 300);
        setTitle("HR app");
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        textFieldX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MaskFormatter formatter = new MaskFormatter();
                formatter.setValidCharacters("-0123456789");
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list1.getSelectedIndex() == -1) {
                    buttonOK.setEnabled(false);

                } else {
                    buttonOK.setEnabled(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        Form1 form = new Form1();
        form.pack();
        form.setVisible(true);
    }

    private double getx() {
        Double text;
        try {
            text = Double.valueOf(textFieldX.getText());
            return text;
        } catch (NumberFormatException | NullPointerException e) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.pack();
            errorMessage.setVisible(true);
            textFieldX.setText("0");
            return 0;
        }
    }

    private void onOK() {
        double x;
        if (list1.getSelectedIndex() == 0) {
            x = getx();
            answer = String.valueOf(pow(pow(x, 2) - x - 1, pow(x, 2) - 1));
            firstYlabel.setText(answer);
        } else if (list1.getSelectedIndex() == 1) {
            x = getx();
            answer = String.valueOf(pow((cos(2 * x) + 5 * sin(x) + 3), 3));
            secondYlabel.setText(answer);
        }
        Dialog dialog = new Dialog();
        dialog.setLabel("Function: " + list1.getSelectedValue()+"; " + "X: " + textFieldX.getText() + "; " +"Answer: "+ answer +";");
        dialog.pack();
        dialog.setVisible(true);
        list1.getSelectionModel().removeSelectionInterval(1, 1);
        //textFieldX.setText("");
    }
}