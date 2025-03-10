package moduls;

/**
 * Класс-перечисление цветов волос
 */
public enum HairColor {
    RED,
    YELLOW,
    WHITE,
    BROWN;

    /**
     * Выводит пронумерованный список всех цветов волос
     */
    public static void show() {
        int cnt = 1;
        for (HairColor color : HairColor.values()) {
            System.out.println(cnt++ + ") " + color);
        }
    }
}