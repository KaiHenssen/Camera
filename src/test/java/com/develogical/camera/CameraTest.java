package com.develogical.camera;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class CameraTest {
    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        Sensor sensor = mock(Sensor.class);
        Camera camera = new Camera(sensor, null, null);

        camera.powerOn();

        verify(sensor).powerUp();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        Sensor sensor = mock(Sensor.class);
        Camera camera = new Camera(sensor, null, null);

        camera.powerOff();

        verify(sensor).powerDown();
    }

    @Test
    public void pressingShutterWhilePowerOn() {
        Sensor sensor = mock(Sensor.class);
        MemoryCard memorycard = mock(MemoryCard.class);
        WriteCompleteListener writeCompleteListener = mock(WriteCompleteListener.class);

        Camera camera = new Camera(sensor, memorycard, writeCompleteListener);

        camera.powerOn();
        camera.pressShutter();

        verify(memorycard).write(eq(sensor.readData()), any());
    }

    @Test
    public void pressingShutterWhilePowerOff() {
        Sensor sensor = mock(Sensor.class);
        MemoryCard memorycard = mock(MemoryCard.class);
        WriteCompleteListener writeCompleteListener = mock(WriteCompleteListener.class);

        Camera camera = new Camera(sensor, memorycard, writeCompleteListener);

        verifyNoMoreInteractions(sensor,memorycard);
        camera.pressShutter();

    }
}
