package Sensors;

import Environment.WorldObject;
import Environment.XMLParserMain;
import Visuals.Car;

import java.util.ArrayList;

/**
 * Created by ral2bp on 2016.09.29..
 */


public class Radar {

    private RadarCalculator radarCalculator;
    private XMLParserMain xml;
    private ArrayList<WorldObject> detectedObjects;
    private static final int radarDistance = 200;
    private static final int radarRadiusInDegree = 30;
    private Car car;
    private double carRotation;
    public double[] RadarCoord;
    public int[][] Triangle;
    public double[] transformation;
    public Radar(){
        setDetectedObjects(new ArrayList<>());
        radarCalculator = new RadarCalculator();
        transformation = new double[]{0,0};
        Triangle = new int[3][2];
        RadarCoord = new double[2];
    }

    private void Refresh(){

        RadarCoord[0] = (double)car.getXCoord()+car.getWidth()/2*Math.cos(car.getRotation());
        RadarCoord[1] = (double)car.getYCoord()*Math.cos(car.getRotation());
    }
    public void GetObjectsFromEnvironment(){
        setDetectedObjects((ArrayList)xml.getDetectedObjects(this.Triangle[1][0],this.Triangle[1][1],this.Triangle[2][0],this.Triangle[2][1],this.Triangle[0][0],this.Triangle[0][1]));
    }

    public void CalculateTriangle(Car car){
        this.Triangle[0][0] = (int)Math.round(RadarCoord[0]);
        this.Triangle[0][1] = (int)Math.round(RadarCoord[1]);
        this.Triangle[1][0] = (int)Math.round(RadarCoord[0] + radarDistance*Math.cos(car.getRotation()));
        this.Triangle[1][1] = (int)Math.round(RadarCoord[1] + Math.tan(radarRadiusInDegree/2)*radarDistance*Math.cos(car.getRotation()));
        this.Triangle[2][0] = (int)Math.round(RadarCoord[0] - radarDistance*Math.cos(car.getRotation()));
        this.Triangle[2][1] = (int)Math.round(RadarCoord[1] - Math.tan(radarRadiusInDegree/2)*radarDistance*Math.cos(car.getRotation()));
    }

    public void setRadarCoord(double x, double y){
        RadarCoord[0] = x;
        RadarCoord[1] = y;
    }

    public ArrayList<WorldObject> getDetectedObjects() {
        return detectedObjects;
    }

    public void setDetectedObjects(ArrayList<WorldObject> detectedObjects) {
        this.detectedObjects = detectedObjects;
    }

    public RadarCalculator getRadarCalculator() {
        return radarCalculator;
    }
}