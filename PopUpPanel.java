import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PopUpPanel extends JFrame {
   JPanel popOut = new JPanel();
   
   public PopUpPanel(LocalTime endTime) {
      setTitle("Incomplete Task");
      setResizable(false);
      // create a timer that will check the current time every second
      Timer timer = new Timer(1000, new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // get the current time
            LocalTime currentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);

            // check if the current time is equal to the popup time
            if (currentTime.equals(endTime)) {
               // create a panel to display
               popOut.setSize(300,150);
               popOut.setLayout(new GridLayout());
               popOut.add(new JLabel("Task not Complete", JLabel.CENTER));
               setMinimumSize(popOut.getSize());
               add(popOut);
               pack();
               //setLocation(b);
               setVisible(true);
               
               // stop the timer after the panel is displayed
               ((Timer) e.getSource()).stop();
            }
         }
      });

      // start the timer
      timer.start();
   }
}
