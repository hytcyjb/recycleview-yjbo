package com.yy.yjbo.recycleview_yjbo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.yy.yjbo.recycleview_yjbo.R;
import com.yy.yjbo.recycleview_yjbo.adapter.MainAdapter;
import com.yy.yjbo.recycleview_yjbo.util.DividerGridItemDecoration;
import com.yy.yjbo.recycleview_yjbo.util.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * recycleview的各种封装和使用
 *  log打印引用：https://github.com/orhanobut/logger
 * @author yjbo
 * @time 2017/3/29 16:59
 * @mail 1457521527@qq.com
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView swipeTarget;
    private MainActivity mContext;
    private int showGideCount = 5;
    private MainAdapter mainAdapter;
    private List<HashMap<String, Object>> listHash = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        testLogger();
        initView();
        getData();

    }


    private void initView() {
        swipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, showGideCount);//网格布局
//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);//线性布局
        swipeTarget.setLayoutManager(layoutManager);

        mainAdapter = new MainAdapter();
        mainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String url, String compoCode, String wordStr) {
                if (position == 0){
                    startActivity(new Intent(mContext,RecycleHFActivity.class));
                }
            }
        });
    }

    private void getData() {

        listHash.clear();

        for (int i = 0; i < 11; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("imageInt", "");
            hashMap.put("acid", "acid" + i);
            hashMap.put("wordStr", "菜单" + i);//真实名称
            hashMap.put("compoCode", "compoCode" + i);//别名
            hashMap.put("compoUrl", "compoUrl" + i);
            hashMap.put("biaozhi", "biaozhi" + i);
            listHash.add(hashMap);
        }
        mainAdapter.bindData(listHash, mContext);

        swipeTarget.addItemDecoration(new DividerGridItemDecoration(mContext,0,0));
        swipeTarget.setAdapter(mainAdapter);
    }
    /**
     * 引入的一个log
     * @author yjbo  @time 2017/3/30 14:28
     */
    private void testLogger() {
        new LogUtils(true);
        LogUtils.d("uknp");
    }

}
