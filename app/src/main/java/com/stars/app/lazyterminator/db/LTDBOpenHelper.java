package com.stars.app.lazyterminator.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/9/15.
 */
public class LTDBOpenHelper extends SQLiteOpenHelper {
    /**
     *  project表建表语句
     */
    public static final String CREATE_TB_PROJECT = "create table tb_project ("
            + "project_id integer primary key autoincrement, "
            + "project_name text, "
            + "project_priority text)";
    /**
     *  task表建表语句
     */
    public static final String CREATE_TB_TASK = "create table tb_task ("
            + "task_id integer primary key autoincrement, "
            + "project_id intger no null,"
            + "task_name text, "
            + "task_process text"
            + "task_priority text)";


    public LTDBOpenHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_PROJECT);//创建project表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
