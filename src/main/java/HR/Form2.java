package HR;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sin;

public class Form2 extends JFrame {
    boolean bool = false;
    boolean flag = false;
    String answer1 = "";
    ArrayList<Double> answers = new ArrayList<Double>();
    ArrayList<Integer> xList = new ArrayList<Integer>();
    private JButton buttonOK;
    private JPanel contentPanel;
    private JList list1;
    private JFormattedTextField textFieldX;
    private JLabel labelx;
    private JLabel secondYlabel;
    private JLabel firstYlabel;

    public Form2() {
        setContentPane(contentPanel);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, 500, 300);
        setTitle("HR app");
        JMenuBar menuBar = new JMenuBar();
        // Добавление в главное меню выпадающих пунктов меню
        menuBar.add(createFileMenu());
        // Подключаем меню к интерфейсу приложения
        setJMenuBar(menuBar);
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
                        bool = false;

                    } else {
                        bool = true;
                    }
                    if (bool & flag) {
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
                    if (bool & flag) {
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
                    if (bool & flag) {
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
                if (bool & flag) {
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

    private JMenu createFileMenu() {
        // Создание выпадающего меню
        JMenu file = new JMenu("File");
        // Пункт меню "Открыть" с изображением
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save as");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem newStandart = new JMenuItem("New recently saved");

        file.add(open);
        file.addSeparator();
        file.add(save);
        file.addSeparator();
        file.add(saveAs);
        file.addSeparator();
        file.add(newStandart);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "";
                str += textFieldX.getText() + "/";
                str += list1.getSelectedValue();
                System.out.println(str);
                FileWriter writer = null; try {
                    writer = new FileWriter("file.txt", false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    writer.write(str);
                    writer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        newStandart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(FileReader reader = new FileReader("file.txt"))
                {
                    char [] s = new char[200];
                   reader.read(s);

                        textFieldX.setValue(String.valueOf((s)).split("/")[0]);
                        System.out.println(s);
                        System.out.println((String.valueOf((s)).split("/")[0]));
                        System.out.println((String.valueOf(s).split("/")[1]));
                        if(String.valueOf((s)).split("/")[1].equals("y=(cos2x+5sinx+3)^3")){
                            list1.setSelectedIndex(1);
                        }else{
                            list1.setSelectedIndex(0);
                        }
                        
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
            }
        });

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char [] str = new char[200];
                File file;
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
                jFileChooser.setFileFilter(filter);
                jFileChooser.showOpenDialog(Form2.this);
                file = jFileChooser.getSelectedFile();
                try {
                    FileReader reader = new FileReader(file);
                    reader.read(str);
                    textFieldX.setValue(String.valueOf((str)).split("/")[0]);
                    System.out.println(str);
                    System.out.println((String.valueOf((str)).split("/")[0]));
                    System.out.println((String.valueOf(str).split("/")[1]));
                    if(String.valueOf((str)).split("/")[1].equals("y=(cos2x+5sinx+3)^3")){
                        list1.setSelectedIndex(1);
                    }else{
                        list1.setSelectedIndex(0);
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "";
                str += textFieldX.getText() + "/";
                str += list1.getSelectedValue();
                System.out.println(str);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showSaveDialog(Form2.this);
                if (result == JFileChooser.APPROVE_OPTION )
                    JOptionPane.showMessageDialog(Form2.this,
                            "File '" + fileChooser.getSelectedFile() +
                                    " ) saved");
                try {
                    FileWriter writer = new FileWriter(fileChooser.getSelectedFile(), false);
                    writer.write(str);
                    writer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        return file;
    }

    private Double calcFunction(int x) {
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

    private ArrayList<Double> getAnswer() {
        double Xo, Xn;
        ArrayList<Double> answer = new ArrayList<>();
        try {
            try {
                Xo = Integer.parseInt(textFieldX.getText().split(",")[0]);
                Xn = Integer.parseInt(textFieldX.getText().split(",")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Xo = Xn = Integer.parseInt(textFieldX.getText());
            }
            if (Xn - Xo >= 2) {
                for (int i = (int) Xo; i <= Xn; i++) {
                    answer.add(calcFunction(i));
                    xList.add(i);
                }
            } else {
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
        if (bool & flag) {
            answers = getAnswer();
            HR.Dialog dialog = new Dialog();
            answer1 += "<html><head><style>\n" +
                    "table, th, td {\n" +
                    "  border: 1px solid black;\n" +
                    "  border-collapse: collapse;\n" +
                    "}\n" +
                    "</style></head><body>" + ("Function: " + list1.getSelectedValue() + "; " + "Xo,Xn: " + textFieldX.getText() + ";<br/>");
            answer1 += "<table><tr><th>X</th><th>Y</th></tr>";
            StringBuilder answerBuilder = new StringBuilder(answer1);
            for (int i = 0; i <= answers.size() - 1; i++) {
                answerBuilder.append("<tr>" + "<td>").append(xList.get(i)).append("</td>").append("<td>").append(answers.get(i)).append("</td>").append("</tr>");
            }
            answer1 = answerBuilder.toString();
            answer1 += "<br/></table></body>";
            dialog.setLabel(answer1);
            dialog.pack();
            dialog.setVisible(true);
            list1.getSelectionModel().removeSelectionInterval(1, 1);
            //textFieldX.setText("");
            Chart chart = new Chart(xList, answers);
            chart.pack();
            chart.setVisible(true);
            answers.clear();
            xList.clear();
        } else {
            ErrorInterval errorInterval = new ErrorInterval();
            errorInterval.pack();
            errorInterval.setVisible(true);
        }
    }

    class ExitAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        ExitAction() {
            dispose();
        }

        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}