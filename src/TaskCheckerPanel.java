import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * TaskCheckerPanel when initialized creates a frame which includes
 * 2 comboboxes whichallow the user to select a user and a task
 * which was completed to check it for completion.
 * 
 * {@code TaskCheckerPanel} extends {@code JFrame} and implements
 * ItemListener interface which is used by the ComboBoxes to select
 * which users and task of user should be checked.
 */

 public class TaskCheckerPanel extends JFrame implements ItemListener {
    private JPanel taskPanel = new JPanel();
    private JLabel pickName,pickTask,selectedUser,selectedTask;
    private JComboBox<String> nameDropDown, taskDropDown;
    private JButton checkButton, uncheckButton;


    public TaskCheckerPanel()
    {
        setTitle("Complete Tasks");
        //setSize(500,300);
        taskPanel.setSize(500,350);
        taskPanel.setLayout(new GridLayout(9,1,1,3));
        setResizable(false);
        setLayout(null);

        //ComboBox for the user that is completing their task
        pickName = new JLabel("Which user completed a task:");
        nameDropDown = new JComboBox<String>();
        for (Person t: Tasks.ArrofNames){
            nameDropDown.addItem(t.getName());
        }
        nameDropDown.addItemListener(this);
        selectedUser = new JLabel(nameDropDown.getSelectedItem() + " selected",JLabel.CENTER);

        //ComboBox to select which task was completed
        pickTask = new JLabel("Which task was completed:");
        taskDropDown = new JComboBox<String>();
        for (Tasks t: Tasks.ArrofTasks){
            if (t.getName().equals((String) nameDropDown.getSelectedItem())) {
                if(!(t.getCompleted()))
                    taskDropDown.addItem(t.getTaskOutline());
            }
        }
        taskDropDown.addItemListener(this);
        selectedTask = new JLabel(taskDropDown.getSelectedItem() + " selected",JLabel.CENTER);

        taskPanel.add(pickName);
        taskPanel.add(nameDropDown);
        taskPanel.add(selectedUser);
        taskPanel.add(pickTask);
        taskPanel.add(taskDropDown);
        taskPanel.add(selectedTask);

        checkButton = new JButton("Check");
        checkButton.addActionListener(new CheckButtonListener());
        taskPanel.add(checkButton);
        uncheckButton = new JButton("Uncheck");
        uncheckButton.addActionListener(new UncheckButtonListener());
        taskPanel.add(uncheckButton);

        add(taskPanel);
        setMinimumSize(taskPanel.getSize());
        pack();
        setVisible(true);
    }
    // iterates through list to find specific task of a specific user then sets that task to completed
    private class CheckButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            for (Tasks t : Tasks.ArrofTasks){
                if (t.getName().equals((String) nameDropDown.getSelectedItem()))
                    if (t.getTaskOutline().equals((String) taskDropDown.getSelectedItem()) && t.getCompleted()==false){
                        t.setCompleted(true);
                        for (Person p : Tasks.ArrofNames){
                            if(p.getName().equals(t.getName())){
                                p.setTaskComplete(p.getTaskComplete()+t.getExpectedTime());
                                p.setEstTaskTimeLeft(p.getEstTaskTimeLeft() - t.getExpectedTime());
                                PanelListItems.fill();
                                //PanelListItems.filler(p.getName()); //it would overwrite the progress of whatever is selected in the dropdown menu ie. gies wrong info 
                            }
                        }
                    }                        
            }
            //saves the updated completed tasks to text files.
            PanelListItems.saveNames("names.txt");
            PanelListItems.saveTasks("tasks.txt");
            //updates the table to include the newly checked task by removing and replacing all rows of the table.
            //for (int i=PanelListItems.table.getRowCount()-1;i>=0;i--)
            //    PanelListItems.model.removeRow(i);
            PanelListItems.model.setRowCount(0);//removes data from table
            PanelListItems.showTable();
            dispose();
        }
    }

    private class UncheckButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            for (Tasks t : Tasks.ArrofTasks)
            {
                if (t.getName().equals((String) nameDropDown.getSelectedItem()))
                {
                    if (t.getTaskOutline().equals((String) taskDropDown.getSelectedItem()) && t.getCompleted()==true)
                    {
                        t.setCompleted(false);

                        for (Person p : Tasks.ArrofNames)
                        {
                            if(p.getName().equals(t.getName()))
                            {
                                p.setTaskComplete(p.getTaskComplete()-t.getExpectedTime());
                                p.setEstTaskTimeLeft(p.getEstTaskTimeLeft() + t.getExpectedTime());
                                PanelListItems.fill();
                                if (PanelListItems.nameDropDownPub.getSelectedItem().equals(t.getName()))
                                    PanelListItems.filler(t.getName()); //it would overwrite the progress of whatever is selected in the dropdown menu ie. gies wrong info 
                            }
                        }
                    } 
                }                       
            }
            //saves the updated completed tasks to text files.
            PanelListItems.saveNames("names.txt");
            PanelListItems.saveTasks("tasks.txt");
            //updates the table to include the newly checked task by removing and replacing all rows of the table.
            //for (int i=PanelListItems.table.getRowCount()-1;i>=0;i--)
            //    PanelListItems.model.removeRow(i);
            PanelListItems.model.setRowCount(0);//removes data from table
            PanelListItems.showTable();
            dispose();
        }
    }



    // combobox selection affects the display in the second combobox and displays text of the selected user.
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == nameDropDown){
            selectedUser.setText(nameDropDown.getSelectedItem() + " selected");
            taskDropDown.removeAllItems();
            for (Tasks t: Tasks.ArrofTasks){
                if (t.getName().equals((String) nameDropDown.getSelectedItem())) {
                    taskDropDown.addItem(t.getTaskOutline());
                }
            }
        }
        if (e.getSource() == taskDropDown){
            selectedTask.setText(taskDropDown.getSelectedItem() + " selected");
        }

    }
}
