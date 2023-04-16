import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationPanel extends JFrame{

    private JPanel p = new JPanel();
    private JLabel messageLabel = new JLabel("New task created!");

    public NotificationPanel() {
        setTitle("Enable Notification");
        setResizable(false);
        setSize(200, 100);

        p.add(messageLabel);

        getContentPane().add(p);

        setLocationRelativeTo(null);
        setVisible(true);

        playSound("mixkit-interface-option-select-2573.wav");
    }

    private void playSound(String soundFilePath) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResource(soundFilePath)));
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}