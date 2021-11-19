package com.company;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class Editor extends JFrame implements ActionListener, DocumentListener {


    public static  void main(String[] args) {
        new Editor();
    }

    public JEditorPane TP;//Text Panel
    private JMenuBar menu;//Menu
    public boolean changed = false;
    private File file;

    //constructor to create app Screen
    public Editor() {
        //Editor the name of our application
        super("Editor");
        TP = new JEditorPane();
        // center means middle of container.
        add(new JScrollPane(TP), "Center");
        TP.getDocument().addDocumentListener(this);

        menu = new JMenuBar();
        setJMenuBar(menu);
        BuildMenu();
        //The size of window
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void BuildMenu() {
        buildFileMenu();
        buildEditMenu();

    }

    //to add edit option like Cut Copy.. , file option like New Open ..
    private void optionMenu(JMenu file,String process,char mnemonic , int VK) {

        JMenuItem action = new JMenuItem(process);
        if(mnemonic != ' '){
            action.setMnemonic(mnemonic);
            action.setAccelerator(KeyStroke.getKeyStroke(VK, InputEvent.CTRL_DOWN_MASK));

        }
        if(mnemonic == ' ')
            action.setAccelerator(KeyStroke.getKeyStroke(VK, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));

        action.addActionListener(this);
        file.add(action);
    }

    //to build File Menu
    private void buildFileMenu() {
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        menu.add(file);

        optionMenu( file,"New",'N',KeyEvent.VK_N);
        optionMenu( file,"Open",'O',KeyEvent.VK_O);
        optionMenu( file,"Save",'S',KeyEvent.VK_S);
        optionMenu( file,"Save as...",' ',KeyEvent.VK_S);
        optionMenu( file,"Quit",'Q',KeyEvent.VK_Q);

    }

    //to build Edit Menu
    private void buildEditMenu() {
        JMenu edit = new JMenu("Edit");
        menu.add(edit);
        edit.setMnemonic('E');
        optionMenu( edit,"Cut",'T',KeyEvent.VK_X);
        optionMenu(edit,"Copy",'C',KeyEvent.VK_C);
        optionMenu(edit,"Paste",'P',KeyEvent.VK_V);
        optionMenu(edit,"Find",'F',KeyEvent.VK_F);
        optionMenu(edit,"Select All",'A',KeyEvent.VK_A);
    }


    private void saveFile(){
        // 0 means yes and no option, 2 Used for warning messages.
        int ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file",
                0, 2);
        //1 value from class method if NO is chosen.
        if (ans == 1)
            return;

        if (file == null) {
            saveAs("Save");
            return;
        }
        String text = TP.getText();
        System.out.println(text);
        try (PrintWriter writer = new PrintWriter(file);){
            if (!file.canWrite())
                throw new Exception("Cannot write file!");
            writer.write(text);
            changed = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        boolean isQuit = action.equals("Quit");
        boolean isOpen = action.equals("Open");
        boolean isSave = action.equals("Save");
        boolean isNew = action.equals("New");

        boolean isSaveAs = action.equals("Save as...");
        boolean isSelectAll = action.equals("Select All");
        boolean isCopy = action.equals("Copy");
        boolean isCut = action.equals("Cut");
        boolean isPast = action.equals("Paste");
        boolean isFind = action.equals("Find");

        if (isQuit) {
            System.exit(0);
        }
        if (isOpen) {
            loadFile();
        }
        if (isSave && changed) {
            //Save file
            saveFile();
        }
        if (isNew && changed) {
            //Save file
            saveFile();
        }
        if (isNew) {
            //New file
            file = null;
            TP.setText("");
            changed = false;
            setTitle("Editor");
        }
        if (isSaveAs) {
            saveAs("Save as...");
        }
        if (isSelectAll) {
            TP.selectAll();
        }
        if (isCopy) {
            TP.copy();
        }
        if (isCut) {
            TP.cut();
        }
        if (isPast) {
            TP.paste();
        }
        if (isFind) {
            FindDialog find = new FindDialog(this, true);
            find.showDialog();
        }
    }

    private void readFile() {
        StringBuilder rs = new StringBuilder();
        try (
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);) {
            String line;
            while ((line = reader.readLine()) != null) {
                rs.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot read file !", "Error !", 0);//0 means show Error Dialog
        }
        TP.setText(rs.toString());
        changed = false;
        setTitle("Editor - " + file.getName());
    }
    private void loadFile() {
        JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
        dialog.setMultiSelectionEnabled(false);
        try {
            int result = dialog.showOpenDialog(this);

            if (result == 1)//1 value if cancel is chosen.
                return;

            if (changed && result == 0){
                //Save file
                saveFile();
            }

            if (result == 0) {// value if approve (yes, ok) is chosen.

                file = dialog.getSelectedFile();
                //Read file
                readFile();

            }
        } catch (Exception e) {
            e.printStackTrace();
            //0 means show Error Dialog
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
    }

    private void saveAs(String dialogTitle) {
        JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
        dialog.setDialogTitle(dialogTitle);
        int result = dialog.showSaveDialog(this);
        if (result != 0)//0 value if approve (yes, ok) is chosen.
            return;
        file = dialog.getSelectedFile();
        try (PrintWriter writer = new PrintWriter(file);){
            writer.write(TP.getText());
            changed = false;
            setTitle("Editor - " + file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changed = true;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changed = true;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        changed = true;
    }

}