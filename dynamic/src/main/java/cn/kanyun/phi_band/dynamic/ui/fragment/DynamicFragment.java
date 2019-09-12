package cn.kanyun.phi_band.dynamic.ui.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.kanyun.phi_band.base.config.ARouterConstants;
import cn.kanyun.phi_band.dynamic.R;
import cn.kanyun.phi_band.dynamic.viewmodel.DynamicViewModel;

/**
 * 在支持路由的页面上添加注解(必选)
 * 这里的路径需要注意的是至少需要有两级，/xx/xx
 * 不同module的一级路径必须不同
 */
@Route(path = ARouterConstants.DYNAMIC_DYNAMIC_FRAGMENT_PATH)
public class DynamicFragment extends Fragment {

    private DynamicViewModel mViewModel;

    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dynamic_resources_prefix_fragment_dynamic, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DynamicViewModel.class);
        // TODO: Use the ViewModel
    }

}
