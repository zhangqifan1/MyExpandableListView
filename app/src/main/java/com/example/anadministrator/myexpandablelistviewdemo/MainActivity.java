package com.example.anadministrator.myexpandablelistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView mEv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //准备数据
        preparData();

    }

    private void preparData() {
        //组的数据
        List<String> glist=new ArrayList<>();
        glist.add("三国");
        glist.add("红楼");
        glist.add("水浒");
        glist.add("西游");

        //子条目的数据
        List<String> clist0=new ArrayList<>();
        clist0.add("刘备");
        clist0.add("关羽");
        clist0.add("张飞");
        List<String> clist1=new ArrayList<>();
        clist1.add("贾宝玉");
        clist1.add("薛宝钗");
        List<String> clist2=new ArrayList<>();
        clist2.add("威武霸气你钒哥");
        clist2.add("大钒哥");
        clist2.add("中钒哥");
        clist2.add("小钒哥");
        List<String> clist3=new ArrayList<>();
        clist3.add("唐僧");
        clist3.add("孙悟空");
        clist3.add("猪八戒");
        clist3.add("沙僧");
        clist3.add("宋海防");

        //记录子列表数据的集合
        List<List<String>> clists=new ArrayList<>();
        clists.add(clist0);
        clists.add(clist1);
        clists.add(clist2);
        clists.add(clist3);
        MyAdapter myAdapter = new MyAdapter(glist, clists, this);
        mEv.setAdapter(myAdapter);
        mEv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                return false;
            }
        });
    }

    private void initView() {
        mEv = (ExpandableListView) findViewById(R.id.ev);
    }
}
