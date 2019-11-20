package com.example.hasee.ui.drawer;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hasee.R;
import com.example.hasee.ui.main.LoginEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

public class BluetoothActivity extends Activity {

    private static final int REQUEST_ENBLE_BT = 2019;
    private BluetoothAdapter mBluetoothAdapter;
    private Button btnOpenBlue;
    private Button btnFindBlue;

    //已配对的设备集合
    private Set<BluetoothDevice> bondedDevices ;
    private Set<String> bondedAdress = new HashSet<>();
    private RecyclerView rvDevicer;

    private ArrayList<BluetoothDevice> devicesList = new ArrayList<>();
    private ArrayList<BluetoothDevice> devicesList1 = new ArrayList<>();
    private BlueDeviceAdapter blueDeviceAdapter;
    private Button btnSearcherBlue;
    private RecyclerView headHasPiPei;
    private BlueDeviceAdapter blueDeviceAdapter1;
    private ScanSettings mScannerSetting;
    private BLEScanCallback mScannerCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bluetooth);
        btnOpenBlue = (Button) findViewById(R.id.btn_open_blue);
        btnOpenBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBlue();

            }
        });

        btnFindBlue = (Button) findViewById(R.id.btn_hav_blue);
        btnFindBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryHasLinkBlue();

            }
        });

        //00001101-0000-1000-8000-00805F9B34FB
        btnSearcherBlue = (Button) findViewById(R.id.btn_searcher_blue);
        btnSearcherBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new LoginEvent());
                searcherDevices();
            }
        });


        rvDevicer = (RecyclerView) findViewById(R.id.rv_devices_has);
        rvDevicer.setLayoutManager(new LinearLayoutManager(BluetoothActivity.this));
        blueDeviceAdapter = new BlueDeviceAdapter(devicesList);
        rvDevicer.setAdapter(blueDeviceAdapter);


        // 注册这个 BroadcastReceiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);// 用BroadcastReceiver来取得搜索结果
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);

        registerReceiver(broadcastReceiver, filter);

       //  搜索完成的广播
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(broadcastReceiver, filter1);

        boolean gpsEnable = isGpsEnable();

        Log.e("BluetoothActivity==", "gpsEnable: ===" + gpsEnable);

        blueDeviceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                BluetoothDevice deviceBlueBean = devicesList.get(position);
//                Method method = null;
//                try {
//                    method = BluetoothDevice.class.getMethod("createBond");
//                    Log.e("BluetoothActivity", "开始配对");
//                    method.invoke(deviceBlueBean);
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }

                BluetoothDevice bluetoothDevice = devicesList.get(position);
                BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(bluetoothDevice.getAddress());
                DeviceControlUtils.getInstance(BluetoothActivity.this).init(device.getAddress());
            }
        });


        View inflate = LayoutInflater.from(BluetoothActivity.this).inflate(R.layout.head_blue, null);
        blueDeviceAdapter.addHeaderView(inflate);
        headHasPiPei = (RecyclerView) inflate.findViewById(R.id.rv_has_blue);
        headHasPiPei.setLayoutManager(new LinearLayoutManager(BluetoothActivity.this));
        blueDeviceAdapter1 = new BlueDeviceAdapter(devicesList1);
        headHasPiPei.setAdapter(blueDeviceAdapter1);
        blueDeviceAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BluetoothDevice bluetoothDevice = devicesList1.get(position);
                BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(bluetoothDevice.getAddress());
                DeviceControlUtils.getInstance(BluetoothActivity.this).init(device.getAddress());
            }
        });
}

    //gps是否可用(有些设备可能需要定位)
    public boolean isGpsEnable() {
        LocationManager locationManager
                = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }


    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                //TODO 提示权限已经被禁用 且不在提示
                return;
            }
            Log.e("BluetoothActivity==", "地理权限---");

        } else {

        }
    }


    /**
     * 搜索周边可检测蓝牙
     * 链接后，记得关闭cancelDiscovery 停止搜索
     */
    private void searcherDevices() {
        // 判断是否在搜索,如果在搜索，就取消搜索
        if (mBluetoothAdapter.isDiscovering()) {
            Log.e("BluetoothActivity==", "正在搜索---");
            mBluetoothAdapter.cancelDiscovery();
        }
        // 开始搜索
//        mBluetoothAdapter.startDiscovery();

//       new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("BluetoothActivity==", "run: saomiao ...");
//                mBluetoothAdapter.startLeScan(callback);
//            }
//        }).start();

        mScannerSetting = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .build();

        BluetoothLeScanner scanner = mBluetoothAdapter.getBluetoothLeScanner();

        mScannerCallback = new BLEScanCallback();

// 这个方法可以有参数，用来过滤要扫描的低功耗蓝牙的，具体的后面讲
        scanner.startScan(null,mScannerSetting,mScannerCallback);


    }

    private class BLEScanCallback extends ScanCallback{


        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice bluetoothDevice = result.getDevice();
            Log.i("TAG", "onLeScan: " + bluetoothDevice.getName() + "/t" + bluetoothDevice.getAddress() + "/n" + bluetoothDevice.getBondState());

            //z找到需要链接的mac蓝牙  然后停止扫描

           super.onScanResult(callbackType, result);
        }


        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    }

    public BluetoothAdapter.LeScanCallback callback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
            Log.i("BluetoothActivity", "onLeScan: " + bluetoothDevice.getName() + "/t" + bluetoothDevice.getAddress() + "/t" + bluetoothDevice.getBondState());

