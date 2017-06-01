package ch.tofind.reflexia.utils;

import java.util.Random;

/**
 * @author Luca Sivillica
 */
public class Point {
    //!
    private Integer x;

    //!
    private Integer y;

    Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public static Point getRandonPointBetween(Integer minX, Integer maxX, Integer minY, Integer maxY) {
        Random random = new Random();

        Integer x = random.nextInt(maxX - minX + 1) + minX;
        Integer y = random.nextInt(maxY - minY + 1) + minY;

        return new Point(x, y);
    }
}
