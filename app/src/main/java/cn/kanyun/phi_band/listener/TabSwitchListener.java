package cn.kanyun.phi_band.listener;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.FragmentUtils;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;

import cn.kanyun.phi_band.base.config.ARouterConstants;

/**
 * Tab选中监听
 */
public class TabSwitchListener implements TabLayout.OnTabSelectedListener {

    Context context;

    public TabSwitchListener(Context context) {
        this.context = context;
    }

    /**
     * 选中了tab的逻辑
     *
     * @param tab
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        LinearLayout layout = (LinearLayout) tab.getCustomView();
        Logger.d("当前布局下子布局个数为：" + layout.getChildCount());
        ImageView imageView = (ImageView) layout.getChildAt(0);
//        TextView textView = (TextView) layout.getChildAt(1);
//        textView.setTextColor(context.getColor(R.color.yellow_color));
        Drawable drawable = imageView.getDrawable();
        drawable.setTint(Color.parseColor("#FFE64538"));
        Fragment fragment;
        int index = tab.getPosition();
        switch (index) {
            case 0:
//                首页Tab
                fragment = (Fragment) ARouter.getInstance().build(ARouterConstants.HOME_HOME_FRAGMENT_PATH).navigation();
                FragmentUtils.showHide(fragment);
                break;
            case 1:
//                运动圈Tab
                fragment = (Fragment) ARouter.getInstance().build(ARouterConstants.DYNAMIC_DYNAMIC_FRAGMENT_PATH).navigation();
                FragmentUtils.showHide(fragment);
                break;
            case 2:
//                设备Tab
                fragment = (Fragment) ARouter.getInstance().build(ARouterConstants.DEVICE_DEVICE_FRAGMENT_PATH).navigation();
                FragmentUtils.showHide(fragment);
                break;
            default:
//                我的Tab
                fragment = (Fragment) ARouter.getInstance().build(ARouterConstants.PERSONAL_PERSONAL_FRAGMENT_PATH).navigation();
                FragmentUtils.showHide(fragment);
        }
    }

    /**
     * 未选中tab的逻辑
     *
     * @param tab
     */
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        LinearLayout layout = (LinearLayout) tab.getCustomView();
        Logger.d("当前布局下子布局个数为：" + layout.getChildCount());
        ImageView imageView = (ImageView) layout.getChildAt(0);
        Drawable drawable = imageView.getDrawable();
        drawable.setTint(Color.parseColor("#FFCCCCCC"));
    }

    /**
     * 再次选中tab的逻辑
     *
     * @param tab
     */
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
