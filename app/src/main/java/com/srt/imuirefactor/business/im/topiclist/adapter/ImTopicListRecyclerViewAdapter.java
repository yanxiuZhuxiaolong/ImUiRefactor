package com.srt.imuirefactor.business.im.topiclist.adapter;

import android.content.Context;
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

    @NonNull
    @Override
    public ImTopicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.im_topiclist_fragment_recyclerview_item_layout, parent, false);
        return new ImTopicListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImTopicListViewHolder holder, int position) {
        setData(holder, dataList.get(position));
    }


    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    private void setData(ImTopicListViewHolder holder, E data) {
        //获取最新的消息内容
        StringBuilder msgContent = new StringBuilder();
        msgContent.append("senderName:");
        //判断是否是图片 内容
        boolean isImgMsg = TextUtils.equals(data.getType(), "20");
        msgContent.append(isImgMsg ? "[图片]" : data.getLatestMsg().getMsg());
        holder.latestMsgContent.setText(msgContent.toString());
        //判断topic 类型
        boolean isGroupType = TextUtils.equals(data.getType(), "2");
        //设置 topic name
        holder.topicNameTv.setText(data.getGroupName());
        // 设置topic 头像
        if (isGroupType) {
            holder.topicImage.setImageResource(R.mipmap.ic_launcher);
        } else {
            loadTopicAvaral(holder.topicImage, data.getImgUrl(), 0, 0);
        }
        //设置发送时间
        holder.latestMsgTime.setText(timeFormate(data.getLatestMsg().getSendTime()));
    }

    /**
     * 对于私聊topic 加载 member
     */
    private void loadTopicAvaral(ImageView imageView, String imgUrl, int w, int h) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = w;
        params.height = h;
        imageView.setLayoutParams(params);

        //选项
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        //加载
        Glide.with(mContext)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }


    private String timeFormate(long timeMillis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        return dateFormat.format(new Date(timeMillis));
    }


    /**
     * topiclist recyclerview 的viewholder
     * 包含的view
     * ImageView groupAvaral
     * TextView groupName
     * TextView latestMsg sender name + latestMsg content
     * TextView latestMsg Time
     */
    static class ImTopicListViewHolder extends RecyclerView.ViewHolder {
        private TextView topicNameTv;
        private TextView latestMsgContent;
        private TextView latestMsgTime;
        private ImageView topicImage;

        public ImTopicListViewHolder(@NonNull View itemView) {
            super(itemView);
            topicImage = itemView.findViewById(R.id.im_topiclist_recyclerview_topic_avaral_imageview);
            latestMsgContent = itemView.findViewById(R.id.im_topiclist_recyclerview_latestmsg_content_textview);
        }
    }
}
