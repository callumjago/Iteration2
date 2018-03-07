package View;

public class Bound {
    private int boundLeft, boundRight, boundTop, boundBottom;
    public Bound(int left, int right, int top, int bottom) {
        boundLeft = left;
        boundRight = right;
        boundTop = top;
        boundBottom = bottom;
    }

    public int getBoundLeft() {
        return boundLeft;
    }

    public int getBoundRight() {
        return boundRight;
    }

    public int getBoundTop() {
        return boundTop;
    }

    public int getBoundBottom() {
        return boundBottom;
    }

    public int getWitdh() {
        return boundRight-boundLeft;
    }

    public int getHeight() {
        return boundBottom-boundTop;
    }

    public boolean collisionTest(int x, int y) {
        if(x >= boundLeft && x <= boundRight && y >= boundTop && y <= boundBottom) {
            return true;
        } else {
            return false;
        }
    }
}
