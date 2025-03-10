package moduls;

/**
 * Класс-перечисление стран
 */
public enum Country {
    CHINA,
    SOUTH_KOREA,
    JAPAN;

    /**
     * Выводит пронумерованный список всех (выбранных) стран
     */
    public static void show() {
        int cnt = 1;
        for (Country country : Country.values()) {
            System.out.println(cnt++ + ") " + country);
        }
    }
}