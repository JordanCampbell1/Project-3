import javax.swing.JFrame;


public class Project3 extends JFrame
{
    public static void main(String [] args)
    {
        JFrame mainFrame = new JFrame("Welcome to the Task Management System");

        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        PanelListItems mainPanel = new PanelListItems();
        
        mainFrame.setSize(650,700);

        mainFrame.getContentPane().add(mainPanel); //can be substitued with add(mainPanel);

        mainFrame.setResizable(false);

        mainFrame.setVisible(true);

    }
}