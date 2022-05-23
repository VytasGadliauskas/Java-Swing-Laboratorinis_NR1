import javax.swing.*;
import java.awt.event.*;

public class AddForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textVardas;
    private JTextField textPavarde;
    private JTextField textPareigos;
    private JTextField textStazas;
    private JTextField textAtlygimas;

    public AddForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here if necessary
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddForm dialog = new AddForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public String getTextVardas(){
        return  textVardas.getText();
    }

    public String getTextPavarde(){
        return  textPavarde.getText();
    }

    public String getTextPareigos(){
        return  textPareigos.getText();
    }
    public String getTextStazas(){
        return  textStazas.getText();
    }
    public String getTextAlga(){
        return  textAtlygimas.getText();
    }

}
