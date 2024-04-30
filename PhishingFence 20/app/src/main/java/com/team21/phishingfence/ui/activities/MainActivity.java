package com.team21.phishingfence.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.team21.phishingfence.R;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        this.drawer = findViewById(R.id.main);
        this.navigationView = findViewById(R.id.nav_view);
        this.toolbar = findViewById(R.id.toolbar);
        this.floatingActionButton = findViewById(R.id.floatingActionHomeButton);

        setDrawer();//设置侧边导航栏与顶部栏汉堡菜单
        setBackHomeButtonListener();//设置返回home页面按钮监听器
        setNavigationListener();//设置侧边导航栏按钮监听器
    }

    private void setDrawer() {
        setSupportActionBar(this.toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);//不现实标题
        }
        //提供汉堡菜单
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, this.drawer, this.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawer.addDrawerListener(toggle);
        toggle.syncState();//同步汉堡菜单状态
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // 处理菜单项点击事件
                menuItem.setChecked(true);  // 选中状态
                MainActivity.this.drawer.closeDrawer(GravityCompat.START);  // 点击后关闭抽屉
                return true;
            }
        });
    }

    private void setBackHomeButtonListener() {
        //返回home页面按钮
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(MainActivity.this,R.id.fragmentContainerView);
                controller.navigate(R.id.homeFragment);
            }
        });
    }

    private void setNavigationListener() {
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                NavController controller = Navigation.findNavController(MainActivity.this, R.id.fragmentContainerView);

                int itemId = menuItem.getItemId();
                //侧边栏
                if (itemId == R.id.nav_newsfeed) {
                    controller.navigate(R.id.newsFeedFragment);
                } else if (itemId == R.id.nav_news_history) {
                    controller.navigate(R.id.historyFragment);
                } else if (itemId == R.id.nav_view_chart) {
                    controller.navigate(R.id.statisticalTrendFragment);
                } else if (itemId == R.id.nav_translate) {
                    controller.navigate(R.id.translateFragment);
                } else if (itemId == R.id.nav_verify) {
                    controller.navigate(R.id.verifyScamFragment);
                } else if (itemId == R.id.nav_emergency) {
                    controller.navigate(R.id.emergencyMenuFragment);
                } else {
                    finish();
                }

                MainActivity.this.drawer.close();
                return true;
            }

        });
    }
}