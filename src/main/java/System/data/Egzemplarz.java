package System.data;

/**
 * Klasa przechowuje danoe o pojedynczym sprzecie wykorzystywanym w wypozyczalni
 */
public class Egzemplarz {

    private StanSprzetu stan_egzemplarza;
    private String numer_seryjny;
    private final Model model;

    /**
     * Konstruktor egzemplarza
     *
     * @param stan_egzemplarza obecny stan egzemplarza
     * @param model            jaki jest model sprzetu
     */
    public Egzemplarz(StanSprzetu stan_egzemplarza, Model model) {
        long nr_seryjny_cnt = 0;
        ++nr_seryjny_cnt;
        this.stan_egzemplarza = stan_egzemplarza;
        this.numer_seryjny = String.valueOf(nr_seryjny_cnt);
        int nrlen = 8;
        if (this.numer_seryjny.length() < nrlen) {
            for (int i = 0; i < nrlen - this.numer_seryjny.length(); i++) {
                this.numer_seryjny = "0" + this.numer_seryjny;
            }
        }
        this.model = model;
    }

    /**
     * aktualizacja stanu sprzetu
     *
     * @param stan_egzemplarza nowy stan
     * @throws Exception jezeli podany argument to null
     */
    public void zmienStanSprzetu(StanSprzetu stan_egzemplarza) throws Exception {
        if (stan_egzemplarza == null)
            throw new Exception("Podano nulla");
        this.stan_egzemplarza = stan_egzemplarza;
        if (this.stan_egzemplarza.equals(StanSprzetu.NIEDOSTEPNY))
            model.setIlosDostepnychEgzemplarzy(model.getIlosDostepnychEgzemplarzy() - 1);
        else if (this.stan_egzemplarza.equals(StanSprzetu.DOSTEPNY))
            model.setIlosDostepnychEgzemplarzy(model.getIlosDostepnychEgzemplarzy() + 1);

    }

    /**
     * Zwraca obecny stan egzemplarza
     *
     * @return obecny stan egzemplarza
     */
    public StanSprzetu getStan_egzemplarza() {
        return stan_egzemplarza;
    }

    /**
     * @return zwraca numer seryjny egzemplarza
     */
    public String getNumer_seryjny() {
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
