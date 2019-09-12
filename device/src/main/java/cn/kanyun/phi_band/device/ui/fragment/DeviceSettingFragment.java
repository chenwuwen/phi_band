package cn.kanyun.phi_band.device.ui.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.beacon.Beacon;
import com.inuker.bluetooth.library.connect.listener.BleConnectStatusListener;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.model.BleGattService;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.kanyun.phi_band.common.transaction.bluetooth.BluetoothService;
import cn.kanyun.phi_band.device.R;
import cn.kanyun.phi_band.device.databinding.DeviceResourcesPrefixFragmentDeviceSettingBinding;
import cn.kanyun.phi_band.device.ui.viewmodel.DeviceSettingViewModel;

/**
 * 设备设置主页面
 */
public class DeviceSettingFragment extends Fragment  implements ServiceConnection {

    /**
     * 打开蓝牙请求码
     * 笔记本：3C:91:80:42:87:3A
     * 手环：2C:15:E1:2A:89:93
     */
    private static final int REQUEST_ENABLE_BT = 0X993;
    private static final String MAC = "3C:91:80:42:87:3A";
    private static final String serviceUUID = "2C:15:E1:2A:89:93";
    private static final String characterUUID = "2C:15:E1:2A:89:93";

    private DeviceSettingViewModel mViewModel;

    Context context;

    BluetoothClient mClient;

    BluetoothService mBluetoothService;

    DeviceResourcesPrefixFragmentDeviceSettingBinding deviceSettingFragmentBinding;

    public static DeviceSettingFragment newInstance() {
        return new DeviceSettingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        deviceSettingFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.device_resources_prefix_fragment_device_setting, container, false);
//        连接设备按钮点击事件
        deviceSettingFragmentBinding.deviceResourcesPrefixConnectButton.setOnClickListener(connectDeviceListener);
        context = requireActivity();
        mClient = new BluetoothClient(context);

        BleManager.getInstance().init(getActivity().getApplication());
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setOperateTimeout(5000);

        Intent intent = new Intent(context, BluetoothService.class);
        getActivity().bindService(intent, this, Context.BIND_AUTO_CREATE);

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


//          检查当前手机是否支持ble 蓝牙,如果不支持退出程序
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            ToastUtils.showLong("您的设备不支持低功耗蓝牙");
            return;
        }
        // 没有开启蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

//        调用自定义的蓝牙服务
        mBluetoothService.connect(mBluetoothAdapter, MAC);

