package com.develogical.camera;

public class Camera {

    Sensor sensor = null;

    public Camera(Sensor sensor) {
        this.sensor = sensor;

    }

    public void pressShutter() {
        // not implemented
    }

    public void powerOn() {
        sensor.powerUp();
    }

    public void powerOff() {
        sensor.powerDown();

    }
}
