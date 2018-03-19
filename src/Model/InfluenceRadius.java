package Model;

import java.awt.*;
import java.util.ArrayList;


public class InfluenceRadius {
    private Point origin;
    private int steps;
    private int current = 1;
    private ArrayList<Point> influence = new ArrayList<Point>();
//    private int x, y, dx, dy, error;

    public InfluenceRadius(Point o, int s) {
        origin = o;
        steps = s;
        influence.add(origin);
    }


    public void drawInfluence() {
//        int d = (5 - current*4)/4;
//        int x = 0;
//        int y = current;
        int x = current-1;
        int y = 0;
        int dx = 1;
        int dy = 1;
        int error = dx - (current << 1);
        while (x >= y) {

            influence.add(new Point((int) origin.getX() + x, (int) origin.getY() + y));
            influence.add(new Point((int) origin.getX() + y, (int) origin.getY() + x));
            influence.add(new Point((int) origin.getX() - y, (int) origin.getY() + x));
            influence.add(new Point((int) origin.getX() - x, (int) origin.getY() + y));
            influence.add(new Point((int) origin.getX() - x, (int) origin.getY() - y));
            influence.add(new Point((int) origin.getX() - y, (int) origin.getY() - x));
            influence.add(new Point((int) origin.getX() + y, (int) origin.getY() - x));
            influence.add(new Point((int) origin.getX() + x, (int) origin.getY() - y));

            if (error <= 0) {
                y++;
                error += dy;
                dy += 2;
            }

            if (error > 0) {
                x--;
                dx += 2;
                error += dx - (current<<1);
            }
//            if (d < 0) {
//                d+= 2 * x + 1;
//            } else {
//                d += 2 * (x-y) + 1;
//                y--;
//            }
        }
    }
    public void extendInfluence() {
        if (current <= steps) {
            current++;
            dump();
            drawInfluence();
        }

    }

    public ArrayList<Point> getInfluence() {
        return influence;
    }

    public void dump() {
        influence.clear();
    }
}