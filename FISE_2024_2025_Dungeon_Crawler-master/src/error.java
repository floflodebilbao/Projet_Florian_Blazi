import javax.swing.*;

public class error extends JPanel{

    JProgressBar healthbar;

    public error(int Life){
        healthbar = new JProgressBar(0,10);
        healthbar.setValue(Life);

        setVisible(true);
    }
}




