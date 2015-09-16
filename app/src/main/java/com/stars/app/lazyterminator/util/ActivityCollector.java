package com.stars.app.lazyterminator.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/14.
 * 活动管理类
 */
public class ActivityCollector {
    public static List<Activity> mActivityList = new ArrayList<Activity>();

    public static void addActivityToList(Activity activity){
        mActivityList.add(activity);
    }

    public static void removeActivityFromList(Activity activity){
        mActivityList.remove(activity);
    }

    public static void removeAllActivityFromList(){
        for(Activity activity: mActivityList){
            if(activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
