package com.mask.mask.APP;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.mask.mask.BluTools.BluManage;
import com.mask.mask.BluTools.BlueTools;
import com.mask.mask.BluTools.ServiceThread;
import com.mask.mask.BluTools.ConnectionThread;
import com.mask.mask.Event.BluStateEvent;
import com.mask.mask.R;
import com.mask.mask.ViewAdapter.SectionsPagerAdapter;
import com.mask.mask.Fragment.MeFragment;
import com.mask.mask.Fragment.ModeFragment;
import com.mask.mask.Fragment.UpdateFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 * 三个碎片，一个模式，一个更新，一个我的
 *管理蓝牙连接
 *
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener,BluManage {

    public final static String TAG = MainActivity.class.getSimpleName();

    public BottomNavigationBar mBnbBar;
    public ViewPager mViewPager;

    public BlueTools mBlueTools;
    public BluBroadReceiver mBluBroadReceiver;
    public ConnectionThread mConnectionThread;

    public AlertDialog mAlertDialog;
    public ServiceThread mServiceThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        registerBluReceiver();
        mBlueTools = BlueTools.getBlueTools(this);
        initView();
        mBlueTools.enableBlu();
    }


    /**
     * 注册蓝牙广播接收器
     */
    public void registerBluReceiver(){
        mBluBroadReceiver = new BluBroadReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);//蓝牙状态
        registerReceiver(mBluBroadReceiver,intentFilter);
    }

    /**
     * 注册蓝牙广播接收器
     */
    public void unRegisterBluReceiver(){
        if (mBluBroadReceiver!=null){
            unregisterReceiver(mBluBroadReceiver);
        }
    }

    private void initView() {
        mViewPager = findViewById(R.id.main_vp_content);
        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(),getFragments()));
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(3);
        mBnbBar = findViewById(R.id.main_bnb_bar);
        mBnbBar.addItem(new BottomNavigationItem(R.drawable.main_bnb_item_mode,"模式"))
            .addItem(new BottomNavigationItem(R.drawable.main_bnb_item_update,"更新"))
            .addItem(new BottomNavigationItem(R.drawable.main_bnb_item_me,"我的"))
            .setFirstSelectedPosition(0)
            .initialise();
        mBnbBar.setTabSelectedListener(this);
    }

    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(ModeFragment.newInstance(this));
        fragmentArrayList.add(UpdateFragment.newInstance(this));
        fragmentArrayList.add(new MeFragment());
        return fragmentArrayList;
    }

    //BottomNavigationBar的监听事件
    @Override
    public void onTabSelected(int position) {
        mViewPager.setCurrentItem(position);

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    //ViewPage的监听事件
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mBnbBar.selectTab(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unRegisterBluReceiver();
        mBlueTools.disableBlu();
    }

    AlertDialog alertDialog;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("确定退出?")
                    .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           alertDialog.dismiss();
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
        }
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setConnectionThread(ServiceThread serviceThread){
        mServiceThread = serviceThread;
    }

    @Override
    public boolean openBlu() {
        return mBlueTools.enableBlu();
    }

    @Override
    public boolean closeBlu() {
        return mBlueTools.disableBlu();
    }

    @Override
    public boolean writeData(String data) {
        if(mServiceThread != null){
            mServiceThread.write(data);
        }
        return false;
    }


    @Override
    public ServiceThread connectionThread() {
        return mServiceThread;
    }

    @Override
    public void showBondedBluListView() {
        final List<BluetoothDevice> devices = mBlueTools.getBondedDevices();
        List<String> devicesName = new ArrayList<>();
        for (BluetoothDevice device : devices){
            devicesName.add(device.getName());
        }
        ListView listView = new ListView(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,devicesName);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAlertDialog.dismiss();
                mConnectionThread = new ConnectionThread(devices.get(position));
                mConnectionThread.start();
                EventBus.getDefault().post(new BluStateEvent("正在连接",BluetoothAdapter.STATE_CONNECTING));
            }
        });
        mAlertDialog = new AlertDialog.Builder(this)
                .setTitle("请选择MASK设配")
                .setView(listView).show();
    }

}
