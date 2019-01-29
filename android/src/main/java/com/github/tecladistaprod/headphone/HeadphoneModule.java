package com.github.tecladistaprod.headphone;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

import android.view.KeyEvent;

import android.widget.Toast;

public class HeadphoneModule extends ReactContextBaseJavaModule {
  private final ReactApplicationContext reactContext;
  private static HeadphoneModule instance = null;

  private DeviceEventManagerModule.RCTDeviceEventEmitter mJSModule = null;

  public HeadphoneModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "HeadphoneModule";
  }

  @ReactMethod
  public void eventListener(int code, KeyEvent keyEvent, String type) {
    if (mJSModule == null) {
      mJSModule = reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }
    int action = keyEvent.getAction();
    int keycode = keyEvent.getKeyCode();

    WritableMap params = new WritableNativeMap();

    params.putInt("keyCode", keycode);
    params.putInt("action", action);

    if(keycode == KeyEvent.KEYCODE_HEADSETHOOK || keycode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE){
      mJSModule.emit("headphoneButton", params);
    } else if(type.equals("keyUp")) {
      mJSModule.emit("keyUp", params);
    } else if(type.equals("keyDown")) {
      mJSModule.emit("keyDown", params);
    } else if(type.equals("keyMultiple")) {
      mJSModule.emit("keyMultiple", params);
    } else if(type.equals("longPress")) {
      mJSModule.emit("longPress", params);
    }
  }

  public static HeadphoneModule initHeadphoneModule(ReactApplicationContext reactContext) {
    instance = new HeadphoneModule(reactContext);
    return instance;
  }

  public static HeadphoneModule getInstance() {
    return instance;
  }

}