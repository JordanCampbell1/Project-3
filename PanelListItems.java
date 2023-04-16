import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
/**
 * PanelListItems when initialized creates a panel which includes
 * the buttons to sort, manipulate and check a user for completion of a task,
 * a table displaying the user tasks and progressbars showing the overall 
 * completion of task and specific user task completion.
 * 
 * {@code PanelListItems} extends {@code JPanel} and implements
 * the ItemListener interface used by the combobox that shows the
 * progress of a speciific user on the progressbar.
 */
public class PanelListItems extends JPanel implements ItemListener{

    private JButton manipulateData, sortTimeTaken, sortTaskCompleted, TaskChecker;

    private JCheckBox notifications;

    public static JProgressBar progressBar,progressPull;

    public static DefaultTableModel model = new DefaultTableModel();
    
    private JLabel topBar = new JLabel("Overall Progress");

    public static JTable table;

    private JScrollPane scrollPane;

    private JPanel tablePanel = new JPanel(), buttonPanel = new JPanel(), notiPanel = new JPanel();
    
    public static int totalTask,totalTaskComplete;  

    public static JComboBox<String> nameDropDownPub;

    public PanelListItems()
    {
        super(null); //establishes a new layout for the GUI to use

        setBounds(0,0,650,700);

        String[] columnNames=  {"First Name",
                "Last Name", 
                "Task Outline",
                "Start Time",
                "Estimated Duration (Mins)",
                "End Time",
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


        notifications = new JCheckBox("Enable Notifications"); //"Enable Notifications for Overdue Tasks"

        notifications.addActionListener(new NotificationsListener());
        notiPanel.setLayout(new GridLayout());
        notiPanel.add(notifications);
        

        
        
         
        
        nameDropDownPub = new JComboBox<>();
        for (Person p: Tasks.ArrofNames){ //udates dropdown with names
            nameDropDownPub.addItem(p.getName());
        }
        nameDropDownPub.addItemListener(this);

        progressBar = new JProgressBar();
        progressBar.setValue(0); //probably redundant due to the fill method below
        progressBar.setStringPainted(true);
        fill();

        progressPull = new JProgressBar();
        progressPull.setValue(0);
        progressPull.setStringPainted(true);
        filler((String)nameDropDownPub.getSelectedItem());

        topBar.setBounds(420,605,150,20);
        tablePanel.setBounds(0,0, 650, 500);
        buttonPanel.setBounds(0,500,650,100);
        //notiPanel.setBounds(0,600,225,100);
        progressBar.setBounds(330,630, 300, 25);
        nameDropDownPub.setBounds(65,605,150,20);
        progressPull.setBounds(0,630,300,25);


        
        add(topBar);
        add(nameDropDownPub);
        add(progressPull);
        add(tablePanel);
        add(buttonPanel);
        add(notiPanel);
        add(progressBar);
        

    }

    // function to dynamically increase progress  
    public static void fill()
    {  
        for (Person t:Tasks.ArrofNames){
            totalTask+=t.getEstTaskTimeLeft() +t.getTaskComplete();
            totalTaskComplete += t.getTaskComplete();
        }
        if (totalTask <= 0){
            progressBar.setMaximum(1);
        }
        else
            progressBar.setMaximum(totalTask);
        progressBar.setValue(totalTaskComplete);
        totalTask = 0;
        totalTaskComplete = 0; 
    }  

    public static void filler(String selectedPerson)  //fills progress bar based on a person's task time info
    {  
        for (Person peeper: Tasks.ArrofNames){
            if (peeper.getName().equals(selectedPerson)){
                progressPull.setMaximum(peeper.getEstTaskTimeLeft() + peeper.getTaskComplete());
                progressPull.setValue(peeper.getTaskComplete());
            }
        }
    }  


/**
 * {@code loadNames} takes the string name of the textfile and
 * initializes a file object using the string to load the names attributes
 * from the text file and iniitialize a person object to add to the
 * arrofNames.  
 *  
 * @param nfile is a string of the name of the textfile used which
 * has a list of the names of the users.
 */
    public void loadNames(String nfile){
        try{
            Scanner nscan = null;
            nscan = new Scanner(new File(nfile));
            while(nscan.hasNext()){
                String[] nextLine = nscan.nextLine().split(" ");
                String name = nextLine[0] + " " + nextLine[1];
                int taskComplete = Integer.parseInt(nextLine[2]);
                int estTaskTimeLeft = Integer.parseInt(nextLine[3]);
                Person p = new Person(name,taskComplete,estTaskTimeLeft);
                Tasks.ArrofNames.add(p);
            }
            nscan.close();
        }
        catch(IOException ioe){}
    }

/**
 * {@code loadTasks} takes the string name of the textfile and
 * initializes a file object using the string to load the tasks attributes
 * from the text file and iniitialize a tasks object to add to the
 * arrofTasks.  
 *  
 * @param tfile is a string of the name of the textfile used which
 * has a list of the attributes of the tasks.
 */
    public void loadTasks(String tfile){
        Scanner tscan = null;
        try{
            tscan = new Scanner(new File(tfile));
            while (tscan.hasNext()){
                String[] nextLine = tscan.nextLine().split(",");
                String name = nextLine[0];
                String taskName = nextLine[1];
                String startTime = nextLine[2];
                int estFin = Integer.parseInt(nextLine[3]);
                boolean completed = Boolean.parseBoolean(nextLine[4]);
                Tasks t = new Tasks(name,taskName,estFin,completed);
                t.setStartTime(LocalTime.parse(startTime).truncatedTo(ChronoUnit.MINUTES));//why do this when startTime 
                t.setEndTime();
                Tasks.ArrofTasks.add(t);
                new PopUpPaneler(t.getName(), t.getTaskOutline(), t.getEndTime());
            }
            tscan.close();
        }
        catch (IOException e) {}
    }

/**
 * {@code saveTasks} takes the string name of the textfile and
 * initializes a file object using the string to save the tasks attributes
 * to the text file.
 *  
 * @param tfile is a string of the name of the textfile used which
 * has a list of the attributes of the tasks.
 */
    public static void saveTasks(String tfile){

        try{
            File dataSaver = new File(tfile);
            PrintWriter saveTo = new PrintWriter(dataSaver);
            for (Tasks t : Tasks.ArrofTasks){
                saveTo.println(t.getName()+","+t.getTaskOutline()+","+t.getStartTime()+ "," +t.getExpectedTime() + "," + t.getCompleted());
            }
            saveTo.close();
        }
        catch (IOException e) {}

    }
/**
 * {@code saveNames} takes the string name of the textfile and
 * initializes a file object using the string to save the person attributes
 * to the text file.
 *  
 * @param nfile is a string of the name of the textfile used which
 * has a list of the attributes of the person.
 */
    public static void saveNames(String nfile){
        try{
            File nameSaver = new File(nfile);
            PrintWriter saveToName = new PrintWriter(nameSaver);
            for (Person t : Tasks.ArrofNames){
                saveToName.println(t.getName() + " " +t.getTaskComplete() + " " + t.getEstTaskTimeLeft());
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
    /**
     * {@code addToTable} adds a person's task to the table if they
     *  have a task attached to them in the text file. 
     * 
     * @param person is a string of a users name that is used
     * to index through the list of tasks and add the matching
     * task to the table to display the task attributes.
     */
    private static void addToTable(String person) 
    {
        for(Tasks s : Tasks.ArrofTasks)
        {
            if(s.getName().matches(person))
            {
                String[] name = s.getName().split(" ");

                String complete = "No";

                if(s.getCompleted() == true){
                    complete = "Yes";
                }

                String[] item={name[0], name[1], s.getTaskOutline(), s.getStartTime(), "" + s.getExpectedTime(), s.getEndTime(), complete};
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

    public void itemStateChanged(ItemEvent e) 
    {
        if (e.getSource() == nameDropDownPub){
            filler((String)nameDropDownPub.getSelectedItem());
        }
        
    }
}