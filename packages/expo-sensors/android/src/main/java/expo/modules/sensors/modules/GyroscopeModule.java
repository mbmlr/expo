// Copyright 2015-present 650 Industries. All rights reserved.

package expo.modules.sensors.modules;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;

import expo.modules.core.Promise;
import expo.modules.core.interfaces.ExpoMethod;

import expo.modules.interfaces.sensors.SensorServiceInterface;
import expo.modules.interfaces.sensors.services.GyroscopeServiceInterface;

public class GyroscopeModule extends BaseSensorModule {
  public GyroscopeModule(Context reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "ExponentGyroscope";
  }

  @Override
  public String getEventName() {
    return "gyroscopeDidUpdate";
  }

  @Override
  protected SensorServiceInterface getSensorService() {
    return getModuleRegistry().getModule(GyroscopeServiceInterface.class);
  }

  protected Bundle eventToMap(SensorEvent sensorEvent) {
    Bundle map = new Bundle();
    map.putDouble("x", sensorEvent.values[0]);
    map.putDouble("y", sensorEvent.values[1]);
    map.putDouble("z", sensorEvent.values[2]);
    return map;
  }

  @ExpoMethod
  public void startObserving(Promise promise) {
    super.startObserving();
    promise.resolve(null);
  }

  @ExpoMethod
  public void stopObserving(Promise promise) {
    super.stopObserving();
    promise.resolve(null);
  }

  @ExpoMethod
  public void setUpdateInterval(int updateInterval, Promise promise) {
    super.setUpdateInterval(updateInterval);
    promise.resolve(null);
  }

  @ExpoMethod
  public void isAvailableAsync(Promise promise) {
    SensorManager mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
    boolean isAvailable = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null;
    promise.resolve(isAvailable);
  }
}
