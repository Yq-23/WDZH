package com.example.firstapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FrameActivity extends FragmentActivity {

    private static final String TAG = "FrameActivity";
    private Fragment mFrangents[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioButton rbtHome, rbtFunc, rbtSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        mFrangents = new Fragment[3];
        fragmentManager = getSupportFragmentManager();
        mFrangents[0] = fragmentManager.findFragmentById(R.id.fragment_main);
        mFrangents[1] = fragmentManager.findFragmentById(R.id.fragment_func);
        mFrangents[2] = fragmentManager.findFragmentById(R.id.fragment_setting);
        fragmentTransaction = fragmentManager.beginTransaction()
                .hide(mFrangents[0]).hide(mFrangents[1]).hide(mFrangents[2]);
        fragmentTransaction.show((mFrangents[0])).commit();

        rbtHome = (RadioButton)findViewById(R.id.radioHome);
        rbtFunc = (RadioButton)findViewById(R.id.radioFunc);
        rbtSetting = (RadioButton)findViewById(R.id.radioSetting);

        radioGroup = (RadioGroup)findViewById(R.id.bottomGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("radioGroup", "checkId=" + checkedId);
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFrangents[0]).hide(mFrangents[1]).hide(mFrangents[2]);
                switch (checkedId){
                    case R.id.radioHome:
                        fragmentTransaction.show(mFrangents[0]).commit();
                        break;
                    case R.id.radioFunc:
                        fragmentTransaction.show(mFrangents[1]).commit();
                        break;
                    case R.id.radioSetting:
                        fragmentTransaction.show(mFrangents[2]).commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}