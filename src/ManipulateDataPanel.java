import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ManipulateDataPanel when initialized creates a frame which includes
 * three buttons that when pressed lead to other panels that change the
 * data of the program.
 * 
 * {@code ManipulateDataPanel} extends {@code JFrame} and has buttonlistener
 * classes that implement the ActionListener interface used by the three buttons.
 */
public class ManipulateDataPanel extends JFrame{ //figure out how to extend from jpanel while updating an object of the class Tasks seamlessly

    private JButton addTaskButton = new JButton("Add Task");
    private JButton editTaskButton = new JButton("Edit Task");
    private JButton deleteTaskButton = new JButton("Delete Task");

    private JPanel pnlsomething = new JPanel();


    public ManipulateDataPanel()
    {
        setTitle("Manipulate Data");
        setBounds(300, 90, 200, 250);
        setResizable(false);

        addTaskButton.addActionListener(new AddTaskButtonListener());
        editTaskButton.addActionListener(new EditTaskButtonListener());
        deleteTaskButton.addActionListener(new DeleteTaskButtonListener());

        pnlsomething.add(addTaskButton);
        pnlsomething.add(editTaskButton);
        pnlsomething.add(deleteTaskButton);

        add(pnlsomething);

        pack();

        setVisible(true);
    }

    private class AddTaskButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            AddButtonPanel something = new AddButtonPanel();

            setVisible(false);
        }
    }

    private class EditTaskButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            EditButtonPanel something = new EditButtonPanel();

            setVisible(false);
        }
    }

    private class DeleteTaskButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            DeleteButtonPanel something = new DeleteButtonPanel();

            setVisible(false);
        }
    }

}
