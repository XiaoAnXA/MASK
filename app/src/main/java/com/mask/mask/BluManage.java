package com.mask.mask;

import com.mask.mask.BluTools.ConnectionManagementThread;

public interface BluManage {

    boolean openBlu();
    boolean closeBlu();
    boolean writeData(String data);
    String ReadData(String readAdd);
    ConnectionManagementThread connectionThread();

}
