package com.mask.mask.BluTools;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;

import com.mask.mask.BroadReceiver.BluBroadReceiver;

public class BlueTools {
    public Context mContext;
    public BluetoothAdapter mBluetoothAdapter;
    public BluetoothDevice mBluetoothDevice;

    public BlueTools(){

    }

    public BlueTools(Context context){
        mContext = context;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * 打开蓝牙
     */
    public void enable(){
        if (!mBluetoothAdapter.isEnabled()){
            mBluetoothAdapter.enable();
        }
    }

    /**
     * 开始搜索蓝牙设备
     */
    public void startDiscovery(){
        mBluetoothAdapter.startDiscovery();
    }

    /**
     * 注册发现蓝牙设备广播
     */
    public void registerBluFindReceiver(){
        BluBroadReceiver bluBroadReceiver = new BluBroadReceiver();
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        mContext.registerReceiver(bluBroadReceiver,intentFilter);
    }


}
