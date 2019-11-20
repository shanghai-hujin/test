package com.example.hasee.ui.drawer;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class ConnectThread extends Thread {

    private final BluetoothDevice mDevice;

    public ConnectThread(BluetoothDevice device) {
        this.mDevice = device;

        BluetoothSocket mSockt = null;

    }
}
