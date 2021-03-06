package hu.oe.nik.autonomouscar.Environment.road_tiles;

import hu.oe.nik.autonomouscar.Environment.WorldObject;

import java.util.Arrays;

/**
 * Created by nemeth on 2016. 09. 30..
 */
public class LaneAdvanced extends RoadTile {


    public LaneAdvanced(int Id, int[] startPosition, int width, int height, double[] Transform, int Zlevel, int Opacity, int[] roadColor1, int[] roadColor2, int[] roadColor3, RoadPaintings1 roadPaintings1, RoadPaintings2 roadPaintings2, RoadPaintings3 roadPaintings3, LaneAdvancedType laneAdvancedType) {
        super(Id, startPosition, width, height, Transform, Zlevel, Opacity, false, roadColor1, roadColor2, roadColor3, roadPaintings1, roadPaintings2, roadPaintings3);
        this.laneAdvancedType = laneAdvancedType;
    }

    public enum LaneAdvancedType {CrossRoads,Rotary,TJunctionLeft,TJunctionRight}
    LaneAdvancedType laneAdvancedType;

    @Override
    public String toString() {
        return "\n" + this.getClass().getSimpleName()+  " elem adatai:  " +
                "\n   Id: " + this.getId() +
                "\n   Position: " + Arrays.toString(this.getPosition()) +
                "\n   Középpont: " + Arrays.toString(this.getCenterPoint()) +
                "\n   Transform: "  + Arrays.toString(this.getTransform()) +
                "\n   Zlevel: " + this.getZLevel() +
                "\n   Opacity: " + this.getOpacity() +
                "\n   CanStuckOnIt: " + this.getCanStuckOnIt() +
                "\n   LaneSimpleType: " + this.laneAdvancedType +
                "\n   RoadColor1: " + Arrays.toString(roadColor1) +
                "\n   RoadColor2: " + Arrays.toString(roadColor2) +
                "\n   RoadColor3: " + Arrays.toString(roadColor3) +
                "\n   RoadPaintigs1: " + this.roadPaintings1 +
                "\n   RoadPaintings2: " + this.roadPaintings2 +
                "\n   RoadPaintings3: " + this.roadPaintings3 +
                "\n";
    }
}

