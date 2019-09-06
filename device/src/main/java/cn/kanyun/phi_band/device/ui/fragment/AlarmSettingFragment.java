package cn.kanyun.phi_band.device.ui.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import cn.kanyun.phi_band.device.R;
import cn.kanyun.phi_band.device.databinding.DeviceResourcesPrefixAlarmSettingFragmentBinding;
import cn.kanyun.phi_band.device.ui.viewmodel.AlarmSettingViewModel;

public class AlarmSettingFragment extends Fragment {

    private AlarmSettingViewModel mViewModel;

    DeviceResourcesPrefixAlarmSettingFragmentBinding alarmSettingFragmentBinding;

    public static AlarmSettingFragment newInstance() {
        return new AlarmSettingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        alarmSettingFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.device_resources_prefix_alarm_setting_fragment, container, false);
        return alarmSettingFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AlarmSettingViewModel.class);
        // TODO: Use the ViewModel
    }

}
