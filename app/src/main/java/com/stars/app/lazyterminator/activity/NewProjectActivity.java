package com.stars.app.lazyterminator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.stars.app.lazyterminator.R;

public class NewProjectActivity extends BaseActivity {
    public static String TAG = "NewProjectActivity";
    private Toolbar mToolbar;
    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        mEditText = (EditText)findViewById(R.id.et_new_project_name);
        mToolbar = (Toolbar)findViewById(R.id.toolbar_project_new);
        mToolbar.setTitle("New Project");
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* 菜单的监听可以在toolbar里设置，也可以像ActionBar那样，通过下面的两个回调方法来处理 */
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.v(TAG, "--------clicked :"+item.toString());

                switch (item.getItemId()) {
                    case R.id.homeAsUp:
                    case R.id.action_done:
                        Toast.makeText(NewProjectActivity.this, "Toolbar action_project_done", Toast.LENGTH_SHORT).show();
                        Log.v(TAG, "--------clicked action_project_done btn!!!!");
                        Intent intent = new Intent(NewProjectActivity.this, ProjectsListActivity.class);
                        startActivity(intent);
                        NewProjectActivity.this.finish();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //将菜单添加到toolbar
        getMenuInflater().inflate(R.menu.menu_projects_new, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
