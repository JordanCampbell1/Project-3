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


    public AddButtonPanel()
    {
        setTitle("Adding a Task"); 
        setBounds(300, 90, 800, 400);
        setResizable(false);

        //place the textfields and the labels here

        namelLabel = new JLabel("Full Name");
        taskOutlinelLabel= new JLabel("Task Outline");
        startDateLabel = new JLabel("Start Date");
        ETFJLabel = new JLabel("Estimated Time to Finish");

        nameTextField = new JTextField(30);
        taskOutlineTextField = new JTextField(50);
        startDateTextField = new JTextField(15);
        ETFTextField = new JTextField(5);


        //may need to adjust the size and locations of these components
        add(namelLabel);
        add(nameTextField);
        add(taskOutlinelLabel);
        add(taskOutlineTextField);
        add(startDateLabel);
        add(startDateTextField);
        add(ETFJLabel);
        add(ETFTextField);


        saveButton.addActionListener(new SaveButtonListener());
        //closeButton.addActionListener(new CloseButtonListener()); // i dont think i need a close button since it is default

        add(saveButton);
        //p.add(closeButton);

        pack();

        setVisible(true);
    }

    
    private class SaveButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) //while loop to check half-filled tasks as to prevent half-filled info to go into the database
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


                Tasks P1 = new Tasks(name, taskOutline, startDate, ETF,false);

                Tasks.ArrofTasks.add(P1);
            }
            catch(NumberFormatException error){}

        }
    }

}
