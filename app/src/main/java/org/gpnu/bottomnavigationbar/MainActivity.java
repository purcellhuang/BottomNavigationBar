package org.gpnu.bottomnavigationbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;

import org.gpnu.bottomnavigationbar.fragment.HomeFragment;
import org.gpnu.bottomnavigationbar.fragment.MineFragment;
import org.gpnu.bottomnavigationbar.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar mBottomNavigationBar;

    // Fragment管理器，和执行器
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;

    private HomeFragment mHomeFragment;  // 首页
    private VideoFragment mVideoFragment; //电影
    private MineFragment mMineFragment;  //我的

    private int lastSelectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        //初始化底部导航
        initializeBottomNavigationBar();
        //设置监听
        mBottomNavigationBar.setTabSelectedListener(this);

        setDefaultFragment();
    }

    private void initializeBottomNavigationBar() {
        // TODO 设置模式
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        // TODO 设置背景色样式
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(R.color.tabBGColor);


        //消息提示
        TextBadgeItem mTextBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.info)
                .setText("5")
                .setTextColorResource(R.color.white)
                .setBorderColorResource(R.color.info)  //外边界颜色
                .setHideOnSelect(false);

        ShapeBadgeItem mShapeBadgeItem = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColor(R.color.info)
                .setShapeColorResource(R.color.info)
                .setSizeInDp(this, 10, 10)
                .setEdgeMarginInDp(this, 2)
//                .setSizeInPixels(30,30)
//                .setEdgeMarginInPixels(-1)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(false);


        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.tab_home_active, "首页").setActiveColorResource(R.color.tabActive)
                        .setInactiveIconResource(R.drawable.tab_home).setInActiveColorResource(R.color.tabInActive))
                .addItem(new BottomNavigationItem(R.drawable.tab_video_active, "电影").setActiveColorResource(R.color.tabActive)
                        .setInactiveIconResource(R.drawable.tab_video).setInActiveColorResource(R.color.tabInActive).setBadgeItem(mShapeBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.tab_mine_active, "我的").setActiveColorResource(R.color.tabActive)
                        .setInactiveIconResource(R.drawable.tab_mine).setInActiveColorResource(R.color.tabInActive).setBadgeItem(mTextBadgeItem))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();


    }


    @Override
    public void onTabSelected(int position) {

        lastSelectedPosition = position;
        //开启事务
        mTransaction = mManager.beginTransaction();
        hideFragment(mTransaction);

        /**
         * fragment 用 add + show + hide 方式
         * 只有第一次切换会创建fragment，再次切换不创建
         *
         * fragment 用 replace 方式
         * 每次切换都会重新创建
         *
         */
        switch (position) {
            case 0:   // 首页
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                    mTransaction.add(R.id.fl_content,
                            mHomeFragment);
                } else {
                    mTransaction.show(mHomeFragment);
                }
                break;
            case 1:    // 电影
                if (mVideoFragment == null) {
                    mVideoFragment = mVideoFragment.newInstance();
                    mTransaction.add(R.id.fl_content,
                            mVideoFragment);
                } else {
                    mTransaction.show(mVideoFragment);
                }
                break;
            case 2:  // 我的

                if (mMineFragment == null) {
                    mMineFragment = MineFragment.newInstance();
                    mTransaction.add(R.id.fl_content,
                            mMineFragment);
                } else {
                    mTransaction.show(mMineFragment);
                }


                break;
        }
        // 事务提交
        mTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }

        if (mVideoFragment != null) {
            transaction.hide(mVideoFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }

    private void setDefaultFragment() {
        mHomeFragment = HomeFragment.newInstance();
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        mTransaction.add(R.id.fl_content, mHomeFragment);
        mTransaction.commit();
    }
}
