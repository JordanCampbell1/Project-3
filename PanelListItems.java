import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.Scanner;

public class PanelListItems extends JPanel{

    private JButton manipulateData, sortTimeTaken, sortTaskCompleted, TaskChecker;

    private JCheckBox notifications;

    private JProgressBar progressBar;

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
                "End Date",
                "Completed"};

        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        loadNames("names.txt");
        loadTasks("tasks.txt");
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
        progressBar.setValue(0); //probably redundant due to the fill method below
        progressBar.setStringPainted(true);

        add(progressBar);

        fill(Tasks.ratioOfTasksCompleted()); //fill the progress bar animation based on ratio calculated
    }

    // function to dynamically increase progress  
    private void fill(int limit)  
    {  
        int i = 0;  
        try{  
            while(i < limit){  
                // fill the menu bar to the defined value using   
                // the setValue( ) method  
                progressBar.setValue(i) ;  
   
                // delay the thread by 100 ms  
                Thread.sleep(100);  
                // increasing the progress every time by 1%  
                i += 1 ;  //i++
            }  
        }  
        catch (Exception e) {  
          System.out.println(e);    
        }  
    }  

    public void loadNames(String nfile){
        Scanner nscan = null;
        try{
            nscan = new Scanner(new File(nfile));
            while(nscan.hasNext()){
                String nextLine = nscan.nextLine();
                Tasks.ArrofNames.add(nextLine);
            }
            nscan.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTasks(String tfile){//may need a function to create a file if file is not found
        Scanner tscan = null;
        try{
            tscan = new Scanner(new File(tfile));
            while (tscan.hasNext()){
                String[] nextLine = tscan.nextLine().split(" ");
                String name = nextLine[0];
                String taskName = nextLine[1];
                String startDate = nextLine[2];
                int estFin = Integer.parseInt(nextLine[3]);
                boolean completed = Boolean.parseBoolean(nextLine[4]);
                Tasks t = new Tasks(name,taskName,startDate,estFin,completed);
                Tasks.ArrofTasks.add(t);
            }
            tscan.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTasks(String tfile){

        try{
            File dataSaver = new File(tfile);
            PrintWriter saveTo = new PrintWriter(dataSaver);
            for (Tasks t : Tasks.ArrofTasks){
                saveTo.println(t.getName()+" "+t.getTaskOutline()+" "+t.getStartDate()+ " " +t.getExpectedTime() + " " + t.getCompleted());
            }
            saveTo.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveNames(String nfile){
        try{
            File nameSaver = new File(nfile);
            PrintWriter saveToName = new PrintWriter(nameSaver);
            for (String t : Tasks.ArrofNames){
                saveToName.println(t);
            }
            saveToName.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
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

                String complete = "No";

                if(s.getCompleted() == true){
                    complete = "Yes";
                }

                String[] item={name[0], name[1], s.getTaskOutline(), s.getStartDate(), "" + s.getExpectedTime(), s.getEndDate(), complete};
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
            TaskCheckerPanel test = new TaskCheckerPanel();
        }
    }

    private class SortTimeTakenListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            SortTimePanel random = new SortTimePanel();
        }
    }

    private class SortTaskCompletedListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            SortTaskCompletedPanel random = new SortTaskCompletedPanel();
        }
    }

    private class NotificationsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            NotificationPanel random = new NotificationPanel();
        }
    }
}
