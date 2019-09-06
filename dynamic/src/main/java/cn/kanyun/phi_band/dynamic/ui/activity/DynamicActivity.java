package cn.kanyun.phi_band.dynamic.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import cn.kanyun.phi_band.dynamic.R;

/**
 * 当该模块作为application运行时,此Activity将作为入口Activity
 * 该Activity使用了Navigation 具体显示是交给Fragment的
 */
public class DynamicActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_resources_prefix_activity_dynamic);
        navController = Navigation.findNavController(this, R.id.dynamic_nav);
        NavigationUI.setupActionBarWithNavController(this, navController);

    }
}
