package com.srt.imuirefactor.business.im.msglist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.PluralsRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.srt.imuirefactor.R;
import com.srt.imuirefactor.business.im.mock.MockMsgDataBean;
import com.srt.imuirefactor.customize.view.ProgressImageContainer;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * create by 朱晓龙 2018/4/23 下午10:21
 */
public class DataAdapter<E extends MockMsgDataBean> extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private ArrayList<E> dataList;

    enum ItemType {
        TYPE_MYMSG, TYPE_MSG
    }

    public DataAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public DataAdapter(Context mContext, ArrayList<E> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    public ArrayList<E> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<E> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i(TAG, "getItemViewType: sender id " + dataList.get(position).getSenderId());
        if (dataList.get(position).getSenderId() == 1) {
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.im_msglist_recycler_mymsg_item_layout, parent, false);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.im_msglist_recycler_msg_item_layout, parent, false);
        }
        return new DataViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
//        Log.i(TAG, "onBindViewHolder: ");
        holder.setData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public long getLatestMsgId() {
        if (dataList != null && dataList.size() != 0) {
            return dataList.get(dataList.size() - 1).getMsgId();
        }
        return -1;
    }


    public static class DataViewHolder extends AbstractViewHolder {
        private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        private Context context;
        private ImageView avaralImg;
        private ProgressImageContainer imageContainer;
        private ImageView msgImage;

        private TextView msgTime;
        private TextView msgContent;

        private TextView historyLine;
        private TextView senderName;

        public DataViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            avaralImg = itemView.findViewById(R.id.sender_avaral);
            msgContent = itemView.findViewById(R.id.msg_textview);
            msgTime = itemView.findViewById(R.id.data);
            imageContainer = itemView.findViewById(R.id.imageview_container);
            msgImage=imageContainer.mRoundCornerImage;
            senderName = itemView.findViewById(R.id.sender_name);
        }

        public void setData(MockMsgDataBean data) {
            //1、判断发送者是不是当前用户本人
            boolean isMyMsg = (data.getTopicId() == 1);
            //2、显示发送者头像
            loadAraval(data.getSenderAvaral());
            senderName.setText(data.getSenderName());
            //3、是否显示时间
            msgTime.setText(timeFormater(data.getSendTime()));
            msgTime.setVisibility(data.isShowData() ? View.VISIBLE : View.GONE);
            //4、显示消息内容
            if (data.getType().equals("10")) {
                msgContent.setText(data.getMsg());
                showTextContent();
            } else if (data.getType().equals("20")) {
                showImageContent();
                ViewGroup.LayoutParams imageParams = imageContainer.getLayoutParams();
                imageParams.height = data.getImgHeight();
                imageParams.width = data.getImgWidth();
                imageContainer.setLayoutParams(imageParams);
                loadImage(data.getImageUrl(), data.getImgWidth(), data.getImgHeight());
            }
        }

        private void showTextContent() {
            msgContent.setVisibility(View.VISIBLE);
            imageContainer.setVisibility(View.GONE);
        }

        private void showImageContent() {
            msgContent.setVisibility(View.GONE);
            imageContainer.setVisibility(View.VISIBLE);
        }


        /**
         * 格式化时间
         */
        private String timeFormater(long time) {
            Date date = new Date(time);
            return simpleDateFormat.format(date);
        }

        /**
         * 加载发送者头像
         */
        private void loadAraval(String url) {
            //选项
            avaralImg.setVisibility(View.VISIBLE);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .dontAnimate()
                    .dontTransform()
                    .placeholder(R.drawable.im_chat_default)
                    .error(R.drawable.im_chat_default);
            Glide.with(avaralImg.getContext())
                    .load(url)
                    .apply(options)
                    .into(avaralImg);
        }

        /**
         * 加载图片消息内容
         */
        private void loadImage(String url, int w, int h) {
            //选项
            Log.i("DataViewHolder", "loadImage: " + url);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .dontAnimate()
                    .dontTransform()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.im_chat_default)
                    .error(R.drawable.im_chat_default);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(msgImage);
        }


    }


}
