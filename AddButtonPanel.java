import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddButtonPanel extends JFrame
{
    private JLabel namelLabel, taskOutlinelLabel, startDateLabel, ETFJLabel;

    private JTextField nameTextField, taskOutlineTextField, startDateTextField, ETFTextField;

    private JButton saveButton = new JButton("Save");

    private JPanel p = new JPanel();


    public AddButtonPanel()
    {
        setTitle("Adding a Task"); 
        setBounds(300, 90, 800, 400);
        setResizable(false);

        p.setLayout(null); //to accurately set the componenets on the panel


        namelLabel = new JLabel("Full Name");
        taskOutlinelLabel= new JLabel("Task Outline");
        startDateLabel = new JLabel("Start Date");
        ETFJLabel = new JLabel("Estimated Time to Finish (in Days)");

        nameTextField = new JTextField(30);
        taskOutlineTextField = new JTextField(50);
        startDateTextField = new JTextField(15);
        ETFTextField = new JTextField(5);


        //may need to adjust the size and locations of these components
        p.add(namelLabel);
        p.add(nameTextField);
        p.add(taskOutlinelLabel);
        p.add(taskOutlineTextField);
        p.add(startDateLabel);
        p.add(startDateTextField);
        p.add(ETFJLabel);
        p.add(ETFTextField);


        saveButton.addActionListener(new SaveButtonListener());
        //closeButton.addActionListener(new CloseButtonListener()); // i dont think i need a close button since it is default

        p.add(saveButton);
        //p.add(closeButton);

        getContentPane().add(p);

        pack();

        setVisible(true);
    }

    
    private class SaveButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) //may need while loop to check half-filled tasks as to prevent half-filled info to go into the database (new frame and panel)
        {
            try
            {
                String name = "";

                if(!(nameTextField.getText().equals("")))
                {
                    name = nameTextField.getText();
                }
    
                String taskOutline = "";
    
                if(!(taskOutlineTextField.getText().equals("")))
                {
                    taskOutline = taskOutlineTextField.getText();
                }
    
                String startDate = "";
    
                if(!(startDateTextField.getText().equals("")))
                {
                    startDate = startDateTextField.getText();
                }
    
                int ETF = 0;
    
                if(Integer.parseInt(startDateTextField.getText()) <= 0)
                {
                    ETF = Integer.parseInt(startDateTextField.getText());
                }


                Tasks P1 = new Tasks(name, taskOutline, startDate, ETF);

                Tasks.ArrofTasks.add(P1);
            }
            catch(NumberFormatException error){}

        }
    }

}
