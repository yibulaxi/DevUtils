package dev.widget.decoration.linear;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import dev.widget.decoration.BaseLinearItemDecoration;

/**
 * detail: RecyclerView Linear 分割线处理 ( 每一条数据 )
 * @author Ttt
 * <pre>
 *     效果:
 *     每一条数据底部添加一条分割线, 最后一条数据不绘制 ( 绘制 ItemCount - 1 条分割线 )
 *     <p></p>
 *     也可以使用内置 {@link DividerItemDecoration}
 *     自定义分割线使用方法
 *     DividerItemDecoration decoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
 *     decoration.setDrawable(Drawable)
 *     recyclerView.addItemDecoration(decoration)
 * </pre>
 */
public class LineItemDecoration
        extends BaseLinearItemDecoration {

    public LineItemDecoration(
            final boolean vertical,
            final float lineHeight
    ) {
        super(vertical, lineHeight);
    }

    public LineItemDecoration(
            final boolean vertical,
            final float lineHeight,
            @ColorInt final int lineColor
    ) {
        super(vertical, lineHeight, lineColor);
    }

    // ==========
    // = 处理方法 =
    // ==========

    @Override
    public void getItemOffsets(
            @NonNull Rect outRect,
            @NonNull View view,
            @NonNull RecyclerView parent,
            @NonNull RecyclerView.State state
    ) {
        if (isVertical()) {
            verticalItemOffsets(outRect, view, parent, state);
        } else if (isHorizontal()) {
            horizontalItemOffsets(outRect, view, parent, state);
        }
    }

    @Override
    public void onDraw(
            @NonNull Canvas canvas,
            @NonNull RecyclerView parent,
            @NonNull RecyclerView.State state
    ) {
        if (isVertical()) {
            verticalDraw(canvas, parent, state);
        } else if (isHorizontal()) {
            horizontalDraw(canvas, parent, state);
        }
    }

    // ============
    // = vertical =
    // ============

    private void verticalItemOffsets(
            Rect outRect,
            View view,
            RecyclerView parent,
            RecyclerView.State state
    ) {
        int itemCount = state.getItemCount();
        if (itemCount <= 1) return;

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(0, (int) mLineHeight, 0, 0);
        }
    }

    private void verticalDraw(
            Canvas canvas,
            RecyclerView parent,
            RecyclerView.State state
    ) {
        int itemCount = state.getItemCount();
        if (itemCount <= 1) return;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(child) != 0) {
                canvas.drawRect(
                        child.getLeft() + mLineLeft,
                        child.getTop() - mLineHeight - mLineOffset,
                        child.getRight() - mLineRight,
                        child.getTop() - mLineOffset,
                        mLinePaint
                );
            }
        }
    }

    // ==============
    // = horizontal =
    // ==============

    private void horizontalItemOffsets(
            Rect outRect,
            View view,
            RecyclerView parent,
            RecyclerView.State state
    ) {
        int itemCount = state.getItemCount();
        if (itemCount <= 1) return;

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set((int) mLineHeight, 0, 0, 0);
        }
    }

    private void horizontalDraw(
            Canvas canvas,
            RecyclerView parent,
            RecyclerView.State state
    ) {
        int itemCount = state.getItemCount();
        if (itemCount <= 1) return;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(child) != 0) {
                canvas.drawRect(
                        child.getLeft() - mLineHeight - mLineOffset,
                        child.getTop() + mLineLeft,
                        child.getLeft() - mLineOffset,
                        child.getBottom() - mLineRight,
                        mLinePaint
                );
            }
        }
    }
}