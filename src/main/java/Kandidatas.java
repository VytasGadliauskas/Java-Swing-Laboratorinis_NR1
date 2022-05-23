public class Kandidatas {
     private String vardas, pavarde, pareigos;
     private int profesinePatirtisMetais;
     private int pageidaujamasAtlyginimas;

     public Kandidatas(String vardas, String pavarde, String pareigos, int profesinePatirtisMetais, int pageidaujamasAtlyginimas) {
          this.vardas = vardas;
          this.pavarde = pavarde;
          this.pareigos = pareigos;
          this.profesinePatirtisMetais = profesinePatirtisMetais;
          this.pageidaujamasAtlyginimas = pageidaujamasAtlyginimas;
     }

     public Kandidatas() {
     }

     public String getVardas() {
          return vardas;
     }

     public void setVardas(String vardas) {
          this.vardas = vardas;
     }

     public String getPavarde() {
          return pavarde;
     }

     public void setPavarde(String pavarde) {
          this.pavarde = pavarde;
     }

     public String getPareigos() {
          return pareigos;
     }

     public void setPareigos(String pareigos) {
          this.pareigos = pareigos;
     }

     public int getProfesinePatirtisMetais() {
          return profesinePatirtisMetais;
     }

     public void setProfesinePatirtisMetais(int profesinePatirtisMetais) {
          this.profesinePatirtisMetais = profesinePatirtisMetais;
     }

     public int getPageidaujamasAtlyginimas() {
          return pageidaujamasAtlyginimas;
     }

     public void setPageidaujamasAtlyginimas(int pageidaujamasAtlyginimas) {
          this.pageidaujamasAtlyginimas = pageidaujamasAtlyginimas;
     }


}
