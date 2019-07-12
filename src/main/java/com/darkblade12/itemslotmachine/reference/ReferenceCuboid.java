package com.darkblade12.itemslotmachine.reference;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.darkblade12.itemslotmachine.cuboid.Cuboid;

public final class ReferenceCuboid {
    private static final String FORMAT = "-?\\d+(@-?\\d+){2}&-?\\d+(@-?\\d+){2}";
    private final ReferenceLocation firstLocation, secondLocation;

    public ReferenceCuboid(ReferenceLocation firstLocation, ReferenceLocation secondLocation) {
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
    }

    public static ReferenceCuboid fromString(String s) throws IllegalArgumentException {
        if ( !s.matches(FORMAT) ) {
            throw new IllegalArgumentException("Invalid format");
        }
        String[] p = s.split("&");
        return new ReferenceCuboid(ReferenceLocation.fromString(p[0]), ReferenceLocation.fromString(p[1]));
    }

    public ReferenceLocation getFirstLocation() {
        return firstLocation;
    }

    public ReferenceLocation getSecondLocation() {
        return secondLocation;
    }

    public Cuboid getCuboid(Location c, Direction d) throws Exception {
        return new Cuboid(firstLocation.getBukkitLocation(c, d), secondLocation.getBukkitLocation(c, d));
    }

    public Cuboid getCuboid(Player p) throws Exception {
        return getCuboid(p.getLocation(), Direction.get(p));
    }

    @Override
    public String toString() {
        return firstLocation + "&" + secondLocation;
    }
}