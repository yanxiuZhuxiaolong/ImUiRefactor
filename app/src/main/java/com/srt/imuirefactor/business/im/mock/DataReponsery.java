package com.srt.imuirefactor.business.im.mock;

import android.content.Context;

import com.srt.imuirefactor.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 朱晓龙 on 2018/4/28 17:13.
 */

public class DataReponsery {
    private final String TAG = getClass().getSimpleName();

    private ArrayList<MockMsgDataBean> msgDataInMemory = new ArrayList<>();
    private ArrayList<MockTopicDataBean> topicDataInMemory = new ArrayList<>();

    private static DataReponsery instance;
    private Context mContext;

    public static DataReponsery getInstance(Context context) {
        if (instance == null) {
            synchronized (DataReponsery.class) {
                if (instance == null) {
                    instance = new DataReponsery(context);
                }
            }
        }
        return instance;
    }

    public DataReponsery(Context context) {
        this.mContext = context;
        //初始化10个topic
        createMockTopicList();
    }


    private MockMsgDataBean createNewMsg(long topicId) {
        Random random = new Random();
        String[] mockStr = mContext.getResources().getStringArray(R.array.mockarray);
        ArrayList<MockRespone.MockImageDataBean> imageDataBeans = MockRespone.getMockResponeData();
        //1、创建msg对象
        MockMsgDataBean dataBean = new MockMsgDataBean();
        //2、获取最远一条消息
        MockMsgDataBean latestDataBean = null;
        if (msgDataInMemory.size() > 0) {
            latestDataBean = msgDataInMemory.get(msgDataInMemory.size() - 1);
        } else {
            latestDataBean = new MockMsgDataBean();
            latestDataBean.setSendTime(System.currentTimeMillis());
            latestDataBean.setMsgId(Long.MAX_VALUE);
        }
        //设置消息发送时间
        //随机时间 5分钟 单位每分钟
        long minute = random.nextInt(6);
        dataBean.setSendTime(latestDataBean.getSendTime() - 10 * 1000);

        //超过一分钟显示时间线
        dataBean.setShowData(minute > 1);
        //设置消息id
        dataBean.setMsgId(latestDataBean.getMsgId() - random.nextInt(5));
        //随机消息类型 80%生成图片消息
        if (random.nextInt(6) > 4) {
            //文字
            dataBean.setType("10");
            int position = random.nextInt(mockStr.length - 1);
            dataBean.setMsg(mockStr[position]);
        } else {
            //图片
            dataBean.setType("20");
            int position = random.nextInt(imageDataBeans.size() - 1);
            MockRespone.MockImageDataBean imageDataBean = imageDataBeans.get(position);
            dataBean.setImageUrl(imageDataBean.getUrl());

            dataBean.setImgWidth(Math.min(imageDataBean.getW(), 500));
            dataBean.setImgHeight(Math.min(imageDataBean.getH(), 500));

        }
        //随机发送者
        dataBean.setSenderId(random.nextInt(10));
        dataBean.setSenderName("senderId : " + dataBean.getSenderId());
        return dataBean;
    }


    public void createMockMsgData(int size) {
        Random random = new Random();
        String[] mockStr = mContext.getResources().getStringArray(R.array.mockarray);
        ArrayList<MockRespone.MockImageDataBean> imageDataBeans = MockRespone.getMockResponeData();
        for (int i = 0; i < size; i++) {
            //1、创建msg对象
            MockMsgDataBean dataBean = new MockMsgDataBean();
            //2、获取最远一条消息
            MockMsgDataBean latestDataBean = null;
            if (msgDataInMemory.size() > 0) {
                latestDataBean = msgDataInMemory.get(msgDataInMemory.size() - 1);

            } else {
                latestDataBean = new MockMsgDataBean();
                latestDataBean.setSendTime(System.currentTimeMillis());
                latestDataBean.setMsgId(Long.MAX_VALUE);
            }
            //设置消息发送时间
            long minute = random.nextInt(6);
            dataBean.setSendTime(latestDataBean.getSendTime() - minute * 60 * 1000);
            dataBean.setShowData(minute > 2);
            //设置消息id
            dataBean.setMsgId(latestDataBean.getMsgId() - random.nextInt(5));
            //随机消息类型 80%生成图片消息
            if (random.nextInt(6) > 0) {
                //文字
                dataBean.setType("10");
                int position = random.nextInt(mockStr.length - 1);
                dataBean.setMsg(mockStr[position]);
            } else {
                //图片
                dataBean.setType("20");
                int position = random.nextInt(imageDataBeans.size() - 1);
                MockRespone.MockImageDataBean imageDataBean = imageDataBeans.get(position);
                dataBean.setImageUrl(imageDataBean.getUrl());

                dataBean.setImgWidth(Math.min(imageDataBean.getW(), 500));
                dataBean.setImgHeight(Math.min(imageDataBean.getH(), 500));

            }
            //随机发送者
            dataBean.setSenderId(random.nextInt(3));
            dataBean.setSenderName("senderId : " + dataBean.getSenderId());
            msgDataInMemory.add(dataBean);
        }
    }

    public void createMockTopicList() {
        if (topicDataInMemory.size() != 0) {
            return;
        }
        Random random = new Random();
        MockTopicDataBean topicDataBean = null;
        for (int i = 0; i < 20; i++) {
            topicDataBean = new MockTopicDataBean();
            topicDataBean.setType(random.nextBoolean() ? "1" : "2");
            topicDataBean.setTopicId(i);
            topicDataBean.setLatestMsg(createNewMsg(i));
            topicDataInMemory.add(topicDataBean);
        }
    }

    public void addNewTopic() {
        Random random = new Random();
        MockTopicDataBean addTopic = new MockTopicDataBean();
        addTopic.setType(random.nextBoolean() ? "1" : "2");
        addTopic.setTopicId(MockTopicDataBean.count);
        addTopic.setLatestMsg(createNewMsg(addTopic.getTopicId()));
        topicDataInMemory.add(addTopic);
        topicUpdateListener.onNewTopic(addTopic.getTopicId());
    }

    public void removeTopic() {
        Random random = new Random();
        int l = topicDataInMemory.size();
        MockTopicDataBean removeTopic = topicDataInMemory.get(random.nextInt(l));
        long topicId = removeTopic.getTopicId();
        topicDataInMemory.remove(removeTopic);
        topicUpdateListener.onTopicMemberChange(topicId);
    }

    public void receiveMsg() {
        Random random = new Random();
        MockTopicDataBean targetTopic = topicDataInMemory.get(random.nextInt(topicDataInMemory.size()));
        MockMsgDataBean newMsg = createNewMsg(targetTopic.getTopicId());
        targetTopic.setLatestMsg(newMsg);
        topicUpdateListener.onNewMsg(targetTopic.getTopicId());
    }

    public void getNextPageData() {
        createMockMsgData(20);
    }

    public ArrayList<MockMsgDataBean> getData() {
        return msgDataInMemory;
    }

    public ArrayList<MockTopicDataBean> getTopicDataInMemory() {
        return topicDataInMemory;
    }


    private TopicUpdateListener topicUpdateListener;

    public void setTopicUpdateListener(TopicUpdateListener topicUpdateListener) {
        this.topicUpdateListener = topicUpdateListener;
    }

    public interface TopicUpdateListener {
        void onNewTopic(long topicId);

        void onTopicMemberChange(long topicId);

        void onNewMsg(long topicId);
    }
}
