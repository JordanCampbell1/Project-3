import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EditButtonPanel extends JFrame implements ItemListener
{
    private JPanel editPanel= new JPanel();
    private JLabel userToBeEdited, userSelected, taskSelected,taskToBeEdited, changeName, changeTasks, changeExpectedTime, changeStartTime;
    private JTextField nameTextField, taskTextField, expectedTimeTextField, changeStartTimTextField;
    JComboBox<String> taskDropDown, userNameDropDown;
    private JButton changeButton;
    
    public EditButtonPanel()
    {
        this.setTitle("Edit Data");
        editPanel.setSize(480,550);
        this.setLayout(null);
        this.setResizable(false);
        editPanel.setLayout(new GridLayout(16,1,1,5));
        
        
        //ComboBox for the user-name choice for data to be edited
        userToBeEdited = new JLabel("Which user would you like to edit:");
        userNameDropDown = new JComboBox<>();
        for (Person t: Tasks.ArrofNames){
                userNameDropDown.addItem(t.getName());
            }
        userNameDropDown.addItemListener(this);
        userSelected = new JLabel(userNameDropDown.getSelectedItem() +" selected", JLabel.CENTER);

        //allow for the user to change all types of data access
        changeName = new JLabel("Change Name or leave empty to keep current name: ");
        taskToBeEdited = new JLabel("Which task would you like to edit");
        //Combobox for which task to edit and then proceeds to allow for tasktext field entry

        //Specific task for that person
        taskDropDown = new JComboBox<>();
        for (Tasks t: Tasks.ArrofTasks){
            if (t.getName().equals((String) userNameDropDown.getSelectedItem()) && !(t.getCompleted())) {
                taskDropDown.addItem(t.getTaskOutline());
            }
        }
        taskDropDown.addItemListener(this);
        taskSelected = new JLabel(taskDropDown.getSelectedItem() +" selected",JLabel.CENTER);
        changeTasks = new JLabel("Change Task or leave empty to keep current task: ");

        changeStartTime = new JLabel("Change Start Time or leave empty to keep current Start Time (HH:MM)(24-Hour Time):");

        changeExpectedTime = new JLabel("Change task expected time or leave empty to keep current expectation (in Minutes):");

        //textfields for options chosen to be changed
        nameTextField = new JTextField(30);
        taskTextField = new JTextField(30);
        expectedTimeTextField = new JTextField(15);

        changeStartTimTextField = new JTextField(10);


        editPanel.add(userToBeEdited);
        editPanel.add(userNameDropDown);
        editPanel.add(userSelected);
        editPanel.add(changeName);
        editPanel.add(nameTextField);
        editPanel.add(taskToBeEdited);
        editPanel.add(taskDropDown);
        editPanel.add(taskSelected);
        editPanel.add(changeTasks);
        editPanel.add(taskTextField);
        editPanel.add(changeStartTime);
        editPanel.add(changeStartTimTextField);
        editPanel.add(changeExpectedTime);
        editPanel.add(expectedTimeTextField);


        changeButton = new JButton("Change");
        changeButton.addActionListener(new ChangeButtonListener());
        editPanel.add(changeButton);


        getContentPane().add(editPanel);
        this.setMinimumSize(editPanel.getSize());
        this.pack();
        this.setVisible(true);
    }

    private class ChangeButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try
            {
                if (e.getSource() == changeButton)
                {
                    if(Integer.parseInt(expectedTimeTextField.getText()) > 0)
                    {
                        for (Tasks t: Tasks.ArrofTasks)
                        {
                            if (t.getName().equals((String) userNameDropDown.getSelectedItem()))
                            {
                                if (t.getTaskOutline().equals(taskDropDown.getSelectedItem()))
                                {
                                    for (Person p : Tasks.ArrofNames)
                                    {
                                        if (p.getName().equals((String) userNameDropDown.getSelectedItem())){
                                            p.setEstTaskTimeLeft(p.getEstTaskTimeLeft() - t.getExpectedTime() +Integer.parseInt(expectedTimeTextField.getText()));
                                        }
                                    }

                                    t.setExpectedTime(Integer.parseInt(expectedTimeTextField.getText()));
                                    t.setEndTime();
                                    new PopUpPaneler(t.getName(), t.getTaskOutline(), t.getEndTime());
                                    PanelListItems.fill();
                                    //PanelListItems.filler(t.getName()); //it would overwrite the progress of whatever is selected in the dropdown menu ie. gies wrong info      
                                }
                            }
                        }
                    }
                }

            }
            catch(ArrayIndexOutOfBoundsException ed){}
            catch(NumberFormatException error){}

            try{
                if(!(taskTextField.getText().equals("")))
                {
                    for (Tasks t: Tasks.ArrofTasks)
                    {
                        if (t.getName().equals((String) userNameDropDown.getSelectedItem()))
                        {
                            if (t.getTaskOutline().equals(taskDropDown.getSelectedItem()))
                            {
                                t.setTaskOutline(taskTextField.getText());
                                new PopUpPaneler(t.getName(), t.getTaskOutline(), t.getEndTime());
                            }
                        }
                    }
                }
            }
            catch(ArrayIndexOutOfBoundsException ed){}
            catch(NumberFormatException error){}

            try
            {
                if(!(changeStartTimTextField.getText().equals("")))
                {
                    for (Tasks t: Tasks.ArrofTasks)
                    {
                        if (t.getName().equals((String) userNameDropDown.getSelectedItem()))
                        {
                            if (t.getTaskOutline().equals(taskDropDown.getSelectedItem()))
                            {
                                t.setStartTime(changeStartTimTextField.getText());
                                t.setEndTime();
                                new PopUpPaneler(t.getName(), t.getTaskOutline(), t.getEndTime());
                            }
                        }
                    }
                }
            }
            catch(ArrayIndexOutOfBoundsException ed){}
            catch(NumberFormatException error){}

            try{
                if(!(nameTextField.getText().equals("")))
                {
                    for (Tasks t: Tasks.ArrofTasks){
                        if (t.getName().equals((String) userNameDropDown.getSelectedItem()))
                        {
                            t.setName(nameTextField.getText());

                            for (Person p : Tasks.ArrofNames)
                            {
                                if (p.getName().equals((String) userNameDropDown.getSelectedItem()))
                                {
                                    p.setName(nameTextField.getText());

                                    new PopUpPaneler(t.getName(), t.getTaskOutline(), t.getEndTime());

                                    PanelListItems.nameDropDownPub.removeAllItems();
                                    
                                    for (Person poro: Tasks.ArrofNames){
                                        PanelListItems.nameDropDownPub.addItem(poro.getName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch(ArrayIndexOutOfBoundsException ed){}
            catch(NumberFormatException error){}

            PanelListItems.saveNames("names.txt");
            PanelListItems.saveTasks("tasks.txt");
            //for (int i=PanelListItems.table.getRowCount()-1;i>=0;i--)
                //PanelListItems.model.removeRow(i);
            PanelListItems.model.setRowCount(0);//removes all the data in the table
            PanelListItems.showTable();
            dispose();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        userSelected.setText(userNameDropDown.getSelectedItem() + " selected");
        if (e.getSource() == userNameDropDown){
            userSelected.setText(userNameDropDown.getSelectedItem() + " selected");
            taskDropDown.removeAllItems();//double semi-colon?
            for (Tasks t: Tasks.ArrofTasks){
                if (t.getName().equals((String) userNameDropDown.getSelectedItem())) {
                    taskDropDown.addItem(t.getTaskOutline());
                }
            }
        }
        taskSelected.setText(taskDropDown.getSelectedItem() + " selected");
        if (e.getSource() == taskDropDown){
            taskSelected.setText(taskDropDown.getSelectedItem() + " selected");
        }
    }
}
