package cn.kanyun.phi_band.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.kanyun.phi_band.R;
import cn.kanyun.phi_band.databinding.ActivityMainBinding;
import cn.kanyun.phi_band.listener.TabSwitchListener;

/**
 * 在支持路由的页面上添加注解(必选)
 * 这里的路径需要注意的是至少需要有两级，/xx/xx
 */
@Route(path = "/app/main")
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    /**
     * TabLayout
     */
    TabLayout tabLayout;

    /**
     * 滑动布局
     */
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        tabLayout = activityMainBinding.footTabLayout;
        viewPager = activityMainBinding.viewPagerContent;

//        设置预加载页面数量的方法
        viewPager.setOffscreenPageLimit(2);
//        设置默认位于第一个
        viewPager.setCurrentItem(0);
//        将TabLayout与Viewpager联动起来(需要注意的是,该方法需要添加在tabLayout添加tab的方法前面(添加tab的方法前 先remove所有tab),否则将找不到tab按钮和文字,因为该方法会移除以添加的tab)
        tabLayout.setupWithViewPager(viewPager);
    }


    /**
     * 初始化Tab
     * 自定义TabLayout布局
     */
    private void initTab() {
//        添加tab前移除全部tab
        tabLayout.removeAllTabs();
//        这里不使用HashMap,是因为HashMap是无序的(不是按照插入顺序),这样的话就会导致tab按钮顺序不一致
        Map<String, Integer> tabMap = new LinkedHashMap<>();

//        第一个tab按钮默认是选中的,所以图标是选中的图标
        tabMap.put(getString(R.string.tab_home_str), R.drawable.ic_tab_icon_home_select);
        tabMap.put(getString(R.string.tab_sport_circle_str), R.drawable.ic_tab_icon_sport_circle_select);
        tabMap.put(getString(R.string.tab_device_str), R.drawable.ic_tab_icon_device_select);
        tabMap.put(getString(R.string.tab_me_str), R.drawable.ic_tab_icon_me_select);
        Iterator<Map.Entry<String, Integer>> it = tabMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();

            View inflate = View.inflate(this, R.layout.custom_tab_layout, null);
            TabLayout.Tab tab = tabLayout.newTab();
            TextView textView = inflate.findViewById(R.id.tab_item_name);
            textView.setText(entry.getKey());
            ImageButton imageButton = inflate.findViewById(R.id.tab_item_img);
            imageButton.setImageResource(entry.getValue());
            tab.setCustomView(inflate);
            tabLayout.addTab(tab);

//        添加监听器
            tabLayout.addOnTabSelectedListener(new TabSwitchListener());

        }
    }
}
