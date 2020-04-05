package HR;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

import static java.awt.Color.RED;

public class Form7 extends JFrame{
    private static final long serialVersionUID = 1L;
    private JPanel contentPanel;
    private JPanel jpanel;
    private JLabel label1;

    public Form7(){
       // JFrame frame = new JFrame("Lab7");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, 500, 500);
        setVisible(true);

        JPanel contentPane = new JPanel(){
            Graphics2D g2;
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g2=(Graphics2D)g;
                g2.setPaint(new Color(136, 0, 0));
                g2.fill (new Rectangle2D.Double(3, 3, 103, 103));
                g2.setPaint(new Color(255, 0, 0));
                g2.fill (new Rectangle2D.Double(303, 3, 103, 103));
                g2.setPaint(new Color(0, 255, 0));
                g2.fill (new Rectangle2D.Double(3, 303, 103, 103));
                g2.setPaint(new Color(0, 136, 0));
                g2.fill (new Rectangle2D.Double(303, 303, 103, 103));
                g2.setPaint(new Color(65, 105, 225));
                g2.rotate(Math.toRadians(-45));
                g2.fill (new RoundRectangle2D.Double(-303/4, 253, 103, 103, 25,25));
            }
        };
        add(contentPane);
        label1.setText("adasdas");
    }
    public static void main(String[] args) {
        Form7 form7 = new Form7();
        form7.pack();
        form7.setVisible(true);
    }
}
