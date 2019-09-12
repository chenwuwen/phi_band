package cn.kanyun.phi_band.common.transaction.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 用BroadcastReceiver来监听蓝牙状态改变的广播
 */
public class AutoConnectReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
        }
        if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
        }
        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
        }
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//            扫描到蓝牙设备
        }

    }
}
