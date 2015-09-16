package com.stars.app.lazyterminator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stars.app.lazyterminator.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/16.
 */
public class LazyTerminatorDB {
    //数据库名称
    public static final String DB_NAME = "DB_LAZY_TERMINATOR";
    //数据库版本
    public static final int DB_VERSION = 1;

    private SQLiteDatabase db;

    private static LazyTerminatorDB mLazyTerminatorDB;

    /*私有化构造函数*/
    private LazyTerminatorDB(Context context) {
        LTDBOpenHelper dbHelper =
                new LTDBOpenHelper(context, DB_NAME, null, DB_VERSION);

         db = dbHelper.getWritableDatabase();
    }

    /*单例模式*/
    public synchronized static LazyTerminatorDB getInstance(Context context) {
        if (mLazyTerminatorDB == null) {
            mLazyTerminatorDB = new LazyTerminatorDB(context);
        }
        return mLazyTerminatorDB;
    }

    /*将project实例存入数据库*/
    public void saveProject(Project project) {
        if (project != null) {
            ContentValues values = new ContentValues();
            values.put("project_name", project.getProjectName());
            values.put("project_priority", project.getProjectPriority());
             db.insert("tb_project", null, values);
        }
    }

    /*从数据库中查出所有project*/
    public List<Project> loadProject() {
        List<Project> list = new ArrayList<Project>();
        Cursor cursor =  db
                .query("tb_project", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Project project = new Project(0, null, null);
                project.setProjectId(cursor.getInt(cursor.getColumnIndex("project_id")));
                project.setProjectName(cursor.getString(cursor.getColumnIndex("project_name")));
                project.setProjectPriority(cursor.getString(cursor.getColumnIndex("project_priority")));
                list.add(project);
                
            } while (cursor.moveToNext());
        }
        return list;
    }
}
