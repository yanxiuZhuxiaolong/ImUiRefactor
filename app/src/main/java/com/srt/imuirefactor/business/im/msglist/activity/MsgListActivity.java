package com.srt.imuirefactor.business.im.msglist.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.srt.imuirefactor.R;
import com.srt.imuirefactor.business.im.interfaces.RecyclerViewItemOnClickListener;
import com.srt.imuirefactor.business.im.mock.MockMsgDataBean;
import com.srt.imuirefactor.business.im.msglist.adapter.DecorateAdapter;
import com.srt.imuirefactor.business.im.msglist.interfaces.MsgListContact;
import com.srt.imuirefactor.business.im.msglist.interfaces.impls.MsgListPresenter;
import com.srt.imuirefactor.business.im.photoview.activity.GalleryActivity;

import java.util.ArrayList;

public class MsgListActivity extends AppCompatActivity implements MsgListContact.IView<MockMsgDataBean> {
    private final String TAG=getClass().getSimpleName();

    public static void invoke(Activity activity){
        Intent intent=new Intent(activity,MsgListActivity.class);
        activity.startActivity(intent);
    }


    private RecyclerView msgRecyclerView;
    private DecorateAdapter msgRecyclerAdapter;
    private EditText inputEditText;

    private MsgListPresenter msgListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msglist_activity);
        viewInit();
        dataInit();
        listenerInit();
    }

    private void viewInit(){
        msgRecyclerView=findViewById(R.id.im_msglist_recyclerview);
        LinearLayoutManager layoutManager=
                new LinearLayoutManager(MsgListActivity.this,LinearLayoutManager.VERTICAL,true);
        layoutManager.setStackFromEnd(false);
        msgRecyclerView.setLayoutManager(layoutManager);
        msgRecyclerView.setItemAnimator(null);
        msgRecyclerAdapter=new DecorateAdapter(MsgListActivity.this,null);
        msgRecyclerView.setAdapter(msgRecyclerAdapter);
        msgRecyclerAdapter.setLoadMoreListener(new DecorateAdapter.LoadMoreListener() {
            @Override
            public void onFooterViewVisiable() {
                //获取上一页消息列表
                msgListPresenter.loadMore();
            }
        });

        inputEditText=findViewById(R.id.msg_edittext);
    }

    private void dataInit(){
        msgListPresenter=new MsgListPresenter(this,MsgListActivity.this);
        //获取最新一页消息
        msgListPresenter.doGetMsgList(0);
    }

    private void listenerInit(){
       msgRecyclerAdapter.setItemClickListener(new RecyclerViewItemOnClickListener() {
           @Override
           public void onItemClicked(View view, int position) {
               ArrayList<String> urls=msgListPresenter.getAllImageUrls();
               int p = msgListPresenter.getUrlPosition(position,urls);
               GalleryActivity.invoke(MsgListActivity.this,urls,p);
               // TODO: 2018/5/8  跳转到 大图界面 
               Toast.makeText(MsgListActivity.this, "点击msg item "+position, Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void hideSoftInput(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Override
    public void onGetMsgList(ArrayList<MockMsgDataBean> msgList) {
        //recycler 当前没有数据 直接全部更新
        msgRecyclerAdapter.setDataList(msgList);
        msgRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore(int size) {
        //首先通知移除 footview
        msgRecyclerAdapter.removeFooterView();
        //然后通知插入 新数据
        msgRecyclerAdapter.notifyItemRangeInserted(msgRecyclerAdapter.getItemCount()-size-1,
                msgRecyclerAdapter.getItemCount()-1);
        if (size>0) {
            msgRecyclerAdapter.addFooterView();
        }
    }

    @Override
    public void onNewMsg(MockMsgDataBean msg) {

    }

    @Override
    public void onSendMsg(MockMsgDataBean msg) {

    }
}
