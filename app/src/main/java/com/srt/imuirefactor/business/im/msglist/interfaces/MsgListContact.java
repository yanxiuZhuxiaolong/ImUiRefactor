package com.srt.imuirefactor.business.im.msglist.interfaces;

import android.app.Activity;

import com.srt.imuirefactor.business.im.mock.MockMsgDataBean;

import java.util.ArrayList;

/**
 * Created by 朱晓龙 on 2018/5/8 15:15.
 */

public interface MsgListContact {
    interface IView<E extends MockMsgDataBean> {
        void onGetMsgList(ArrayList<E> msgList);
        void onLoadMore(int size);
        void onNewMsg(E msg);
        void onSendMsg(E msg);
    }


    interface IPresenter<E extends MockMsgDataBean>{
        void doGetMsgList(long topicId);
        void loadMore();
        void doSendMsg(E msg);
    }

}
