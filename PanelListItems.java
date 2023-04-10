import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class PanelListItems extends JPanel{

    private JButton manipulateData, sortTimeTaken, sortTaskCompleted, TaskChecker;

    private JCheckBox notifications;

    private JProgressBar progressBar;

    private JTable mainTable = new JTable();

    private DefaultTableModel model = new DefaultTableModel();

    private JTable table;

    private JScrollPane scrollPane;
    

    public PanelListItems()
    {
        super(new GridLayout(2,1)); //establishes a new layout for the GUI to use


        String[] columnNames=  {"First Name",
                "Last Name", 
                "Task Outline",
                "Start Date",
                "Expected Time to Finish",
                "End Date"};

        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        showTable(); //list of data items to be put in the table in the main panel

        table.setPreferredScrollableViewportSize(new Dimension(500, Tasks.ArrofTasks.size()*15 +50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);

        add(scrollPane);

        //the above deals with list of data items in a table


        manipulateData = new JButton("Manipulate Data");
        TaskChecker = new JButton("Check a Task");
        sortTimeTaken = new JButton("Sort by Time Needed to Complete");
        sortTaskCompleted = new JButton("Sort by Completed Tasks");

        manipulateData.addActionListener(new ManipulateDataListener());
        TaskChecker.addActionListener(new TaskCheckerListener());
        sortTimeTaken.addActionListener(new SortTimeTakenListener());
        sortTaskCompleted.addActionListener(new SortTaskCompletedListener());

        add(manipulateData);
        add(TaskChecker);
        add(sortTimeTaken);
        add(sortTaskCompleted);


    
        notifications = new JCheckBox("Enable Notifications for Overdue Tasks");

        notifications.addActionListener(new NotificationsListener());

        add(notifications);


        progressBar = new JProgressBar();

        add(progressBar);
    }

    public void showTable() //adds all existing persons', that are in the text file, tasks to the table
    {
        for(String person : Tasks.ArrofNames)
        {
            addToTable(person);
        }
    }

    private void addToTable(String person) //adds a person's task to the table if they have a task attached to them in the text file
    {
        for(Tasks s : Tasks.ArrofTasks)
        {
            if(s.getName().matches(person))
            {
                String[] name= s.getName().split(" ");
                String[] item={name[0], name[1], s.getTaskOutline(), s.getStartDate(), "" + s.getExpectedTime(), s.getEndDate()};
                model.addRow(item);   
            }

        }

             

    }

    //add functionality to these actionlisteners

    private class ManipulateDataListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ManipulateDataPanel test = new ManipulateDataPanel();
        }
    }

    private class TaskCheckerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
        }
    }

    private class SortTimeTakenListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
        }
    }

    private class SortTaskCompletedListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
        }
    }

    private class NotificationsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
        }
    }
}
