package com.mask.mask.BluTools;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;

import com.mask.mask.Event.BluStateEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BlueTools {
    private static BlueTools mBlueTools;
    public Context mContext;
    public BluetoothAdapter mBluetoothAdapter;

    public static BlueTools getBlueTools(Context context){
        if (mBlueTools != null){
            return mBlueTools;
        }
        mBlueTools = new BlueTools(context);
        return mBlueTools;
    }

    public BlueTools(){

    }

    public BlueTools(Context context){
        mContext = context;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * 打开蓝牙
     */
    public boolean enableBlu(){
        if (!mBluetoothAdapter.isEnabled()){
            mBluetoothAdapter.enable();
        } else {
            EventBus.getDefault().post(new BluStateEvent("蓝牙已打开",BluetoothAdapter.STATE_ON));
        }
        return true;
    }

    /**
     * 开始搜索蓝牙设备
     *
     */
    public void startDiscovery(){
        if (mBluetoothAdapter!=null) {
            if (mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.startDiscovery();
            } else {
                enableBlu();
                startDiscovery();
            }
        }
    }

    /**
     * 关闭蓝牙
     */
    public boolean disableBlu(){
        if (mBluetoothAdapter!=null) {
            mBluetoothAdapter.disable();
            return true;
        }
        return false;
    }

    /**
     * 获取已经配对的设备
     *
     * @return
     */
    public List<BluetoothDevice> getBondedDevices() {
        List<BluetoothDevice> devices = new ArrayList<>();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // 判断是否有配对过的设备
        if (pairedDevices.size() > 0) {
            devices.addAll(pairedDevices);
        }
        return devices;
    }
    /**
     * 获取已经配对的设备
     *
     * @return
     */
    public List<String> getBondedDevicesName() {
        List<BluetoothDevice> devices = new ArrayList<>();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // 判断是否有配对过的设备
        if (pairedDevices.size() > 0) {
            devices.addAll(pairedDevices);
        }
        List<String> devicesName = new ArrayList<>();
        for (BluetoothDevice device : devices){
            devicesName.add(device.getName());
        }
        return devicesName;
    }
}
