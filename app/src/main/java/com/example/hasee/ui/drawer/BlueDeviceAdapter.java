package com.example.hasee.ui.drawer;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hasee.R;

import java.util.ArrayList;

public class BlueDeviceAdapter extends BaseQuickAdapter<BluetoothDevice, BaseViewHolder> {

    public BlueDeviceAdapter( @Nullable ArrayList<BluetoothDevice> data) {
        super(R.layout.item_blue_device, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, BluetoothDevice item) {
        if(!TextUtils.isEmpty(item.getName())){
            helper.setText(R.id.tv_device_name,item.getName());

        }else {
            helper.setText(R.id.tv_device_name,"无名蓝牙");

        }
        helper.setText(R.id.tv_device_adress,item.getAddress());
        helper.setText(R.id.tv_blue_time,"type值=="+item.getType());
    }
}
