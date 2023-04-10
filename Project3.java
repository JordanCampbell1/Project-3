import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project3 extends JFrame
{
    public static void main(String [] args)
    {
        JFrame mainFrame = new JFrame("MainPanel");

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelListItems mainPanel = new PanelListItems();

        mainFrame.getContentPane().add(mainPanel);

        mainFrame.pack();

        mainFrame.setVisible(true);

    }
}