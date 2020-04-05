package HR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Dialog extends JDialog {
    private JPanel contentPane;
    public  JLabel label;
    private JButton buttonOK;
    private JButton print;
    private JButton buttonCancel;

    public Dialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        Toolkit toolkit =  Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width/2 - 250, dimension.height/2 - 150, 500, 300);
        setTitle("Answer");
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPrint();
            }
        });
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onPrint() {
        FileWriter writer = null; try {
            writer = new FileWriter("printFile.pdf", false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            Form2 form2 = new Form2();
            writer.write(form2.answer1);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        File file = new File("printFile.pdf");
        try {
            Desktop.getDesktop().print(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void setLabel(String s) {
        label.setText(s);
    }

    public static void main(String[] args) {
        Dialog dialog = new Dialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
