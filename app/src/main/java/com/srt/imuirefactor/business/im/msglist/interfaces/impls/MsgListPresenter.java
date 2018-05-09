package com.srt.imuirefactor.business.im.msglist.interfaces.impls;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.srt.imuirefactor.business.im.mock.DataReponsery;
import com.srt.imuirefactor.business.im.mock.MockMsgDataBean;
import com.srt.imuirefactor.business.im.msglist.interfaces.MsgListContact;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by 朱晓龙 on 2018/5/8 15:17.
 */

public class MsgListPresenter implements MsgListContact.IPresenter {
    private final String TAG=getClass().getSimpleName();
    private MsgListContact.IView view;
    private Context mContext;
    private DataReponsery dataReponsery;

    public MsgListPresenter(MsgListContact.IView view, Context mContext) {
        this.view = view;
        this.mContext = mContext;
        dataReponsery = DataReponsery.getInstance(mContext);
    }


    @Override
    public void doGetMsgList(long topicId) {
        dataReponsery.createMockMsgData(10);
        view.onGetMsgList(dataReponsery.getData());
    }

    private android.os.Handler handler=new android.os.Handler();


    @Override
    public void loadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int oldSize = dataReponsery.getData().size();
                if (oldSize<50){
                    dataReponsery.getNextPageData();
                }
                int newSize = dataReponsery.getData().size();
                Log.i(TAG, "loadMore: oldSize = "+oldSize +" newSize = "+newSize);
                view.onLoadMore(newSize - oldSize);
            }
        },1000);


    }

    @Override
    public void doSendMsg(MockMsgDataBean msg) {

    }

    public int getUrlPosition(int p,ArrayList<String> urls){
        MockMsgDataBean mockMsgDataBean=dataReponsery.getData().get(p);
        for (String url : urls) {
            if (TextUtils.equals(url,mockMsgDataBean.getImageUrl())) {
                return urls.indexOf(url);
            }
        }
        return 0;
    }
    public ArrayList<String> getAllImageUrls(){
        ArrayList<String> urls=new ArrayList<>();
        for (MockMsgDataBean msgDataBean : dataReponsery.getData()) {
            if (msgDataBean.getType().equals("20")) {
                urls.add(msgDataBean.getImageUrl());
            }
        }
        return urls;
    }
}
