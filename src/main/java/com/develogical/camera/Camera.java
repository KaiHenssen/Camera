package com.develogical.camera;

public class Camera {

    private final Sensor sensor;
    private final MemoryCard memorycard;
    private boolean ison = false;

    public Camera(Sensor sensor, MemoryCard memorycard) {
        this.sensor = sensor;
        this.memorycard = memorycard;
    }

    public void pressShutter() {
        if (ison) {
            byte[] dateToWriteToMemoryCard = sensor.readData();
            memorycard.write(dateToWriteToMemoryCard, new WriteCompleteListener() {
                @Override
                public void writeComplete() {
                    System.out.println("This is really getting called");
                }
            });
        }
    }
    public void powerOn() {
        ison=true;
        sensor.powerUp();
    }

    public void powerOff() {
        ison=false;
        sensor.powerDown();

    }
}
