package com.develogical.camera;

import com.sun.webkit.event.WCChangeListener;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class CameraTest {
    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        Sensor sensor = mock(Sensor.class);
        Camera camera = new Camera(sensor, null);

        camera.powerOn();

        verify(sensor).powerUp();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        Sensor sensor = mock(Sensor.class);
        Camera camera = new Camera(sensor, null);

        camera.powerOff();

        verify(sensor).powerDown();
    }

    /*
    @Test
    public void pressingShutterWhilePowerOn() {
        Sensor sensor = mock(Sensor.class);MemoryCard memorycard = mock(MemoryCard.class);

        Camera camera = new Camera(sensor, memorycard);

        camera.powerOn();
        camera.pressShutter();

        ArgumentCaptor<WriteCompleteListener> argumentCaptor = ArgumentCaptor.forClass(WriteCompleteListener.class);

        verify(memorycard).write(eq(sensor.readData()), argumentCaptor.capture());

        argumentCaptor.getValue().writeComplete();
    }
    */

    @Test
    public void pressingShutterWhilePowerOn() {
        Sensor sensor = mock(Sensor.class);MemoryCard memorycard = mock(MemoryCard.class);

        Camera camera = new Camera(sensor, memorycard);

        camera.powerOn();
        camera.pressShutter();

        verify(memorycard).write(eq(sensor.readData()), any());
    }

    @Test
    public void pressingShutterWhilePowerOff() {
        Sensor sensor = mock(Sensor.class);
        MemoryCard memorycard = mock(MemoryCard.class);

        Camera camera = new Camera(sensor, memorycard);

        verifyNoMoreInteractions(sensor,memorycard);
        camera.pressShutter();
    }

    @Test
    public void correctbehaviourswitchingoffwhendatabeingwritten() {

        Sensor sensor = mock(Sensor.class);
        MemoryCard memorycard = mock(MemoryCard.class);
        ArgumentCaptor<WriteCompleteListener> argumentCaptor = ArgumentCaptor.forClass(WriteCompleteListener.class);

        Camera camera = new Camera(sensor, memorycard);

        camera.powerOn();
        camera.pressShutter();
        verify(memorycard).write(eq(sensor.readData()), argumentCaptor.capture());

        camera.powerOff();

        verify(sensor, times(0)).powerDown();

        argumentCaptor.getValue().writeComplete();

        verify(sensor, times(1)).powerDown();
    }

    @Test
    public void bug() {

        Sensor sensor = mock(Sensor.class);
        MemoryCard memorycard = mock(MemoryCard.class);
        ArgumentCaptor<WriteCompleteListener> argumentCaptor = ArgumentCaptor.forClass(WriteCompleteListener.class);

        Camera camera = new Camera(sensor, memorycard);

        camera.powerOn();
        camera.pressShutter();
        verify(memorycard).write(eq(sensor.readData()), argumentCaptor.capture());

        argumentCaptor.getValue().writeComplete();

        verify(sensor, times(0)).powerDown();
    }
}
