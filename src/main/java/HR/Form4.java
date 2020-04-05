package HR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form4 extends JFrame{
    private JPanel contentPanel;
    private JPanel jpanel;
    private JLabel label;

    public Form4() {
        setContentPane(jpanel);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создание строки главного меню
        JMenuBar menuBar = new JMenuBar();
        // Добавление в главное меню выпадающих пунктов меню
        menuBar.add(createFileMenu());
        // Подключаем меню к интерфейсу приложения
        setJMenuBar(menuBar);
        // Открытие окна
        setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, 500, 300);
        setTitle("HR app");
    }
    private JMenu createFileMenu()
    {
        // Создание выпадающего меню
        JMenu file = new JMenu("File");
        // Пункт меню "Открыть" с изображением
        JMenuItem newMain = new JMenuItem("New main");
        JMenuItem lab7 = new JMenuItem("Lab7");
        JMenuItem exit = new JMenuItem(new Form4.ExitAction());
        exit.setIcon(new ImageIcon("images/exit.png"));
        file.add(newMain);
        file.addSeparator();
        file.add(lab7);
        file.addSeparator();
        file.add(exit);
        lab7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Form7 form7  = new Form7();
                form7.setVisible(true);
            }
        });
        label.setText("<html><font color=\"green\"><b>Ім'я: Жильцов Сергій Сергійович</b></font><br/>" +
                "<font color=\"purple\"><em>Номер групи: 6.1216-2</html></em></font>");
        newMain.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Form2 form2  = new Form2();
                form2.setVisible(true);
            }
        });
        return file;
    }

    static class ExitAction extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        ExitAction() {
            putValue(NAME, "Exit");
        }
        public void actionPerformed(ActionEvent e) {

        }
    }

    public static void main(String[] args) {
        Form4 form4 = new Form4();
        form4.pack();
        form4.setVisible(true);
    }
}
