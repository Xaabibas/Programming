package moduls;

public enum HairColor {
    RED,
    YELLOW,
    WHITE,
    BROWN;

    public static void show() {
        int cnt = 1;
        for (HairColor color : HairColor.values()) {
            System.out.println(cnt++ + ") " + color);
        }
    }
}