package System.Users;

import System.data.Egzemplarz;
import System.data.Kategoria;
import System.data.Model;
import System.data.StanSprzetu;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;


class KlientTest {

    @Test
    void zglosZgubienieZniszczenia() {
    }

    @Test
    void wydluzWypozyczenie() {
    }

    @Test
    void wypozyczSprzet() {
        Kategoria kategoria=new Kategoria("testKat","");
        Model model=new Model("tsetModel",kategoria);
        ArrayList<Egzemplarz> egzemplarzArrayList=new ArrayList<>();
        for(int i=0;i<5;i++)
            egzemplarzArrayList.add(new Egzemplarz(StanSprzetu.DOSTEPNY,i,model));
        model.setEgzemplarze(egzemplarzArrayList);


        Klient klient=new Klient("mikolaj","szymczyk","admin","admin","admin@admin.pl",TypUzytkownika.KLIENT,1);
        try {
            klient.wypozyczSprzet(model,new Date(1),new Date(15),5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void wyszukajModel() {
    }
}