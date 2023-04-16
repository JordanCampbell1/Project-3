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
    private JLabel namelLabel, taskOutlinelLabel, ETFJLabel, startDateLabel;

    private JTextField nameTextField, taskOutlineTextField, ETFTextField, startTimeField;

    private JButton saveButton = new JButton("Save");

    private JPanel p = new JPanel();


    public AddButtonPanel()
    {
        setTitle("Adding a Task");
        p.setSize(200,100);
        p.setLayout(new GridLayout(7,1)); 
        //setMinimumSize(p.getSize()); //dont think this is needed
        setResizable(false);

        //p.setLayout(null); //to accurately set the componenets on the panel


        namelLabel = new JLabel("Full Name");
        namelLabel.setBounds(0,0,75,75);
        taskOutlinelLabel= new JLabel("Task Outline");
        taskOutlinelLabel.setBounds(0,150,75,75);
        ETFJLabel = new JLabel("Estimated Duration (in mins)");

        startDateLabel = new JLabel("Start Time of Task (in HH:MM) (24-Hour Time)");

        nameTextField = new JTextField(30);
        nameTextField.setBounds(0,75,75,75);
        taskOutlineTextField = new JTextField(50);
        taskOutlineTextField.setBounds(0,225,75,75);
        ETFTextField = new JTextField(5);

        startTimeField = new JTextField(8);



        //may need to adjust the size and locations of these components
        p.add(namelLabel);
        p.add(nameTextField);
        p.add(taskOutlinelLabel);
        p.add(taskOutlineTextField);
        p.add(ETFJLabel);
        p.add(ETFTextField);
        p.add(startDateLabel);
        p.add(startTimeField);


        saveButton.addActionListener(new SaveButtonListener());
        //closeButton.addActionListener(new CloseButtonListener()); // i dont think i need a close button since it is default

        p.add(saveButton);
        //p.add(closeButton);

        getContentPane().add(p);

        pack();

        setVisible(true);
    }

    //test
    private class SaveButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) 
        {
            Boolean same = false;

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

                String startTime = "";
    
                if(!(startTimeField.getText().equals("")))
                {
                    startTime = startTimeField.getText();
                }
    
    
                int ETF = 0;
    
                if(Integer.parseInt(ETFTextField.getText()) > 0)
                {
                    ETF = Integer.parseInt(ETFTextField.getText());
                }


                //if data is entered then it will be added to the arraylist
                if((!(name.equals("")))&&
                (!(taskOutline.equals("")))&&
                (!(startTime.equals(""))) &&
                (ETF > 0))
                {
                    Tasks P1 = new Tasks(name, taskOutline, startTime, ETF);
                    Tasks.ArrofTasks.add(P1);

                    new PopUpPaneler(P1.getName(),P1.getTaskOutline(),P1.getEndTime());

                    for (Person t: Tasks.ArrofNames){
                        //checks if the name is already recorded
                        if (t.getName().equals(name))
                            same = true;
                    }

                    //creating a new person object with 0 task complete and the expected time of the task.
                    if (!same){
                        Person peeps = new Person(name,0,P1.getExpectedTime());
                        Tasks.ArrofNames.add(peeps);
                        PanelListItems.nameDropDownPub.addItem(name);
                        PanelListItems.fill();
                    }
                    //Since the person already exist just add the expected time to finish to the persons total expected time for tasks.
                    else{
                        for (Person pele : Tasks.ArrofNames){
                            if (pele.getName().equals(name)){
                                pele.setEstTaskTimeLeft(pele.getEstTaskTimeLeft()+P1.getExpectedTime());
                                PanelListItems.fill();
                                //PanelListItems.filler(name); //it would overwrite the progress of whatever is selected in the dropdown menu ie. gies wrong info 
                            }
                        }
                    }
                    same = false;
                    PanelListItems.saveNames("names.txt");
                    PanelListItems.saveTasks("tasks.txt");
                    //for (int i=PanelListItems.table.getRowCount()-1;i>=0;i--)//table.getRowCount(0) does the same thing
                        //PanelListItems.model.removeRow(i);
                    PanelListItems.model.setRowCount(0); //to empty the table of data
                    PanelListItems.showTable();
                    dispose();
                }
                else{
                    new PopUpPanel();
                }


                
            }
            catch(ArrayIndexOutOfBoundsException el){}
            catch(NumberFormatException error){}

        }
    }

}
