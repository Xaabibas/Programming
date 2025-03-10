package moduls;

/**
 * Класс-перечисление цветов глаз
 */
public enum EyeColor {
    BLACK,
    YELLOW,
    ORANGE,
    WHITE,
    BROWN;

    /**
     * Выводит пронумерованный список всех цветов глаз
     */
    public static void show() {
        int cnt = 1;
        for (EyeColor color : EyeColor.values()) {
            System.out.println(cnt++ + ") " + color);
        }
    }
}
