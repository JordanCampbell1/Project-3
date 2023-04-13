import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PanelListItems extends JPanel implements ItemListener{

    private JButton manipulateData, sortTimeTaken, sortTaskCompleted, TaskChecker;

    private JCheckBox notifications;

    private JProgressBar progressBar;

    public static DefaultTableModel model = new DefaultTableModel();

    public static JTable table;

    private JScrollPane scrollPane;

    private JPanel tablePanel = new JPanel(), buttonPanel = new JPanel(),taskedPanel = new JPanel(),notiPanel = new JPanel();

    public static JComboBox<String> nameDropDownPub;
    

    public PanelListItems()
    {
        super(null); //establishes a new layout for the GUI to use

        setBounds(0,0,650,700);

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
        tablePanel.setLayout(new GridLayout());
        scrollPane = new JScrollPane(table);

        tablePanel.add(scrollPane);

        //the above deals with list of data items in a table


        manipulateData = new JButton("Manipulate Data");
        TaskChecker = new JButton("Check a Task");
        sortTimeTaken = new JButton("Sort by Time Needed to Complete");
        sortTaskCompleted = new JButton("Sort by Completed Tasks");

        manipulateData.addActionListener(new ManipulateDataListener());
        TaskChecker.addActionListener(new TaskCheckerListener());
        sortTimeTaken.addActionListener(new SortTimeTakenListener());
        sortTaskCompleted.addActionListener(new SortTaskCompletedListener());
        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.add(manipulateData);
        buttonPanel.add(TaskChecker);
        buttonPanel.add(sortTimeTaken);
        buttonPanel.add(sortTaskCompleted);


        taskedPanel.setLayout(new GridLayout(2,1));
        notifications = new JCheckBox("Enable Notifications");

        notifications.addActionListener(new NotificationsListener());
        notiPanel.setLayout(new GridLayout());
        notiPanel.add(notifications);

        nameDropDownPub = new JComboBox<>();
        for (Person t: Tasks.ArrofNames){
            nameDropDownPub.addItem(t.getName());
        }
        nameDropDownPub.addItemListener(this);
        //taskedPanel.add(nameDropDown);
        add(nameDropDownPub);
        progressBar = new JProgressBar();
        progressBar.setValue(0); //probably redundant due to the fill method below
        progressBar.setStringPainted(true);

        //taskedPanel.add(progressBar);
        add(progressBar);
        tablePanel.setBounds(0,0, 650, 500);
        buttonPanel.setBounds(0,500,650,100);
        notiPanel.setBounds(0,600,225,100);
        progressBar.setBounds(375,635, 225, 25);
        nameDropDownPub.setBounds(425,605,150,25);
        //taskedPanel.setBounds(225,600,425,100);

        add(tablePanel);
        add(buttonPanel);
        add(notiPanel);
        //add(taskedPanel);
        

    }

    // function to dynamically increase progress  
    private void fill(int completes,int limit)  
    {  
        int i = 0;
        progressBar.setMaximum(limit); 
        try{  
            while(i <= completes){  
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
        try{
            Scanner nscan = null;
            nscan = new Scanner(new File(nfile));
            while(nscan.hasNext()){
                String[] nextLine = nscan.nextLine().split(" ");
                String name = nextLine[0];
                int taskComplete = Integer.parseInt(nextLine[1]);
                int estTaskTimeLeft = Integer.parseInt(nextLine[2]);
                Person p = new Person(name,taskComplete,estTaskTimeLeft);
                Tasks.ArrofNames.add(p);
            }
            nscan.close();
        }
        catch(IOException ioe){}
    }

    public void loadTasks(String tfile){
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
        catch (IOException e) {}
    }

    public static void saveTasks(String tfile){

        try{
            File dataSaver = new File(tfile);
            PrintWriter saveTo = new PrintWriter(dataSaver);
            for (Tasks t : Tasks.ArrofTasks){
                saveTo.println(t.getName()+" "+t.getTaskOutline()+" "+t.getStartDate()+ " " +t.getExpectedTime() + " " + t.getCompleted());
            }
            saveTo.close();
        }
        catch (IOException e) {}

    }

    public static void saveNames(String nfile){
        try{
            File nameSaver = new File(nfile);
            PrintWriter saveToName = new PrintWriter(nameSaver);
            for (Person t : Tasks.ArrofNames){
                saveToName.println(t.getName() + " " + t.getEstTaskTimeLeft() + " " + t.getTaskComplete());
            }
            saveToName.close();
        }
        catch (IOException e) {}
    }

    public static void showTable() //adds all existing persons', that are in the text file, tasks to the table
    {
        for(Person person : Tasks.ArrofNames)
        {
            addToTable(person.getName());
        }
    }

    private static void addToTable(String person) //adds a person's task to the table if they have a task attached to them in the text file
    {
        for(Tasks s : Tasks.ArrofTasks)
        {
            if(s.getName().matches(person))
            {
                String[] name= s.getName().split(",");

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

    public void itemStateChanged(ItemEvent e) {
        ArrayList<Tasks> numofTask = new ArrayList<>(); 
        ArrayList<Tasks> numCompleted = new ArrayList<>();
        if (e.getSource() == nameDropDownPub){
            for (Tasks t: Tasks.ArrofTasks){
                if (t.getName().equals((String) nameDropDownPub.getSelectedItem())) {
                    numofTask.add(t);
                    if (t.getCompleted() == true){
                        numCompleted.add(t);
                    }
                }
            }
            fill(numCompleted.size(),numofTask.size());
        }
            numofTask.clear();
            numCompleted.clear();
    }
}
