package cn.kanyun.phi_band.device.listener;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import cn.kanyun.phi_band.device.ui.fragment.DeviceSettingFragment;

public class ConnectDeviceListener implements View.OnClickListener {

    Context context;
    public ConnectDeviceListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

    }
}
