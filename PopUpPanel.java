import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopUpPanel extends JFrame{

    private JPanel p = new JPanel();

    private JLabel tLabel = new JLabel("You Have OverDue Tasks");

    public PopUpPanel()
    {
        setTitle("Issue With Tasks");

        p.setBackground(Color.RED);

        p.add(tLabel);

        getContentPane().add(p);

        pack();

        setVisible(true);
    }
    
}
