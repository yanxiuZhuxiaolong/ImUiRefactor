package com.srt.imuirefactor.business.im.msglist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.srt.imuirefactor.R;
import com.srt.imuirefactor.business.im.interfaces.RecyclerViewItemOnClickListener;
import com.srt.imuirefactor.business.im.mock.MockMsgDataBean;

import java.util.ArrayList;

/**
 * Created by 朱晓龙 on 2018/5/2 18:32.
 * 负责 添加或删除 headerview、fotterView
 * 目前只实现单个 header footer的添加
 */

public class DecorateAdapter extends RecyclerView.Adapter<AbstractViewHolder> {

    private  final String TAG = getClass().getSimpleName();
    private DataAdapter<MockMsgDataBean> innerAdapter;

    private final int ITEM_TYPE_HEADER = 0X01;
    private final int ITEM_TYPE_FOOTER = 0X02;
    private final int ITEM_TYPE_DATA = 0X03;

    private View headerView;
    private View footerView;

    private Context context;

    public DecorateAdapter(Context context, ArrayList<MockMsgDataBean> datalist) {
        this.context = context;
        innerAdapter = new DataAdapter<MockMsgDataBean>(context, datalist);
    }


    private boolean hasFooterView=true;
    /**
     * 每次 拉取到新数据 并 加入到数据集更新UI 后 添加
     * */
    public void addFooterView(){
        hasFooterView=true;
        notifyItemInserted(getItemCount());
    }
    /**
     * 每次拉取到数据后 删除foot
     * */
    public void removeFooterView(){
        Log.i(TAG, "removeFooterView: ");
        hasFooterView=false;
        notifyItemRemoved(getItemCount());
    }


    @Override
    public int getItemViewType(int position) {
        Log.i(TAG, "getItemViewType: datasize  "+innerAdapter.getItemCount());
        if (position == innerAdapter.getItemCount()) {
            return ITEM_TYPE_FOOTER;
        }
        return innerAdapter.getItemViewType(position);
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        if (viewType == ITEM_TYPE_FOOTER) {
            Log.i("DecorateAdapter", "onCreateViewHolder: footer");
            footerView = LayoutInflater.from(context).inflate(R.layout.msg_list_header_view, parent, false);
            return new HeadFooterViewHolder(footerView);
        }
        return innerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolder holder, int position) {
        //判断是否是 data 数据
        int type = getItemViewType(position);
        if (type == ITEM_TYPE_DATA) {
            innerAdapter.onBindViewHolder((DataAdapter.DataViewHolder) holder, position);
            if (itemClickListener != null) {
                ((DataAdapter.DataViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClicked(((DataAdapter.DataViewHolder) holder).itemView,position);
                    }
                });
            }
        } else if (type == ITEM_TYPE_FOOTER || type == ITEM_TYPE_HEADER) {
            Log.i("DecorateAdapter", "onBindViewHolder: footer");
            if (loadMoreListener != null&&hasFooterView) {
                loadMoreListener.onFooterViewVisiable();
            }
        }
    }



    @Override
    public int getItemCount() {
        //判断footview是否存在
        int size= innerAdapter.getItemCount()+(hasFooterView?1:0);
        Log.i(TAG, "getItemCount: "+size);
        return size;
    }

    public void setDataList(ArrayList<MockMsgDataBean> dataList) {
        innerAdapter.setDataList(dataList);
    }

    public static class HeadFooterViewHolder extends AbstractViewHolder {

        public HeadFooterViewHolder(View itemView) {
            super(itemView);
        }
    }


    private RecyclerViewItemOnClickListener itemClickListener;

    public void setItemClickListener(RecyclerViewItemOnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private LoadMoreListener loadMoreListener;

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public interface LoadMoreListener{
        void onFooterViewVisiable();
    }

}
