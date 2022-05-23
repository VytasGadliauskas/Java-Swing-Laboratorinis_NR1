import java.util.ArrayList;

public class CsvData {
    private ArrayList<String> csvData;
    private String line;

    public CsvData() {
    }

    public CsvData(ArrayList<String> csvData) {
        this.csvData = csvData;
    }

    public ArrayList<String> getCsvData() {
        return csvData;
    }

    public void setCsvData(ArrayList<String> csvData) {
        this.csvData = csvData;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

}
