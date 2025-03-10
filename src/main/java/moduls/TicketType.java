package moduls;

/**
 * Класс-перечисление типов билета
 */
public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;

    /**
     * Выводит пронумерованный список всех типов билетов
     */
    public static void show() {
        int cnt = 1;
        System.out.println("Доступные типы:");
        for (TicketType type : TicketType.values()) {
            System.out.println(cnt++ + ") " + type);
        }
    }
}