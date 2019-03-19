package com.develogical.camera;

public class Camera {

    private final Sensor sensor;
    private final MemoryCard memorycard;
    private final WriteCompleteListener writeCompleteListener;
    private boolean ison = false;

    public Camera(Sensor sensor, MemoryCard memorycard, WriteCompleteListener writeCompleteListener) {
        this.sensor = sensor;
        this.memorycard = memorycard;
        this.writeCompleteListener = writeCompleteListener;
    }

    public void pressShutter() {
        if (ison) {
            //WriteCompleteListener writeCompleteListener = new WriteCompleteListener();
            byte[] dateToWriteToMemoryCard = sensor.readData();
            memorycard.write(dateToWriteToMemoryCard, writeCompleteListener);
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
