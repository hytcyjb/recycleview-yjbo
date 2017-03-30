package com.yy.yjbo.recycleview_yjbo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

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
    private int showGideCount = 2;
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
        swipeTarget.addItemDecoration(new DividerGridItemDecoration(mContext));

        huiAdapter = new HuiAdapter();

    }

    private void getData() {

        listHash.clear();

        for (int i = 0; i < 4; i++) {
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
    /**
     * 引入的一个log
     * @author yjbo  @time 2017/3/30 14:28
     */
    private void testLogger() {
        /**
         * 参考：http://os.51cto.com/art/201609/517854.htm
         *     http://os.51cto.com/art/201609/517855.htm
         * @author yjbo  @time 2017/3/30 13:51
         */
//        Logger.init("MainActivity");
//        Logger.i("yjbo--i-1");
//        Logger.d("yjbo--d");
//        Logger.e("yjbo--e--d以下的级别不打印-1");
//        Logger.v("yjbo--v");
//        Logger.w("yjbo--w-1");
//        Logger.wtf("yjbo--wtf-1");
//        Logger.t("yjbo--t");
//        Logger.xml("yjbo-xml-1");
//        Settings setting = Logger.init("MainActivity");
//        setting.logLevel(LogLevel.FULL) //  显示全部日志，LogLevel.NONE不显示日志，默认是Full
//                .methodCount(5)         //  方法栈打印的个数，默认是2
//                .methodOffset(0)        //  设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
//                .hideThreadInfo();     //  隐藏线程信息
//                .logAdapter(new AndroidLogAdapter());// 自定义一个打印适配器，这里适配了Android的Log打印，你也可以自己实现LogAdapter接口来做一些特殊需求的日志打印适配

        new LogUtils(true);


        LogUtils.d("uknp");


    }

}
