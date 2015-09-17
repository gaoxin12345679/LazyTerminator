package com.stars.app.lazyterminator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.stars.app.lazyterminator.R;
import com.stars.app.lazyterminator.adapter.ProjectListAdapter;
import com.stars.app.lazyterminator.model.Project;

import java.util.ArrayList;
import java.util.List;


public class ProjectsListActivity extends BaseActivity {

    public static String TAG = "ProjectsListActivity";
    private Toolbar mToolbar;
    private SwipeListView mSwipeListView;
    ProjectListAdapter mProjectListAdapter;

    private List<Project> mProjectListTest = new ArrayList<Project>();

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
        mProjectListTest.add(p1);
        mProjectListTest.add(p2);
        mProjectListTest.add(p3);
        mProjectListTest.add(p4);
        mProjectListTest.add(p5);
        mProjectListTest.add(p6);
        mProjectListTest.add(p7);
        mProjectListTest.add(p8);
        mProjectListTest.add(p9);
        mProjectListTest.add(p10);
        mProjectListTest.add(p11);
    }

    private void initSwipeListView() {
        mSwipeListView = (SwipeListView) findViewById(R.id.slv_project_list);
        mProjectListAdapter =
                new ProjectListAdapter(this, mProjectListTest, mSwipeListView);
        mSwipeListView.setAdapter(mProjectListAdapter);
        mSwipeListView.setSwipeListViewListener(new BaseSwipeListViewListener(){
            @Override
            public void onClickFrontView(int position) {
                super.onClickFrontView(position);
                Toast.makeText(getApplicationContext(), mProjectListTest.get(position)+" clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDismiss(int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    mProjectListTest.remove(position);
                }
                mProjectListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartOpen(int position, int action, boolean right) {
                // TODO Auto-generated method stub
                super.onStartOpen(position, action, right);
                mSwipeListView.closeOpenedItems();
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
