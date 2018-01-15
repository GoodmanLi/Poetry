package com.cruse.lee.poetry;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cruse.lee.poetry.views.MainBottomButton;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainBottomPoetry) MainBottomButton mainBottomPoetry;
    @BindView(R.id.mainBottomRecommend) MainBottomButton mainBottomRecommend;
    @BindView(R.id.mainBottomCenter) MainBottomButton mainBottomCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mainBottomCenter.observeOther(mainBottomPoetry,mainBottomRecommend);
        mainBottomPoetry.observeOther(mainBottomCenter,mainBottomRecommend);
        mainBottomRecommend.observeOther(mainBottomPoetry,mainBottomCenter);
    }

    @OnClick({R.id.mainBottomPoetry, R.id.mainBottomRecommend, R.id.mainBottomCenter})
    public void onBottomClick(View view){
        switch (view.getId()){
            case R.id.mainBottomPoetry:
                showSnackToast(view, "1");
                break;
            case R.id.mainBottomRecommend:
                showSnackToast(view, "2");
                break;
            case R.id.mainBottomCenter:
                showSnackToast(view, "3");
                break;
            default:
                break;
        }
    }

    private void showSnackToast(View view, String msg){
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }


}
