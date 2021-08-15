package com.shen.player.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.green
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.shen.player.R
import com.shen.player.model.LyricBean
import com.shen.player.util.LyricLoader
import com.shen.player.util.LyricUtil
import org.jetbrains.anko.doAsync
import java.time.Duration

/**
 * 作者:shenjianli
 * 创建时间： 2021/8/11 22:07
 * 描述:该类主要作用
 */
class LyricView: View {

    val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }
    val list by lazy {
        ArrayList<LyricBean>()
    }

    var viewW = 0
    var viewH = 0


    constructor(context: Context):super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    var bigSize = 0f
    var smallSize = 0f
    var white = 0
    var green = 0
    var centerLine = 0
    var lineHeight = 0


    var updateByPro = true //指定是否可以根据进度更新歌词

    init {
        bigSize = resources.getDimension(R.dimen.big_size)
        smallSize = resources.getDimension(R.dimen.small_size)
        white = resources.getColor(R.color.black)
        green = resources.getColor(R.color.green)

        lineHeight = resources.getDimensionPixelOffset(R.dimen.line_height)


        paint.textAlign = Paint.Align.CENTER

//        for (i in 0 until 30 ){
//            list.add(LyricBean(2000*i,"正在播放第 $i 行歌词"))
//        }

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(list.size == 0){
            drawSingleLine(canvas)
        }
        else{
            drawMutilLine(canvas)
        }
    }

    var duration = 0
    var progress = 0
    fun setSongDuration(duration: Int){
        this.duration = duration
    }


    var offsetY = 0f
    private fun drawMutilLine(canvas: Canvas?) {



        if(updateByPro){
            var lineTime = 0
            if(centerLine == list.size -1){
                lineTime = duration - list.get(centerLine).startTime
            }
            else{
                val centerSt = list.get(centerLine).startTime
                val nextS = list.get(centerLine+1).startTime
                lineTime = nextS - centerSt
            }

            val offsetTime = progress - list.get(centerLine).startTime

            val offsetPercent = offsetTime/(lineTime.toFloat())

            offsetY = offsetPercent * lineHeight

        }


        val centerText = list[centerLine].content
        val bounds = Rect()
        paint.getTextBounds(centerText, 0, centerText.length, bounds)

        val textW = bounds.width()
        val textH = bounds.height()

        val centerY = viewH / 2 + textH / 2 - offsetY

        for ((index,value) in list.withIndex()){
            if(index == centerLine){
                paint.color = green
                paint.textSize = bigSize
            }
            else{
                paint.color = white
                paint.textSize = smallSize
            }
            val curX = viewW/2
            val curY = centerY + (index - centerLine) * lineHeight
            if(curY < 0){
                continue
            }
            if(curY > (viewH + lineHeight)){
                break
            }
            val curText = value.content
            canvas?.drawText(curText,curX.toFloat(),curY.toFloat(),paint)
        }

    }

    private fun drawSingleLine(canvas: Canvas?) {
        paint.textSize = bigSize
        paint.color = green
        val text = "正在加载歌词..."
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)

        val textW = bounds.width()
        val textH = bounds.height()

        //val x = viewW/2 - textW/2
        val y = viewH / 2 + textH / 2
        //绘制内容
        canvas?.drawText(text, viewW / 2.toFloat(), y.toFloat(), paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewW = w
        viewH = h
    }

    fun updateProgress(progress:Int){
        if(!updateByPro){
            return
        }
        if(list.size == 0){
            return
        }
        this.progress = progress
        //最后一行居中
        if(progress >= list.get(list.size-1).startTime){
            centerLine = list.size -1
        }
        else{
            for (index in 0 until list.size -1){
                val curStartTime = list.get(index).startTime
                val nextStartTime = list.get(index + 1).startTime
                if(progress in curStartTime until nextStartTime){
                    centerLine = index
                    break
                }
            }
        }





        invalidate() //onDraw()

        //postInvalidate()//onDraw()可子线程绘制，
        //requestLayout() //布局参数改变刷新



    }


    fun setSongName(name:String){
        doAsync {
            val file = LyricLoader.loadLyricFile(name)
            val list = LyricUtil.parseLyric(file)
            this@LyricView.list.clear()
            this@LyricView.list.addAll(list)
        }
    }

    var downY = 0f
    var markY = 0f
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when(it.action){
                MotionEvent.ACTION_DOWN -> {
                    //停止更新进度
                    updateByPro = false
                    downY = event.y
                    markY = this.offsetY
                }
                MotionEvent.ACTION_MOVE -> {
                    val endY = event.y
                    val offY = downY - endY
                    this.offsetY = offY + markY

                    if(Math.abs(this.offsetY) >= lineHeight){
                        val offsetLine = (this.offsetY /lineHeight).toInt()
                        centerLine += offsetLine

                        if(centerLine < 0){
                            centerLine = 0
                        }
                        else if(centerLine > list.size -1){
                            centerLine = list.size -1
                        }

                        this.downY = endY
                        this.offsetY = this.offsetY % lineHeight

                        this.markY = this.offsetY

//                        listener?.let {
//                            it(list.get(centerLine).startTime)
//                        }

                        listener?.invoke(list.get(centerLine).startTime)
                    }

                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    updateByPro = true
                }

            }
        }
        return true
    }


    var listener:((progress:Int)->Unit)? = null

    fun setProgressListener(listener:(progress:Int)->Unit){
        this.listener = listener
    }

}