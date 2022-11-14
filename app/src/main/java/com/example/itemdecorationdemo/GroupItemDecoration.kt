package com.example.itemdecorationdemo

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.graphics.Bitmap

import android.view.LayoutInflater






class GroupItemDecoration(context: Context): RecyclerView.ItemDecoration() {

    val paint = Paint()
    val bottomPaint = Paint()
    val paint1 = Paint()
    var icon: Bitmap
    var icon2: Bitmap

    init {
        icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.chn)
        icon2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.hot)
        paint.isAntiAlias = true
        paint.setColor(Color.GRAY)
        bottomPaint.color = Color.BLUE
        paint1.textSize = dp2Px(12).toFloat()
        paint1.color = Color.WHITE
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position: Int = parent.getChildAdapterPosition(view)
        if (position % 5 == 0) {
            outRect.set(0, dp2Px(20), 0, 0)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        /*val count = parent.childCount
        for (i in 0 until count) {
            val view = parent.getChildAt(i);
            val position: Int = parent.getChildAdapterPosition(view)
            if (position % 5 == 0) {
                val dividerTop = view.getTop() - dp2Px(20)
                val dividerLeft = parent.getPaddingLeft()
                val dividerBottom = view.getTop()
                val dividerRight = parent.getWidth() - parent.getPaddingRight()
                c.drawRect(dividerLeft.toFloat(), dividerTop.toFloat(), dividerRight.toFloat(), dividerBottom.toFloat(), paint)
                c.drawBitmap(icon, 20f, dividerTop + dp2Px(5).toFloat(), paint)
                val text = if (position / 5 == 0) "水果" else "蔬菜"
                c.drawText(text, parent.getPaddingLeft() + 30f +icon.width, (dividerTop + dp2Px(13)).toFloat(), paint1)
            }
        }*/

    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val childCount: Int = parent.childCount
        val groupHeight = dp2Px(40)
        // 获取最后一个View
        val relativeLastPosition = childCount - 1
        val childView: View = parent.getChildAt(relativeLastPosition)
        // 如果不足一屏，就不用绘制
        if (childView.bottom < parent.height) {
            return
        }

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        c.drawRect(left.toFloat(), (parent.height - groupHeight).toFloat(), right.toFloat(), parent.getHeight().toFloat(), bottomPaint)
        c.drawText("我是底部", left.toFloat(), (parent.height - groupHeight / 2).toFloat() , paint1)

    }

    fun dp2Px(dp: Int): Int{
        val scale: Float = Resources.getSystem().displayMetrics.scaledDensity
        return (dp * scale).toInt()
    }

}