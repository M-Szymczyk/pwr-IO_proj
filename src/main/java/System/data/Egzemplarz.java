package System.data;

/**
 * Klasa przechowuje danoe o pojedynczym sprzecie wykorzystywanym w wypozyczalni
 */
public class Egzemplarz {
    private StanSprzetu stan_egzemplarza;
    private Integer numer_seryjny;//todo zastanowisc sie czy nie powienien tu byc string
    private Model model;


    /**
     * Konstruktor egzemplarza
     * @param stan_egzemplarza obecny stan egzemplarza
     * @param numer_seryjny numer seryjny sprzetu
     * @param model jaki jest model sprzetu
     */
    public Egzemplarz(StanSprzetu stan_egzemplarza, Integer numer_seryjny, Model model) {
        this.stan_egzemplarza = stan_egzemplarza;
        this.numer_seryjny = numer_seryjny;
        this.model = model;
    }

    /**
     * aktualizacja stanu sprzetu
     * jezeli podany argument to null, nie zostanie zmieniony stan
     * @param stan_egzemplarza nowy stan
     */
    public void zmienStanSprzetu(StanSprzetu stan_egzemplarza) throws Exception {
        if(stan_egzemplarza==null)
            throw new Exception("Podano nulla");
        this.stan_egzemplarza=stan_egzemplarza;
    }

    /**
     * Zwraca obecny stan egzemplarza
     * @return obecny stan egzemplarza
     */
    public StanSprzetu getStan_egzemplarza() {
        return stan_egzemplarza;
    }

    /**
     * @return zwraca numer seryjny egzemplarza
     */
    public Integer getNumer_seryjny() {
        return numer_seryjny;
    }

    /**
     * @return model egzemplarza
     */
    public Model getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Egzemplarz)) return false;

        Egzemplarz that = (Egzemplarz) o;

        if (getStan_egzemplarza() != that.getStan_egzemplarza()) return false;
        if (!getNumer_seryjny().equals(that.getNumer_seryjny())) return false;
        return getModel().equals(that.getModel());
    }

    @Override
    public int hashCode() {
        int result = getStan_egzemplarza().hashCode();
        result = 31 * result + getNumer_seryjny().hashCode();
        result = 31 * result + getModel().hashCode();
        return result;
    }


}