package cn.kanyun.phi_band.common.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.inuker.bluetooth.library.model.BleGattService;

public class StateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
    }
}
