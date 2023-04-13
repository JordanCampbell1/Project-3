import java.awt.GridLayout;
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
        p.setSize(300,300);
        p.setLayout(new GridLayout(9,1)); 
        setMinimumSize(p.getSize());
        setResizable(false);

        //p.setLayout(null); //to accurately set the componenets on the panel


        namelLabel = new JLabel("Full Name");
        namelLabel.setBounds(0,0,75,75);
        taskOutlinelLabel= new JLabel("Task Outline");
        taskOutlinelLabel.setBounds(0,150,75,75);
        startDateLabel = new JLabel("Start Date");
        ETFJLabel = new JLabel("Estimated Time to Finish (in Days)");

        nameTextField = new JTextField(30);
        nameTextField.setBounds(0,75,75,75);
        taskOutlineTextField = new JTextField(50);
        taskOutlineTextField.setBounds(0,225,75,75);
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
            Boolean same = false;
            try
            {
                String name = "";

                if(!(nameTextField.getText().equals("")))
                {
                    String [] arrofname = nameTextField.getText().split(" ");

                    name = arrofname[0] + "," + arrofname[1]; //allows save task method to work properly since the format for name is "FIRSTNAME,LASTNAME"
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
    
                if(Integer.parseInt(ETFTextField.getText()) > 0)
                {
                    ETF = Integer.parseInt(ETFTextField.getText());
                }


                //if data is entered then it will be added to the arraylist
                //maybe a more efficient of way of doing this
                if((!(nameTextField.getText().equals("")))&&
                (!(taskOutlineTextField.getText().equals("")))&&
                (!(startDateTextField.getText().equals("")))&&
                (Integer.parseInt(ETFTextField.getText()) > 0))
                {
                    Tasks P1 = new Tasks(name, taskOutline, startDate, ETF);
                    Tasks.ArrofTasks.add(P1);
                    for (String t: Tasks.ArrofNames){
                        if (t.equals(name))
                            same = true;
                    }
                    if (!same)
                        Tasks.ArrofNames.add(name);
                    same = false;
                    PanelListItems.saveNames("names.txt");
                    PanelListItems.saveTasks("tasks.txt");
                    for (int i=PanelListItems.table.getRowCount()-1;i>=0;i--)
                        PanelListItems.model.removeRow(i);
                    PanelListItems.showTable();
                    dispose();
                }
                else{//otherwise the pop up panel is shown
                    PopUpPanel t = new PopUpPanel();
                }


                
            }
            catch(ArrayIndexOutOfBoundsException el){}
            catch(NumberFormatException error){}

        }
    }

}
