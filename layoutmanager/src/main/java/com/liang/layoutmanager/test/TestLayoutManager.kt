package com.liang.layoutmanager.test

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.util.SparseArray
import androidx.recyclerview.widget.RecyclerView
import com.liang.layoutmanager.ILayoutManager
import com.liang.layoutmanager.utils.getDecoratedMeasurementHorizontal
import com.liang.layoutmanager.utils.getDecoratedMeasurementVertical
import com.liang.layoutmanager.utils.getHorizontalSpace
import kotlin.math.max

class TestLayoutManager : ILayoutManager {

    private val mItemRects = SparseArray<Rect>()
    private var mVerticalOffset = 0
    private var mFirstVisitPosition = 0
    private var mListVisitPosition = 0

    constructor(context: Context) : this(context, VERTICAL, false)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    ) {
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
//        super.onLayoutChildren(recycler, state)

        if (state?.itemCount == 0) {
            //没有Item可布局，就回收全部临时缓存 (参考自带的LinearLayoutManager)
            //这里的没有Item，是指Adapter里面的数据集，
            //可能临时被清空了，但不确定何时还会继续添加回来
            recycler?.let { removeAndRecycleAllViews(it) }
            return
        }

        if (state?.isPreLayout!!) {
            return
        }

        //暂时分离和回收全部有效的Item
        recycler?.let { detachAndScrapAttachedViews(it) }

        if (getLayoutHelpers().isEmpty()){
            return
        }


        mVerticalOffset = 0
        mFirstVisitPosition = 0
        mListVisitPosition = itemCount

        getLayoutHelpers().forEach {
            it.onItemLayout(recycler!!, state, 0)
        }
        fill(recycler!!, state, 0)

    }

    /**
     * 填充childView的核心方法,应该先填充，再移动。
     * 在填充时，预先计算dy的在内，如果View越界，回收掉。
     * 一般情况是返回dy，如果出现View数量不足，则返回修正后的dy.
     *
     * @param recycler
     * @param state
     * @param dy       RecyclerView给我们的位移量,+,显示底端， -，显示头部
     * @return 修正以后真正的dy（可能剩余空间不够移动那么多了 所以return <|dy|）
     */
    private fun fill(recycler: RecyclerView.Recycler, state: RecyclerView.State, dy: Int): Int {
        var dy = dy

        var topOffset = paddingTop

        //回收越界子View
        if (childCount > 0) {//滑动时进来的
            for (i in childCount - 1 downTo 0) {
                val child = getChildAt(i)

                child.let {
                    if (dy > 0) {//需要回收当前屏幕，上越界的View
                        if (getDecoratedBottom(child!!) - dy < topOffset) {
                            removeAndRecycleView(child, recycler)
                            mFirstVisitPosition++
                            return@let
                        }
                    } else if (dy < 0) {//回收当前屏幕，下越界的View
                        if (getDecoratedTop(child!!) - dy > height - paddingBottom) {
                            removeAndRecycleView(child, recycler)
                            mListVisitPosition--
                            return@let
                        }
                    }
                }

            }
            //detachAndScrapAttachedViews(recycler);
        }

        var leftOffset = paddingLeft
        var lineMaxHeight = 0
        //布局子View阶段
        if (dy >= 0) {
            var minPos = mFirstVisitPosition
            mListVisitPosition = itemCount - 1
            if (childCount > 0) {
                val lastView = getChildAt(childCount - 1)
                minPos = getPosition(lastView!!) + 1//从最后一个View+1开始吧
                topOffset = getDecoratedTop(lastView)
                leftOffset = getDecoratedRight(lastView)
                lineMaxHeight = max(lineMaxHeight, getDecoratedMeasurementVertical(this, lastView))
            }
            //顺序addChildView
            for (i in minPos..mListVisitPosition) {
                //找recycler要一个childItemView,我们不管它是从scrap里取，还是从RecyclerViewPool里取，亦或是onCreateViewHolder里拿。
                val child = recycler.getViewForPosition(i)
                addView(child)
                measureChildWithMargins(child, 0, 0)
                //计算宽度 包括margin
                if (leftOffset + getDecoratedMeasurementHorizontal(
                        this,
                        child
                    ) <= getHorizontalSpace(this)
                ) {//当前行还排列的下
                    layoutDecoratedWithMargins(
                        child,
                        leftOffset,
                        topOffset,
                        leftOffset + getDecoratedMeasurementHorizontal(this, child),
                        topOffset + getDecoratedMeasurementVertical(this, child)
                    )

                    //保存Rect供逆序layout用
                    val rect = Rect(
                        leftOffset,
                        topOffset + mVerticalOffset,
                        leftOffset + getDecoratedMeasurementHorizontal(this, child),
                        topOffset + getDecoratedMeasurementVertical(this, child) + mVerticalOffset
                    )
                    mItemRects.put(i, rect)

                    //改变 left  lineHeight
                    leftOffset += getDecoratedMeasurementHorizontal(this, child)
                    lineMaxHeight =
                        Math.max(lineMaxHeight, getDecoratedMeasurementVertical(this, child))
                } else {//当前行排列不下
                    //改变top  left  lineHeight
                    leftOffset = paddingLeft
                    topOffset += lineMaxHeight
                    lineMaxHeight = 0

                    //新起一行的时候要判断一下边界
                    if (topOffset - dy > height - paddingBottom) {
                        //越界了 就回收
                        removeAndRecycleView(child, recycler)
                        mListVisitPosition = i - 1
                    } else {
                        layoutDecoratedWithMargins(
                            child,
                            leftOffset,
                            topOffset,
                            leftOffset + getDecoratedMeasurementHorizontal(this, child),
                            topOffset + getDecoratedMeasurementVertical(this, child)
                        )

                        //保存Rect供逆序layout用
                        val rect = Rect(
                            leftOffset,
                            topOffset + mVerticalOffset,
                            leftOffset + getDecoratedMeasurementHorizontal(this, child),
                            topOffset + getDecoratedMeasurementVertical(
                                this,
                                child
                            ) + mVerticalOffset
                        )
                        mItemRects.put(i, rect)

                        //改变 left  lineHeight
                        leftOffset += getDecoratedMeasurementHorizontal(this, child)
                        lineMaxHeight =
                            Math.max(lineMaxHeight, getDecoratedMeasurementVertical(this, child))
                    }
                }
            }
            //添加完后，判断是否已经没有更多的ItemView，并且此时屏幕仍有空白，则需要修正dy
            val lastChild = getChildAt(childCount - 1)
            if (getPosition(lastChild!!) === itemCount - 1) {
                val gap = height - paddingBottom - getDecoratedBottom(lastChild!!)
                if (gap > 0) {
                    dy -= gap
                }

            }

        } else {
            /**
             * ##  利用Rect保存子View边界
             * 正序排列时，保存每个子View的Rect，逆序时，直接拿出来layout。
             */
            var maxPos = itemCount - 1
            mFirstVisitPosition = 0
            if (childCount > 0) {
                val firstView = getChildAt(0)
                maxPos = getPosition(firstView!!) - 1
            }
            for (i in maxPos downTo mFirstVisitPosition) {
                val rect = mItemRects.get(i)

                if (rect.bottom - mVerticalOffset - dy < paddingTop) {
                    mFirstVisitPosition = i + 1
                    break
                } else {
                    val child = recycler.getViewForPosition(i)
                    addView(child, 0)//将View添加至RecyclerView中，childIndex为1，但是View的位置还是由layout的位置决定
                    measureChildWithMargins(child, 0, 0)
                    layoutDecoratedWithMargins(
                        child,
                        rect.left,
                        rect.top - mVerticalOffset,
                        rect.right,
                        rect.bottom - mVerticalOffset
                    )
                }
            }
        }


        Log.d(
            "TAG",
            "count= [" + childCount + "]" + ",[recycler.getScrapList().size():" + recycler.scrapList.size + ", dy:" + dy + ",  mVerticalOffset" + mVerticalOffset + ", "
        )

        return dy
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        //位移0、没有子View 当然不移动
        if (dy == 0 || childCount === 0) {
            return 0
        }

        var realOffset = dy//实际滑动的距离， 可能会在边界处被修复
        //边界修复代码
        if (mVerticalOffset + realOffset < 0) {//上边界
            realOffset = -mVerticalOffset
        } else if (realOffset > 0) {//下边界
            //利用最后一个子View比较修正
            val lastChild = getChildAt(childCount - 1)
            if (getPosition(lastChild!!) === itemCount - 1) {
                val gap = height - paddingBottom - getDecoratedBottom(lastChild!!)
                if (gap > 0) {
                    realOffset = -gap
                } else if (gap == 0) {
                    realOffset = 0
                } else {
                    realOffset = Math.min(realOffset, -gap)
                }
            }
        }

        realOffset = fill(recycler, state, realOffset)//先填充，再位移。

        mVerticalOffset += realOffset//累加实际滑动距离

        offsetChildrenVertical(-realOffset)//滑动

        return realOffset
    }


}