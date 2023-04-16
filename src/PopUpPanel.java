import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 * PopUpPanel when initialized creates a frame which includes
 * text informing the user of theri mistakes.
 * 
 * {@code PopUpPanel} extends {@code JFrame}.
 */
public class PopUpPanel extends JFrame{

    private JPanel p = new JPanel();

    private JLabel tLabel = new JLabel("You Did Not Enter All Of The Data for This Task"); //adjust size of font

    public PopUpPanel()
    {
        setTitle("Issue With New Task Entry");
        setResizable(false);
        setSize(300, 400);

        p.setBackground(Color.RED);

        p.add(tLabel);
        tLabel.setFont(new Font("Arial", Font.BOLD, 20));
        setLocationRelativeTo(null); // Center the frame on the screen

        PopUpPanel.playSound("mixkit-arcade-retro-game-over-213.wav");

        getContentPane().add(p);

        pack();

        setVisible(true);
    }
    
    private static void playSound(String filePath) 
    {
        try {
            File soundFile = new File(filePath);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }


}