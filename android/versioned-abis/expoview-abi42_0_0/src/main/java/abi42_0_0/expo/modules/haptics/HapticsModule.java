package abi42_0_0.expo.modules.haptics;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import abi42_0_0.org.unimodules.core.ExportedModule;
import abi42_0_0.org.unimodules.core.Promise;
import abi42_0_0.org.unimodules.core.interfaces.ExpoMethod;
import abi42_0_0.expo.modules.haptics.arguments.HapticsInvalidArgumentException;
import abi42_0_0.expo.modules.haptics.arguments.HapticsImpactType;
import abi42_0_0.expo.modules.haptics.arguments.HapticsNotificationType;
import abi42_0_0.expo.modules.haptics.arguments.HapticsSelectionType;
import abi42_0_0.expo.modules.haptics.arguments.HapticsVibrationType;

public class HapticsModule extends ExportedModule {
  private static final String TAG = "ExpoHaptics";
  private final Vibrator mVibrator;

  HapticsModule(Context context) {
    super(context);
    mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
  }

  @Override
  public String getName() {
    return TAG;
  }

  @ExpoMethod
  public void notificationAsync(String type, Promise promise) {
    try {
      vibrate(HapticsNotificationType.fromString(type));
      promise.resolve(null);
    } catch (HapticsInvalidArgumentException e) {
      promise.reject(e);
    }
  }

  @ExpoMethod
  public void selectionAsync(Promise promise) {
    vibrate(new HapticsSelectionType());
    promise.resolve(null);
  }

  @ExpoMethod
  public void impactAsync(String style, Promise promise) {
    try {
      vibrate(HapticsImpactType.fromString(style));
      promise.resolve(null);
    } catch (HapticsInvalidArgumentException e) {
      promise.reject(e);
    }
  }

  private void vibrate(HapticsVibrationType type) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      mVibrator.vibrate(VibrationEffect.createWaveform(type.getTimings(), type.getAmplitudes(), -1));
    } else {
      mVibrator.vibrate(type.getOldSDKPattern(), -1);
    }
  }
}
