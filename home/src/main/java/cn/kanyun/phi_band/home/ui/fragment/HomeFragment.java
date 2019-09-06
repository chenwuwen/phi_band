package cn.kanyun.phi_band.home.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.kanyun.phi_band.base.config.ARouterConstants;
import cn.kanyun.phi_band.home.R;
import cn.kanyun.phi_band.home.databinding.HomeResourcesPrefixFragmentHomeBinding;
import cn.kanyun.phi_band.home.viewmodel.HomeViewModel;


/**
 * 首页
 * 在支持路由的页面上添加注解(必选)
 * 这里的路径需要注意的是至少需要有两级，/xx/xx
 * 不同module的一级路径必须不同
 */
@Route(path = ARouterConstants.HOME_HOME_FRAGMENT_PATH)
public class HomeFragment extends Fragment {

    HomeResourcesPrefixFragmentHomeBinding homeBinding;

    HomeViewModel homeViewModel;

    public static Fragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.home_resources_prefix_fragment_home, container, false);
        return homeBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }
}
