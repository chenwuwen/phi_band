package cn.kanyun.phi_band.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import cn.kanyun.phi_band.device.ui.fragment.DeviceFragment;
import cn.kanyun.phi_band.dynamic.ui.fragment.DynamicFragment;
import cn.kanyun.phi_band.home.ui.fragment.HomeFragment;
import cn.kanyun.phi_band.personal.ui.fragment.PersonalFragment;


/**
 * 设置ViewPager与Fragment的关系
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {


    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public MyViewPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return DynamicFragment.newInstance();
            case 2:
                return DeviceFragment.newInstance();
            default:
                return PersonalFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    /**
     * 当ViewPager与TabLayout结合使用时,不必给TabLayout添加tab,
     * 也就是不用调用TabLayout.addTab().只需要重写PagerAdapter.getPageTitle()方法即可.
     */
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 0:
//                return "皮肤";
//            default:
//                return "设置";
//        }
//
//    }


}
