import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 * AddButtonPanel when initialized creates a frame which includes
 * the names and tasks of users and a delete button for the removal
 * of those tasks and names from the arrayList if checked.
 * 
 * {@code DeleteButtonPanel} extends {@code JFrame} and implements
 * the ActionListener interface used by the delete button.
 */
public class DeleteButtonPanel extends JFrame
{
    private DefaultTableModel model;
    private JTable table;
    /**
     * The deleteButton is implemented as a button that when
     * pressed deletes the user checked from the arrayLists of tasks.
     * 
     * {@code deleteButton} is {@code JButton} object that is added
     * to the panel and implements its action when clicked.
     */
    private JButton deleteButton;
    private boolean same=false;

    public DeleteButtonPanel()
    {
        setTitle("Select the respective Checkbox and click Delete to Remove the Tasks");
        setLayout(new GridLayout());
        setBounds(300, 90, 200, 250);
        setResizable(false);
        //Dimension size = new Dimension(200, 250);
        //setMinimumSize(size);


        //sets the datatype for each column
        model = new DefaultTableModel(){

            public Class<?> getColumnClass(int column){
            
                switch(column)
                {
                    case 0:
                        return Boolean.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;

                    default: 
                        return String.class;
                }
            }
        };




        table = new JTable(model);
        add(new JScrollPane(table));

        //names the columns
        model.addColumn("Select");
        model.addColumn("Full Name");
        model.addColumn("Task Outline");


        //fill the rows of the table with data   
        
        try
        {
            int innercount = 0;//prints data correctly if there is a scenario where a task with a name is not next to each other 
            //in short - ensures that each row is populated from each person's task(s)
            for(Person person : Tasks.ArrofNames) 
            {
                for(int count = 0; count < Tasks.ArrofTasks.size(); count++)
                {
                    if(Tasks.ArrofTasks.get(count).getName().matches(person.getName()))
                    {
                        model.addRow(new Object[0]);
                        model.setValueAt(false, innercount, 0);
                        model.setValueAt(Tasks.ArrofTasks.get(count).getName(), innercount, 1);
                        model.setValueAt(Tasks.ArrofTasks.get(count).getTaskOutline(), innercount, 2);
                        
                        innercount++;
                    }
                }
            }
        }   
        catch(IndexOutOfBoundsException e){ System.out.println(e.getStackTrace());}
        catch(Exception t){System.out.println(t.getStackTrace()); }
        
            
        

        //delete selected rows from table 

        deleteButton = new JButton("Delete"); //maybe add a confirm delete button with a yes or no functionality
        deleteButton.addActionListener(new ButtonListener());

        //deleteButton.setBounds(); //for further accuracy of where it is placed and the size of it
        add(deleteButton);
        
        pack();

        setVisible(true);

    }
    

    /**
     * {@code actionPerformed} method is called which If the checked data in the
     *  table matches the data in the arraylist then it will delete it from the
     *  arraylist and close the window.
    */
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            for(int row = 0; row < table.getRowCount(); row++) //rows of table != size of ArrofTasks apparently //goes through table to look for boolean
            {
                Boolean checked = Boolean.valueOf(table.getValueAt(row, 0).toString());

                if(checked == true)
                {
                    try
                    {

                    
                        for(int count = 0; count < Tasks.ArrofTasks.size(); count++)//checks the data in the row with a checked checkbox
                        {
                            if(table.getValueAt(row, 1).toString().matches(Tasks.ArrofTasks.get(count).getName())
                            && table.getValueAt(row, 2).toString().matches(Tasks.ArrofTasks.get(count).getTaskOutline()))
                            {
                                int oldExpectedTime = Tasks.ArrofTasks.get(count).getExpectedTime();
                                same = false;//baseline boolean
                                Tasks.ArrofTasks.remove(count); //deletes the task that was checked by the user
                                

                                for (Tasks t: Tasks.ArrofTasks){
                                    //checks if another instance of the name is in task
                                    if (t.getName().equals(table.getValueAt(row, 1).toString()))
                                        same = true;
                                }
                                //if the name is unique/no other task have that name then remove the name from the array.
                                if (!(same)){
                                    Tasks.ArrofNames.remove(count); //remove name at the same time
                                    PanelListItems.nameDropDownPub.removeAllItems();
                                    for (Person p: Tasks.ArrofNames){
                                        PanelListItems.nameDropDownPub.addItem(p.getName());
                                    }
                                    PanelListItems.fill();
                                    //PanelListItems.filler(Tasks.ArrofNames.get(count).getName()); //it would overwrite the progress of whatever is selected in the dropdown menu ie. gies wrong info 
                                }
                                //if the name is on another task then just remove the expected time from the person class and adjust the progressbars.
                                else{
                                    for (Person pele : Tasks.ArrofNames){
                                        if (pele.getName().equals(table.getValueAt(row, 1).toString())){
                                            pele.setEstTaskTimeLeft(pele.getEstTaskTimeLeft()-oldExpectedTime);
                                            PanelListItems.fill();
                                            if (PanelListItems.nameDropDownPub.getSelectedItem().equals(pele.getName()))
                                                PanelListItems.filler(Tasks.ArrofNames.get(count).getName()); 
                                        }
                                    }
                                }
                                
                            }
                        }
                    }
                    catch(IndexOutOfBoundsException tt){}
                }
            }
            PanelListItems.saveTasks("tasks.txt"); //overwrite file
            PanelListItems.saveNames("names.txt");//overwrite file
            PanelListItems.model.setRowCount(0);//removes data from table
            PanelListItems.showTable();//updates main table

            dispose(); //closes window and frees memory
            //setVisible(false); //hides the window but still consumes memory
        }
    }
}