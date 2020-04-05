package HR;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sin;

public class Form2 extends JFrame {
    private JButton buttonOK;
    private JPanel contentPanel;
    private JList list1;
    private JFormattedTextField textFieldX;
    private JLabel labelx;
    private JLabel secondYlabel;
    private JLabel firstYlabel;
    boolean bool = false;
    boolean flag = false;
    ArrayList<Double> answers = new ArrayList<Double>();
    ArrayList<Integer> xList = new ArrayList<Integer>();
    public Form2() {
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
        textFieldX.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                {
                    if (textFieldX.getText().length() < 3 || !textFieldX.getText().contains(",")) {
                        bool =false;

                    } else {
                        bool = true;
                    }
                    if(bool & flag) {
                        buttonOK.setEnabled(true);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                {
                    if (textFieldX.getText().length() < 3 || !textFieldX.getText().contains(",")) {
                        bool = false;

                    } else {
                        bool = true;
                    }
                    if(bool & flag) {
                        buttonOK.setEnabled(true);
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                {
                    if (textFieldX.getText().length() < 3 || !textFieldX.getText().contains(",")) {
                        bool = false;

                    } else {
                        bool = true;
                    }
                    if(bool & flag) {
                        buttonOK.setEnabled(true);
                    }
                }
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list1.getSelectedIndex() == -1) {
                    flag = false;

                } else {
                    flag = true;
                }
                if(bool & flag) {
                    buttonOK.setEnabled(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        Form2 form = new Form2();
        form.pack();
        form.setVisible(true);
    }

    private Double calcFunction(int x){
        Double answer = null;
        if (list1.getSelectedIndex() == 0) {
            answer = pow(pow(x, 2) - x - 1, pow(x, 2) - 1);
            return answer;
        } else if (list1.getSelectedIndex() == 1) {
            answer = pow((cos(2 * x) + 5 * sin(x) + 3), 3);
            return answer;
        }
        return answer;
    }

    private ArrayList<Double> getAnswer(){
        double Xo, Xn;
        ArrayList<Double> answer = new ArrayList<>();
        try {
            try {
                Xo = Integer.parseInt(textFieldX.getText().split(",")[0]);
                Xn = Integer.parseInt(textFieldX.getText().split(",")[1]);
            }catch(ArrayIndexOutOfBoundsException e){
                Xo = Xn = Integer.parseInt(textFieldX.getText());
            }
            if (Xn-Xo >= 2) {
                for (int i = (int) Xo; i <= Xn; i++) {
                    answer.add(calcFunction(i));
                    xList.add(i);
                }
            }else{
                ErrorInterval errorInterval = new ErrorInterval();
                errorInterval.pack();
                errorInterval.setVisible(true);
            }
        } catch (NumberFormatException | NullPointerException e) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.pack();
            errorMessage.setVisible(true);
            answer.add(null);
        }
        return answer;
    }

    private void onOK() {
        if(bool & flag) {
            answers = getAnswer();
            String answer = "";
            HR.Dialog dialog = new Dialog();
            answer += "<html><head><style>\n" +
                    "table, th, td {\n" +
                    "  border: 1px solid black;\n" +
                    "  border-collapse: collapse;\n" +
                    "}\n" +
                    "</style></head><body>" + ("Function: " + list1.getSelectedValue() + "; " + "Xo,Xn: " + textFieldX.getText() + ";<br/>");
            answer += "<table><tr><th>X</th><th>Y</th></tr>";
            StringBuilder answerBuilder = new StringBuilder(answer);
            for (int i = 0; i <= answers.size() - 1; i++) {
                answerBuilder.append("<tr>" + "<td>").append(xList.get(i)).append("</td>").append("<td>").append(answers.get(i)).append("</td>").append("</tr>");
            }
            answer = answerBuilder.toString();
            answer += "<br/></table></body>";
            dialog.setLabel(answer);
            dialog.pack();
            dialog.setVisible(true);
            list1.getSelectionModel().removeSelectionInterval(1, 1);
            //textFieldX.setText("");
            Chart chart = new Chart(xList,answers);
            chart.pack();
            chart.setVisible(true);
            answers.clear();
            xList.clear();
        }else{
            ErrorInterval errorInterval = new ErrorInterval();
            errorInterval.pack();
            errorInterval.setVisible(true);
        }
    }
}