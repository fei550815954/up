package cn.kingcd.up;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.lzy.okgo.OkGo;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Activity的基类
 *
 * @author fei
 */

public abstract class BaseActivity extends AutoLayoutActivity {
    private ProgressDialog dialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceId());
        //插件初始化
        ButterKnife.inject(this);
        initView();
        initData();
        initListener();
        initHandler();
        //将activity条件进集合方便管理
        ActivityUtils.getActivityManager().addActivity(this);
    }

    /**
     * 获取布局文件
     *
     * @return 布局文件
     */
    protected abstract int getResourceId();

    /**
     * 初始化view
     */
    protected void initView() {
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 初始化监听
     */
    protected void initListener() {
    }

    /**
     * 初始化handler
     */
    protected void initHandler() {
    }

    /**
     * dialog显示
     *
     * @param s
     */
    protected void showDialog(String s) {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(s);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * dialog关闭
     */
    protected void stopDialog() {
        try {
            if (null != dialog) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 下拉刷新状态关闭，下拉刷新为系统自带的
     */
    protected void stopSrl(final SwipeRefreshLayout srl) {
        try {
            srl.post(new Runnable() {
                @Override
                public void run() {
                    if (srl!=null&srl.isRefreshing()){
                        srl.setRefreshing(false);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
        ActivityUtils.getActivityManager().finishActivity(this);
        OkGo.getInstance().cancelTag(this);
    }


}
