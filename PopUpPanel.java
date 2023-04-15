import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * PopUpPanel when initialized creates a frame which includes
 * text informing the user of theri mistakes.
 * 
 * {@code PopUpPanel} extends {@code JFrame}.
 */
public class PopUpPanel extends JFrame{

    private JPanel p = new JPanel();

    private JLabel tLabel = new JLabel("You Did Not Enter All Of The Data for This Task"); //adjust size of font

    public PopUpPanel()
    {
        setTitle("Issue With New Task Entry");
        setResizable(false);
        setSize(300, 400);

        p.setBackground(Color.RED);

        p.add(tLabel);

        getContentPane().add(p);

        pack();

        setVisible(true);
    }
    
}