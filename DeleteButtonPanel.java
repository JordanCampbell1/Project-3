import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


public class DeleteButtonPanel extends JFrame
{
    private DefaultTableModel model;
    private JTable table;
    private JButton deleteButton;


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
        int innercount = 0;
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
            
        

        //delete selected rows from table 

        deleteButton = new JButton("Delete"); //maybe add a confirm delete button with a yes or no functionality
        deleteButton.addActionListener(new ButtonListener());

        //deleteButton.setBounds(); //for further accuracy of where it is placed and the size of it
        add(deleteButton);
        
        pack();

        setVisible(true);

    }
    

    //if the checked data in the table matches the data in the arraylist then it will delete it from the arraylist and close the window
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            for(int row = 0; row < table.getRowCount(); row++) //rows of table != size of ArrofTasks apparently //goes through table to look for boolean
            {
                Boolean checked = Boolean.valueOf(table.getValueAt(row, 0).toString());

                if(checked == true)
                {
                    for(int count = 0; count < Tasks.ArrofTasks.size(); count++)
                    {
                        if(table.getValueAt(row, 1).toString().matches(Tasks.ArrofTasks.get(count).getName())
                        && table.getValueAt(row, 2).toString().matches(Tasks.ArrofTasks.get(count).getTaskOutline()))
                        {
                            Tasks.ArrofTasks.remove(count); //deletes the task that was checked by the user
                            Tasks.ArrofNames.remove(count); //remove name at the same time
                            PanelListItems.fill();
                    }
                }
                    
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