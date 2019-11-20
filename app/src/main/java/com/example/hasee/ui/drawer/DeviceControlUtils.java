package com.example.hasee.ui.drawer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

@SuppressLint("NewApi")
public class DeviceControlUtils {
    private final static String TAG = "BluetoothLeService";

    public static DeviceControlUtils mDeviceControlUtils = null;

    /**
     * 获取DeviceControlUtils单利
     * @param mContext
     * @return
     */
    public static  DeviceControlUtils getInstance(Activity mContext){
        if(mDeviceControlUtils==null){
            mDeviceControlUtils = new DeviceControlUtils(mContext);
        }
        return mDeviceControlUtils;
    }

    /**
     * 构造函数
     * @param mContext
     */
    private DeviceControlUtils(Context mContext){
        this.mContext = mContext;
    }

    private BluetoothLeService mBluetoothLeService=null;
    private Context mContext = null;
    private String mDeviceAddress = null;
    private String mDeviceName = null;
    private boolean mConnected = false;

    /**
     * 初始化蓝牙逻辑
     * @param deviceAddress
     */
    public void init(String deviceAddress){
        this.mDeviceAddress = deviceAddress;

        //先注册蓝牙服务
        Intent gattServiceIntent = new Intent(mContext, BluetoothLeService.class);
        boolean bll = mContext.bindService(gattServiceIntent, mServiceConnection,mContext.BIND_AUTO_CREATE);
        if (bll) {
            System.out.println("---------------");
        } else {
            System.out.println("===============");
        }

        //在注册Receiver
        mContext.registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }

    }

    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
            }
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
               if(mBluetoothLeService!=null)
                    displayGattServices(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                //获取数据
//                displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };


    /**
     * 手动处理连接service
     */
    public void connectedService(){
        mBluetoothLeService.connect(mDeviceAddress);
    }

    /**
     * 手动断开service连接
     */
    public void disconnectService(){
        if(mServiceConnection!=null) {
            mBluetoothLeService.disconnect();
        }
    }

    /**
     * 解绑service
     */
    public void onDestroy() {
        if(mServiceConnection!=null) {
            mContext.unbindService(mServiceConnection);
            mBluetoothLeService = null;
        }
    }

    /**
     * 解绑Receiver
     */
    public void onPause() {
        if(mConnected) {
            mContext.unregisterReceiver(mGattUpdateReceiver);
            mConnected = false;
        }
    }

    /**
     * 释放关闭蓝牙服务的部分
     */
    public void releaseBluetoothService(){
        if(mBluetoothLeService!=null) {
            mBluetoothLeService.close();
            disconnectService();
            onPause();
            onDestroy();
        }
    }

    /**
     * 展示gattServices里的特征点，过滤UUID执行不同的操作
     * @param gattServices
     */
    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null)
            return;
        String uuid = null;
        for (BluetoothGattService gattService : gattServices) {
            uuid = gattService.getUuid().toString();
            Log.d(TAG, "displayGattServices: "+uuid);
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                uuid = gattCharacteristic.getUuid().toString();
                if (uuid.toUpperCase().contains(BluetoothLeService.BLUETOOTH_WHITE_ORDER_TO_LOCK_UUID)) {
                    //写入数据
                    writeDataToLock(gattCharacteristic);
                }else if (uuid.toUpperCase().contains(BluetoothLeService.BLUETOOTH_LOCK_NOTIFY_READ_UUID)) {
                    if(mBluetoothLeService!=null)
                        mBluetoothLeService.setCharacteristicNotification(gattCharacteristic, true);
                }
            }
        }
    }

    /**
     * 写入数据给蓝牙锁 https://blog.csdn.net/java__han/article/details/77848223
     * @param gattCharacteristic
     */
    public void writeDataToLock(BluetoothGattCharacteristic gattCharacteristic){
        byte [] send = new byte[20];
        byte[] downOrder = { (byte) 0x95,0x04, 0x20, 0x40, 0x00, 0x01,0x00,0x00};
        int crc16Text = CRC16CheckUtil.getCRC16(send,send.length-2);
        //String crc16result = String.format("%02x",crc16Text);
        send[6]=(byte) Integer.parseInt(Integer.toHexString(crc16Text&0xff), 16);
        send[7]=(byte) Integer.parseInt(Integer.toHexString((crc16Text>>8)&0xff), 16);
        gattCharacteristic.setValue(send);
        boolean status =mBluetoothLeService.writeCharacteristic(gattCharacteristic);
        if(status){

        }
    }


    /**
     * 过滤ACTION
     * @return
     */
    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        intentFilter.addAction(BluetoothLeService.EXTRA_DATA);
        return intentFilter;
    }


}
