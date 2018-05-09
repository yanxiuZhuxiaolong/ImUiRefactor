package com.srt.imuirefactor.business.im.mock;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.util.Random;

/**
 * Created by 朱晓龙 on 2018/5/9 13:45.
 */

public class MsgSender {
    private final int MSG_START = 0X01;
    private final int MSG_PROCESS = 0X02;
    private final int MSG_SUCCESS = 0X03;
    private final int MSG_FAILURE = 0X04;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_START:
                    //开始发送 下一步开始 回调
                    ((SendStateCallback) msg.obj).onSuccess();
                    break;
                case MSG_PROCESS:
                    if (100<process++){
                        ((SendStateCallback) msg.obj).onProcess(process);
                        Message remsg=obtainMessage();
                        remsg.what=MSG_PROCESS;
                        remsg.obj=msg.obj;
                        sendMessageDelayed(remsg,random.nextInt(10)*100);
                    }else {
                        if (random.nextBoolean()) {
                            ((SendStateCallback) msg.obj).onSuccess();

                        }else {
                            ((SendStateCallback) msg.obj).onFailure();
                        }
                    }

                    break;
                case MSG_SUCCESS:
                    break;
                case MSG_FAILURE:
                    break;
                default:
                    break;
            }
        }
    };
    private int process = 0;
    private int state = 0;
    private Random random = new Random();

    public void doSendMsg(MockMsgDataBean msg, SendStateCallback callback) {
        Message message=handler.obtainMessage();
        message.what=MSG_START;
        message.obj=callback;
        handler.sendMessage(message);
    }


    public interface SendStateCallback {
        void onSuccess();

        void onFailure();

        void onProcess(int process);
    }
}
