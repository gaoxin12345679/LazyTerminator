package com.stars.app.lazyterminator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stars.app.lazyterminator.R;
import com.stars.app.lazyterminator.model.Project;

import java.util.List;

/**
 * Created by Administrator on 2015/9/17.
 */
public class ProjectListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Project> mData;
    private ListView mListView;
    private Context mContext;

    public ProjectListAdapter(Context context, List<Project> data, ListView listView) {
        this.mContext = context;
        this.mData = data;
        this.mListView = listView;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.project_list_item, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = (TextView)convertView.findViewById(R.id.tv_project_name);
            holder.btnDel = (ImageButton)convertView.findViewById(R.id.btn_del);
            holder.btnEdit = (ImageButton)convertView.findViewById(R.id.btn_edit);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        if(mData!=null && mData.size()>0){
            holder.tvTitle.setText(mData.get(position).getProjectName());
        }

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了删除button" + position, Toast.LENGTH_LONG).show();
                mData.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"点击了修改button"+position,Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }



    static class ViewHolder {
        TextView tvTitle;
        ImageButton btnDel;
        ImageButton btnEdit;
    }
}
