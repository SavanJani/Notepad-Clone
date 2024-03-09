import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
//import java.security.Key;


public class Notepad extends JFrame implements ActionListener {

    JTextArea area;
    String text;
    Notepad(){
        setTitle("NotePad");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800,800));
        pack();
        setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("notes2.png"));
        Image img = image.getImage();
        setIconImage(img);

        JMenuBar menubar = new JMenuBar();

        menubar.setBackground(Color.LIGHT_GRAY);
        menubar.setFont(new Font("ARIAL",Font.PLAIN,14));

        JMenu file = new JMenu("File");

        JMenuItem New = new JMenuItem("New");
        New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        New.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
        exit.addActionListener(this);

        file.add(New);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem selectall = new JMenuItem("SelectAll");
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);



        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectall);

        JMenu helpmenu = new JMenu("Help");
        JMenuItem help = new JMenuItem("About");
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        help.addActionListener(this);

        helpmenu.add(help);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(helpmenu);
        setJMenuBar(menubar);

        area = new JTextArea();
        area.setFont(new Font("arial", Font.PLAIN, 18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);

        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("New")){
            area.setText("");
        } else if (ae.getActionCommand().equals("Open")){
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("txt files","txt");
            chooser.addChoosableFileFilter(restrict);
            int action = chooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File file = chooser.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader,null);
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (ae.getActionCommand().equals("Save")){
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");

            int action = saveas.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File filename = new File(saveas.getSelectedFile()+ ".txt");
            BufferedWriter outfile = null;
            try{
                outfile = new BufferedWriter(new FileWriter(filename));
                area.write(outfile);
            }catch(Exception e){
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("Print")){
            try{
                area.print();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if (ae.getActionCommand().equals("Exit")){
            System.exit(0);
        }else if (ae.getActionCommand().equals("Copy")){
            text = area.getSelectedText();
        }

        else if (ae.getActionCommand().equals("Paste")){
            area.insert(text,area.getCaretPosition());
        }else if (ae.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange("",area.getSelectionStart(),area.getSelectionEnd());
        }else if (ae.getActionCommand().equals("SelectAll")){
            area.selectAll();
        }else if (ae.getActionCommand().equals("About")){
            new About().setVisible(true);
        }
    }

}
