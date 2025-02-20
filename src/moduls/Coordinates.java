package moduls;

public class Coordinates {
    private Float x; //Значение поля должно быть больше -626, Поле не может быть null
    private Long y; //Поле не может быть null

    public Coordinates(Float x, Long y) {
        this. x = x;
        this.y = y;
    }
    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}