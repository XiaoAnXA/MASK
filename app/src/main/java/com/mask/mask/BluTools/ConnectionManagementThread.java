package com.mask.mask.BluTools;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.mask.mask.Event.BluStateEvent;
import com.mask.mask.Util.DataExchangeUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * 接收和发送数据操作类
 */
public class ConnectionManagementThread extends Thread {

    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    public ConnectionManagementThread(BluetoothSocket socket) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) {

        }
        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run() {
        byte[] buffer = new byte[1024];
        int bytes;
        while (true) {
            try {
                bytes = mmInStream.read(buffer);
                Log.e("接收的数据", "run:"+ DataExchangeUtil.HexToString(buffer));
                Log.e("接收的数据", "run:"+ Arrays.toString(buffer));
            } catch (IOException e) {
                EventBus.getDefault().post(new BluStateEvent("已断开连接", BluetoothAdapter.STATE_DISCONNECTED));
                break;
            }
        }
    }

    public void write(String data) {
        try {
            mmOutStream.write(DataExchangeUtil.HexString2Bytes(data));
            mmOutStream.flush();
            Log.e("发送的数据", "run: "+String.valueOf(data));
        } catch (IOException e) {
            EventBus.getDefault().post(new BluStateEvent("已断开连接", BluetoothAdapter.STATE_DISCONNECTED));
        }
    }

    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
        }
        finally {
            EventBus.getDefault().post(new BluStateEvent("已断开连接", BluetoothAdapter.STATE_DISCONNECTED));
        }
    }
}
