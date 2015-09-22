package com.stars.app.lazyterminator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.stars.app.lazyterminator.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/16.
 */
public class DBManager {
    public static String TAG = "DBManager";

    //数据库名称
    public static final String DB_NAME = "DB_LT";
    //数据库版本
    public static final int DB_VERSION = 1;

    private SQLiteDatabase db;

    //private static DBManager sMDBManager;

    /*私有化构造函数*/
    public DBManager(Context context) {
        DBHelper dbHelper =
                new DBHelper(context, DB_NAME, null, DB_VERSION);

        db = dbHelper.getWritableDatabase();
    }

    /**
     * 关闭数据库
     */
    public void closeDB() {
        db.close();
    }

    /*单例模式*/
   /* public synchronized static DBManager getInstance(Context context) {
        if (sMDBManager == null) {
            sMDBManager = new DBManager(context);
        }
        return sMDBManager;
    }
*/
    /*将project实例存入数据库*/
    public void addNewProject(Project project) {
        Log.v(TAG, "-------addNewProject:" + project.getProjectName() + "|" + project.getProjectPriority()
                + "|" + project.getProjectDesc() + "|" + project.getCreateDate());
        if (project != null) {
            ContentValues values = new ContentValues();
            values.put("project_name", project.getProjectName());
            values.put("project_priority", project.getProjectPriority());
            values.put("project_desc", project.getProjectDesc());
            values.put("project_createDate", project.getCreateDate());
            db.insert("tb_project", null, values);
        }
    }

    /*从数据库中查出所有project*/
    public List<Project> queryAllProject() {
        List<Project> list = new ArrayList<Project>();
        Cursor cursor = db
                .query("tb_project", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setProjectName(cursor.getString(cursor.getColumnIndex("project_name")));
                project.setProjectPriority(cursor.getInt(cursor.getColumnIndex("project_priority")));
                list.add(project);

            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * delete 一条数据
     */
    public void deleteProject(Project project){
        db.delete("tb_project", "project_name = ?", new String[]{String.valueOf(project.getProjectName())});
    }
}
