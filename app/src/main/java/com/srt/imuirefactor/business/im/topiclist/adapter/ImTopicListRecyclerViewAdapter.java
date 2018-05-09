package com.srt.imuirefactor.business.im.topiclist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.srt.imuirefactor.R;
import com.srt.imuirefactor.business.im.interfaces.RecyclerViewItemOnClickListener;
import com.srt.imuirefactor.business.im.mock.MockTopicDataBean;
import com.srt.imuirefactor.customize.view.CircleView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 朱晓龙 on 2018/5/7 16:33.
 */

public final class ImTopicListRecyclerViewAdapter<E extends MockTopicDataBean> extends RecyclerView.Adapter<ImTopicListRecyclerViewAdapter.ImTopicListViewHolder> {

    /**
     * 数据集合
     */
    private List<E> dataList;

    private Context mContext;

    /**
     * recyclerview 点击监听
     */
    private RecyclerViewItemOnClickListener mRecyclerViewItemOnClickListener;

    public void setmRecyclerViewItemOnClickListener(RecyclerViewItemOnClickListener mRecyclerViewItemOnClickListener) {
        this.mRecyclerViewItemOnClickListener = mRecyclerViewItemOnClickListener;
    }

    public ImTopicListRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ImTopicListRecyclerViewAdapter(List<E> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    public void setDataList(List<E> dataList) {
        this.dataList = dataList;
    }

    public List<E> getDataList() {
        return dataList;
    }

    @NonNull
    @Override
    public ImTopicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.im_topiclist_fragment_recyclerview_item_layout, parent, false);
        return new ImTopicListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ImTopicListViewHolder holder, int position) {
        holder.setData(dataList.get(position));
        if (mRecyclerViewItemOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerViewItemOnClickListener.onItemClicked(holder.itemView,position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }






    /**
     * topiclist recyclerview 的viewholder
     * 包含的view
     * ImageView groupAvaral
     * TextView groupName
     * TextView latestMsg sender name + latestMsg content
     * TextView latestMsg Time
     */
    static class ImTopicListViewHolder<E extends MockTopicDataBean> extends RecyclerView.ViewHolder {
        private TextView topicNameTv;
        private TextView latestMsgContent;
        private TextView latestMsgTime;
        private ImageView topicImage;
        private CircleView redDot;



        public ImTopicListViewHolder(@NonNull View itemView) {
            super(itemView);
            // TODO: 2018/5/8  findViewById
            topicImage=itemView.findViewById(R.id.avatar_imageview);
            latestMsgTime=itemView.findViewById(R.id.time_textView);
            latestMsgContent=itemView.findViewById(R.id.msg_textview);
            topicNameTv=itemView.findViewById(R.id.sender_textview);
            redDot=itemView.findViewById(R.id.reddot_circleview);
        }
        private void setData( E data) {
            //判断是否是图片 内容
            boolean isImgMsg = TextUtils.equals(data.getType(), "20");
            //判断topic 类型
            boolean isGroupType = TextUtils.equals(data.getType(), "2");

            //获取最新的消息内容
            StringBuilder msgContent = new StringBuilder();
            msgContent.append("senderName:");
            msgContent.append(isImgMsg ? "[图片]" : data.getLatestMsg().getMsg());
            latestMsgContent.setText(msgContent.toString());
            //设置 topic name

            // 设置topic 头像
            if (isGroupType) {
                topicNameTv.setText("班级群聊"+data.getTopicId());
                topicImage.setImageResource(R.drawable.icon_chat_class);
            } else {
                topicNameTv.setText("用户私聊"+data.getTopicId());
                loadTopicAvaral(topicImage, data.getImgUrl());
            }
            //设置发送时间
            latestMsgTime.setText(timeFormate(data.getLatestMsg().getSendTime()));
            //设置红点显示
            redDot.setVisibility(data.isShowDot()?View.VISIBLE:View.INVISIBLE);
        }

        /**
         * 对于私聊topic 加载 member头像
         */
        private void loadTopicAvaral(ImageView imageView, String imgUrl) {
            //选项
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .dontAnimate()
                    .dontTransform()
                    .placeholder(R.drawable.im_chat_default)
                    .error(R.drawable.im_chat_default);
            //加载
            Glide.with(imageView.getContext())
                    .load(imgUrl)
                    .apply(options)
                    .into(imageView);
        }


        private String timeFormate(long timeMillis) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            return dateFormat.format(new Date(timeMillis));
        }
    }
}
