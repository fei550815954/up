package cn.kingcd.up;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;

/**
 * ==============================================
 * <p>蓝牙工具类
 * ==============================================
 * 版权所有 违法必究
 * <p>
 * 创建作者：fei
 * <p>
 * 创建时间：2018/7/4
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
public class BluetoothUtils {
    /**
     * 是不是有蓝牙（是否支持蓝牙）
     *
     * @return
     */
    public static boolean isHaveBlue() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();  //获取蓝牙适配器
        if (bluetoothAdapter == null) {
            return false;
        }
        return true;
    }

    /**
     * 蓝牙是否开启
     *
     * @return
     */
    public static boolean isOpenBlue() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            return false;
        }
        return true;
    }

    /**
     * 请求打开蓝牙
     */
    public static void startBlue(Context context) {
        // 请求打开
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        context.startActivity(intent);
    }

}
