package com.yijian.dzpoker.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yijian.dzpoker.R;
import com.yijian.dzpoker.util.DzApplication;


/**
 * 带有toolbar, back arrow的activity
 */
public abstract class BaseBackActivity extends AppCompatActivity implements View.OnClickListener{

    protected TextView tv_back,tv_title;
    protected DzApplication application;
    protected String strLoginName;
    protected int userId;
    protected String remoteURL;

    protected abstract int getLayoutId();


    protected void initViews(){
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        application=(DzApplication) getApplication();
        strLoginName=application.getLoginName();
        userId=application.getUserId();
        remoteURL=getString(R.string.url_remote);
        initViews();
    }


}
