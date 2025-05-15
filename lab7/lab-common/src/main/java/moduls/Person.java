package moduls;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Person implements Serializable {
    final private static long serialVersionUID = 15L;
    private LocalDateTime birthday;
    private EyeColor eyeColor;
    private HairColor hairColor;
    private Country nationality;

    public Person(LocalDateTime birthday, EyeColor eyeColor, HairColor hairColor, Country nationality) {
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public Person() {

    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(birthday, eyeColor, hairColor, nationality);
    }

    @Override
    public String toString() {
        return "Person{" + "birthday=" + birthday + ", eyeColor=" + eyeColor + ", hairColor=" + hairColor + ", nationality=" + nationality + '}';
    }
}