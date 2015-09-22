package com.stars.app.lazyterminator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.stars.app.lazyterminator.R;
import com.stars.app.lazyterminator.adapter.ProjectListAdapter;
import com.stars.app.lazyterminator.db.DBManager;
import com.stars.app.lazyterminator.model.Project;

import java.util.ArrayList;
import java.util.List;


public class ProjectListActivity extends BaseActivity {

    public static String TAG = "ProjectListActivity";
    private Toolbar mToolbar;
    private ListView mListView;
    ProjectListAdapter mProjectListAdapter;

    private List<Project> mData = new ArrayList<Project>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_project_list);
        getProjectDataFromDB();
        initToolBar();
        initListView();
    }

    /**
     * 从数据库中查询出所有的项目信息
     */
    private void getProjectDataFromDB() {
        DBManager mDBManager = new DBManager(this);
        mData = mDBManager.queryAllProject();
    }

    /**
     * 初始化listview
     */
    private void initListView() {
        mListView = (ListView) findViewById(R.id.lv_project_list);
        mProjectListAdapter = new ProjectListAdapter(this, mData, mListView);
        mListView.setAdapter(mProjectListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v(TAG, "----onItemClick:" + position);
                //点击listview的条目 进入到project详情界面，并将项目名称传递给task界面

                Project project = mData.get(position);

                Intent intent = new Intent(ProjectListActivity.this, TaskListActivity.class);
                intent.putExtra("project_name", project.getProjectName());
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_project_list);
        mToolbar.setTitle("Project List");
        setSupportActionBar(mToolbar);

        /* 菜单的监听可以在toolbar里设置，也可以像ActionBar那样，通过下面的两个回调方法来处理 */
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_newproject:
                        //Toast.makeText(ProjectListActivity.this, "Toolbar action_newproject", Toast.LENGTH_SHORT).show();
                        Log.v(TAG, "--------clicked new project btn!!!!");
                        Intent intent = new Intent(ProjectListActivity.this, NewProjectActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //将菜单添加到toolbar
        getMenuInflater().inflate(R.menu.menu_project_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
