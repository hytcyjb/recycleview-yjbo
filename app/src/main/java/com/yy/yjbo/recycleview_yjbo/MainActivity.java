package com.yy.yjbo.recycleview_yjbo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * recycleview的各种封装和使用
 *
 * @author yjbo
 * @time 2017/3/29 16:59
 * @mail 1457521527@qq.com
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView swipeTarget;
    private MainActivity mContext;
    private int showGideCount = 3;
    private HuiAdapter huiAdapter;
    private List<HashMap<String, Object>> listHash = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        getData();
    }


    private void initView() {
        swipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, showGideCount);//网格布局
        swipeTarget.setLayoutManager(layoutManager);
        swipeTarget.addItemDecoration(new DividerGridItemDecoration(mContext));

        huiAdapter = new HuiAdapter();

    }

    private void getData() {
        listHash.clear();

        for (int i = 0; i < 8; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("imageInt", "");
            hashMap.put("acid", "acid" + i);
            hashMap.put("wordStr", "wordStr" + i);//真实名称
            hashMap.put("compoCode", "compoCode" + i);//别名
            hashMap.put("compoUrl", "compoUrl" + i);
            hashMap.put("biaozhi", "biaozhi" + i);
            listHash.add(hashMap);
        }
        huiAdapter.bindData(listHash, mContext);
        setHeader(swipeTarget);
        swipeTarget.setAdapter(huiAdapter);
    }
    private void setHeader(RecyclerView recyclerView) {
        View header = LayoutInflater.from(mContext).inflate(R.layout.fragment_navigation_footer, recyclerView, false);

        View footer = LayoutInflater.from(mContext).inflate(R.layout.fragment_navigation_footer, recyclerView, false);
        huiAdapter.setHeaderView(header);
        huiAdapter.addfoot(footer);
    }
}
