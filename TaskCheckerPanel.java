import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class TaskCheckerPanel extends JFrame implements ItemListener {
    private JLabel pickName,pickTask,selectedUser,selectedTask;
    private JComboBox nameDropDown, taskDropDown;
    private JButton checkButton;
    public TaskCheckerPanel(){
        setTitle("Complete Tasks");
        setBounds(300, 90, 800, 200);
        setResizable(false);
        //ComboBox for the user that is completing their task
        TaskCheckerPanel userSelected = new TaskCheckerPanel();
        pickName = new JLabel("Which user completed a task");
        nameDropDown = new JComboBox((ComboBoxModel) Tasks.ArrofNames);
        nameDropDown.addItemListener(userSelected);
        selectedUser = new JLabel("no name selected");

        //ComboBox to select which task was completed
        TaskCheckerPanel taskSelected = new TaskCheckerPanel();
        pickTask = new JLabel("Which task was completed");
        taskDropDown = new JComboBox();
        for (Tasks t: Tasks.ArrofTasks){
            if (t.getName().equals((String) nameDropDown.getSelectedItem())) {
                taskDropDown.addItem(t.getTaskOutline());
            }
        }
        taskDropDown.addItemListener(taskSelected);
        selectedTask = new JLabel("no task selected");

        add(pickName);
        add(nameDropDown);
        add(selectedUser);
        add(pickTask);
        add(taskDropDown);
        add(selectedTask);

        checkButton.addActionListener(new CheckButtonListener());
        add(checkButton);
        pack();
        setVisible(true);
    }
    // iterates through list to find specific task of a specific user then sets that task to completed
    private class CheckButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            for (Tasks t : Tasks.ArrofTasks){
                if (t.getName().equals((String) nameDropDown.getSelectedItem()))
                    if (t.getTaskOutline().equals((String) taskDropDown.getSelectedItem()))
                        t.setCompleted(true);
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == nameDropDown){
            selectedUser.setText(nameDropDown.getSelectedItem() + " selected");
        }
        if (e.getSource() == taskDropDown){
            selectedTask.setText(taskDropDown.getSelectedItem() + " selected");
        }

    }
}
