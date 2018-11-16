package com.mask.mask.BroadReceiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 *
 */
public class BluBroadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_FOUND.equals(action)){
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (device.getName().equals("MASK")){  //如果找到目标设备就关闭蓝牙搜索
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();

            }
        }

    }
}
