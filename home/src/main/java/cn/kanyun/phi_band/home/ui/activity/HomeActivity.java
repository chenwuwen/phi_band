package cn.kanyun.phi_band.home.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import cn.kanyun.phi_band.home.R;

/**
 * 当该模块作为application运行时,此Activity将作为入口Activity
 * 该Activity使用了Navigation 具体显示是交给Fragment的
 */
public class HomeActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_resources_prefix_activity_home);
        navController = Navigation.findNavController(this, R.id.home_nav);
        NavigationUI.setupActionBarWithNavController(this, navController);


    }
}
