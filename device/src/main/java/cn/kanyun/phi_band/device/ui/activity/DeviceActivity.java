package cn.kanyun.phi_band.device.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.kanyun.phi_band.base.config.ARouterConstants;
import cn.kanyun.phi_band.device.BuildConfig;
import cn.kanyun.phi_band.device.R;


/**
 * 当该模块作为application运行时,此Activity将作为入口Activity
 * 该Activity使用了Navigation 具体显示是交给Fragment的
 */
public class DeviceActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_resources_prefix_activity_device);
        navController = Navigation.findNavController(this, R.id.device_nav);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    /**
     * 重写onSupportNavigateUp（）方法，目的是将back事件委托出去。
     * 若栈中有两个以上Fragment，点击back键就会返回到上一个Fragment
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.device_nav);
        return NavHostFragment.findNavController(fragment).navigateUp();
    }

}
