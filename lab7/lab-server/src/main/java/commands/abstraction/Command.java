package commands.abstraction;

import managers.CollectionManager;

/**
 * Абстрактный класс команды
 */
public abstract class Command implements Executable, Describable {
    private final CollectionManager cm;

    /**
     * @param cm - менеджер коллекции
     */
    public Command(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * @return возвращает менеджер коллекции
     */
    public CollectionManager getCm() {
        return cm;
    }

}
