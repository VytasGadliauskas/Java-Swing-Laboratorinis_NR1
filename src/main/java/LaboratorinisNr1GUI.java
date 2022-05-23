import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class LaboratorinisNr1GUI extends JFrame {
    private JPanel Main;
    private JButton buttonLoadCsv;
    private JButton buttonAddLine;
    private JButton buttonDeleteLine;
    private JTable table1;
    private JButton buttonFilters;
    private JButton buttonAbout;
    private JLabel labelStatus;
    private JButton buttonSaveFile;
    private JButton buttonStats;
    private JButton buttonPrint;

    private JScrollPane scrollPane;

    private File csvfile;

    private static LaboratorinisNr1GUI laboratorinisNr1GUI;

    private DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();

    private ArrayList<Kandidatas> kandidatai = new ArrayList<>();

    public LaboratorinisNr1GUI() {

        tableModel.addColumn("Vardas");
        tableModel.addColumn("Pavarde");
        tableModel.addColumn("Pareigos");
        tableModel.addColumn("Stazas");
        tableModel.addColumn("Atlyginimas");

        buttonLoadCsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(laboratorinisNr1GUI);
                if (result == JFileChooser.APPROVE_OPTION) {
                    csvfile = fileChooser.getSelectedFile();
                    Operacijos operacijos = new Operacijos(csvfile);
                    kandidatai = operacijos.getKandidatai();
                    refreshTable(kandidatai);
                }
            }
        });
        buttonAddLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddForm addForm = new AddForm();
                addForm.setTitle("Laboratorinis Nr.:1 Prideti irasa");
                addForm.setModal(true);
                addForm.pack();
                addForm.setLocationRelativeTo(laboratorinisNr1GUI);
                addForm.setVisible(true);
                if (!addForm.getTextVardas().equals("")) {
                    Kandidatas kandidatas = new Kandidatas(addForm.getTextVardas(), addForm.getTextPavarde(),
                            addForm.getTextPareigos(), Integer.parseInt(addForm.getTextStazas()),
                            Integer.parseInt(addForm.getTextAlga()));
                    addKandidatas(kandidatas);
                }
            }
        });
        buttonDeleteLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() != -1) {
                    int row = table1.getSelectedRow();
                    String vardas = table1.getModel().getValueAt(row, 0).toString();
                    String pavarde = table1.getModel().getValueAt(row, 1).toString();
                    removeKandidatas(vardas, pavarde);
                }
            }
        });
        buttonFilters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterForm filterForm = new FilterForm();
                filterForm.setTitle("Laboratorinis Nr.:1");
                filterForm.setModal(true);
                filterForm.pack();
                filterForm.setLocationRelativeTo(laboratorinisNr1GUI);
                filterForm.setVisible(true);
                filtraiKandidatu(filterForm.getComboBoxFitroTipas(), filterForm.getVardas(),
                        filterForm.getPavarde(), filterForm.getPareigos(), filterForm.getStazas());
            }
        });
        buttonAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutForm aboutForm = new AboutForm();
                aboutForm.setTitle("Laboratorinis Nr.:1");
                aboutForm.setModal(true);
                aboutForm.pack();
                aboutForm.setLocationRelativeTo(laboratorinisNr1GUI);
                aboutForm.setVisible(true);
            }
        });
        buttonSaveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrintWriter os = null;
                if (csvfile != null) {
                    int reply = JOptionPane.showConfirmDialog(
                            null,
                            "Failas " + csvfile.getName() + " bus perasytas",
                            "Demesio !!!",
                            JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        try {
                            os = new PrintWriter(csvfile);
                            for (int row = 0; row < table1.getRowCount(); row++) {
                                for (int col = 0; col < table1.getColumnCount(); col++) {
                                    os.print(table1.getValueAt(row, col));
                                    os.print(",");
                                }
                                os.println();
                            }
                            os.close();
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                }
            }
        });
        buttonStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatisticForm statisticForm = new StatisticForm();
                statisticForm.setTitle("Laboratorinis Nr.:1 Statistikos");
                statisticForm.setModal(true);
               // statisticForm.
                statisticForm.pack();
                statisticForm.setLocationRelativeTo(laboratorinisNr1GUI);
                statisticForm.setVisible(true);
            }
        });
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean print = table1.print();
                    if (!print) {
                        JOptionPane.showMessageDialog(null, "Unable To Print !!..");
                    }
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

        });
    }

    public void refreshTable(ArrayList<Kandidatas> kandidatai) {
        tableModel.setRowCount(0);
        labelStatus.setText("Statusas: rasta " + kandidatai.size() + " irasu ");
        kandidatai.forEach(el -> {
            tableModel.addRow(new Object[]{el.getVardas(), el.getPavarde(), el.getPareigos(),
                    el.getProfesinePatirtisMetais(), el.getPageidaujamasAtlyginimas()});
        });
        labelStatus.setText("Statusas: rasta " + this.kandidatai.size() + " irasu ");
    }

    public void addKandidatas(Kandidatas kandidatas) {
        this.kandidatai.add(kandidatas);
        refreshTable(this.kandidatai);
    }

    public void removeKandidatas(String vardas, String pavarde) {
        Kandidatas kandidatasToRemove = null;
        for (Kandidatas kan : kandidatai) {
            if (vardas.equals(kan.getVardas()) && pavarde.equals(kan.getPavarde())) {
                kandidatasToRemove = kan;
            }
        }
        this.kandidatai.remove(kandidatasToRemove);
        refreshTable(this.kandidatai);
    }

    public void filtraiKandidatu(int filtras, String vardas, String pavarde,
                                 String pareigos, int stazas) {

        Operacijos operacijos = new Operacijos(this.kandidatai);
        ArrayList<Kandidatas> sortedKandidatai;
        switch (filtras) {
            case 0:
                if (!vardas.equals("") && !pavarde.equals("")) {
                    sortedKandidatai = operacijos.filtrasVardasPavarde(vardas, pavarde);
                    refreshTable(sortedKandidatai);
                    labelStatus.setText("Statusas: pagal filtra [vardas,pavarde] rasta " +
                            sortedKandidatai.size() + " irasu ");
                }
                break;
            case 1:
                sortedKandidatai = operacijos.filtrasVidutinisAtlyginimas();
                refreshTable(sortedKandidatai);
                labelStatus.setText("Statusas: pagal filtra [maziau nei vidutinis atlyginimas] rasta " +
                        sortedKandidatai.size() + " irasu ");
                break;
            case 2:
                if (!vardas.equals("") && !pavarde.equals("")&&!pareigos.equals("")&&stazas>0) {
                    sortedKandidatai = operacijos.filtrasVardasPavardePareigasStaza(vardas, pavarde,
                            pareigos,stazas);
                    refreshTable(sortedKandidatai);
                    labelStatus.setText("Statusas: pagal filtra [vardas,pavarde,pareigos,stazas] rasta " +
                            sortedKandidatai.size() + " irasu ");
                }
                break;
            default:
                refreshTable(this.kandidatai);
        }

    }

    public static void main(String[] args) {
        laboratorinisNr1GUI = new LaboratorinisNr1GUI();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        laboratorinisNr1GUI.setSize(width/2, height/2);
        laboratorinisNr1GUI.setTitle("Laboratorinis Nr.:1");
        laboratorinisNr1GUI.setContentPane(new LaboratorinisNr1GUI().Main);
        laboratorinisNr1GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        laboratorinisNr1GUI.setPreferredSize(new Dimension(640, 400));
        laboratorinisNr1GUI.setVisible(true);
        laboratorinisNr1GUI.setLocationRelativeTo(null);
        laboratorinisNr1GUI.pack();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
