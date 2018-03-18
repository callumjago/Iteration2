package Model;

import java.awt.*;
import java.util.ArrayList;


public class InfluenceRadius {
    private Point origin;
    private int steps;
    private int current = 1;
    private int direction;
    private ArrayList<Point> influence = new ArrayList<Point>();
//    private int x, y, dx, dy, error;

    public InfluenceRadius(Point o, int step) {
        origin = o;
        steps = step;
        influence.add(origin);
        direction = -1;
    }

    public InfluenceRadius(Point o, int step, Angle orientation) {
        origin = o;
        steps = step;
        direction = orientation.getDegree();
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

            if (direction == -1) {

                //top-right
                influence.add(new Point((int) origin.getX() + x, (int) origin.getY() + y));
                influence.add(new Point((int) origin.getX() + y, (int) origin.getY() + x));
                //top-left
                influence.add(new Point((int) origin.getX() - y, (int) origin.getY() + x));
                influence.add(new Point((int) origin.getX() - x, (int) origin.getY() + y));
                //bottom-left
                influence.add(new Point((int) origin.getX() - x, (int) origin.getY() - y));
                influence.add(new Point((int) origin.getX() - y, (int) origin.getY() - x));
                //bottom-right
                influence.add(new Point((int) origin.getX() + y, (int) origin.getY() - x));
                influence.add(new Point((int) origin.getX() + x, (int) origin.getY() - y));
            }
            //+x+y, +x-y
            else if (direction == 0) {
                influence.add(new Point((int) origin.getX() + x, (int) origin.getY() + y));
                influence.add(new Point((int) origin.getX() + x, (int) origin.getY() - y));
            }
            //top right
            else if (direction == 45) {
                influence.add(new Point((int) origin.getX() + x, (int) origin.getY() + y));
                influence.add(new Point((int) origin.getX() + y, (int) origin.getY() + x));
            }
            //+y+x, -y+x
            else if (direction == 90) {
                influence.add(new Point((int) origin.getX() + y, (int) origin.getY() + x));
                influence.add(new Point((int) origin.getX() - y, (int) origin.getY() + x));
            }
            //top left
            else if (direction == 135) {
                influence.add(new Point((int) origin.getX() - y, (int) origin.getY() + x));
                influence.add(new Point((int) origin.getX() - x, (int) origin.getY() + y));
            }
            //-x+y, -x-y
            else if (direction == 180) {
                influence.add(new Point((int) origin.getX() - x, (int) origin.getY() + y));
                influence.add(new Point((int) origin.getX() - x, (int) origin.getY() - y));
            }
            //bottom left
            else if (direction == 225) {
                influence.add(new Point((int) origin.getX() - x, (int) origin.getY() - y));
                influence.add(new Point((int) origin.getX() - y, (int) origin.getY() - x));
            }
            //-y-x, +y-x
            else if (direction == 270) {
                influence.add(new Point((int) origin.getX() - y, (int) origin.getY() - x));
                influence.add(new Point((int) origin.getX() + y, (int) origin.getY() - x));
            }
            //bottom right
            else if (direction == 315) {
                influence.add(new Point((int) origin.getX() + y, (int) origin.getY() - x));
                influence.add(new Point((int) origin.getX() + x, (int) origin.getY() - y));
            }

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

    public void skipToStep(int step) {
        if (step < steps) {
            if (step < current) {
                current = 0;
                while (current < step) {
                    extendInfluence();
                }
                dump();
                drawInfluence();
            } else {
                while (current < step) {
                    extendInfluence();
                }
                dump();
                drawInfluence();
            }
        }
        else {}
    }
    public ArrayList<Point> getInfluence() {
        return influence;
    }

    public void dump() {
        influence.clear();
    }
}
