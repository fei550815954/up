package cn.kingcd.up;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.clj.fastble.BleManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        initBle();
        initPermissions();
    }

    private void initPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity instance
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) {

                    } else {

                    }

                });
    }


    private void initBle() {
        BleManager.getInstance().init(getApplication());
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(0, 0)
                .setConnectOverTime(20000)
                .setOperateTimeout(7000);
    }

    @OnClick(R.id.tv_watch1)
    public void onClick() {
        Intent intent=new Intent(this,SaoMiao.class);
        intent.putExtra("type","KY_S1");
        startActivity(intent);
    }
}
