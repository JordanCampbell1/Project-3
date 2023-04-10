import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


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



        String currName = task.getName();
        String currTask = task.getTaskOutline();
        String currStartDate = task.getStartDate();
        int currETF = task.getExpectedTime();


        System.out.println("Hit enter to keep name as ["+currName+"], or enter new name:");
        String name = scan.nextLine();
        if (name.equals(""))
            name = currName;
            
        System.out.println("Hit enter to keep the Task Outline as ["+ currTask +"] or enter new size:");
        String sizeEntry=scan.nextLine();
        int size;
        if (sizeEntry.equals(""))
            size = currSize;
        else
            size = Integer.parseInt(sizeEntry);

        System.out.println("Hit enter to keep price at ["+currPrice+"] or enter new price:");
        String priceEntry = scan.nextLine();
        int price;
        if(priceEntry.equals(""))
            price = currPrice;
        else
            price = Integer.parseInt(priceEntry);

        System.out.println("Hit enter to keep level at ["+currLevel+"] or enter new level:");
        String levelEntry = scan.nextLine();
        int level;
        if(levelEntry.equals(""))
            level = currLevel;
        else
            level = Integer.parseInt(levelEntry);

        System.out.println("Hit enter to keep competitorArea at ["+currcompetitorArea +"] or enter new competitorArea:");
        String competitorAreaEntry = scan.nextLine();
        int competitorArea;
        if (competitorAreaEntry.equals(""))
            competitorArea = currcompetitorArea;
        else
            competitorArea = Integer.parseInt(competitorAreaEntry);

        System.out.println("Hit enter to keep numSecurity at ["+currnumSecurity +"] or enter new numSecurity:");
        String numSecurityEntry=scan.nextLine();
        int numSecurity;
        if (numSecurityEntry.equals(""))
            numSecurity = currnumSecurity;
        else
            numSecurity = Integer.parseInt(numSecurityEntry);

        System.out.println("Hit enter to keep barArea at ["+currbarArea +"] or enter new barArea:");
        String barAreaEntry=scan.nextLine();
        int barArea;
        if (barAreaEntry.equals(""))
            barArea = currbarArea;
        else
            barArea = Integer.parseInt(barAreaEntry);



        task.setName(name);
        task.setTaskOutline(taskOutline);
        task.setStartDate(startDate);
        task.setExpectedTime(ETF);

    }

    private class AddTaskButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            AddButtonPanel something = new AddButtonPanel();
        }
    }

    private class EditTaskButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            EditButtonPanel something = new EditButtonPanel();
        }
    }

    private class DeleteTaskButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            DeleteButtonPanel something = new DeleteButtonPanel();
        }
    }

}
