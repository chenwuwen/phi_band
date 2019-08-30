package cn.kanyun.phi_band.ui.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.kanyun.phi_band.R;
import cn.kanyun.phi_band.ui.viewmodel.TrainViewModel;

public class TrainFragment extends Fragment {

    private TrainViewModel mViewModel;

    public static TrainFragment newInstance() {
        return new TrainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.train_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TrainViewModel.class);
        // TODO: Use the ViewModel
    }

}
