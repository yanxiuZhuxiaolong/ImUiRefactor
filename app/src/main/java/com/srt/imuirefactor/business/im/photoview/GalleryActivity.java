package com.srt.imuirefactor.business.im.photoview;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.srt.imuirefactor.R;
import com.srt.imuirefactor.business.im.photoview.adapter.ImGalleryViewPagerAdapter;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
    public static void invoke(Activity activity,ArrayList<String> imgUrls,int currentPosition){
        Intent intent=new Intent(activity,GalleryActivity.class);
        intent.putExtra("imgurls",imgUrls);
        intent.putExtra("position",currentPosition);
        activity.startActivity(intent);
    }



    private ViewPager viewPager;
    private ArrayList<String> urls;
    private int initPosition=0;
    private ImGalleryViewPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        viewInit();
        dataInit();
        listenerInit();
    }

    private void viewInit(){
        viewPager=findViewById(R.id.im_gallery_viewpager);
    }
    private void dataInit(){
        urls=getIntent().getStringArrayListExtra("imgurls");
        initPosition=getIntent().getIntExtra("position",0);
        pagerAdapter=new ImGalleryViewPagerAdapter(urls);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(initPosition);
    }

    private void listenerInit(){
        pagerAdapter.setItemOnClickListener(new ImGalleryViewPagerAdapter.ItemOnClickListener() {
            @Override
            public void onClicked() {
                GalleryActivity.this.finish();
            }
        });
    }
}
