package com.srt.imuirefactor.business.im.photoview.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.srt.imuirefactor.R;
import com.srt.imuirefactor.customize.view.ZoomImageView;

import java.util.ArrayList;

/**
 * Created by 朱晓龙 on 2018/5/8 17:11.
 */

public class ImGalleryViewPagerAdapter extends PagerAdapter {
    private ArrayList<String> datalist;


    public ImGalleryViewPagerAdapter(ArrayList<String> datalist) {
        this.datalist = datalist;
    }

    @Override
    public int getCount() {
        return datalist==null?0:datalist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ZoomImageView zoomImageView=new ZoomImageView(container.getContext());
        ViewGroup.LayoutParams layoutParams=new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        zoomImageView.setLayoutParams(layoutParams);
        Glide.with(zoomImageView.getContext())
                .load(datalist.get(position))
                .into(zoomImageView);
        container.addView(zoomImageView);
        if (itemOnClickListener != null) {
            zoomImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClickListener.onClicked();
                }
            });
        }
        return zoomImageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    private ItemOnClickListener itemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    public interface ItemOnClickListener{
        void onClicked();
    }
}
