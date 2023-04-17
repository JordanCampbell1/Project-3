import java.sql.Date;

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

        if(PanelListItems.boolNOTIFICATION == true) //only runs if the user enabled notifications
        {
            NotificationPanel p = new NotificationPanel();
        }
        
        mainFrame.setSize(650,650);

        mainFrame.getContentPane().add(mainPanel); 
        
        mainFrame.setResizable(false);

        mainFrame.setVisible(true);

    }
}