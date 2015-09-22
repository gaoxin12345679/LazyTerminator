package com.stars.app.lazyterminator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.stars.app.lazyterminator.R;

public class TaskListActivity extends BaseActivity {
    public static String TAG = "TaskListActivity";
    private Toolbar mToolbar;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        initToolBar();
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_task_list);

        //根据 项目列表界面 传递的 项目名称 设置 toolbar的title
        mToolbar.setTitle(getIntent().getStringExtra("project_name"));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.v(TAG, "-----onOptionsItemSelected---clicked :" + item);
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(TaskListActivity.this, ProjectListActivity.class);
                startActivity(intent);
                TaskListActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
