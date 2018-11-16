package cn.kingcd.up;

import android.app.Application;
import android.content.Context;

import com.clj.fastble.data.BleDevice;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;


/**
 * @author fei
 */

public class MyApp extends Application {
    public static String access_token;
    public static int step2;
    public static String refresh_token;
    private static MyApp instance;
    public static String mac;
    public static String device_name;
    public static BleDevice myBle;
    public static boolean isOpenBlue=false;

    @Override
    public void onCreate() {
        super.onCreate();
        L.isDebug = true;//正式打包的时候请注释这行以及okgo日志设置为不打印
        instance = this;
        initOkGo();
    }




    /**
     * 初始化ok
     */
    private void initOkGo() {
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("fei");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        builder.readTimeout(30000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(20000, TimeUnit.MILLISECONDS);
        builder.connectTimeout(20000, TimeUnit.MILLISECONDS);
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));
        builder.hostnameVerifier(new SafeHostnameVerifier());
        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3)
                ;
    }

    private class SafeHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            //这个以后要和服务器讨论，因为https现在现在什么效验都没做
//            if (hostname.equals("www.kingcd.cn") || hostname.equals("kingcd.cn")) {
//                return true;
//            }
            return true;
        }
    }

    public static Context getInstance() {
        return instance;
    }



}
