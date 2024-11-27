/*import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.event.KeyEvent;

public class test extends JFrame {
    public JProgressBar bar;
    int vie = 5;

    public test(int vie){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel p = new JPanel();
        JProgressBar bar = new JProgressBar(0,10);
        bar.setValue(vie);
        bar.setStringPainted(true);
        p.add(bar);
        setContentPane(p);
    }

    public void change_vie(test bar){
        int vie = 0;
        while (vie<10){
            vie++;
            bar = new test(vie);
        }
    }

    public static void main(String[] arguments) {
        int vie = 2;
        test HB = new test(vie);
        HB.pack();
        HB.setVisible(true);
        HB.change_vie(HB);
        HB.setVisible(true);
    }
}*/
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class test extends JFrame {

    public JProgressBar progressbar;

    /**
     * Constructeur
     */
    public test() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel pane = new JPanel();
        pane.setLayout(new FlowLayout());
        progressbar = new JProgressBar(0, 100);
        progressbar.setValue(0);
        progressbar.setStringPainted(true);
        pane.add(progressbar);
        setContentPane(pane);
    }

    /**
     * Afficher la progression de tache
     */

    public void loop() {
        int position = 0;
        while (position < 105) {
            progressbar.setValue(position);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            position += 5;
        }
    }

    /**
     * Exemple
     * @param arguments
     */
    public static void main(String[] arguments) {
        test frame = new test();
        frame.pack();
        frame.setVisible(true);
        frame.loop();
    }
}
