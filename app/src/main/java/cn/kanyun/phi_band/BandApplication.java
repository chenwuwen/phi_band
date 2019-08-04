package cn.kanyun.phi_band;

import android.app.Application;

public class BandApplication extends Application {

    public static BandApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
