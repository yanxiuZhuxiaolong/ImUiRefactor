package com.srt.imuirefactor.business.im.mock;

/**
 * Created by 朱晓龙 on 2018/4/28 16:03.
 */

public class MockMsgDataBean {

    public  static int count;

    public MockMsgDataBean() {
        count++;
    }

    //消息类型
    String type="10";
    //消息内容
    String msg="";
    //图片地址
    String imageUrl="";
    //图片 尺寸
    int imgWidth=0;
    int imgHeight=0;
    //所属topic
    long topicId;
    //msgId
    long msgId;
    //发送者id
    long senderId;

    /**
     * 这些信息由网络获取后 在本地进行数据看存储时进行 判断赋值 减少ui层的数据计算
     * */
    String senderAvaral;
    String senderName;
    //发送时间
    long sendTime;
    //是否显示发送时间
    boolean showData=false;

    public boolean isShowData() {
        return showData;
    }

    public void setShowData(boolean showData) {
        this.showData = showData;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public String getSenderAvaral() {
        return senderAvaral;
    }

    public void setSenderAvaral(String senderAvaral) {
        this.senderAvaral = senderAvaral;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public long getTopicId() {
        return topicId;
    }

    public long getMsgId() {
        return msgId;
    }

    public long getSenderId() {
        return senderId;
    }

    public long getSendTime() {
        return sendTime;
    }
}
