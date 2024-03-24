package com.example.phishingfence.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.phishingfence.R;
import com.example.phishingfence.ui.fragments.AwarnessFragment;
import com.example.phishingfence.ui.fragments.EducationFragment;
import com.example.phishingfence.ui.fragments.EmergencyFragment;
import com.example.phishingfence.ui.fragments.HomeFragment;
import com.example.phishingfence.ui.fragments.NewsFeedFragment;
import com.example.phishingfence.ui.fragments.ScamScenairoFragment;
import com.example.phishingfence.ui.fragments.StatisticalTrendFragment;
import com.example.phishingfence.ui.fragments.TranslationFragment;
import com.example.phishingfence.ui.fragments.VerifyScamFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    //声明私有成员变量
    private HomeFragment mHomeFragment;
    private TranslationFragment mTranslationFragment;
    private EmergencyFragment mEmergencyFragment;
    private AwarnessFragment mAwarnessFragment;
    private EducationFragment mEducationFragment;
    private ScamScenairoFragment mScamScenairoFragment;
    private VerifyScamFragment mVerifyScamFragment;
    private NewsFeedFragment mNewsFeedFragment;
    private StatisticalTrendFragment mStatisticalTrendFragment;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private ImageView mImg_home;
    private ImageView mImg_close;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mImg_home = findViewById(R.id.img_home);
        View headerView = mNavigationView.getHeaderView(0);
        mImg_close = headerView.findViewById(R.id.close);

        setupClickListeners();
    }

    private void setupClickListeners()
    {
        //设置点击监听器
        setupNavigationButtonListener();
        setupCloseButtonListener();
        setupHamMenuButtonListener();
        setupHomeButtonListener();
    }

    private void setupNavigationButtonListener()
    {
        //mDrawerLayout设置点击事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                if (item.getItemId()==R.id.nav_translation)
                {
                    selectedFragment(1);
                }
                else if (item.getItemId()==R.id.nav_emergency)
                {
                    selectedFragment(2);
                }
                else if (item.getItemId()==R.id.nav_awarness)
                {
                    selectedFragment(3);
                }
                else
                {
                    selectedFragment(4);
                }

                //点击按钮后关闭侧边抽屉
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //默认首页选中
        selectedFragment(0);
    }

    private void setupCloseButtonListener()
    {
        //关闭侧边栏点击事件
        mImg_close.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 关闭侧边抽屉
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void setupHamMenuButtonListener()
    {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 处理点击事件，比如打开或关闭DrawerLayout
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }
                else
                {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void setupHomeButtonListener()
    {
        //返回主页面点击事件
        mImg_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment(0);
            }
        });
    }

    private void selectedFragment(int position)
    {
        androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);

        //根据position实现跳转Fragment
        if (position == 0)
        {
            if(mHomeFragment == null)
            {
                mHomeFragment = new HomeFragment();
                fragmentTransaction.add(R.id.content,mHomeFragment);
                //设置home页面的点击监听器
                setupHomeFragmentListener();
            }
            else
            {
                fragmentTransaction.show(mHomeFragment);
            }
        }
        else if (position == 1)
        {
            if(mTranslationFragment == null)
            {
                mTranslationFragment = new TranslationFragment();
                fragmentTransaction.add(R.id.content,mTranslationFragment);
            }
            else
            {
                fragmentTransaction.show(mTranslationFragment);
            }
        }
        else if (position == 2)
        {
            if(mEmergencyFragment == null)
            {
                mEmergencyFragment = new EmergencyFragment();
                fragmentTransaction.add(R.id.content,mEmergencyFragment);
            }
            else
            {
                fragmentTransaction.show(mEmergencyFragment);
            }
        }
        else if (position == 3)
        {
            if(mAwarnessFragment == null)
            {
                mAwarnessFragment = new AwarnessFragment();
                fragmentTransaction.add(R.id.content,mAwarnessFragment);
            }
            else
            {
                fragmentTransaction.show(mAwarnessFragment);
            }
        }
        else if(position == 4)
        {
            if(mEducationFragment == null)
            {
                mEducationFragment = new EducationFragment();
                fragmentTransaction.add(R.id.content,mEducationFragment);
            }
            else
            {
                fragmentTransaction.show(mEducationFragment);
            }
        }
        else if(position == 5)
        {
            if(mScamScenairoFragment == null)
            {
                mScamScenairoFragment = new ScamScenairoFragment();
                fragmentTransaction.add(R.id.content,mScamScenairoFragment);
                //设置scam scenairo页面的点击监听器
                setupScamScenairoFragmentListener();
            }
            else
            {
                fragmentTransaction.show(mScamScenairoFragment);
            }
        }
        else if(position == 6)
        {
            if(mVerifyScamFragment == null)
            {
                mVerifyScamFragment = new VerifyScamFragment();
                fragmentTransaction.add(R.id.content,mVerifyScamFragment);
            }
            else
            {
                fragmentTransaction.show(mVerifyScamFragment);
            }
        }
        else if(position == 7)
        {
            if(mNewsFeedFragment == null)
            {
                mNewsFeedFragment = new NewsFeedFragment();
                fragmentTransaction.add(R.id.content,mNewsFeedFragment);
                setupNewsFeedFragmentListener();
            }
            else
            {
                fragmentTransaction.show(mNewsFeedFragment);
            }
        }
        else if(position == 8)
        {
            if(mStatisticalTrendFragment == null)
            {
                mStatisticalTrendFragment = new StatisticalTrendFragment();
                fragmentTransaction.add(R.id.content,mStatisticalTrendFragment);
            }
            else
            {
                fragmentTransaction.show(mStatisticalTrendFragment);
            }
        }

        //提交
        fragmentTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction)
    {
        //隐藏所有Fragment
        if(mHomeFragment!=null)
        {
            fragmentTransaction.hide(mHomeFragment);
        }

        if(mTranslationFragment!=null)
        {
            fragmentTransaction.hide(mTranslationFragment);
        }

        if(mEmergencyFragment!=null)
        {
            fragmentTransaction.hide(mEmergencyFragment);
        }

        if(mAwarnessFragment!=null)
        {
            fragmentTransaction.hide(mAwarnessFragment);
        }

        if(mEducationFragment!=null)
        {
            fragmentTransaction.hide(mEducationFragment);
        }

        if(mScamScenairoFragment!=null)
        {
            fragmentTransaction.hide(mScamScenairoFragment);
        }

        if(mVerifyScamFragment!=null)
        {
            fragmentTransaction.hide(mVerifyScamFragment);
        }

        if(mNewsFeedFragment!=null)
        {
            fragmentTransaction.hide(mNewsFeedFragment);
        }

        if(mStatisticalTrendFragment!=null)
        {
            fragmentTransaction.hide(mStatisticalTrendFragment);
        }
    }

    public void setupHomeFragmentListener()
    {
        mHomeFragment.setOnHomeFragmentInteractionListener(new HomeFragment.OnHomeFragmentInteractionListener() {
            @Override
            public void onScamScenarioClick() {
                selectedFragment(5);
            }

            @Override
            public void onVerifyScamClick() {
                selectedFragment(6);
            }
        });
    }

    public void setupScamScenairoFragmentListener()
    {
        mScamScenairoFragment.setOnScamScenairoFragmentInteractionListener(new ScamScenairoFragment.OnScamScenairoFragmentInteractionListener() {
            @Override
            public void onNewsFeedClick() {
                selectedFragment(7);
            }

            @Override
            public void onViewStatisticsClick() {
                selectedFragment(8);
            }
        });
    }

    public void setupNewsFeedFragmentListener()
    {
        mNewsFeedFragment.setOnNewsFeedFragmentInteractionListener(new NewsFeedFragment.OnNewsFeedFragmentInteractionListener() {
            @Override
            public void onBackClick() {
                selectedFragment(5);
            }
        });
    }
}