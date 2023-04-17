import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * AddButtonPanel when initialized creates a frame which includes
 * the inputs for adding a tasks to arraylist of tasks.
 * 
 * {@code AddButtonPanel} extends {@code JFrame} and implements
 * the ActionListener interface used by save button.
 */
public class AddButtonPanel extends JFrame
{
    private JLabel namelLabel, taskOutlinelLabel, ETFJLabel, startDateLabel;

    private JTextField nameTextField, taskOutlineTextField, ETFTextField, startTimeField;
    /**
     * The saveButton is implemented as a button that when
     * pressed saves the user input to the arrayLists of tasks.
     * {@code saveButton} is {@code JButton} object that is added
     * to the panel.
     */
    private JButton saveButton = new JButton("Save");

    private JPanel p = new JPanel();

    public AddButtonPanel()
    {
        setTitle("Adding a Task");
        p.setSize(200,100);
        p.setLayout(new GridLayout(9,1)); 
        setMinimumSize(p.getSize());
        setResizable(false);

        //p.setLayout(null); //to accurately set the componenets on the panel


        namelLabel = new JLabel("Full Name");
        namelLabel.setBounds(0,0,75,75);
        taskOutlinelLabel= new JLabel("Task Outline");
        taskOutlinelLabel.setBounds(0,150,75,75);
        ETFJLabel = new JLabel("Estimated Duration (in mins)");

        startDateLabel = new JLabel("Start Time (in HH:MM) (24-Hour)");

        nameTextField = new JTextField(30);
        nameTextField.setBounds(0,75,75,75);
        taskOutlineTextField = new JTextField(30);
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

    /**
     * {@code actionPerformed} method is called when the save button is clicked
     * to save the user text input to the array of tasks.
     */
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

                //System.out.println("NAME: " + nameTextField.getText().equals(""));
    
                String taskOutline = "";
    
                if(!(taskOutlineTextField.getText().equals("")))
                {
                    taskOutline = taskOutlineTextField.getText();
                }
                //System.out.println("TASKOUTLINE: " + taskOutlineTextField.getText().equals(""));

                String startTime = "";
    
                if(!(startTimeField.getText().equals("")))
                {
                    startTime = startTimeField.getText();
                }

                //System.out.println("starttime: " + startTimeField.getText().equals(""));
    
    
                int ETF = 0;
                
    
                if(Integer.parseInt(ETFTextField.getText()) > 0)
                {
                    ETF = Integer.parseInt(ETFTextField.getText());                   
                }
                
                //System.out.println("ETF: " +  getsimple ETFTextField.getText());
                //System.out.println("ETF: " + (Integer.parseInt(ETFTextField.getText()) > 0) );
                //nothing is printed from ETFtectfield for some reason
                


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
                                if (PanelListItems.nameDropDownPub.getSelectedItem().equals(name))
                                    PanelListItems.filler(name); 
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
