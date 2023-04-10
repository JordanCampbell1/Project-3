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
        setBounds(300, 90, 200, 250);


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
        for(int count = 0; count < Tasks.ArrofTasks.size() ; count++)
        {
            model.addRow(new Object[0]);
            model.setValueAt(false, count, 0);

            for(String person : Tasks.ArrofNames)
            {
                for(Tasks t : Tasks.ArrofTasks)
                {
                    if(t.getName().matches(person))
                    {
                        model.setValueAt(t.getName(), count, 1);
                        model.setValueAt(t.getTaskOutline(), count, 2);
                    }
                }
            }
            
        }

        //delete selected rows from table 

        deleteButton = new JButton("Delete"); //maybe add a confirm delete button with a yes or no functionality
        deleteButton.addActionListener(new ButtonListener());

        //deleteButton.setBounds(); //for further accuracy of where it is paled and the size of it
        add(deleteButton);
        


    }
    

    //if the checked data in the table matches the data in the arraylist then it will delete it from the arraylist and close the window
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            for(int row = 0; row < table.getRowCount(); row++)
            {
                Boolean checked = Boolean.valueOf(table.getValueAt(row, 0).toString());

                if(checked == true)
                {
                    for(Tasks t: Tasks.ArrofTasks)
                    {
                        if(table.getValueAt(row, 1).toString().matches(t.getName())
                        && table.getValueAt(row, 2).toString().matches(t.getTaskOutline()))
                        {
                            Tasks.ArrofTasks.remove(t); //deletes the task that was checked by the user
                        }
                    }

                    
                }
            }

            setVisible(false); //closes the window
        }
    }
}
