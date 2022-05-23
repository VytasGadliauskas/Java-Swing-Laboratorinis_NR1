import javax.swing.*;
import java.awt.event.*;

public class FilterForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textVardas;
    private JTextField textPavarde;
    private JTextField textPareigos;
    private JTextField textStazas;
    private JComboBox comboBoxFiltroTipas;

    public FilterForm() {
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
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOK() {
        // add your code here
         dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        FilterForm dialog = new FilterForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public String getVardas(){
        return textVardas.getText();
    }

    public String getPavarde(){
        return textPavarde.getText();
    }

    public String getPareigos(){
        return textPareigos.getText();
    }

    public int getStazas(){
        if (!textStazas.getText().equals("")) {
            return Integer.parseInt(textStazas.getText());
        } else {
            return 0;
        }
    }

   public int getComboBoxFitroTipas(){
       return comboBoxFiltroTipas.getSelectedIndex();
   }

}
