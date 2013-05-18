package com.mohammadag.disablecameraalerts;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodHook;

public class DisableCameraAlerts implements IXposedHookLoadPackage {

	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		
		if (!lpparam.packageName.equals("com.sec.android.app.camera"))
			return;
		
		XposedBridge.log("Hooking SamsungCamera to disable battery low alerts");
		
		try {
			findAndHookMethod("com.sec.android.app.camera.AbstractCameraActivity", lpparam.classLoader,
					"handleLowBattery", boolean.class, new XC_MethodHook() {
	    		@Override
	    		protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
	    			param.setResult(null);
	    		}
			});
		} catch (Throwable t) {
			XposedBridge.log(t);
		}
		
		findAndHookMethod("com.sec.android.app.camera.Camcorder", lpparam.classLoader, "handleLowBattery", boolean.class, new XC_MethodHook() {
    		@Override
    		protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
    			param.setResult(null);
    		}
		});
		
		findAndHookMethod("com.sec.android.app.camera.Camcorder", lpparam.classLoader, "handlePluggedLowBattery", boolean.class, new XC_MethodHook() {
    		@Override
    		protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
    			param.setResult(null);
    		}
		});
		
		findAndHookMethod("com.sec.android.app.camera.Camera", lpparam.classLoader, "handlePluggedLowBattery", boolean.class, new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				param.setResult(null);
			}
		});
	}
}