//        search();
//        connect();
//        BleManager bleManager = BleManager.getInstance();
//        bleManager.setConnectOverTime(30 * 1000);
//        bleManager.connect(MAC, new BleGattCallback() {
//            @Override
//            public void onStartConnect() {
//                // 开始连接
//                ToastUtils.showShort("开始");
//            }
//
//            @Override
//            public void onConnectFail(BleDevice bleDevice, BleException exception) {
//                // 连接失败
//                ToastUtils.showLong("失败");
//            }
//
//            @Override
//            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
//                // 连接成功，BleDevice即为所连接的BLE设备
//                ToastUtils.showLong("成功");
//            }
//
//            @Override
//            public void onDisConnected(boolean isActiveDisConnected, BleDevice bleDevice, BluetoothGatt gatt, int status) {
//                // 连接中断，isActiveDisConnected表示是否是主动调用了断开连接方法
//                ToastUtils.showLong("中断");
//            }
//        });

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        可读 4个特征值
//        00001800-0000-1000-8000-00805f9b34fb
//        可读 通知 一个特征值
//        00001801-0000-1000-8000-00805f9b34fb
//        可读 通知 可写 两个
//        000018a0-0000-1000-8000-00805f9b34fb

    }

    public void search() {
        List<BluetoothDevice> findDeviceList = new ArrayList<>();
        SearchRequest request = new SearchRequest.Builder()
                .searchBluetoothLeDevice(3000, 3)   // 先扫BLE设备3次，每次3s
                .searchBluetoothClassicDevice(5000) // 再扫经典蓝牙5s
                .searchBluetoothLeDevice(2000)      // 再扫BLE设备2s
                .build();
        mClient.search(request, new SearchResponse() {
            @Override
            public void onSearchStarted() {
                Logger.d("开始搜索蓝牙设备");
            }


            @Override
            public void onDeviceFounded(SearchResult device) {
                findDeviceList.add(device.device);
                Beacon beacon = new Beacon(device.scanRecord);
                BluetoothLog.v(String.format("beacon for %s\n%s", device.getAddress(), beacon.toString()));
            }

            @Override
            public void onSearchStopped() {
                Logger.d("停止搜索蓝牙设备");
                ToastUtils.showLong("已停止搜索蓝牙");
                StringBuilder stringBuilder = new StringBuilder();
                for (BluetoothDevice device : findDeviceList) {
                    stringBuilder.append(device.getName() + "->" + device.getAddress() + "\n");
                }
                ToastUtils.showLong(stringBuilder.toString());
                Logger.d("已搜索到的蓝牙设备：+\n" + stringBuilder.toString());

            }

            @Override
            public void onSearchCanceled() {
                Logger.d("取消搜索蓝牙设备");
            }
        });

    }

    public void connect() {

//        配置连接参数
        BleConnectOptions options = new BleConnectOptions.Builder()
                .setConnectRetry(3)   // 连接如果失败重试3次
                .setConnectTimeout(300 * 1000)   // 连接超时30s
                .setServiceDiscoverRetry(3)  // 发现服务如果失败重试3次
                .setServiceDiscoverTimeout(200 * 1000)  // 发现服务超时20s
                .build();


//        连接过程包括了普通的连接(connectGatt)和发现服务(discoverServices)，这里收到回调时表明服务发现已完成。
//        回调参数BleGattProfile包括了所有的service和characteristic的uuid。返回的code表示操作状态，包括成功，失败或超时等，所有常量都在Constants类中
        mClient.connect(MAC, options, new BleConnectResponse() {
            @Override
            public void onResponse(int code, BleGattProfile profile) {
//                这个方法是最后回调的,也就是连接完成,或者连接失败(重试了设定次数后的失败,重试3次,那么第三次失败后会回调该方法)
                ToastUtils.showLong("服务发现回调");
                if (profile != null) {
                    StringBuilder sb = new StringBuilder();
                    for (BleGattService service : profile.getServices()) {
                        sb.append(service.toString() + "\n");
                    }
                    ToastUtils.showLong("服务发现完成! \n" + sb.toString());
                } else {
                    ToastUtils.showLong("未发现服务");
                }
                if (code == com.inuker.bluetooth.library.Constants.REQUEST_SUCCESS) {
                    Logger.d("蓝牙连接成功");
                } else {
                    ToastUtils.showLong("蓝牙连接失败! code：" + code);
                }
            }
        });
//        也可以主动获取连接状态
        int status = mClient.getConnectStatus(MAC);
        switch (status) {
            case com.inuker.bluetooth.library.Constants.STATUS_UNKNOWN:
                ToastUtils.showLong("当前蓝牙连接状态：未知");
                break;
            case com.inuker.bluetooth.library.Constants.STATUS_DEVICE_CONNECTED:
                ToastUtils.showLong("当前蓝牙连接状态：设备已连接");
                break;
            case com.inuker.bluetooth.library.Constants.STATUS_DEVICE_CONNECTING:
                ToastUtils.showLong("当前蓝牙连接状态：设备连接中");
                break;
            case com.inuker.bluetooth.library.Constants.STATUS_DEVICE_DISCONNECTING:
                ToastUtils.showLong("当前蓝牙连接状态：设备断开连接中");
                break;
            default:
//                com.inuker.bluetooth.library.Constants.STATUS_DEVICE_DISCONNECTED
                ToastUtils.showLong("当前蓝牙连接状态：设备已断开");

        }
//        监听蓝牙连接状态可以注册回调，只有两个状态：连接和断开。
        mClient.registerConnectStatusListener(MAC, mBleConnectStatusListener);
    }

    /**
     * 蓝牙连接状态监听
     * 只有两个状态：连接和断开
     */
    private final BleConnectStatusListener mBleConnectStatusListener = new BleConnectStatusListener() {

        @Override
        public void onConnectStatusChanged(String mac, int status) {
            if (status == com.inuker.bluetooth.library.Constants.STATUS_CONNECTED) {
                ToastUtils.showLong("MAC地址：" + mac + "  蓝牙已连接");
                Logger.d("蓝牙已连接");
            } else if (status == com.inuker.bluetooth.library.Constants.STATUS_DISCONNECTED) {
                ToastUtils.showLong("MAC地址：" + mac + "    蓝牙已断开");
                Logger.d("已断开蓝牙连接");
            }
        }
    };


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
//        获得Service对象(此时就可以使用这个对象去调用service中自定义的方法了)
        mBluetoothService = ((BluetoothService.BluetoothBinder) service).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mBluetoothService = null;
    }
}
