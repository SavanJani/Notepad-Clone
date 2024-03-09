import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class About extends JFrame implements ActionListener {
        About(){
            setBounds(400,100,600,500);
            setLayout(null);
            ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("windows.png"));
            Image i2 = i.getImage().getScaledInstance(300,60, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel headericon = new JLabel(i3);
            headericon.setBounds(80,40,400,80);
            add(headericon);

            ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("notes2.png"));
            Image i5 = i4.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
            ImageIcon i6 = new ImageIcon(i5);
            JLabel icon = new JLabel(i6);
            icon.setBounds(30,150,70,70);
            add(icon);

            JLabel text = new JLabel("<html>NotePad<br>Version 0.1.0 (OS Build Java)<br>Made By Savan Jani. All Rights Reserved </html>");
            text.setBounds(100,50,500,300);
            text.setFont(new Font("SANS_SERIF", Font.PLAIN,16));
            add(text);

            JButton button = new JButton("OK");
            button.setBounds(230,300,120,25);
            button.addActionListener(this);
            add(button);

            setVisible(true);
        }
        public void actionPerformed(ActionEvent ae){
            this.setVisible(false);
        }
}