//            //重复过滤方法，列表中包含不该设备才加入列表中，并刷新列表
//            if (!deviceList.contains(bluetoothDevice)) {
//                //将设备加入列表数据中
//                deviceList.add(bluetoothDevice);
//            }

        }
    };

    /**
     * 查找蓝牙 已配对的设备集合
     */
    private void queryHasLinkBlue() {
        bondedDevices =mBluetoothAdapter.getBondedDevices();
        if (bondedDevices.size() > 0) {
            devicesList.clear();
            for (BluetoothDevice device : bondedDevices) {
                // 把名字和地址取出来添加到适配器中
                devicesList1.add(device);
            }
            blueDeviceAdapter1.notifyDataSetChanged();
        }
    }

    private void openBlue() {
        if (mBluetoothAdapter != null) {
            //启用
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENBLE_BT);
            btnOpenBlue.setText("蓝牙已开启");
            //第二种方式
            // mBluetoothAdapter.enable();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initBlue();

    }

    private void initBlue() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toasty.error(BluetoothActivity.this, "此设备不支持蓝牙").show();
        }

        if (mBluetoothAdapter.isEnabled()) {
        } else {
            //没有开启蓝牙
            btnOpenBlue.setText("开启蓝牙");
            Toasty.error(BluetoothActivity.this, "没有开启蓝牙，手动开启").show();
        }

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            // 不支持 BLE 设备
            Toasty.error(BluetoothActivity.this, "不支持 BLE 设备").show();
        }
    }


    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                BluetoothClass bluetoothClass = intent.getParcelableExtra(BluetoothDevice.EXTRA_CLASS);

                if(bluetoothDevice != null){
                    Log.e("BluetoothActivity地址==", bluetoothDevice.getAddress());
                    if (bluetoothDevice.getName() != null) {
                        Log.e("BluetoothActivity名字==", bluetoothDevice.getName());
                    }
                    devicesList.add(bluetoothDevice);
                }

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                blueDeviceAdapter.notifyDataSetChanged();
                mBluetoothAdapter.stopLeScan(callback);
                mBluetoothAdapter.cancelDiscovery();
            }else if(BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                switch (device.getBondState()) {
                    case BluetoothDevice.BOND_BONDING:
                        Log.d("BlueToothTestActivity", "正在配对......");
                        break;
                    case BluetoothDevice.BOND_BONDED:
                        Log.d("BlueToothTestActivity", "完成配对");
                        connect(device);//连接设备
                        break;
                    case BluetoothDevice.BOND_NONE:
                        Log.d("BlueToothTestActivity", "取消配对");
                    default:
                        break;
                }
            }
        }
    };

    static final String SPP_UUID = "00000000-0000-1000-8000-00805F9B34FB";
    private void connect(BluetoothDevice btDev) {
        UUID uuid = UUID.fromString(SPP_UUID);
//        try {
//             btSocket = btDev.createRfcommSocketToServiceRecord(uuid);
//            btSocket =(BluetoothSocket) btDev.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(btDev,1);
//
//            Log.d("BlueToothTestActivity", "开始连接...");
//          //  btSocket.connect();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            Log.e("BlueToothTestActivity", "开始连接......链接失败");
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }catch (Exception e){
//
//        }
    }



    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }
}
