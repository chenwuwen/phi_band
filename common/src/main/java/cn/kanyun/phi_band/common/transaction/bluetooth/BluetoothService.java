package cn.kanyun.phi_band.common.transaction.bluetooth;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.UUID;

public class BluetoothService extends Service {

    private BluetoothAdapter bluetoothAdapter;
    private String bluetoothDeviceAddress;
    private BluetoothGatt bluetoothGatt;
    private int connectionState = STATE_DISCONNECTED;

    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNETING = 1;
    private static final int STATE_CONNECTED = 2;

    public final static String ACTION_GATT_CONNECTED = "cn.kanyun.phi_band.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED = "cn.kanyun.phi_band.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED = "cn.kanyun.phi_band.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE = "cn.kanyun.phi_band.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA = "cn.kanyun.phi_band.bluetooth.le.EXTRA_DATA";


    private final BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        // 你的中央设备连接上 ble 设备后，会回调这个这方法。
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            String intenAction;
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                intenAction = ACTION_GATT_CONNECTED;
                connectionState = STATE_CONNECTED;
                broadcastUpdate(intenAction);
                // 连接上后，紧接着就是要寻找里面 Service
                bluetoothGatt.discoverServices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                intenAction = ACTION_GATT_DISCONNECTED;
                connectionState = STATE_DISCONNECTED;
                broadcastUpdate(intenAction);

            }

        }

        @Override
        // service 发现后就会触发这个方法，然后你就需要找到你和 ble 约定好的 service
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
            }
        }
    };

    public void onCharacteristicREad() {

    }


    private void broadcastUpdate(String actionGattServicesDiscovered) {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
