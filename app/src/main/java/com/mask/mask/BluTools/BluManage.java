package com.mask.mask.BluTools;

import com.mask.mask.BluTools.ServiceThread;

import java.io.Serializable;

public interface BluManage extends Serializable{

    /**
     * 打开蓝牙
     * @return
     */
    boolean openBlu();

    /**
     * 关闭蓝牙
     * @return
     */
    boolean closeBlu();

    /**
     * 写入数据
     * @param data
     * @return
     */
    boolean writeData(String data);


    /**
     * 获取连接管理
     * @return
     */
    ServiceThread connectionThread();

    /**
     * 显示已近配对过的蓝牙设备
     */
    void showBondedBluListView();

}
