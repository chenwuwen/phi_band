package cn.kanyun.phi_band.listener;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;

/**
 * Tab选中监听
 */
public class TabSwitchListener implements TabLayout.OnTabSelectedListener {

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
        TextView textView = (TextView) layout.getChildAt(1);

        Drawable drawable = imageView.getDrawable();

    }

    /**
     * 未选中tab的逻辑
     *
     * @param tab
     */
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

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
