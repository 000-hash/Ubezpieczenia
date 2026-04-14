import java.util.Objects;

public class Polisa {

    private String numerPolisy;
    private String klient;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;
    private static int liczbaUtworzonychPolis=0;
    private static final double OPLATA_ADMINISTRACYJNA=25;

    public Polisa(String numerPolisy, String klient, double skladkaBazowa,
                  int poziomRyzyka, double wartoscPojazdu, boolean czyMaAlarm, boolean czyBezszkodowyKlient) {
        this.numerPolisy = numerPolisy;
        this.klient = klient;
        this.skladkaBazowa = skladkaBazowa;
        this.poziomRyzyka = poziomRyzyka;
        this.wartoscPojazdu = wartoscPojazdu;
        this.czyMaAlarm = czyMaAlarm;
        this.czyBezszkodowyKlient = czyBezszkodowyKlient;
        liczbaUtworzonychPolis++;
    }

    public String getNumerPolisy() {
        return numerPolisy;
    }

    public String getKlient() {
        return klient;
    }

    public double getSkladkaBazowa() {
        return skladkaBazowa;
    }

    public int getPoziomRyzyka() {
        return poziomRyzyka;
    }

    public double getWartoscPojazdu() {
        return wartoscPojazdu;
    }

    public boolean isCzyMaAlarm() {
        return czyMaAlarm;
    }

    public boolean isCzyBezszkodowyKlient() {
        return czyBezszkodowyKlient;
    }

    public String pobierzPodsumowanieRyzyka(){
        if(poziomRyzyka<=2){
            return " Polisa "+ numerPolisy +": small_risk ";
        }else if(poziomRyzyka==3){
            return " Polisa "+ numerPolisy +": medium_risk ";
        }else {
            return " Polisa "+ numerPolisy +": large_risk ";
        }

    }

    public double obliczSkladkeKoncowa() {
        double skladka_koncowa = skladkaBazowa + OPLATA_ADMINISTRACYJNA;

        skladka_koncowa += poziomRyzyka * 120;
        if (wartoscPojazdu > 7000) {
            skladka_koncowa += 100;
        }

        if (czyMaAlarm) {
            skladka_koncowa -= 100;
        }

        if (czyBezszkodowyKlient) {
            skladka_koncowa *= 0.95;
        }
        double minimal_skladka = Math.max(skladkaBazowa, 500);
        if (skladka_koncowa < minimal_skladka) {
            skladka_koncowa = minimal_skladka;
        }

        return round_number(skladka_koncowa);


    }
    private double round_number(double skladka_koncowa){
       return Math.round(skladka_koncowa*100.0) / 100.0;
    }


    public double obliczSkladkeOdnowieniowa(){
        double current_skladka = obliczSkladkeKoncowa();
        double renewed_skladka = current_skladka;

        if(poziomRyzyka == 4){
            renewed_skladka *= 1.10;
        } else if(poziomRyzyka >= 5){
            renewed_skladka *= 1.20;
        }
        if(wartoscPojazdu > 7000){
            renewed_skladka += 150;
        }
        if(czyBezszkodowyKlient){
            renewed_skladka *= 0.92;
        }
        if(czyMaAlarm){
            renewed_skladka *= 0.95;
        }
        double minimum = current_skladka*0.90;
        double maximum = current_skladka*1.25;

        if(renewed_skladka < minimum){
            renewed_skladka = minimum;
        }
        if(renewed_skladka > maximum){
            renewed_skladka = maximum;
        }
        return round_number(renewed_skladka);
    }
    public static int pobierzLiczbeUtworzonychPolis(){
        return liczbaUtworzonychPolis;
    }

    @Override
    public String toString() {
        return "Polisa{" +
                "numerPolisy='" + numerPolisy + '\'' +
                ", klient='" + klient + '\'' +
                ", skladkaBazowa=" + skladkaBazowa +
                ", poziomRyzyka=" + poziomRyzyka +
                ", wartoscPojazdu=" + wartoscPojazdu +
                ", czyMaAlarm=" + czyMaAlarm +
                ", czyBezszkodowyKlient=" + czyBezszkodowyKlient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Polisa polisa = (Polisa) o;
        return Objects.equals(numerPolisy, polisa.numerPolisy);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numerPolisy);
    }
}
