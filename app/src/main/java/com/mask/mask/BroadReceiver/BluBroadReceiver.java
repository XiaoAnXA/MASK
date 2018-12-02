package com.mask.mask.BroadReceiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mask.mask.BluTools.ClsUtils;
import com.mask.mask.Event.BluStateEvent;
import com.mask.mask.Util.LogUtil;

import org.greenrobot.eventbus.EventBus;


/**
 *监控本地蓝牙状态
 */
public class BluBroadReceiver extends BroadcastReceiver {
    public String TAG = "BluBroadReceiver";
    public static final String FLAG_BLU = "TH-05";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null){
            return;
        }
        switch (action){
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                int BluState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);
                LogUtil.e(TAG, "onReceiveACTION_STATE_CHANGED: "+String.valueOf(BluState));
                switch (BluState){
                    case BluetoothAdapter.STATE_OFF:
                        EventBus.getDefault().post(new BluStateEvent("蓝牙已关闭",BluetoothAdapter.STATE_OFF));
                        break;
                    case BluetoothAdapter.STATE_ON:
                        EventBus.getDefault().post(new BluStateEvent("蓝牙已打开",BluetoothAdapter.STATE_ON));
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        EventBus.getDefault().post(new BluStateEvent("蓝牙正在关闭",BluetoothAdapter.STATE_TURNING_OFF));
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        EventBus.getDefault().post(new BluStateEvent("蓝牙正在关闭",BluetoothAdapter.STATE_TURNING_ON));
                        break;
                }
                break;
            case BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED:
                break;
            case BluetoothDevice.ACTION_FOUND://发现设备
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                if (deviceName.equals(FLAG_BLU)){
                    if (device.getBondState() == BluetoothDevice.BOND_NONE){
                        try {
                            ClsUtils.createBond(device.getClass(), device);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case BluetoothDevice.ACTION_PAIRING_REQUEST://配对请求
                LogUtil.e("配对请求:","正在配对吧");
                break;
        }

    }
}
