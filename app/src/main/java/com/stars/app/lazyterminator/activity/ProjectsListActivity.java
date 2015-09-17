package com.stars.app.lazyterminator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fortysevendeg.swipelistview.SwipeListView;
import com.stars.app.lazyterminator.R;



public class ProjectsListActivity extends BaseActivity {

    public static String TAG = "ProjectsListActivity";
    private Toolbar mToolbar;
    private SwipeListView mSwipeListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_projects_list);
        initToolBar();
        initSwipeListView();
    }

    private void initSwipeListView() {
        //mSwipeListView = (SwipeListView) findViewById(R.id.slv_project_list);
    }

    private void initToolBar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar_project_list);
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
