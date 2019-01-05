package com.mask.mask.BluTools;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;

import com.mask.mask.Event.BluStateEvent;
import com.mask.mask.Event.ReceiveData;
import com.mask.mask.Util.DataExchangeUtil;
import com.mask.mask.Util.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * 接收和发送数据操作类
 */
public class ServiceThread extends Thread {

    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    public ServiceThread(BluetoothSocket socket) {
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
        int length;
        while (true) {
            try {
                length = mmInStream.read(buffer);

                String data = new String(Arrays.copyOf(buffer,length),"utf-8");
                LogUtil.e("接收的数据", "run:"+data);
                EventBus.getDefault().post(new ReceiveData(""));
            } catch (IOException e) {
                EventBus.getDefault().post(new BluStateEvent("已断开连接", BluetoothAdapter.STATE_DISCONNECTED));
                break;
            }
        }
    }

    public void write(final String data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mmOutStream.write(DataExchangeUtil.HexString2Bytes(data));
                    mmOutStream.flush();
                    LogUtil.e("发送的数据", "run: "+String.valueOf(data));
                } catch (IOException e) {
                    EventBus.getDefault().post(new BluStateEvent("已断开连接", BluetoothAdapter.STATE_DISCONNECTED));
                }
            }
        }).start();
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
