package moduls;

public enum Country {
    CHINA,
    SOUTH_KOREA,
    JAPAN;

    public static void show() {
        int cnt = 1;
        for (Country country : Country.values()) {
            System.out.println(cnt++ + ") " + country);
        }
    }
}