package moduls;

import java.util.Objects;

/**
 * Класс координат
 */
public class Coordinates {
    /**
     * Координата x, значение поля должно быть больше -626, не может быть null
     */
    private Float x;
    /**
     * Координата y, не может быть null
     */
    private Long y;

    /**
     * @param x - координата x
     * @param y - координата y
     */
    public Coordinates(Float x, Long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Присвоение координатам значение 0
     */
    public Coordinates() {
        this.x = 0F;
        this.y = 0L;
    }

    /**
     * @return возвращает координату x
     */
    public Float getX() {
        return x;
    }

    /**
     * Устанавливает координату x
     *
     * @param x - координата x
     */
    public void setX(Float x) {
        this.x = x;
    }

    /**
     * @return возвращает координату y
     */
    public Long getY() {
        return y;
    }

    /**
     * Устанавливает координату y
     *
     * @param y - координате y
     */
    public void setY(Long y) {
        this.y = y;
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
        Coordinates that = (Coordinates) o;
        return Float.compare(x, that.x)==0 && Long.compare(y, that.y)==0;
    }

    /**
     * @return возвращает хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * @return возвращает объект, приведенный к строковому виду
     */
    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }
}