import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

public class DeleteButtonPanel extends JFrame
{

    private DefaultTableModel model;
    private JTable table;
    private JButton deleteButton;


    public DeleteButtonPanel()
    {
        setTitle("Select the respective Checkbox and click Delete to Remove the Tasks");
        setBounds(300, 90, 200, 250);

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

    }
    

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            for(int i = 0; i < table.getRowCount(); i++)
            {
                
            }
        }
    }
}
