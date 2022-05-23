import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Operacijos {
    private ArrayList<Kandidatas> kandidatai;

    public Operacijos(File csvfile) {
        this.kandidatai = new ArrayList<>();
        ArrayList<String> failoFuomenys = readFile(csvfile);
        failoFuomenys.forEach( el -> {
           kandidatai.add(parseLineToKandidatas(el));
        });
    }

    public Operacijos(ArrayList<Kandidatas> kandidatai) {
        this.kandidatai = kandidatai;
    }

    public ArrayList<Kandidatas> filtrasVardasPavarde(String vardas, String pavarde){
        ArrayList<Kandidatas> sortedKandidatai = new ArrayList<>();
        for (Kandidatas kan: this.kandidatai) {
            if(vardas.equals(kan.getVardas()) && pavarde.equals(kan.getPavarde())){
               sortedKandidatai.add(kan);
            }
        }
        return sortedKandidatai;
    }

    public ArrayList<Kandidatas> filtrasVidutinisAtlyginimas(){
        ArrayList<Kandidatas> sortedKandidatai = new ArrayList<>();
        int maxAtlyginimas = 0;
        int minAtlyginimas = Integer.MAX_VALUE;
        for (Kandidatas kan: this.kandidatai) {
            if (maxAtlyginimas < kan.getPageidaujamasAtlyginimas()) {
                maxAtlyginimas = kan.getPageidaujamasAtlyginimas();
            }
            if (minAtlyginimas > kan.getPageidaujamasAtlyginimas()) {
                minAtlyginimas = kan.getPageidaujamasAtlyginimas();
            }
        }
        int skirtumas = (maxAtlyginimas-minAtlyginimas)/2;
        int vidurkis = skirtumas+minAtlyginimas;
        for (Kandidatas kan: this.kandidatai) {
            if(vidurkis > kan.getPageidaujamasAtlyginimas() ){
                sortedKandidatai.add(kan);
            }
        }
        return sortedKandidatai;
    }

    public ArrayList<Kandidatas> filtrasVardasPavardePareigasStaza(String vardas,String pavarde,
                                                                  String pareigos,int stazas){
        ArrayList<Kandidatas> sortedKandidatai = new ArrayList<>();
        for (Kandidatas kan: this.kandidatai) {
            if(vardas.equals(kan.getVardas())&&pavarde.equals(kan.getPavarde())&&
                            pareigos.equals(kan.getPareigos())&&stazas == kan.getProfesinePatirtisMetais()){
                sortedKandidatai.add(kan);
            }
        }
        return sortedKandidatai;
    }

    public Kandidatas parseLineToKandidatas(String line){
        String[] lineArr = line.split(",");
        Kandidatas kandidatas = new Kandidatas();
        if (lineArr.length == 5) {
            kandidatas.setVardas(lineArr[0]);
            kandidatas.setPavarde(lineArr[1]);
            kandidatas.setPareigos(lineArr[2]);
            kandidatas.setProfesinePatirtisMetais(Integer.parseInt(lineArr[3]));
            kandidatas.setPageidaujamasAtlyginimas(Integer.parseInt(lineArr[4]));
        }
        return kandidatas;
    }

    public ArrayList<String> readFile(File csvfile){
        ArrayList<String> duomenys = new ArrayList<String>();
        try {
            Scanner myReader = new Scanner(csvfile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                duomenys.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return duomenys;
    }

    public void addKandidatas(Kandidatas kandidatas){
        kandidatai.add(kandidatas);
    }

    public ArrayList<Kandidatas> getKandidatai() {
        return kandidatai;
    }
}
