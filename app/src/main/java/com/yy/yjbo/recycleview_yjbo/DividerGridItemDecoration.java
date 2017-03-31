package com.yy.yjbo.recycleview_yjbo;

/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * limitations under the License.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;


/**
 * This class is from the v7 samples of the Android SDK. It's not by me!
 * <p/>
 * See the license above for details.
 * <p>
 * //设置分割线
 * locationList.addItemDecoration((new DividerItemDecoration(this,
 * DividerItemDecoration.VERTICAL_LIST)));
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;
    private int headCount = 0;//头部布局
    private int footCount = 0;//尾部布局
//    private boolean isDraw = false;//是否完成绘画

    public DividerGridItemDecoration(Context context, int mheadCount, int mfootCount) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        this.headCount = mheadCount;
        this.footCount = mfootCount;
        LogUtils.d("=-初始化==" + headCount + "=-==" + footCount);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {
//        if (!isDraw) {
//            isDraw = true;
            drawHorizontal(c, parent);
            drawVertical(c, parent);
//        }

    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    /**
     * 顶部的和尾部的得去除（setAdapter时得传过来），其余的就自己处理得注意第一行将显示顶部的最上一横线显示出来，或者显示最下一行横线
     *
     * @author yjbo  @time 2017/3/30 17:28
     */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();

        int spanCount = getSpanCount(parent);
        int childCount2 = parent.getAdapter().getItemCount();
        LogUtils.d("00-childCount-h=" + childCount + "==spanCount=" + spanCount + "==childCount2==" + childCount2
                + "=-==" + headCount + "=-==" + footCount);
        for (int i = 0; i < childCount; i++) {
//            if (i == 0 || i == childCount-1) continue;
//            if (i != 3) continue;

            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();


//            final int left = child.getLeft() - params.leftMargin;
//            final int right = child.getRight() + params.rightMargin
//                    + mDivider.getIntrinsicWidth();
//            final int top = child.getBottom() + params.bottomMargin;
//            final int bottom = top + mDivider.getIntrinsicHeight();
            if (headCount != 0 && i == 0) {//绘制最顶部的一行，只执行一次，
                int headHight = 0;
                for (int j = 0; j < headCount; j++) {//这是在头部高度相等时可以（*headCount），否则则for循环相加
                    headHight += parent.getChildAt(j).getBottom();
                }
                final int left0 = parent.getChildAt(0).getLeft() - params.leftMargin;
                final int right0 = parent.getChildAt(0).getRight() + params.rightMargin
                        + mDivider.getIntrinsicWidth();
                final int top0 = params.bottomMargin + headHight;
                final int bottom0 = top0 + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left0, top0, right0, bottom0);
                LogUtils.d("==顶部==" + params.bottomMargin + "====" + child.getBottom() + "----" + mDivider.getIntrinsicHeight() + "==" + left0
                        + "==" + top0 + "==" + right0 + "==" + bottom0 + "==headCount==" + headCount);
                mDivider.draw(c);
            }
//            if (i == 1 || i == 2){
//                mDivider.setBounds(0, 100, right, 120);
//                mDivider.draw(c);
//                LogUtils.d("==上下左右的边距=00="+left+"----"+0+"----"+right+"----"+mDivider.getIntrinsicHeight());
//            }
//            mDivider.setBounds(left, top-16, right, bottom-16);
            if (headCount > i) {//有头尾布局
//                mDivider.setBounds(left, top, right, bottom);
//                LogUtils.d("==有头尾布局=="+left+"----"+top+"----"+right+"----"+bottom);
//                mDivider.draw(c);
            } else {
                if (footCount != 0 && i >= childCount - footCount) {//此时不绘制尾部
                } else {
                    final int left = child.getLeft() - params.leftMargin;
                    final int right = child.getRight() + params.rightMargin
                            - mDivider.getIntrinsicWidth();
                    final int top = child.getBottom() + params.bottomMargin - mDivider.getIntrinsicHeight();
                    final int bottom = top + mDivider.getIntrinsicHeight();

                    mDivider.setBounds(left, top, right, bottom);
                    LogUtils.d("==上下左右的边距==" + left + "----" + top + "----" + right + "----" + bottom);
                    mDivider.draw(c);
                }
            }
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        int spanCount = getSpanCount(parent);
        int childCount2 = parent.getAdapter().getItemCount();
        //11-childCount=11==spanCount=3==childCount2==18 说明childCount2是最完整的，而childCount是不完整的；
        LogUtils.d("11-childCount=" + childCount + "==spanCount=" + spanCount + "==childCount2==" + childCount2);

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();

            if (headCount > i) {//有头文件时不绘制

            } else {
                if (footCount != 0 && i >= childCount - footCount) {//此时不绘制尾部
                } else {
                    if (i == headCount) {//绘制最左侧，一个横条，只绘制一次
                        LogUtils.d("==for循环参数===" + childCount + "===" + headCount + "--" + footCount + "==" + childCount2);
                        for (int j = 0; j <= childCount2 - footCount - headCount; j++) {
                            //这是每行的第一个得绘制
                            if ((j) % spanCount == 0) {//说明是列表第一个（除去头）
                                View child2 = parent.getChildAt(j);
                                if (child2 == null) {
                                    LogUtils.d("==怎么会空呢==" + j);
                                    break;
                                }
                                RecyclerView.LayoutParams params2 = (RecyclerView.LayoutParams) child2
                                        .getLayoutParams();
                                final int top0 = child2.getTop() - params2.topMargin - mDivider.getIntrinsicWidth();
                                final int bottom0 = child2.getBottom() + params2.bottomMargin;
                                final int left0 = 0;//child2.getLeft() - params2.leftMargin
                                final int right0 = left0 + mDivider.getIntrinsicWidth();

                                LogUtils.d("==for循环参数===垂直时绘制图形==每行最左侧==" + j + "--" + headCount + "-|-" + spanCount
                                        + "===" + left0 + "===" + top0 + "===" + right0 + "===" + bottom0
                                        + "===" + child2.getLeft() + "====" + params2.leftMargin);
                                mDivider.setBounds(left0, top0, right0, bottom0);
                                mDivider.draw(c);
                            }
                        }
                    }
                    if (i == headCount) {
                        for (int x = 0; x <= childCount2 - footCount - headCount; x++) {
                            View child3 = parent.getChildAt(x);
                            if (child3 == null) {
                                LogUtils.d("==怎么会空呢==" + x);
                                break;
                            }
                            RecyclerView.LayoutParams params3 = (RecyclerView.LayoutParams) child3
                                    .getLayoutParams();
                            final int top = child3.getTop() - params3.topMargin - mDivider.getIntrinsicWidth();
                            final int bottom = child3.getBottom() + params3.bottomMargin;
                            final int left = child3.getRight() + params3.rightMargin - mDivider.getIntrinsicWidth();
                            final int right = left + mDivider.getIntrinsicWidth();

                            LogUtils.d("垂直时绘制图形====" + i + "===" + left + "===" + top + "===" + right + "===" + bottom);
                            mDivider.setBounds(left, top, right, bottom);
                            mDivider.draw(c);
                        }
                    }
                }
            }
        }
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount) {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        LogUtils.d("==getItemOffsets==");
//        LogUtils.d("spanCount="+spanCount+"===childCount="+childCount+"==itemPosition="+itemPosition);
//        if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
//        {
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
//        } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
//        {
//            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
//        } else
//        {
//            if (itemPosition == 0 || childCount == 9|| childCount == 8|| childCount == 7) return;
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(),
//                    mDivider.getIntrinsicHeight());
//        }
    }
}