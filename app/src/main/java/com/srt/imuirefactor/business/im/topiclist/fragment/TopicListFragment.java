package com.srt.imuirefactor.business.im.topiclist.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.srt.imuirefactor.R;
import com.srt.imuirefactor.business.im.interfaces.ImUnreadMsgListener;
import com.srt.imuirefactor.business.im.interfaces.RecyclerViewItemOnClickListener;
import com.srt.imuirefactor.business.im.interfaces.TitlebarActionListener;
import com.srt.imuirefactor.business.im.mock.DataReponsery;
import com.srt.imuirefactor.business.im.mock.MockTopicDataBean;
import com.srt.imuirefactor.business.im.msglist.activity.MsgListActivity;
import com.srt.imuirefactor.business.im.topiclist.adapter.ImTopicListRecyclerViewAdapter;
import com.srt.imuirefactor.business.im.topiclist.interfaces.TopicListContact;
import com.srt.imuirefactor.business.im.topiclist.interfaces.impls.TopicListPresenter;
import com.srt.imuirefactor.business.im.topiclist.sorter.ImTopicSorter;
import com.srt.imuirefactor.customize.view.ImTitleLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 朱晓龙 on 2018/5/7 10:17.
 * topic 列表 页面
 */

public class TopicListFragment extends Fragment implements TopicListContact.View<MockTopicDataBean>
        , View.OnClickListener {


    public TopicListFragment() {
        // Required empty public constructor
    }

    public TitlebarActionListener titlebarActionListener;
    public ImUnreadMsgListener imUnreadMsgListener;
    private ImTitleLayout mImTitleLayout;

    private RecyclerView im_topiclist_fragment_recyclerview;
    private ImTopicListRecyclerViewAdapter<MockTopicDataBean> mRecyclerAdapter;
    //    private SwipeRefreshLayout im_topiclist_fragment_swiperefreshlayout;
    private View root;

    private TopicListPresenter topicListPresenter;

    /**
     * TopicListFragment的持有者需要设置 titlebar的点击监听
     */
    public void setTitlebarActionListener(TitlebarActionListener titlebarActionListener) {
        this.titlebarActionListener = titlebarActionListener;
    }

    /**
     * TopicListFragment 的持有者需要设置新消息提醒的监听
     */
    public void setImUnreadMsgListener(ImUnreadMsgListener imUnreadMsgListener) {
        this.imUnreadMsgListener = imUnreadMsgListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.im_topiclist_fragment, container, false);
            viewInit(root);
            dataInit();
            listenerInit();
        }
        return root;
    }


    private void viewInit(View view) {
        mImTitleLayout = view.findViewById(R.id.im_title_layout);
        im_topiclist_fragment_recyclerview = view.findViewById(R.id.im_topiclist_fragment_recyclerview);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(false);
        im_topiclist_fragment_recyclerview.setLayoutManager(layoutManager);
    }

    private void listenerInit() {
        mImTitleLayout.setmTitlebarActionClickListener(new ImTitleLayout.TitlebarActionClickListener() {
            @Override
            public void onLeftComponentClicked() {


                //title左侧控件被点击
                if (titlebarActionListener != null) {
                    titlebarActionListener.onLeftComponentClicked();
                }
            }

            @Override
            public void onRightComponpentClicked() {
                if (titlebarActionListener != null) {
                    titlebarActionListener.onRightComponpentClicked();
                }
            }
        });
        mRecyclerAdapter.setmRecyclerViewItemOnClickListener(new RecyclerViewItemOnClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                topicListPresenter.setRedDot(mRecyclerAdapter.getDataList().get(position).getTopicId(),
                        (ArrayList<MockTopicDataBean>) mRecyclerAdapter.getDataList(), false);
//                mRecyclerAdapter.notifyItemChanged(position);
                mRecyclerAdapter.notifyDataSetChanged();
                MsgListActivity.invoke(getActivity());
            }
        });


        /*========================*/
        root.findViewById(R.id.addtopic).setOnClickListener(this);
        root.findViewById(R.id.removetopic).setOnClickListener(this);
        root.findViewById(R.id.msg).setOnClickListener(this);

    }

    //获取数据库数据进行UI 显示
    private void dataInit() {
        topicListPresenter = new TopicListPresenter(this, getActivity());

        mRecyclerAdapter = new ImTopicListRecyclerViewAdapter<MockTopicDataBean>(getActivity());
        im_topiclist_fragment_recyclerview.setAdapter(mRecyclerAdapter);
        // TODO: 2018/5/8  使用Constans imId
        topicListPresenter.doGetTopicList(0);
    }

    /**
     * 执行 {@link TopicListContact.Presenter} doGetTopicList 方法后的结果回调
     * 获取topiclist的回调
     */
    @Override
    public void onGetTopicList(List<MockTopicDataBean> topicList) {
        mRecyclerAdapter.setDataList(topicList);
        mRecyclerAdapter.notifyDataSetChanged();
    }

    /**
     * ui层  对于底层topic信息改变时的回调
     * 当接收到MQTT消息或HTTP请求返回的时候 执行更新操作
     */
    @Override
    public void onTopicInfoUpdate(List<MockTopicDataBean> topicList, long topicId, int type) {
        //根据具体需求执行相应的更新
        switch (type) {
            case 0://添加topic
                break;
            case 1://删除topic
                break;
            case 2://topic成员变更
                break;
            default:
                //未知情况 进行一次全刷新吧……
                break;
        }
        mRecyclerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addtopic:
                topicListPresenter.mockAddTopic();
                break;
            case R.id.removetopic:
                topicListPresenter.mockRemoveTopic();
                break;
            case R.id.msg:
                topicListPresenter.mockNewMsg();
                break;
        }
    }
}
