package moduls;

import java.io.Serializable;
import java.util.Objects;
public class Coordinates implements Serializable {
    final private static long serialVersionUID = 15L;
    private Float x;
    private Long y;
    public Coordinates(Float x, Long y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates() {
        this.x = 0F;
        this.y = 0L;
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
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }
}