package Environment.road_signs;

import Environment.WorldObject;

/**
 * Created by nemet on 2016. 09. 30..
 */
public class Speed extends WorldObject{

    public Speed(int Id, int[] startPosition, double[] Transform, int Zlevel, int Opacity, SpeedType speedType) {
        super(Id, startPosition, 80, 80, Transform, Zlevel, Opacity, false);
        this.speedType = speedType;
    }

    public enum SpeedType { Ten, Twenty, Forty, Fifty, Seventy, Ninety, Hundred}
    SpeedType speedType;


}
