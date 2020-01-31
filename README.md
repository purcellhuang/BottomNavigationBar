# BottomNavigationBar
基本使用
效果如下图：


## 1.导入相关依赖
**GitHub:[GitHub:https://github.com/Ashok-Varma/BottomNavigation](https://github.com/Ashok-Varma/BottomNavigation)**
```
    添加依赖：
    implementation 'com.ashokvarma.android:bottom-navigation-bar:2.1.0'
```
## 2.布局文件添加布局
```
        <com.ashokvarma.bottomnavigation.BottomNavigationBar
        android:id="@+id/bottom_navigation_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```
## 3.Java代码中添加属性配置
```
public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar mBottomNavigationBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        initializeBottomNavigationBar();

        //添加监听  实现BottomNavigationBar.OnTabSelectedListener接口
        //public void onTabSelected(int position) 未选中 -> 选中
        //public void onTabUnselected(int position) 选中 -> 未选中
        //public void onTabReselected(int position) 选中 -> 选中
        mBottomNavigationBar.setTabSelectedListener(this);
    }

     private void initializeBottomNavigationBar() {
        // TODO 设置模式
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        // TODO 设置背景色样式
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(R.color.tabBGColor);

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.tab_home_active, "首页").setActiveColorResource(R.color.tabActive)
                        .setInactiveIconResource(R.drawable.tab_home).setInActiveColorResource(R.color.tabInActive))
                .addItem(new BottomNavigationItem(R.drawable.tab_video_active, "电影").setActiveColorResource(R.color.tabActive)
                        .setInactiveIconResource(R.drawable.tab_video).setInActiveColorResource(R.color.tabInActive))
                .addItem(new BottomNavigationItem(R.drawable.tab_mine_active, "我的").setActiveColorResource(R.color.tabActive)
                        .setInactiveIconResource(R.drawable.tab_mine).setInActiveColorResource(R.color.tabInActive))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();

    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
```
**三种模式：**

* MODE_DEFAULT
如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式

* MODE_FIXED （固定大小）
填充模式，未选中的Item会显示文字，没有换挡动画。
宽度=总宽度/action个数
最大宽度: 168dp
最小宽度: 80dp
Padding：6dp（8dp）、10dp、12dp
字体大小：12sp、14sp

* MODE_SHIFTING （不固定大小）
换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画

**三种Style：**

* BACKGROUND_STYLE_DEFAULT
如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。

* BACKGROUND_STYLE_STATIC
点击的时候没有水波纹效果
航条的背景色是白色，加上setBarBackgroundColor（）可以设置成你所需要的任何背景颜色

* BACKGROUND_STYLE_RIPPLE
点击的时候有水波纹效果
导航条的背景色是你设置的处于选中状态的 Item的颜色（ActiveColor），也就是setActiveColorResource这个设置的颜色

## 4.设置Badges，一般用于消息提醒
```
 mTextBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.main_color)
                .setText("5")
                .setTextColorResource(R.color.white)
                .setBorderColorResource(R.color.colorPrimaryDark)  //外边界颜色
                .setHideOnSelect(false);

        mShapeBadgeItem = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColor(R.color.main_color)
                .setShapeColorResource(R.color.main_color)
                .setSizeInDp(this,10,10)
                .setEdgeMarginInDp(this,2)
//                .setSizeInPixels(30,30)
//                .setEdgeMarginInPixels(-1)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(false);
```
## 5.手动显示与隐藏
```
bottomNavigationBar.hide();//隐藏
bottomNavigationBar.hide(true);//隐藏是否启动动画，这里并不能自定义动画
bottomNavigationBar.unHide();//显示
bottomNavigationBar.hide(true);//隐藏是否启动动画，这里并不能自定义动画
```
