package com.srt.imuirefactor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.srt.imuirefactor.business.im.DemoActivity;

/**
 * Created by 朱晓龙 on 2018/5/7 9:54.

 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void toTopicList(View view){
        DemoActivity.invoke(MainActivity.this,0);
    }

    public void toMsgList(View view){
        DemoActivity.invoke(MainActivity.this,1);
    }

}
