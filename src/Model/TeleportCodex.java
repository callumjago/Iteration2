package Model;

import java.awt.*;
import java.io.File;

public class TeleportCodex extends Codex {
    private static final File teleportCodex = new File(System.getProperty("user.dir") + "/GameFiles/Codex/TeleportLocations/TeleportLocations.csv");

    TeleportCodex() {
        super(teleportCodex);
    }

    public int getDestinationMap(int ID) {
        return Integer.parseInt(map.get(ID).get(0));
    }

    public Point getDestinationPosition(int ID) {
        Point destination = new Point(Integer.parseInt(map.get(ID).get(1)), Integer.parseInt(map.get(ID).get(2)));
        return destination;
    }
}