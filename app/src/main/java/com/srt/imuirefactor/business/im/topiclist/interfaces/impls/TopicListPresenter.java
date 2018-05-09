package com.srt.imuirefactor.business.im.topiclist.interfaces.impls;

import android.content.Context;
import android.util.Log;

import com.srt.imuirefactor.business.im.mock.DataReponsery;
import com.srt.imuirefactor.business.im.mock.MockMsgResponse;
import com.srt.imuirefactor.business.im.mock.MockTopicDataBean;
import com.srt.imuirefactor.business.im.topiclist.interfaces.TopicListContact;
import com.srt.imuirefactor.business.im.topiclist.sorter.ImTopicSorter;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;

/**
 * Created by 朱晓龙 on 2018/5/8 9:31.
 */

public class TopicListPresenter implements TopicListContact.Presenter {
    private final String TAG=getClass().getSimpleName();

    private TopicListContact.View view;
    private Context mContext;
    private DataReponsery dataReponsery;
    public TopicListPresenter(TopicListContact.View view, Context mContext) {
        this.view = view;
        this.mContext = mContext;
        dataReponsery=DataReponsery.getInstance(mContext);
        dataReponsery.setTopicUpdateListener(updateListener);
    }

    private DataReponsery.TopicUpdateListener updateListener=new DataReponsery.TopicUpdateListener() {
        @Override
        public void onNewTopic(long topicId) {
            Log.i(TAG, "onNewTopic: ");
            setRedDot(topicId,dataReponsery.getTopicDataInMemory(),true);
            ImTopicSorter.insertTopicToTop(topicId,dataReponsery.getTopicDataInMemory());
            view.onTopicInfoUpdate(dataReponsery.getTopicDataInMemory(),topicId,0);
        }

        @Override
        public void onTopicMemberChange(long topicId) {
            Log.i(TAG, "onTopicMemberChange: ");
            view.onTopicInfoUpdate(dataReponsery.getTopicDataInMemory(),topicId,1);
        }

        @Override
        public void onNewMsg(long topicId) {
            Log.i(TAG, "onNewMsg: ");
            setRedDot(topicId,dataReponsery.getTopicDataInMemory(),true);
            ImTopicSorter.insertTopicToTop(topicId,dataReponsery.getTopicDataInMemory());
            view.onTopicInfoUpdate(dataReponsery.getTopicDataInMemory(),topicId,2);
        }
    };
    @Override
    public void doGetTopicList(int imId) {
        Log.i(TAG, "doGetTopicList: ");
        if (view != null) {
            ArrayList<MockTopicDataBean> topicList=DataReponsery.getInstance(mContext).getTopicDataInMemory();
            ImTopicSorter.sortByLatestTime(topicList);
            view.onGetTopicList(topicList);
        }
    }

    public void setRedDot(long topicId,ArrayList<MockTopicDataBean> topics,boolean show){
        for (MockTopicDataBean topic : topics) {
            if (topic.getTopicId()==topicId) {
                topic.setShowDot(show);
                break;
            }
        }
    }


    public void mockAddTopic(){
        dataReponsery.addNewTopic();
    }
    public void mockRemoveTopic(){
        dataReponsery.removeTopic();
    }
    public void mockNewMsg(){
        dataReponsery.receiveMsg();
    }


}
