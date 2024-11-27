import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class loading_screen {
    JFrame loading_screen;

    public loading_screen(){
        loading_screen = new JFrame("Bienvenue à toi t'es prêt à te faire soigner en bien ou pas");
        loading_screen.setSize(400,600);
        loading_screen.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton startButton = new JButton("Commencer");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setFocusPainted(false);
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.BLACK);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        loading_screen.add(startButton);
    }
}

