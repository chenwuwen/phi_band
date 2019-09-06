package cn.kanyun.phi_band.device.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.kanyun.phi_band.base.config.ARouterConstants;
import cn.kanyun.phi_band.device.R;
import cn.kanyun.phi_band.device.databinding.DeviceResourcesPrefixFragmentDeviceBinding;
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

        deviceBinding.connectHistory.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.device_resources_prefix_action_device_resources_prefix_device_fragment_to_device_resources_prefix_device_setting_fragment);
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
