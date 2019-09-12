package cn.kanyun.phi_band.device.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.Logger;

import cn.kanyun.phi_band.base.config.ARouterConstants;
import cn.kanyun.phi_band.device.R;

@Route(path = ARouterConstants.DEVICE_DEVICE_SETTING_ACTIVITY_PATH)
public class DeviceSettingActivity extends AppCompatActivity {

    /**
     * 获取ARoute传递的参数,这个变量名需要与传递参数的key保持一致
     * 同时该变量的修饰符不能为private
     * 同时需要在onCreate 加入 ARouter.getInstance().inject(this);
     */
    @Autowired
    public int deviceId;

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_resources_prefix_activity_device_setting);
//        获得ARoute传递的参数
        ARouter.getInstance().inject(this);
        Logger.d("传递的DeviceId是：",deviceId);
        navController = Navigation.findNavController(this, R.id.device_setting_nav);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }
}
