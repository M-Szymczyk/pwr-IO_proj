package System.data;

import sun.util.calendar.BaseCalendar;

import java.util.ArrayList;
import java.util.Date;

public class Wypozyczenie {
    private Integer id_wypozyczajacego;
    private Date data_wypozyczenia;
    private Date data_zwrotu;
    private Double koszt_wypozyczenia;
    ArrayList<Egzemplarz> egzemplarze;



    public void wydluzWypozyczenie(Date nowaData){
        setData_zwrotu(nowaData);
    }

    public void naliczDodatkowaOplate(Double oplata){
        setKoszt_wypozyczenia(getKoszt_wypozyczenia()+oplata);
    }

    public Wypozyczenie(Integer id_wypozyczajacego, Date data_wypozyczenia, Date data_zwrotu, Model m,Integer ilosc) throws Exception {
        this.id_wypozyczajacego = id_wypozyczajacego;
        this.data_wypozyczenia = data_wypozyczenia;
        this.data_zwrotu = data_zwrotu;
        this.egzemplarze = new ArrayList<>();
        int dodano=0;
        for (Egzemplarz e : m.getEgzemplarze())

            if (e.getStan_egzemplarza().equals(StanSprzetu.DOSTEPNY)) {
                this.egzemplarze.add(e);
                dodano++;
                if(ilosc>dodano)
                    return;
            }

        throw new Exception("Brak dostepnych egzemplarzy");
    }

    public void setData_zwrotu(Date data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }

    public void setKoszt_wypozyczenia(Double koszt_wypozyczenia) {
        this.koszt_wypozyczenia = koszt_wypozyczenia;
    }

    public Integer getId_wypozyczajacego() {
        return id_wypozyczajacego;
    }

    public Date getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public Date getData_zwrotu() {
        return data_zwrotu;
    }

    public Double getKoszt_wypozyczenia() {
        return koszt_wypozyczenia;
    }

    public ArrayList<Egzemplarz> getEgzemplarze() {
        return egzemplarze;
    }
}
