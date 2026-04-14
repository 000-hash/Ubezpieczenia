import java.util.ArrayList;

public class BiuroUbezpieczen {
    private String nazwa;
    private ArrayList<Polisa> polisy;

    public BiuroUbezpieczen(String nazwa) {
        this.nazwa = nazwa;
        this.polisy = new ArrayList<>();
    }

    public void dodajPolise(Polisa polisa) {
        if(polisa == null) {
            throw new IllegalArgumentException("Polisa nie moze byc null");
        }
        polisy.add(polisa);
    }

    public void wypiszRaport(){
        System.out.println("Raport Biura Ubezpieczen: "+ nazwa);

        if(polisy.isEmpty()) {
            System.out.println("Brak polis w systemie");
            return;
        }
        for(Polisa polisa : polisy) {
            System.out.println("Numer polisy: " + polisa.getNumerPolisy());
            System.out.println("Klient: " + polisa.getKlient());
            System.out.println("Ocena ryzyka: " + polisa.pobierzPodsumowanieRyzyka() );
            System.out.println("Skladka koncowa: " + polisa.obliczSkladkeKoncowa());
            System.out.println("Skladka odnowienia: " + polisa.obliczSkladkeOdnowieniowa());
        }
    }
    public double policzLacznaSkladke(){
        double wynik = 0.0;
        for(Polisa polisa : polisy) {
            wynik += polisa.obliczSkladkeKoncowa();
        }
        return Math.round(wynik*100.0)/100.0;
    }
    public double policzLacznaPrognozeOdnowien(){
        double wynik = 0.0;
        for(Polisa polisa : polisy) {
            wynik += polisa.obliczSkladkeOdnowieniowa();
        }
        return Math.round(wynik*100.0)/100.0;
    }
    public int policzPolisyWysokiegoRyzyka(){
        int wynik = 0;
        for(Polisa polisa : polisy) {
            if(polisa.getPoziomRyzyka() >= 4) {
                wynik++;
            }
        }
        return wynik;

    }
    public Polisa znajdzPoNumerze(String numerPolisy) {
        for(Polisa polisa : polisy) {
            if(polisa.getNumerPolisy().equals(numerPolisy)) {
                return polisa;
            }
        }
        return null;
    }
    public void wypiszTanszeNiz(double prog){
        System.out.println("Tansze niz: " + prog + " : ");
        for(Polisa polisa : polisy) {
            if (polisa.obliczSkladkeKoncowa() < prog) {
                System.out.println(polisa);
            }
        }

    }

}
