package com.example.anadministrator.myexpandablelistviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张祺钒
 * on2017/9/11.
 */

public class MyAdapter extends BaseExpandableListAdapter {
    private List<String> glist;
    private List<List<String>> clists;
    private Context context;
    private int childCount;
    private final Map<String, Boolean> map;

    public MyAdapter(List<String> glist, List<List<String>> clists, Context context) {
        this.glist = glist;
        this.clists = clists;
        this.context = context;
        map = new HashMap<>();
        init(false);
    }

    private void init(boolean b) {
        map.put("gCheckBox",b);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return glist.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return clists.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return glist.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return clists.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    //组和子条目有稳定Id  数据改变时不受影响
    @Override
    public boolean hasStableIds() {
        return true;
    }
    //设置默认为false

    //获取组的视图
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder gHolder;
        if(view==null){
            gHolder=new GroupViewHolder();
            view=View.inflate(context,R.layout.group_item,null);
            gHolder.checkBox=view.findViewById(R.id.cbGroup);
            gHolder.textView=view.findViewById(R.id.tvGrouop);
            view.setTag(gHolder);
        }else{
            gHolder = (GroupViewHolder) view.getTag();
        }

        if(map.get("gCheckBox")==false){
            gHolder.checkBox.setChecked(false);
        }
        gHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(context,"选了",Toast.LENGTH_SHORT).show();
                if(b==true){
                    init(true);
                }else{
                    init(false);
                }
            }
        });
        //如果是展开的状态  就设置展开的图片  相反。。。。。。
//        if(isExpanded){
//            //展开的图片
//            gholder.imageView.setImageResource(R.drawable.b);
//        }else{
//            //收起的图片
//            gholder.imageView.setImageResource(R.drawable.a);
//        }
        gHolder.textView.setText(glist.get(i));
        return view;
    }
    //获取子条目的视图
    @Override
    public View getChildView(int i, int i1, boolean b, View view, final ViewGroup viewGroup) {
        ChildViewHolder cHolder;
        if(view==null){
            cHolder=new ChildViewHolder();
            view=View.inflate(context,R.layout.child_item,null);
            cHolder.checkBox=view.findViewById(R.id.cbChild);
            cHolder.textView=view.findViewById(R.id.tvChild);
            view.setTag(cHolder);
        }else{
            cHolder = (ChildViewHolder) view.getTag();
        }
        childCount = viewGroup.getChildCount();
        Boolean gCheckBox = map.get("gCheckBox");
        if(gCheckBox==true){
            cHolder.checkBox.setChecked(true);
            notifyDataSetChanged();
        }

        cHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b==false){
                    Toast.makeText(context,"没选",Toast.LENGTH_SHORT).show();
                    init(false);
                }else{
                    a+=1;
                    if(a==childCount){
                        init(true);
                    }
                }

            }
        });



        cHolder.textView.setText(clists.get(i).get(i1));
        return view;
    }
    int a=0;
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    static  class  GroupViewHolder{
        CheckBox checkBox;
        TextView textView;
    }
    static class ChildViewHolder{
        CheckBox checkBox;
        TextView textView;
    }
}
