package cn.kingcd.up;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;


public class ActivityUtils {
    private static Stack<Activity> activityStack;
    private static ActivityUtils instance;

    private ActivityUtils() {
    }

    public static ActivityUtils getActivityManager() {
        if (instance == null) {
            instance = new ActivityUtils();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public Activity getLastActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public void finishLastActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void finishClassActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }
    public Activity findClassActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void ExitApp(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

}
