package com.gshlib.wxw.myscan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gshlib.wxw.myscan.tools.ApplicationSetting;
import com.gshlib.wxw.myscan.tools.ScanListener;
import com.gshlib.wxw.myscan.tools.ScanSetting;
import com.gshlib.wxw.myscan.tools.ScannerDevice;
import com.gshlib.wxw.myscan.tools.ScannerDeviceListener;
import com.gshlib.wxw.myscan.tools.ScannerFactory;
import com.gshlib.wxw.myscan.tools.SoundUtil;

public class MainActivity extends AppCompatActivity {

    private ScannerFactory scannerDevice;
    private Button btn_Scan;
    private TextView tv_code;
    private SoundUtil soundUtil = null;

    private boolean isStartScan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initScanview();
    }


    private void initScanview() {
        btn_Scan = (Button) findViewById(R.id.btn_Scan);
        tv_code = (TextView) findViewById(R.id.tv_code);
        btn_Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScan();
            }
        });
    }

    private void startScan() {
        if (scannerDevice != null) {
            scannerDevice.start();
        }
        isStartScan = true;
    }

    private void stopScan() {
        isStartScan = false;
        if (scannerDevice != null) {
            scannerDevice.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!ApplicationSetting.Scanner_Device_Name.equals("camera")) {
            scannerDevice = ScannerFactory.getInstance(ApplicationSetting.Scanner_Device_Name);
            if (scannerDevice != null) {
                scannerDevice.init(this);
                scannerDevice.setCodeConfig(ScannerDevice.Barcode_Type_All, false);
                scannerDevice.setCodeConfig(ScannerDevice.CODE128, true);
                scannerDevice.addListener(new ScannerDeviceListener() {
                    @Override
                    public boolean ScanCode(String drug_number) {
                        if ((drug_number.length() != 20 && drug_number.length() != 16))
                            return false;
                        //CheckScanCode(drug_number);
                        //scannerDevice.keepScreenOnDelay(30,drug_number_textview);
                        scannerDevice.keepScreenOnDelay(30, tv_code);
                        return true;
                    }
                });
            } else {
                Toast.makeText(this, ScannerFactory.getLastError(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (scannerDevice != null) {
            scannerDevice.release();
        }
    }


    private void playPrompt() {
        soundUtil.playPrompt();
    }
}
