package com.srt.imuirefactor.business.im.topiclist.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srt.imuirefactor.R;
import com.srt.imuirefactor.business.im.interfaces.ImUnreadMsgListener;
import com.srt.imuirefactor.business.im.interfaces.TitlebarActivonCallback;
import com.srt.imuirefactor.customize.view.ImTitleLayout;

/**
 * Created by 朱晓龙 on 2018/5/7 10:17.
 * topic 列表 页面
 */

public class TopicListFragment extends Fragment {


    public TopicListFragment() {
        // Required empty public constructor
    }

    public TitlebarActivonCallback titlebarActivonCallback;
    public ImUnreadMsgListener imUnreadMsgListener;
    private ImTitleLayout mImTitleLayout;
    private RecyclerView im_topiclist_fragment_recyclerview;
    private SwipeRefreshLayout im_topiclist_fragment_swiperefreshlayout;
    private View root;


    public void setTitlebarActivonCallback(TitlebarActivonCallback titlebarActivonCallback) {
        this.titlebarActivonCallback = titlebarActivonCallback;
    }

    public void setImUnreadMsgListener(ImUnreadMsgListener imUnreadMsgListener) {
        this.imUnreadMsgListener = imUnreadMsgListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.im_topiclist_fragment, container, false);
            viewInit(root);
            listenerInit();
        }
        return root;
    }


    private void viewInit(View view) {
        mImTitleLayout = view.findViewById(R.id.im_title_layout);
        im_topiclist_fragment_recyclerview = view.findViewById(R.id.im_topiclist_fragment_recyclerview);
        im_topiclist_fragment_swiperefreshlayout = view.findViewById(R.id.im_topiclist_fragment_swiperefreshlayout);
    }

    private void listenerInit(){
        mImTitleLayout.setmTitlebarActionClickListener(new ImTitleLayout.TitlebarActionClickListener() {
            @Override
            public void onLeftComponentClicked() {

            }

            @Override
            public void onRightComponpentClicked() {

            }
        });
        im_topiclist_fragment_swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }
    //获取数据库数据进行UI 显示
    private void dataInit(){

    }

}
