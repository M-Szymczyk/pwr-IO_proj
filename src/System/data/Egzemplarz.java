package System.data;

public class Egzemplarz {
    private StanSprzetu stan_egzemplarza;
    private Integer numer_seryjny;
    private Model model;


    public Egzemplarz(StanSprzetu stan_egzemplarza, Integer numer_seryjny, Model model) {
        this.stan_egzemplarza = stan_egzemplarza;
        this.numer_seryjny = numer_seryjny;
        this.model = model;
    }
    public void zmienStanSprzetu(StanSprzetu stan_egzemplarza){
        this.stan_egzemplarza=stan_egzemplarza;
    }


    public StanSprzetu getStan_egzemplarza() {
        return stan_egzemplarza;
    }
    public Integer getNumer_seryjny() {
        return numer_seryjny;
    }

    public Model getModel() {
        return model;
    }
}
