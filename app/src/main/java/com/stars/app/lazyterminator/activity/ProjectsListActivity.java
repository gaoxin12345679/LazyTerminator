package com.stars.app.lazyterminator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.stars.app.lazyterminator.R;



public class ProjectsListActivity extends BaseActivity {

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_projects_list);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setTitle("Project");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_newproject:
                Toast.makeText(this, "clicked new project", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.v("ProjectsListActivity", "---------onCreateOptionsMenu");
        super.onCreateOptionsMenu(menu);
        //getSupportMenuInflater().inflate(R.menu.menu_projects_list, menu);
        MenuItem addMenuItem = menu.add("add").setIcon(R.drawable.content_new);
        addMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        addMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                /*
                new AlertDialog.Builder(ProjectsListActivity.this).setTitle("input project name").setIcon(
                        android.R.drawable.ic_dialog_info).setView(
                        new EditText(ProjectsListActivity.this)).setPositiveButton("OK", null)
                        .setNegativeButton("CANCEL", null).show();
                */
                Intent intent = new Intent(ProjectsListActivity.this, NewProjectActivity.class);
                startActivity(intent);

                return true;
            }
        });
        return true;
    }
}
