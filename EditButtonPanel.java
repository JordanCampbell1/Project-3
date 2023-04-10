import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EditButtonPanel extends JFrame implements ItemListener
{
    private JLabel userToBeEdited, userSelected, taskSelected,taskToBeEdited, changeName, changeTasks, changeStartDate, changeEndDate;
    private JTextField nameTextField, taskTextField, startDateTextField, endDateTextField;
    private JButton save;
    static JComboBox taskDropDown, userNameDropDown;
    private JButton changeButton = new JButton("Change");
    
    public EditButtonPanel()
    {
        setTitle("Edit Data");
        setBounds(300, 90, 800, 400);
        setResizable(false);
        //ComboBox for the user-name choice for data to be edited
        EditButtonPanel selectedName = new EditButtonPanel();
        userToBeEdited = new JLabel("Which user would you like to edit:");
        userNameDropDown = new JComboBox((ComboBoxModel) Tasks.ArrofNames);
        userNameDropDown.addItemListener(selectedName);
        String user = (String) userNameDropDown.getSelectedItem();
        userSelected = new JLabel(" no name selected");
        //allow for the user to change all types of data access
        changeName = new JLabel("Change Name or leave empty to keep current name: ");
        taskToBeEdited = new JLabel("which task would you like to edit");
        //Combobox for which task to edit and then proceeds to allow for tasktext field entry
        EditButtonPanel selectedTask = new EditButtonPanel();
        taskDropDown = new JComboBox((ComboBoxModel) Tasks.ArrofTasks);
        taskDropDown.addItemListener(selectedTask);
        taskSelected = new JLabel("no task selected");
        changeTasks = new JLabel("Change Task or leave empty to keep current task: ");
        changeStartDate =  new JLabel("Change Start of task or leave empty to keep current start: ");
        changeEndDate = new JLabel("Change task expected time or leave empty to keep current expectation: ");

        nameTextField = new JTextField(30);
        taskTextField = new JTextField(50);
        startDateTextField = new JTextField(15);
        endDateTextField = new JTextField(15);

        add(userToBeEdited);
        add(userNameDropDown);
        add(userSelected);
        add(changeName);
        add(nameTextField);
        add(taskToBeEdited);
        add(taskDropDown);
        add(changeTasks);
        add(taskTextField);
        add(changeStartDate);
        add(startDateTextField);
        add(changeEndDate);
        add(endDateTextField);

        changeButton.addActionListener(new ChangeButtonListener());
    }

    private class ChangeButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try
            {
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
            catch(NumberFormatException error){}
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == userNameDropDown){
            userSelected.setText(userNameDropDown.getSelectedItem() + " selected");
        }
        if (e.getSource() == taskDropDown){
            taskSelected.setText(taskDropDown.getSelectedItem() + " selected");
        }
    }
}
