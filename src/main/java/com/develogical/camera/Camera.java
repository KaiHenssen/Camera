package com.develogical.camera;

public class Camera {

    private final Sensor sensor;
    private final MemoryCard memorycard;
    private boolean ison = false;
    public boolean copyinProgress = false;

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
                    Camera.this.copyinProgress = false;
                    if (!ison) {
                        sensor.powerDown();
                    }
                }
            });
            copyinProgress = true;
        }
    }

    public void powerOn() {
        ison = true;
        sensor.powerUp();
    }

    public void powerOff() {
        ison = false;
        if (!this.copyinProgress) {
            sensor.powerDown();
        }
    }
}
