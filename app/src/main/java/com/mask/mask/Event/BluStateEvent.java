package com.mask.mask.Event;

/**
 * 蓝牙状态事件
 */
public class BluStateEvent {
    public String state;
    public int bluEvent;
    public BluStateEvent(String msg,int event){
        state = msg;
        bluEvent = event;
    }
}
