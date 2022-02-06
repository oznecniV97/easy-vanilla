package org.oznecniv97.easyvanilla.utils;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;
import java.util.stream.Stream;

public final class AxisUtils {

    private AxisUtils() { }

    public static Stream<Direction> getDirections(final Vec3 delta) {
        return Stream.of(getXDirection(delta), getYDirection(delta), getZDirection(delta))
                .filter(Objects::nonNull);
    }

    public static Stream<Direction> getHorizontalDirections(final Vec3 delta) {
        return Stream.of(getXDirection(delta), getZDirection(delta))
                .filter(Objects::nonNull);
    }

    public static Direction getXDirection(final Vec3 delta) {
        return getDirection(delta.x, Direction.EAST, Direction.WEST);
    }

    public static Direction getYDirection(final Vec3 delta) {
        return getDirection(delta.y, Direction.UP, Direction.DOWN);
    }

    public static Direction getZDirection(final Vec3 delta) {
        return getDirection(delta.z, Direction.SOUTH, Direction.NORTH);
    }

    private static Direction getDirection(final double axisMovement, final Direction positive, final Direction negative) {
        if(axisMovement > 0) {
            return positive;
        } else if(axisMovement < 0) {
            return negative;
        } else {
            return null;
        }
    }

}
