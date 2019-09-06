package cn.kanyun.phi_band.dynamic.ui.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.kanyun.phi_band.dynamic.R;
import cn.kanyun.phi_band.dynamic.viewmodel.DynamicViewModel;

public class DynamicFragment extends Fragment {

    private DynamicViewModel mViewModel;

    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dynamic_resources_prefix_dynamic_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DynamicViewModel.class);
        // TODO: Use the ViewModel
    }

}
