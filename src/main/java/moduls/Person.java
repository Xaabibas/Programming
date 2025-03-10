package moduls;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс личности
 */
public class Person {
    /**
     * Дата рождения, может быть null
     */
    private LocalDateTime birthday;
    /**
     * Цвет глаз, может быть null
     */
    private EyeColor eyeColor;
    /**
     * Цвет волос, может быть null
     */
    private HairColor hairColor;
    /**
     * Страна, может быть null
     */
    private Country nationality;

    /**
     * @param birthday    - дата рождения
     * @param eyeColor    - цвет глаз
     * @param hairColor   - цвет волос
     * @param nationality - страна
     */
    public Person(LocalDateTime birthday, EyeColor eyeColor, HairColor hairColor, Country nationality) {
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    /**
     * Конструктор для пустого объекта
     */
    public Person() {

    }

    /**
     * @return возвращает значение поля birthday
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * Устанавливает значение поле birthday
     *
     * @param birthday - дата рождения
     */
    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    /**
     * @return возвращает значение поля eyeColor
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }

    /**
     * Устанавливает значение поле eyeColor
     *
     * @param eyeColor - значение поля eyeColor
     */
    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * @return возвращает значение поля hairColor
     */
    public HairColor getHairColor() {
        return hairColor;
    }

    /**
     * Устанавливает значение поля hairColor
     *
     * @param hairColor - значение поля hairColor
     */
    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * @return возвращает значение поля nationality
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Устанавливает значение поля nationality
     *
     * @param nationality - значение поля nationality
     */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
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
        Person person = (Person) o;
        return birthday.equals(person.birthday) && eyeColor==person.eyeColor && hairColor==person.hairColor && nationality==person.nationality;
    }

    /**
     * @return возвращает хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(birthday, eyeColor, hairColor, nationality);
    }

    /**
     * @return возвращает объект, приведенный к строковому виду
     */
    @Override
    public String toString() {
        return "Person{" + "birthday=" + birthday + ", eyeColor=" + eyeColor + ", hairColor=" + hairColor + ", nationality=" + nationality + '}';
    }
}