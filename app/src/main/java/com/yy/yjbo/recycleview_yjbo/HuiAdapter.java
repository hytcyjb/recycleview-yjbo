package com.yy.yjbo.recycleview_yjbo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjbo
 * @descr 汇的页面，动态显示内部的按钮
 * @time 2016/9/9 14:16
 */
public class HuiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_FOOTER = 2;
    private List<HashMap<String, Object>> mlistHash;
    private Context mContext;
    private View mHeaderView;
    private View mfooterView;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TYPE_HEADER){
                        return gridManager.getSpanCount();
                    }else  if (getItemViewType(position) == TYPE_FOOTER){
                        return gridManager.getSpanCount();
                    }else {
                        return 1;
                    }
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView)
//    {
//        innerAdapter.onAttachedToRecyclerView(recyclerView);
//
//        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//        if (layoutManager instanceof GridLayoutManager)
//        {
//            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
//            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
//
//            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
//            {
//                @Override
//                public int getSpanSize(int position)
//                {
//                    int viewType = getItemViewType(position);
//                    if (mHeaderViews.get(viewType) != null)
//                    {
//                        return layoutManager.getSpanCount();
//                    } else if (mFootViews.get(viewType) != null)
//                    {
//                        return layoutManager.getSpanCount();
//                    }
//                    if (oldLookup != null)
//                        return oldLookup.getSpanSize(position);
//                    return 1;
//                }
//            });
//            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
//        }
//    }

    public void bindData(List<HashMap<String, Object>> listHash, Context context) {
        this.mlistHash = listHash;
        this.mContext = context;

        LogUtils.i("Jerry");
        notifyDataSetChanged();
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
//    public void setSencodHeaderView(View headerView) {
//        mHeaderView = headerView;
//        notifyItemInserted(0);
//    }

    public void updateHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemChanged(0);
    }
    /**
     * 添加一个底部布局，为了留白
     * @author yjbo  @time 2016/11/28 15:19
     */
    public void addfoot(View footerView) {
        mfooterView = footerView;
        notifyItemChanged(mlistHash.size());
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (mfooterView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        if (position == mlistHash.size()+1) return TYPE_FOOTER;//position == mlistHash.size()+1;相当于加了头尾俩个了
        return TYPE_NORMAL;
    }

    @Override
    public huiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new huiHolder(mHeaderView);
        if (mfooterView != null && viewType == TYPE_FOOTER) return new huiHolder(mfooterView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hui_layout, parent, false);
        return new huiHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(holder);
        String imageInt = (String) mlistHash.get(pos).get("imageInt");
        final String wordStr = (String) mlistHash.get(pos).get("wordStr");
        final String compoUrl = (String) mlistHash.get(pos).get("compoUrl");
        final String compoCode = (String) mlistHash.get(pos).get("compoCode");



        if (mListener == null) return;
        ((huiHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mListener.onItemClick(pos, compoUrl, compoCode, wordStr);
            }
        });

    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
//        if(mHeaderView != null){
//            position--;
//        }
//        if (mfooterView != null){
//            position--;
//        }
        return position -1;
//        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        int totalCount = 0;
        if(mHeaderView != null){
            totalCount++;
        }
        if (mfooterView != null){
            totalCount++;
        }
        return  totalCount+mlistHash.size();
//        return mHeaderView == null ? mlistHash.size() : mlistHash.size() + 1;
    }

    public class huiHolder extends RecyclerView.ViewHolder {
        ImageView navigItemIbt;
        TextView itemTxt;
        //        TextView buttom_white;
        LinearLayout item_layout;
        public huiHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) return;
            if (itemView == mfooterView) return;
            itemTxt = (TextView) itemView.findViewById(R.id.navig_item_txt);
//            buttom_white = (TextView) itemView.findViewById(R.id.buttom_white);
            item_layout = (LinearLayout) itemView.findViewById(R.id.item_layout);
            navigItemIbt = (ImageView) itemView.findViewById(R.id.navig_item_ibt);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, String url, String compoCode, String wordStr);
    }
}
