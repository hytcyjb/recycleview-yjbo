package com.yy.yjbo.recycleview_yjbo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.yy.yjbo.recycleview_yjbo.R;
import com.yy.yjbo.recycleview_yjbo.adapter.HuiAdapter;
import com.yy.yjbo.recycleview_yjbo.test.DividerGridItemDecorationCopy;
import com.yy.yjbo.recycleview_yjbo.util.DividerGridItemDecoration;
import com.yy.yjbo.recycleview_yjbo.util.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 有头部和尾部的recycleview
 * @author yjbo
 * @time 2017/3/31 9:59
 * @mail 1457521527@qq.com
 */
public class RecycleHFActivity extends AppCompatActivity {
    private RecyclerView swipeTarget;
    private RecycleHFActivity mContext;
    private int showGideCount = 3;
    private HuiAdapter huiAdapter;
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
        swipeTarget.setLayoutManager(layoutManager);
        huiAdapter = new HuiAdapter();

    }

    private void getData() {

        listHash.clear();

        for (int i = 0; i < 56; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("imageInt", "");
            hashMap.put("acid", "acid" + i);
            hashMap.put("wordStr", "菜单" + i);//真实名称
            hashMap.put("compoCode", "compoCode" + i);//别名
            hashMap.put("compoUrl", "compoUrl" + i);
            hashMap.put("biaozhi", "biaozhi" + i);
            listHash.add(hashMap);
        }
        huiAdapter.bindData(listHash, mContext);
        setHeader(swipeTarget);
        LogUtils.d("==测试网格线是先走DividerGridItemDecoration类方法的还是先走adapter的===");
        swipeTarget.addItemDecoration(new DividerGridItemDecorationCopy(mContext,1,1));
        swipeTarget.setAdapter(huiAdapter);
    }
    private void setHeader(RecyclerView recyclerView) {
        View header = LayoutInflater.from(mContext).inflate(R.layout.fragment_navigation_footer, recyclerView, false);

        View footer = LayoutInflater.from(mContext).inflate(R.layout.fragment_navigation_footer, recyclerView, false);
        huiAdapter.setHeaderView(header);
        huiAdapter.addfoot(footer);
    }
    /**
     * 引入的一个log
     * @author yjbo  @time 2017/3/30 14:28
     */
    private void testLogger() {
        LogUtils.d("uknp");
    }

}
