package com.mask.mask.BluTools;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.mask.mask.BluTools.ConnectionManagementThread;
import com.mask.mask.Event.BluStateEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.UUID;

/**
 * 蓝牙连接类
 */
public class ConnectionThread extends Thread {

    public final static String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";

    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    public ConnectionManagementThread mConnectionManagementThread;

    public ConnectionThread(BluetoothDevice device) {
        BluetoothSocket tmp = null;
        mmDevice = device;
        try {
            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(MY_UUID));
        } catch (IOException e) { }
        mmSocket = tmp;
    }

    public void run() {
        try {
            mmSocket.connect();
            EventBus.getDefault().post(new BluStateEvent("已连接", BluetoothAdapter.STATE_CONNECTED));
        } catch (IOException connectException) {
            try {
                mmSocket.close();
                EventBus.getDefault().post(new BluStateEvent("已断开连接", BluetoothAdapter.STATE_CONNECTED));
            } catch (IOException closeException) { }
            return;
        }
        mConnectionManagementThread = new ConnectionManagementThread(mmSocket);
        mConnectionManagementThread.start();
        EventBus.getDefault().post(mConnectionManagementThread);
    }

    public void cancel() {
        try {
            mmSocket.close();
            EventBus.getDefault().post(new BluStateEvent("已断开连接", BluetoothAdapter.STATE_CONNECTED));
        } catch (IOException e) { }
    }
}
