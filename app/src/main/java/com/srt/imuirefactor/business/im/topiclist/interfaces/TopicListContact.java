package com.srt.imuirefactor.business.im.topiclist.interfaces;

import java.util.List;

/**
 * Created by 朱晓龙 on 2018/5/8 9:18.
 */

public interface TopicListContact {
    interface View<E>{
        void onGetTopicList(List<E> topicList);
        void onTopicInfoUpdate(List<E> topicList,long topicId,int type);
    }
    interface Presenter{
        void doGetTopicList(int imId);
    }
}
