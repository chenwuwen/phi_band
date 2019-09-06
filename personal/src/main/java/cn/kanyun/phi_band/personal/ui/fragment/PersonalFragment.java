package cn.kanyun.phi_band.personal.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.kanyun.phi_band.base.config.ARouterConstants;
import cn.kanyun.phi_band.personal.R;


/**
 * 在支持路由的页面上添加注解(必选)
 * 这里的路径需要注意的是至少需要有两级，/xx/xx
 * 不同module的一级路径必须不同
 */
@Route(path = ARouterConstants.PERSONAL_PERSONAL_FRAGMENT_PATH)
public class PersonalFragment extends Fragment {


    public static Fragment newInstance() {
        return new PersonalFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.personal_resources_prefix_fragment_personal, container, false);
    }

}
