package com.yy.yjbo.recycleview_yjbo.adapter;

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

import com.yy.yjbo.recycleview_yjbo.util.LogUtils;
import com.yy.yjbo.recycleview_yjbo.R;

import java.util.HashMap;
import java.util.List;

/**
 * 首页的adapter
 * @author yjbo
 * @time 2017/3/31 10:04
 * @mail 1457521527@qq.com
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_FOOTER = 2;
    private List<HashMap<String, Object>> mlistHash;
    private Context mContext;

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


    public void bindData(List<HashMap<String, Object>> listHash, Context context) {
        this.mlistHash = listHash;
        this.mContext = context;

        notifyDataSetChanged();
    }


    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    @Override
    public int getItemViewType(int position) {
//        if (position == 0) return TYPE_HEADER;
//        if (position == mlistHash.size()+1) return TYPE_FOOTER;//position == mlistHash.size()+1;相当于加了头尾俩个了
        return TYPE_NORMAL;
    }

    @Override
    public huiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hui_layout, parent, false);
        return new huiHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (getItemViewType(position) == TYPE_HEADER) return;
//        if (getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(holder);
        String imageInt = (String) mlistHash.get(pos).get("imageInt");
        final String wordStr = (String) mlistHash.get(pos).get("wordStr");
        final String compoUrl = (String) mlistHash.get(pos).get("compoUrl");
        final String compoCode = (String) mlistHash.get(pos).get("compoCode");


        ((huiHolder) holder).itemTxt.setText(""+wordStr);
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
        return position;
//        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        int totalCount = 0;
        return  totalCount+mlistHash.size();
    }

    public class huiHolder extends RecyclerView.ViewHolder {
        ImageView navigItemIbt;
        TextView itemTxt;
        LinearLayout item_layout;
        public huiHolder(View itemView) {
            super(itemView);
            itemTxt = (TextView) itemView.findViewById(R.id.navig_item_txt);
            item_layout = (LinearLayout) itemView.findViewById(R.id.item_layout);
            navigItemIbt = (ImageView) itemView.findViewById(R.id.navig_item_ibt);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, String url, String compoCode, String wordStr);
    }
}
