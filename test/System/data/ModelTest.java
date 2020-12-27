package System.data;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;

public class ModelTest {
    Model mod;
    Kategoria kat;
    ArrayList<Egzemplarz> arrayList;
    Egzemplarz e1,e2;
    @Before
    public void setUp(){
        kat=new Kategoria("kat","");
        mod=new Model("mod",kat);
        arrayList=new ArrayList<>();
        e1=new Egzemplarz(StanSprzetu.DOSTEPNY,mod);
        e2=new Egzemplarz(StanSprzetu.DOSTEPNY,mod);
        arrayList.add(e1);
        arrayList.add(e2);
        mod.setEgzemplarze(arrayList);
    }

    @Test
    public void usunEgzemplarz() {
        int size = arrayList.size();
        mod.usunEgzemplarz(1);
//        assertThat(mod.getEgzemplarze(), hasItems(e2));
        assertEquals(size-1,mod.getEgzemplarze().size());
    }

    @Test
    public void setEgzemplarze() {
        ArrayList<Egzemplarz> arrayList1=new ArrayList<>();
        arrayList1.add(new Egzemplarz(StanSprzetu.NIEDOSTEPNY,mod));
        mod.setEgzemplarze(arrayList1);
        assertEquals(arrayList1,mod.getEgzemplarze());

    }
    @Test(expected = Exception.class)
    public void setEgzemplarze2(){
        Model mod1=new Model("mod1",kat);
        ArrayList<Egzemplarz> arrayList2=new ArrayList<>();
        arrayList2.add(new Egzemplarz(StanSprzetu.USZKODZONY,mod1));
        mod.setEgzemplarze(arrayList2);
        //todo wydaje mi sie ze powinno wyzucic blad jak chcesz przypisac egzemplarz innego modelu

    }
}