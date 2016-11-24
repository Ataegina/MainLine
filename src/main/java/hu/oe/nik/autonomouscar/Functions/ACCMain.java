package hu.oe.nik.autonomouscar.Functions;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Sensors.Radar.DetectedObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ral2bp on 2016.09.29. and by Mitró Tamás on 2016.11.24.
 */
public class ACCMain {

    private static ACCMain instance = null;
    public static ACCMain getInstance(){
        if (instance == null){
            instance = new ACCMain();
        }
        return instance;
    }
    private double targetSpeed;
    private double timegap;
    private double acceleration;
    private boolean isAccOn;
    private Bus bus;
    private List<DetectedObject> nearestFourObjects;

    private ACCMain(){
        this.targetSpeed = 0;
        this.timegap = 1.4;
        this.acceleration = 0.0;
        this.isAccOn = false;
        this.bus = Bus.getInstance();
        nearestFourObjects = new ArrayList<DetectedObject>();
    }

    public double getTargetSpeed(){
        return this.targetSpeed;
    }

    public void setTargetSpeed(double targetSpeed) {
        if(isAccOn){
            if(targetSpeed >= 30.0 && targetSpeed <= 180.0){
                this.targetSpeed = targetSpeed;
            } else if (targetSpeed < 30.0) {
                this.targetSpeed = 30.0;
            } else {
                this.targetSpeed = 180.0;
            }
        }
    }

    public double getTimegap() {
        return timegap;
    }

    /**
     * If the passed parameter is above 0, this method will increase the timegap up to 2.0 with 0.2 steps,
     * if the passed parameter is below 0, this method will decrease the timegap down to 1.0 with 0.2 steps.
     * @param givenTimegap
     */
    public void setTimegap(int givenTimegap) {
        if(isAccOn) {
            if (givenTimegap > 0 && this.timegap < 2.0) {
                this.timegap += 0.2;
            } else if (givenTimegap < 0 && givenTimegap > 1.0) {
                this.timegap -= 0.2;
            }
        }
    }

    public double getAcceleration() {
        return acceleration;
    }

    /**
     * If the passed parameter is above 0, this method will increase the acceleration up to 3.5 m/s2 with 1.0 steps,
     * if the passed parameter is below 0, this method will decrease the acceleration down to -3.5 m/s2 with 1.0 steps.
     * @param actualAcceleration
     */
    public void setAcceleration(int actualAcceleration) {
        if(isAccOn) {
            if (actualAcceleration > 0 && this.acceleration < 3.5) {
                // when the car is accelerating
                this.acceleration += 1.0;
            } else if (actualAcceleration < 0 && this.acceleration > -3.5) {
                // when the car is slowing down
                this.acceleration -= 1.0;
            }
        }
    }

    public boolean isAccOn() {
        return isAccOn;
    }

    /**
     * This method can be started the ACC function
     * @param actualSpeedOfCar
     */
    // At this invoke the ACCMain exists already
    public void setAccOn(double actualSpeedOfCar) {
        if(!isAccOn){
            this.isAccOn = true;
            this.targetSpeed = actualSpeedOfCar;
            getDetectedObjectsFromRadar();
        }
    }

    public void setAccOff() {
        if(isAccOn){
            this.isAccOn = false;
        }
    }

    private void getDetectedObjectsFromRadar(){
        this.nearestFourObjects = bus.getFourNearestFromRadar();
    }

    // MUST TO IMPLEMENT!
    private void definitionOfClosestObject(){
        // A nearestFourObjects listából ki kell venni a legközelebbi objektumot a saját sávunkban
        // és meg kell határozni a távolságát a saját autótól (d)
        // A sebesség és a timegap (tau időállandó) szorzata adja meg a d-t
        // d = targetSpeed * timegap
        // A távolságnak megfelelően lehet állítani a timegap-et 1.0 és 2.0 között mp-ben.
    }

    @Override
    public String toString() {
        return "ACCMain{" +
                ", targetSpeed=" + targetSpeed +
                ", timegap=" + timegap +
                ", acceleration=" + acceleration +
                ", isAccOn=" + isAccOn +
                '}';
    }
}
