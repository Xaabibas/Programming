package moduls;

public enum EyeColor {
    BLACK,
    YELLOW,
    ORANGE,
    WHITE,
    BROWN;

    public static void show() {
        int cnt = 1;
        for (EyeColor color : EyeColor.values()) {
            System.out.println(cnt++ + ") " + color);
        }
    }
}
