import javax.swing.JFrame;

/**
 * Project3 when initialized creates a frame which includes
 * the PanelListItems panel.
 * 
 * {@code Project3} extends {@code JFrame}.
 */
public class Project3 extends JFrame
{
    public static void main(String [] args)
    {
        JFrame mainFrame = new JFrame("Welcome to the Task Management System");

        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        PanelListItems mainPanel = new PanelListItems();
        
        mainFrame.setSize(650,750);

        mainFrame.getContentPane().add(mainPanel); 
        
        mainFrame.setResizable(false);

        mainFrame.setVisible(true);

    }
}