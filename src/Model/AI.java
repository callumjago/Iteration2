package Model;

import javafx.geometry.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public abstract class AI {
    private GameState gs;
    private Entity entity;
    public AI(Entity entity, GameState gs) {
        this.entity = entity;
        this.gs = gs;
    }

    abstract void tick(); //Tells AI to generate a move and call it on entity

    public Entity getEntity() {
        return entity;
    }

    public GameState getGameState() {
        return gs;
    }

    public ArrayList<Point> getPath(Point goal) {
        ArrayList<Point> path = new ArrayList<>();
        if(goal.x == entity.getPosition().x && goal.y == entity.getPosition().y) {//Already there
            return path;
        }

        Queue<Point> queue = new LinkedList<>();
        ArrayList<Point> visited = new ArrayList<>();
        Queue<Point> adj;

        HashMap<Point, Angle> actions = new HashMap<>();
        HashMap<Point, Point> nodeList = new HashMap<>();

        queue.add(entity.getPosition());
        Point vert = new Point(-1, -1);

        while(!queue.isEmpty()) {
            vert = queue.remove();
            visited.add(vert);
            if(vert.x == goal.x && vert.y == goal.y) {
                goal = vert;
                break;
            }

            adj = getAdjacent(vert);
            Point next;
            while(!adj.isEmpty()) {
                next = adj.remove();
                if(!gs.getTileAt(next.x, next.y).isPassable()) {//Tile not passable
                    continue;
                }
                if(visited.contains(next)) {//Already visited
                    continue;
                }

                //actions.put()
                nodeList.put(next, vert);
                queue.add(next);
            }
        }
        Point current = goal;
        while(entity.getPosition().x != current.x || entity.getPosition().y != current.y) {
            path.add(current);
            current = nodeList.get(current);
        }

        return path;
    }

    public Queue<Point> getAdjacent(Point p) {
        Queue<Point> adj = new LinkedList<>();
        if(p.x-1 >= 0) {
            adj.add(new Point(p.x-1, p.y));
        }
        if(p.x < gs.getWidth()-1) {
            adj.add(new Point(p.x+1, p.y));
        }
        if(p.y-1 >= 0) {
            adj.add(new Point(p.x, p.y-1));
        }
        if(p.y+1 < gs.getHeight()) {
            adj.add(new Point(p.x, p.y+1));
        }
        return adj;
    }

    //Returns angle Between two points
    public Angle getDirection(Point p1, Point p2) {

        if(p2.x > p1.x) {
            return new Angle(0);
        } else if(p2.y > p1.y) {
            return new Angle(90);
        } else if(p2.x < p1.x) {
            return new Angle(180);
        } else {
            return new Angle(270);
        }
    }
}
