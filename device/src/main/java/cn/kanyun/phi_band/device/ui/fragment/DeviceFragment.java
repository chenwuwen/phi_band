package cn.kanyun.phi_band.device.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;

import cn.kanyun.phi_band.base.config.ARouterConstants;
import cn.kanyun.phi_band.device.R;
import cn.kanyun.phi_band.device.databinding.DeviceResourcesPrefixFragmentDeviceBinding;
import cn.kanyun.phi_band.device.ui.activity.DeviceActivity;
import cn.kanyun.phi_band.device.ui.viewmodel.DeviceViewModel;


/**
 * 在支持路由的页面上添加注解(必选)
 * 这里的路径需要注意的是至少需要有两级，/xx/xx
 * 不同module的一级路径必须不同
 */
@Route(path = ARouterConstants.DEVICE_DEVICE_FRAGMENT_PATH)
public class DeviceFragment extends Fragment {

    DeviceViewModel deviceViewModel;

    DeviceResourcesPrefixFragmentDeviceBinding deviceBinding;

    public static Fragment newInstance() {
        return new DeviceFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        deviceBinding = DataBindingUtil.inflate(inflater, R.layout.device_resources_prefix_fragment_device, container, false);
//        点击已绑定的设备,跳转到设备设置页面
        deviceBinding.connectHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                跳转时,传递Int类型的参数,这个值是被点击的设备的ID(在SQLite中保存)
                ARouter.getInstance().build(ARouterConstants.DEVICE_DEVICE_SETTING_ACTIVITY_PATH).withInt("deviceId", 1).navigation();
            }
        });


        return deviceBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        deviceViewModel = ViewModelProviders.of(this).get(DeviceViewModel.class);
        // TODO: Use the ViewModel

    }

}
