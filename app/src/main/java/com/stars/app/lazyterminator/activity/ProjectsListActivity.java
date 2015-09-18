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
import android.widget.Toast;

import com.stars.app.lazyterminator.R;
import com.stars.app.lazyterminator.adapter.ProjectListAdapter;
import com.stars.app.lazyterminator.model.Project;

import java.util.ArrayList;
import java.util.List;


public class ProjectsListActivity extends BaseActivity {

    public static String TAG = "ProjectsListActivity";
    private Toolbar mToolbar;
    private ListView mListView;
    ProjectListAdapter mProjectListAdapter;

    private List<Project> mTestData = new ArrayList<Project>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_projects_list);
        initProjectListTestData();
        initToolBar();
        initSwipeListView();
    }

    private void initProjectListTestData() {
        Project p1 = new Project(1, "project1", 2);
        Project p2 = new Project(2, "project2", 2);
        Project p3 = new Project(3, "project3", 2);
        Project p4 = new Project(4, "project4", 2);
        Project p5 = new Project(5, "project5", 2);
        Project p6 = new Project(6, "project6", 2);
        Project p7 = new Project(7, "project7", 2);
        Project p8 = new Project(8, "project8", 2);
        Project p9 = new Project(9, "project9", 2);
        Project p10 = new Project(10, "project10", 2);
        Project p11 = new Project(11, "project11", 2);
        Project p12 = new Project(12, "project12", 2);
        Project p13 = new Project(13, "project13", 2);
        Project p14 = new Project(14, "project14", 2);
        Project p15 = new Project(15, "project15", 2);
        Project p16 = new Project(16, "project16", 2);
        Project p17 = new Project(17, "project17", 2);

        mTestData.add(p1);
        mTestData.add(p2);
        mTestData.add(p3);
        mTestData.add(p4);
        mTestData.add(p5);
        mTestData.add(p6);
        mTestData.add(p7);
        mTestData.add(p8);
        mTestData.add(p9);
        mTestData.add(p10);
        mTestData.add(p11);
        mTestData.add(p12);
        mTestData.add(p13);
        mTestData.add(p14);
        mTestData.add(p15);
        mTestData.add(p16);
        mTestData.add(p17);
    }

    private void initSwipeListView() {
        mListView = (ListView) findViewById(R.id.lv_project_list);
        mProjectListAdapter = new ProjectListAdapter(this, mTestData, mListView);
        mListView.setAdapter(mProjectListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

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
                        Toast.makeText(ProjectsListActivity.this, "Toolbar action_newproject", Toast.LENGTH_SHORT).show();
                        Log.v(TAG, "--------clicked new project btn!!!!");
                        Intent intent = new Intent(ProjectsListActivity.this, NewProjectActivity.class);
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
        getMenuInflater().inflate(R.menu.menu_projects_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
