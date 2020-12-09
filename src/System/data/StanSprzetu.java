package System.data;

public enum StanSprzetu {
    DOSTEPNY(1),
    NIEDOSTEPNY(0),
    USZKODZONY(2);

    Integer stan;
    StanSprzetu(int i) {
        stan=i;
    }

    @Override
    public String toString() {
        return "StanSprzetu{" +
                "stan=" + stan +
                '}';
    }
}
