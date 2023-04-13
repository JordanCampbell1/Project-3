import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EditButtonPanel extends JFrame implements ItemListener
{
    private JPanel editPanel= new JPanel();
    private JLabel userToBeEdited, userSelected, taskSelected,taskToBeEdited, changeName, changeTasks, changeStartDate, changeEndDate;
    private JTextField nameTextField, taskTextField, startDateTextField, endDateTextField;
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
        for (String t: Tasks.ArrofNames){
                userNameDropDown.addItem(t);
            }
        userNameDropDown.addItemListener(this);
        userSelected = new JLabel(userNameDropDown.getSelectedItem() +" selected", JLabel.CENTER);

        //allow for the user to change all types of data access
        changeName = new JLabel("Change Name or leave empty to keep current name: ");
        taskToBeEdited = new JLabel("which task would you like to edit");
        //Combobox for which task to edit and then proceeds to allow for tasktext field entry

        //Specific task for that person
        taskDropDown = new JComboBox<>();
        for (Tasks t: Tasks.ArrofTasks){
            if (t.getName().equals((String) userNameDropDown.getSelectedItem())) {
                taskDropDown.addItem(t.getTaskOutline());
            }
        }
        taskDropDown.addItemListener(this);
        taskSelected = new JLabel(taskDropDown.getSelectedItem() +" selected",JLabel.CENTER);
        changeTasks = new JLabel("Change Task or leave empty to keep current task: ");
        changeStartDate =  new JLabel("Change Start of task or leave empty to keep current start: ");
        changeEndDate = new JLabel("Change task expected time or leave empty to keep current expectation: ");

        //textfields for options chosen to be changed
        nameTextField = new JTextField(30);
        taskTextField = new JTextField(30);
        startDateTextField = new JTextField(15);
        endDateTextField = new JTextField(15);

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
        editPanel.add(changeStartDate);
        editPanel.add(startDateTextField);
        editPanel.add(changeEndDate);
        editPanel.add(endDateTextField);
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
                if (e.getSource() == changeButton){
                    if(!(nameTextField.getText().equals("")))
                {
                    for (Tasks t: Tasks.ArrofTasks){
                        if (t.getName().equals((String) userNameDropDown.getSelectedItem())){
                            t.setName(nameTextField.getText());
                        }
                    }
                }

                if(!(taskTextField.getText().equals("")))
                {
                    for (Tasks t: Tasks.ArrofTasks){
                        if (t.getName().equals((String) userNameDropDown.getSelectedItem()))
                            if (t.getTaskOutline().equals(taskDropDown.getSelectedItem()))
                                t.setTaskOutline(taskTextField.getText());
                    }
                }

                if(!(startDateTextField.getText().equals("")))
                {
                    for (Tasks t: Tasks.ArrofTasks){
                        if (t.getName().equals((String) userNameDropDown.getSelectedItem()))
                            if (t.getTaskOutline().equals(taskDropDown.getSelectedItem()))
                                t.setStartDate(startDateTextField.getText());
                    }
                }

                if(Integer.parseInt(endDateTextField.getText()) <= 0)
                {
                    for (Tasks t: Tasks.ArrofTasks){
                        if (t.getName().equals((String) userNameDropDown.getSelectedItem()))
                            if (t.getTaskOutline().equals(taskDropDown.getSelectedItem()))
                                t.setExpectedTime(Integer.parseInt(endDateTextField.getText()));
                    }
                }
                
            }

            }
            catch(NumberFormatException error){}
            PanelListItems.saveNames("names.txt");
            PanelListItems.saveTasks("tasks.txt");
            for (int i=PanelListItems.table.getRowCount()-1;i>=0;i--)
                PanelListItems.model.removeRow(i);
            PanelListItems.showTable();
            dispose();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        userSelected.setText(userNameDropDown.getSelectedItem() + " selected");
        if (e.getSource() == userNameDropDown){
            userSelected.setText(userNameDropDown.getSelectedItem() + " selected");
            taskDropDown.removeAllItems();;
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
