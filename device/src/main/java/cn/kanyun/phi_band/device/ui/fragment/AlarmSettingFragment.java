package cn.kanyun.phi_band.device.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import cn.kanyun.phi_band.device.R;
import cn.kanyun.phi_band.device.databinding.DeviceResourcesPrefixFragmentAlarmSettingBinding;
import cn.kanyun.phi_band.device.ui.viewmodel.AlarmSettingViewModel;

/**
 * 闹钟设置详细页面
 */
public class AlarmSettingFragment extends Fragment {

    private AlarmSettingViewModel mViewModel;

    DeviceResourcesPrefixFragmentAlarmSettingBinding alarmSettingFragmentBinding;

    public static AlarmSettingFragment newInstance() {
        return new AlarmSettingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        alarmSettingFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.device_resources_prefix_fragment_alarm_setting, container, false);
        return alarmSettingFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AlarmSettingViewModel.class);
        // TODO: Use the ViewModel
    }

}
