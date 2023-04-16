import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SortTaskCompletedPanel extends JFrame{

    private JPanel sortedTablePanel = new JPanel();
    private DefaultTableModel models = new DefaultTableModel();
    private JScrollPane scrollPaner;
    public SortTaskCompletedPanel()
    {
        setTitle("Persons with Completed Tasks");
        setResizable(false);
        sortedTablePanel.setSize(850,100); //only affects the size when setminimum() is not using getpreferreedsize as an argument
        sortedTablePanel.setLayout(new GridLayout());


        String [] columnNames ={"First Name","Last Name","Task Hours Completed", "Est Task Time Left"};
        models = new DefaultTableModel(columnNames,0);
        JTable tablet = new JTable(models);

        addToTable();

        tablet.setPreferredScrollableViewportSize(new Dimension(500, Tasks.ArrofNames.size()*15 +50));
        tablet.setFillsViewportHeight(true);
        scrollPaner = new JScrollPane(tablet);
        sortedTablePanel.add(scrollPaner);

        setMinimumSize(sortedTablePanel.getSize());

        add(sortedTablePanel);

        pack();
        setVisible(true);
    }

    private void addToTable() //adds a person's task to the table if they have a task attached to them in the text file
    {
        Collections.sort(Tasks.ArrofNames);

        for(Person s : Tasks.ArrofNames)
        {            
            String[] name= s.getName().split(" ");
            String[] item={name[0], name[1], Integer.toString(s.getTaskComplete()), Integer.toString(s.getEstTaskTimeLeft())};
            models.addRow(item);   
            
        } 
    }
    
}
