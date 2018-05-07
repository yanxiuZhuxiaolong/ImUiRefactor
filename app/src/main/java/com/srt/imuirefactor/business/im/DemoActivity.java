package com.srt.imuirefactor.business.im;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.srt.imuirefactor.R;
import com.srt.imuirefactor.business.im.topiclist.fragment.TopicListFragment;

/**
 * Created by 朱晓龙 on 2018/5/7 9:52.
 *  * 项目引入IM的 activityt
 */

public class DemoActivity extends AppCompatActivity {
    public static final int TYPE_TOPIC=0X01;
    public static final int TYPE_MSG=0X02;


    private TopicListFragment topicListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);
        int type=getIntent().getIntExtra("type",0);
        topicListFragment=new TopicListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_contain,topicListFragment).commit();
    }


    public static void invoke(Activity activity,int type){
        Intent intent=new Intent(activity,DemoActivity.class);
        intent.putExtra("type",type);
        activity.startActivity(intent);
    }
}
