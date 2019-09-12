package cn.kanyun.phi_band.device.ui.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.kanyun.phi_band.device.R;
import cn.kanyun.phi_band.device.ui.viewmodel.DeviceDetailViewModel;

public class DeviceDetailFragment extends Fragment {

    private DeviceDetailViewModel mViewModel;

    public static DeviceDetailFragment newInstance() {
        return new DeviceDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.device_resources_prefix_fragment_device_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DeviceDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}
