package cn.kanyun.phi_band.common.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.orhanobut.logger.Logger;

import cn.kanyun.phi_band.base.config.ARouterConstants;
import cn.kanyun.phi_band.common.R;

/**
 * 二维码扫描Activity
 */
@Route(path = ARouterConstants.COMMON_QRSCANNER_ACTIVITY_PATH)
public class QrScannerActivity extends AppCompatActivity {

    /**
     * 获取ARoute传递的参数,这个变量名需要与传递参数的key保持一致
     * 同时该变量的修饰符不能为private
     * 同时需要在onCreate 加入 ARouter.getInstance().inject(this);
     */
    @Autowired
    public String from;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        ARouter.getInstance().inject(this);
        Logger.d("from:" + from);

//        二维码 扫描库

//         Activity下的用法
        IntentIntegrator integrator = new IntentIntegrator(this);
//      Fragment下的用法
//      IntentIntegrator.forFragment(HomeFragment.class);

        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt(getString(R.string.link_common_is_supported_please_retry));
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }
}
