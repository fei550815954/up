package cn.kingcd.up;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.example.zhouwei.library.CustomPopWindow;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ==============================================
 * <p>
 * ==============================================
 * 版权所有 违法必究
 * <p>
 * 创建作者：fei
 * <p>
 * 创建时间：2018/9/17
 * <p>
 * 修订历史：
 * <p>
 * 修订时间：
 * ==============================================
 * ==================《程序员》==================
 * =======十年生死两茫茫，写程序，到天亮。=======
 * ==============千行代码，Bug何处藏。===========
 * =======纵使上线又怎样，朝令改，夕断肠。=======
 * ----------------------------------------------
 * ======领导每天新想法，天天改，日日忙。========
 * =============相顾无言，惟有泪千行。===========
 * ======每晚灯火阑珊处，夜难寐，加班狂。========
 * ==============================================
 */
public class SaoMiao extends BaseActivity {
    @InjectView(R.id.recycler)
    RecyclerView recycler;
    List<BleDevice> list = new ArrayList<BleDevice>();
    @InjectView(R.id.tv_LiJi)
    TextView tvLiJi;
    @InjectView(R.id.tv_ShangChuan)
    TextView tvShangChuan;
    @InjectView(R.id.rl10)
    RelativeLayout rl10;
    private BaseQuickAdapter<BleDevice, BaseViewHolder> adapter;

    @Override
    protected int getResourceId() {
        return R.layout.saomiao;
    }

    @Override
    protected void initData() {
        super.initData();
        // 扫描结束，列出所有扫描到的符合扫描规则的BLE设备（主线程）
        recycler.setLayoutManager(new LinearLayoutManager(SaoMiao.this));
        adapter = new BaseQuickAdapter<BleDevice, BaseViewHolder>(R.layout.adapter_equipment, list) {
            @Override
            protected void convert(BaseViewHolder helper, BleDevice item) {
                helper.setText(R.id.tv_Num, (helper.getAdapterPosition() + 1) + "");
                helper.setText(R.id.tv_Mac, item.getMac());
                helper.setText(R.id.tv_Name, item.getName());
                helper.setText(R.id.tv_XinHao, String.valueOf(item.getRssi()));
            }
        };
        recycler.setAdapter(adapter);
    }

    @OnClick({R.id.tv_LiJi, R.id.tv_ShangChuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_LiJi:
                if (!BluetoothUtils.isOpenBlue()) {
                    BluetoothUtils.startBlue(this);
                } else {
                    if("立即搜索".equals(tvLiJi.getText().toString())){
                        tvLiJi.setText("停止扫描");
                        tvShangChuan.setVisibility(View.GONE);
                        list.clear();
                        search();
                    }else if ("停止扫描".equals(tvLiJi.getText().toString())){
                        BleManager.getInstance().cancelScan();
                        tvShangChuan.setVisibility(View.VISIBLE);
                        tvLiJi.setText("立即搜索");
                    }
                }
                break;
            case R.id.tv_ShangChuan:
                 showNickName();
                break;
            default:
        }
    }

    private void up(String pass) {
        if (list.size() != 0) {
            JSONArray array = new JSONArray();
            JSONObject obj = new JSONObject();
            try {
                for (int i = 0; i < list.size(); i++) {
                    JSONObject object = new JSONObject();
                    object.put("mac", list.get(i).getMac());
                    array.put(object);
                }
                obj.put("pass", pass);
                obj.put("type", getIntent().getStringExtra("type"));
                obj.put("device", array);
                L.e("-----" + obj.toString());
                networkRequests(obj.toString());
            } catch (Exception e) {

            }
        }
    }
    /**
     * 网络请求登录
     */
    private void networkRequests(String s) {
        showDialog("上传中...");
        OkGo.<String>post(UrlRes.up)
                .tag(this)
                .params("data", s)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        L.e("上传设备返回的信息是+" + response.body());
                        String status= JSONUtils.getString(response.body(),"status","");
                        String data=JSONUtils.getString(response.body(),"data","");
                        if ("200".equals(status)){
                            T.staticShowToast(JSONUtils.getString(response.body(),"message",""));
                            L.e("---"+JSONUtils.getString(response.body(),"message",""));
                        }else {
                            T.staticShowToast(JSONUtils.getString(response.body(),"message",""));
                            L.e("---"+JSONUtils.getString(response.body(),"message",""));
                        }

                        stopDialog();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        stopDialog();
                    }

                });
    }
    /**
     * 搜索设备
     */
    private void search() {
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
                // 只扫描指定广播名的设备，可选
                .setDeviceName(true, getIntent().getStringExtra("type"))
                // 扫描超时时间，可选，默认10秒
                .setScanTimeOut(500000)
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);
        BleManager.getInstance().scan(new BleScanCallback() {
            @Override
            public void onScanStarted(boolean success) {
                tvShangChuan.setVisibility(View.GONE);
            }

            @Override
            public void onScanning(BleDevice bleDevice) {
                // 扫描到一个符合扫描规则的BLE设备（主线程）
                if (getIntent().getStringExtra("type").equals(bleDevice.getName())){
                    list.add(bleDevice);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {
//                L.e("扫描结束bind+" + scanResultList.size());
                try {
                    tvShangChuan.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            BleManager.getInstance().cancelScan();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showNickName() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_nick_name, null);
        CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)//显示的布局，还可以通过设置一个View
                .setBgDarkAlpha(0.5f) // 控制亮度
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED)
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .create()//创建PopupWindow
                .showAtLocation(rl10, Gravity.BOTTOM, 0, 0);
        ((TextView) contentView.findViewById(R.id.tv_OK)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();
                up(((EditText) contentView.findViewById(R.id.et_NiCheng)).getText().toString());
            }
        });
        contentView.findViewById(R.id.tv_QuXiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();
            }
        });

    }


}
