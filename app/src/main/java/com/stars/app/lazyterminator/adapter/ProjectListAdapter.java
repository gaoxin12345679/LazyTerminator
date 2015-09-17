package com.stars.app.lazyterminator.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fortysevendeg.swipelistview.SwipeListView;
import com.stars.app.lazyterminator.R;
import com.stars.app.lazyterminator.model.Project;

import java.util.List;

/**
 * Created by Administrator on 2015/9/17.
 */
public class ProjectListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Project> mProjects;
    private SwipeListView mSwipeListView;

    public ProjectListAdapter(Context context, List<Project> objects, SwipeListView swipeListView) {
        this.mProjects = objects;
        this.mSwipeListView = swipeListView;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mProjects.size();
    }

    @Override
    public Object getItem(int position) {
        return mProjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.project_list_item, parent, false);
        } else {
            view = convertView;
        }

        //获取project list item 中的控件
        //ImageView ivPriority = (ImageView)view.findViewById(R.id.iv_priority);
        TextView tvName = (TextView) view.findViewById(R.id.tv_project_name);
        //ImageView ivDetail = (ImageView)view.findViewById(R.id.iv_project_detal);
        Button btnEdit= (Button) view.findViewById(R.id.btn_edit);
        Button btnDel= (Button) view.findViewById(R.id.btn_del);

        //设置项目名称
        tvName.setText(mProjects.get(position).getProjectName());

        //设置 删除按钮 的事件
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("ProjectListAdapter", "--------btnDel");
                mSwipeListView.closeAnimate(position);
                mSwipeListView.dismiss(position);
            }
        });



        return view;
    }
}
