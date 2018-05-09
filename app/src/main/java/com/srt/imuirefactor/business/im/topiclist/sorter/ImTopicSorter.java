package com.srt.imuirefactor.business.im.topiclist.sorter;

import android.text.TextUtils;

import com.srt.imuirefactor.business.im.mock.MockTopicDataBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by 朱晓龙 on 2018/5/8 11:21.
 * 负责处理所有关于topiclist页面的排序工作
 * 需要线程同步
 */

public class ImTopicSorter {



    /**
     * 将原始topic 列表 按照 群聊私聊的不同 进行一次 排序
     * 群聊在前 私聊在后
     * */
    private static void groupTopicByType(ArrayList<MockTopicDataBean> topicList){
        synchronized (topicList) {
            ArrayList<MockTopicDataBean> groupTopic = new ArrayList<>();
            ArrayList<MockTopicDataBean> privateTopic = new ArrayList<>();
            for (MockTopicDataBean topicDataBean : topicList) {
                if (TextUtils.equals(topicDataBean.getType(), "2")) {
                    groupTopic.add(topicDataBean);
                } else {
                    privateTopic.add(topicDataBean);
                }
            }
            topicList.clear();
            topicList.addAll(groupTopic);
            topicList.addAll(privateTopic);
        }
    }

    /**
     * 收到新消息后的排序操作
     * 只对收到消息的topic 进行置顶操作
     * */
    public static void insertTopicToTop(long topicId, ArrayList<MockTopicDataBean> topicList){
        synchronized (topicList) {
            if (topicList == null) {
                return;
            }
            Iterator<MockTopicDataBean> iterator = topicList.iterator();
            //首先找到目标topic
            MockTopicDataBean targetTopic = null;
            while (iterator.hasNext()) {
                targetTopic = iterator.next();
                if (topicId == targetTopic.getTopicId()) {
                    //然后进行原有位置的移除操作
                    iterator.remove();
                    break;
                }
            }
            topicList.add(0, targetTopic);
            groupTopicByType(topicList);
        }
    }
    /**
     * 应用启动后 获取全部topic的信息进行一次排序
     * 这次排序 根据 lstestMsgId 以及 latestMsgSendTime来进行
     * 排序比较标准为 以消息最新者置顶
     * */
    public static void sortForSetUp(ArrayList<MockTopicDataBean> topicList){
        synchronized (topicList) {
            //
            Comparator<MockTopicDataBean> comparator = new Comparator<MockTopicDataBean>() {
                @Override
                public int compare(MockTopicDataBean t1, MockTopicDataBean t2) {
                    if (t1.getLatestMsg().getSendTime()>t2.getLatestMsg().getSendTime()) {
                        return 1;
                    }else if (t1.getLatestMsg().getSendTime()<t2.getLatestMsg().getSendTime()){
                        return -1;
                    }else {
                        return 0;
                    }
                }
            };
            Collections.sort(topicList,comparator);
            groupTopicByType(topicList);
        }
    }

}
