package cn.kanyun.phi_band.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import cn.kanyun.phi_band.R;
import top.androidman.SuperButton;

/**
 * 开屏页
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 跳过倒计时提示5秒
     */
    private int recLen = 2;

    /**
     * 跳过按钮
     */
    private SuperButton skipButton;
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        setContentView(R.layout.activity_splash);
        initView();
        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        /**
         * 正常情况下不点击跳过
         */
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(SplashActivity.this, cn.kanyun.phi_band.ui.activity.MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);//延迟5S后发送handler信息

    }

    private void initView() {
        //跳过按钮
        skipButton = findViewById(R.id.skipButton);
        //跳过监听
        skipButton.setOnClickListener(this);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // UI 线程
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    skipButton.setVisibility(View.VISIBLE);
                    skipButton.setText(getString(R.string.skip_str) + recLen);
                    recLen--;
                    if (recLen < 0) {
                        timer.cancel();
                        skipButton.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };

    /**
     * 点击跳过
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.skipButton:
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(SplashActivity.this, cn.kanyun.phi_band.ui.activity.MainActivity.class);
                startActivity(intent);
                finish();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                break;
            default:
                break;
        }
    }

}
