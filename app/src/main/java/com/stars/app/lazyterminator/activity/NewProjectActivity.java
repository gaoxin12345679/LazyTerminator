package com.stars.app.lazyterminator.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.stars.app.lazyterminator.R;
import com.stars.app.lazyterminator.db.DBManager;
import com.stars.app.lazyterminator.model.Project;
import com.stars.app.lazyterminator.util.TextUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewProjectActivity extends BaseActivity {
    public static String TAG = "NewProjectActivity";

    public Toolbar mToolbar = null;
    public EditText mEditTextPName = null;
    public RadioGroup mRadioGroupPLevel = null;
    public EditText mEditTextPDesc = null;
    public Button mButtonReminder = null;

    public DBManager mDBManager;

    //新建项目的实体
    public Project mNewProject = null;
    //项目的内容
    public String mPName = null;
    public String mPDesc = null;
    public int mPLevel = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "----onCreate----");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        //mDBManager = DBManager.getInstance(this);
        mDBManager = new DBManager(this);
        mNewProject = new Project();

        initUI();

        setUpToolbar();
    }

    /**
     * 设置 toolBar
     */
    private void setUpToolbar() {
        mToolbar.setTitle("New Project");
        setSupportActionBar(mToolbar);
        //让 toolbar左边显示向上的箭头按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 获取 控件中的数据
     */
    private void getInputData() {
        mPName = mEditTextPName.getText().toString().trim();
        mPDesc = mEditTextPDesc.getText().toString().trim();
        Log.v(TAG, "------getInputData:" + mPName + "|" + mPDesc + "|" + mPLevel);
    }

    /**
     * 将用户输入的数据 加载到数据库中
     */
    private void saveDataToDb() {
        this.mNewProject.setProjectName(mPName);
        this.mNewProject.setProjectDesc(mPDesc);
        this.mNewProject.setProjectPriority(mPLevel);
        String createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.mNewProject.setCreateDate( createDate );
        this.mDBManager.addNewProject(mNewProject);
        Log.v(TAG, "----saveDataToDb:" + this.mNewProject.toString());
    }

    /**
     * 检查 新建项目的输入数据是否合法
     */
    private boolean isLegalData() {
        getInputData();
        if (TextUtil.isEmpty(mPName)) {
            return false;
        }
        return true;
    }

    /**
     * 获取 UI上控件的引用
     */
    private void initUI() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_project_new);
        mEditTextPName = (EditText) findViewById(R.id.et_new_project_name);
        mRadioGroupPLevel = (RadioGroup) findViewById(R.id.rg_new_project_level);
        mEditTextPDesc = (EditText) findViewById(R.id.et_new_project_description);
        mButtonReminder = (Button) findViewById(R.id.btn_new_project_reminder);
        mRadioGroupPLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.v(TAG, "----onCheckedChanged----" + checkedId);
                switch (checkedId) {
                    case R.id.rbtn_normal:
                        mPLevel = 1;
                        break;
                    case R.id.rbtn_urgent:
                        mPLevel = 2;
                        break;
                    case R.id.rbtn_extra_urgent:
                        mPLevel = 3;
                        break;
                    default:
                        mPLevel = 0;
                }
                Log.v(TAG, "------initUI mPLevel:" + mPLevel);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //将菜单添加到toolbar
        getMenuInflater().inflate(R.menu.menu_project_new, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * 点击 toolbar上的 返回箭头按钮时，返回project界面
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v(TAG, "-----onOptionsItemSelected---clicked :" + item);
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(NewProjectActivity.this, ProjectListActivity.class);
                startActivity(intent);
                NewProjectActivity.this.finish();
                break;
            case R.id.action_done:
                Log.v(TAG, "----onOptionsItemSelected----clicked action_done:");
                if (!isLegalData()) {
                    //创建一个AlertDialog对话框
                    Dialog dialog = new AlertDialog.Builder(NewProjectActivity.this)
                            .setTitle("提示")
                            .setMessage("项目名称不能为空")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                    dialog.show();
                } else {
                    this.saveDataToDb();
                    Intent intent1 = new Intent(NewProjectActivity.this, ProjectListActivity.class);
                    startActivity(intent1);
                    NewProjectActivity.this.finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDBManager.closeDB();
    }

}
