package com.gyf.immersionbar.activity;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.gyf.immersionbar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by geyifeng on 2017/5/8.
 */

public class KeyBoardActivity extends BaseActivity {

    @BindView(R.id.line)
    LinearLayout layout;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private List<Map<String, Object>> mapList;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_key_board;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .init();
//        解决软键盘与底部输入框冲突问题，或者使用以下方法，任选其一
//        KeyboardPatch.patch(this).enable();
//        KeyboardPatch.patch(this,layout).enable();  //layout指的是当前布局的根节点
    }

    @Override
    protected void initData() {
        mapList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("desc", "我是假数据" + i);
            mapList.add(map);
        }
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        listView.setAdapter(new SimpleAdapter(this, mapList, R.layout.item_simple, new String[]{"desc"}, new int[]{R.id.text}));
    }

    @Override
    protected void setListener() {
        //toolbar返回按钮监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);
                finish();
            }
        });
    }
}
