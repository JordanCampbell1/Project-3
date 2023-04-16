import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopUpPanel extends JFrame{

    private JPanel p = new JPanel();

    private JLabel tLabel = new JLabel("You Did Not Enter All Of The Data for This Task"); //adjust size of font

    public PopUpPanel()
    {
        setTitle("Issue With New Task Entry");
        setResizable(false);
        setSize(400, 500);

        p.setBackground(Color.RED);

        p.add(tLabel);
        tLabel.setFont(new Font("Arial", Font.BOLD, 20));

        getContentPane().add(p);
        setLocationRelativeTo(null); // Center the frame on the screen
        
        PopUpPanel.playSound("Discord notification - Pop Up beep [sound effect] (3).mp3");

        pack();

        setVisible(true);
        
    }private static void playSound(String filePath) {
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