import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * PopUpPaneler when initialized creates a frame which informs
 * the user they did not complete their task.
 * 
 * {@code PopUpPaneler} extends {@code JFrame}.
 */
public class PopUpPaneler extends JFrame {
   JPanel popOut = new JPanel();
   String name,taskName,endTime;
   boolean same=true;
   int count, oldExpectedTime;
   Tasks telling;
   /**
    * PopUpPaneler constructor for class.
    * @param name is the name of the person.
    * @param taskName is the name of the task.
    * @param endTime is the time when the task should be finished by.
    */
   public PopUpPaneler(String name, String taskName, String endTime) {
      this.name = name;
      this.taskName = taskName;
      this.endTime = endTime;
      setTitle("Overdue Task(s)");
      setResizable(false);

      for (Tasks pt:Tasks.ArrofTasks){
         if (pt.getName().equals(name)){
            if (pt.getTaskOutline().equals(taskName)){
               telling = pt;
               oldExpectedTime = pt.getExpectedTime();
            }
         }
      }
      // create a timer that will check the current time every second
      Timer timer = new Timer(1000, new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // get the current time
            LocalTime currentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);

            //Checks if the task was completed and no longer pops up
            for (Tasks lee : Tasks.ArrofTasks){
               if (lee.getName().equals(name)){
                  if (lee.getCompleted()){
                     ((Timer) e.getSource()).stop();
                     return;
                  }
               }
            }
            //Detects if edits were made to task and returns from the instance effectively "deleting" the pop up.
            for (Tasks leer : Tasks.ArrofTasks){
               if (leer.equals(telling))
                  if (leer.getName().equals(name) && leer.getTaskOutline().equals(taskName) && leer.getEndTime().equals(endTime)) {
                  // all properties are the same, do nothing
              } else {
                  // at least one property has changed
                  ((Timer) e.getSource()).stop();
                  return;
              }
            }
            
            // check if the current time is equal to the popup time
            if (currentTime.equals(LocalTime.parse(endTime))) {
               // create a panel to display
               popOut.setSize(300,150);
               popOut.setLayout(new GridLayout());
               setMinimumSize(popOut.getSize());

               popOut.add(new JLabel("One or More Task(s) are Overdue", JLabel.CENTER));
               popOut.setBackground(Color.red);
               
               add(popOut);
               pack();
               new NotificationPanel("mixkit-interface-option-select-2573.wav").playSound("mixkit-interface-option-select-2573.wav");
               //setLocation(b);
               setVisible(true);
               for(int j =0;j<Tasks.ArrofTasks.size();j++){
                  if (Tasks.ArrofTasks.get(j).getName().equals(name)){
                     if (endTime.equals(Tasks.ArrofTasks.get(j).getEndTime())){
                        for(Tasks p : Tasks.ArrofTasks){
                           if (p.getName().equals(name)){
                              count++;
                           }
                        }
                        if (Tasks.ArrofTasks.get(j).getTaskOutline().equals(taskName)){
                              if (count < 2){
                                 for(int i =0;i<Tasks.ArrofNames.size();i++){
                                    if (Tasks.ArrofNames.get(i).getName().equals(name)){
                                       if (endTime.equals(Tasks.ArrofTasks.get(j).getEndTime())){
                                          Tasks.ArrofNames.remove(i);
                                       }
                                       PanelListItems.nameDropDownPub.removeAllItems();
                                       for (Person picka: Tasks.ArrofNames){
                                           PanelListItems.nameDropDownPub.addItem(picka.getName());
                                       }
                                    }
                                 }
                              }
                              else{
                                 for (Person pele : Tasks.ArrofNames){
                                    if (pele.getName().equals(name)){
                                        pele.setEstTaskTimeLeft(pele.getEstTaskTimeLeft()-oldExpectedTime);
                                    }
                              }
                           }
                           }
                           
                           Tasks.ArrofTasks.remove(j);
                           PanelListItems.fill();
                           //PanelListItems.filler(name); //it would overwrite the progress of whatever is selected in the dropdown menu ie. gies wrong info 
                           PanelListItems.saveNames("names.txt");
                           PanelListItems.saveTasks("tasks.txt");
                           //for (int i=PanelListItems.table.getRowCount()-1;i>=0;i--)//table.getRowCount(0) does the same thing
                              //PanelListItems.model.removeRow(i);
                           PanelListItems.model.setRowCount(0); //to empty the table of data
                           PanelListItems.showTable();
                        }
                     }
                     
               }
               // stop the timer after the panel is displayed
               ((Timer) e.getSource()).stop();
            }
         }
      });

      // start the timer
      timer.start();
   }

   /**
    * Getter method for the name of the person.
    * @return the name of the person.
    */
   public String getName(){
      return name;
   }
   /**
    * Getter method for the name of the task.
    * @return the task name.
    */
   public String getTaskName(){
      return taskName;
   }
   /**
    * Setter method for the name.
    * @param name is the new name to be set.
    */
   public void setName(String name){
      this.name = name;
   }

   /**
    * Setter method for the task name
    * @param taskName is the new task name to be set.
    */
   public void setTaskName(String taskName){
      this.taskName = taskName;
   }
}
