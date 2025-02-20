package moduls;

public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;

    public static void show() {
        int cnt = 1;
        System.out.println("Доступные типы:");
        for (TicketType type : TicketType.values()) {
            System.out.println(cnt++ + ") " + type);
        }
    }
}