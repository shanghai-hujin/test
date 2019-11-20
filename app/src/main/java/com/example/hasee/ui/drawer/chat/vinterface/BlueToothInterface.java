package com.example.hasee.ui.drawer.chat.vinterface;

import android.bluetooth.BluetoothDevice;

/**
 * Created by ZengZeHong on 2017/5/15.
 */

public interface BlueToothInterface {
    void getBlutToothDevices(BluetoothDevice device, int rssi);
    void searchFinish();
}
