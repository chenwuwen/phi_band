package cn.kanyun.phi_band.device.ui.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.beacon.Beacon;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.connect.response.BluetoothResponse;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;

import cn.kanyun.phi_band.databinding.DeviceResourcesPrefixDeviceSettingFragmentBinding;
import cn.kanyun.phi_band.device.R;
import cn.kanyun.phi_band.device.listener.ConnectDeviceListener;
import cn.kanyun.phi_band.device.ui.viewmodel.DeviceSettingViewModel;

import static com.google.gson.internal.bind.TypeAdapters.UUID;

public class DeviceSettingFragment extends Fragment {

    /**
     * 打开蓝牙请求码
     */
    private static final int REQUEST_ENABLE_BT = 0X993;

    private DeviceSettingViewModel mViewModel;

    Context context;

    DeviceResourcesPrefixDeviceSettingFragmentBinding deviceSettingFragmentBinding;

    public static DeviceSettingFragment newInstance() {
        return new DeviceSettingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        deviceSettingFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.device_resources_prefix_device_setting_fragment, container, false);
        deviceSettingFragmentBinding.deviceResourcesPrefixConnectText.setOnClickListener(connectDeviceListener);
        return deviceSettingFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DeviceSettingViewModel.class);
        // TODO: Use the ViewModel
    }

    //    点击连接设备按钮监听事件
    View.OnClickListener connectDeviceListener = v -> {
//    BluetoothAdapter 代表设备自己的蓝牙适配器，整个系统只有一个蓝牙适配器，我们的应用程序可以使用此对象与其交互
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // 说明此设备不支持蓝牙操作
            ToastUtils.showLong("你的设备不支持蓝牙");
            return;
        }
        // 没有开始蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        connect();

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    public void connect() {
        BluetoothClient mClient = new BluetoothClient(context);
        SearchRequest request = new SearchRequest.Builder()
                .searchBluetoothLeDevice(3000, 3)   // 先扫BLE设备3次，每次3s
                .searchBluetoothClassicDevice(5000) // 再扫经典蓝牙5s
                .searchBluetoothLeDevice(2000)      // 再扫BLE设备2s
                .build();
        mClient.search(request, new SearchResponse() {
            @Override
            public void onSearchStarted() {

            }


            @Override
            public void onDeviceFounded(SearchResult device) {
                Beacon beacon = new Beacon(device.scanRecord);
                BluetoothLog.v(String.format("beacon for %s\n%s", device.getAddress(), beacon.toString()));
            }

            @Override
            public void onSearchStopped() {

            }

            @Override
            public void onSearchCanceled() {

            }
        });

//        mClient.connect("2C:15:E1:2A:89:93",new BleConnectResponse());

    }
}
