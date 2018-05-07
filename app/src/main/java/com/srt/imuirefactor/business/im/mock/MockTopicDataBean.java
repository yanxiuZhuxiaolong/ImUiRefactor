package com.srt.imuirefactor.business.im.mock;

/**
 * Created by 朱晓龙 on 2018/5/7 16:59.
 */

public class MockTopicDataBean {

    String type; //1 私聊 2 群聊
    long topicId;
    String groupName;
    String imgUrl;
    MockMsgDataBean latestMsg;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setLatestMsg(MockMsgDataBean latestMsg) {
        this.latestMsg = latestMsg;
    }

    public MockMsgDataBean getLatestMsg() {
        return latestMsg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
