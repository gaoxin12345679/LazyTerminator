package com.stars.app.lazyterminator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.stars.app.lazyterminator.R;

public class NewProjectActivity extends BaseActivity {
    private ActionBar mActionBar;
    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setTitle("New Project");
        mEditText = (EditText)findViewById(R.id.et_new_project_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.v("ProjectsListActivity", "---------onCreateOptionsMenu");
        super.onCreateOptionsMenu(menu);
        //getSupportMenuInflater().inflate(R.menu.menu_projects_list, menu);
        MenuItem addMenuItem = menu.add("done").setIcon(R.drawable.ic_cab_done_holo_dark);
        addMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        addMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(NewProjectActivity.this, ProjectsListActivity.class);
                startActivity(intent);
                NewProjectActivity.this.finish();
                return true;
            }
        });
        return true;
    }

}
