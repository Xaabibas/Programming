package moduls;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс билетов
 */
public class Ticket implements Comparable<Ticket>, Serializable {
    final private static long serialVersionUID = 15L;
    /**
     * Идентификационный номер, значение поля уникально, больше 0, генерируется автоматически
     */
    private long id;
    /**
     * Имя на билете, не может быть null, строка не может быть пуста
     */
    private String name;
    /**
     * Координаты, не может быть null
     */
    private Coordinates coordinates;
    /**
     * Дата создания, не может быть null, значение генерируется автоматически
     */
    private LocalDateTime creationDate;
    /**
     * Цена, больше 0
     */
    private float price;
    /**
     * Тип, может быть null
     */
    private TicketType type;
    /**
     * Личность, может быть null
     */
    private Person person;

    /**
     * Счетчик билетов для генерации поля id
     */
    private static long cnt = 1;

    /**
     * Конструктор
     */
    public Ticket() {
        this.id = cnt++;
        this.creationDate = LocalDateTime.now();
    }

    /**
     * @return возвращает значение поля type
     */
    public TicketType getType() {
        return type;
    }

    /**
     * Устанавливает значение поля type
     *
     * @param type - значение поля type
     */
    public void setType(TicketType type) {
        this.type = type;
    }

    /**
     * @return возвращает значение поля price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Устанавливает значение поля price
     *
     * @param price - значение поля price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return возвращает значение поля name
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает значение поля name
     *
     * @param name - значение поля name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return возвращает значение поля creationTime
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Устанавливает значение поля creationDate
     *
     * @param creationDate - значение поля creationDate
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return возвращает значение поля coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Устанавливает значение поля coordinates
     *
     * @param coordinates - значение поля coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return возвращает значение поля person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Устанавливает значение поля person
     *
     * @param person - значение поля person
     */
    public void setPerson(Person person) {
        this.person = person;
    }


    /**
     * Устанавливает значение поля id
     */
    public void setId() {
        this.id = cnt++;
    }

    /**
     * Переопределение метода определения эквивалентности объектов
     *
     * @param o - сравниваемый объект
     * @return возвращает true если o эквивалентен и false - в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this==o) {
            return true;
        }
        if (o==null || getClass()!=o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Float.compare(price, ticket.price)==0 && name.equals(ticket.name) && coordinates.equals(ticket.coordinates) && type==ticket.type && person.equals(ticket.person);
    }

    /**
     * @return возвращает хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, price, type, person);
    }

    /**
     * @return возвращает объект, приведенный к строковому виду
     */
    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", name='" + name + '\'' + ", coordinates=" + coordinates.toString() + ", creationDate=" + creationDate.toString() + ", price=" + price + ", type=" + type + ", person=" + person + '}';
    }

    /**
     * Задает естественный порядок сравнения
     *
     * @param o the object to be compared.
     * @return возвращает разность полей id
     */
    @Override
    public int compareTo(Ticket o) {
        return (int) (this.price - o.price);
    }
}