package dev.hq.circularprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Custom View : CircularProgressBar.
 *
 * https://github.com/HQDev/CircularProgressBar
 *
 * @author HQDev
 * @version 1.0 (17.11.2014)
 * @since 14.11.2014
 */
public class CircularProgressBar extends View {
	
	private int baseColor;
	private int foregroundColor;
	private float circleWidth;
	private int textColor;
	private float textSize;
	private int progress;
	private int completed;
	private boolean textVisibility;
	
	private Paint paint;
	private RectF oval;
	
	public CircularProgressBar(Context context) {
		this(context,null);
	}
	
	public CircularProgressBar(Context context,AttributeSet attrs) {
		this(context,attrs,0);
	}	
	
	public CircularProgressBar(Context context,AttributeSet attrs,int defStyle) {
		super(context,attrs,defStyle);
		
		paint = new Paint();
		oval = new RectF();
		
	    TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CircularProgressBar,defStyle,0);
	    
	    if (typedArray != null) {
	    	setBaseColor(typedArray.getColor(R.styleable.CircularProgressBar_baseColor,0xffe8e8e7));
			setForegroundColor(typedArray.getColor(R.styleable.CircularProgressBar_foregroundColor,Color.GREEN));
			setCircleWidth(typedArray.getDimension(R.styleable.CircularProgressBar_circleWidth,60f));
			setTextColor(typedArray.getColor(R.styleable.CircularProgressBar_textColor,0xffff8201));
			setTextSize(typedArray.getDimension(R.styleable.CircularProgressBar_textSize,60f));
			setProgress(typedArray.getInteger(R.styleable.CircularProgressBar_progress,0));
			setCompleted(typedArray.getInteger(R.styleable.CircularProgressBar_completed,10000));
			setTextVisibility(typedArray.getBoolean(R.styleable.CircularProgressBar_textVisibility,true));
	    }
	    
	    typedArray.recycle();

	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int center = getWidth()/2;
		int radius = (int)(center - circleWidth/2);		paint.setColor(baseColor);
		paint.setStyle(Paint.Style.STROKE); 		paint.setStrokeWidth(circleWidth);		paint.setAntiAlias(true); 		oval.set(center - radius,center - radius,center + radius,center + radius);
		
		canvas.drawArc(oval,270,360,false,paint);
				
		paint.setStrokeWidth(circleWidth);
		paint.setColor(foregroundColor); 
		
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawArc(oval,270,360 * progress / completed,false,paint); 

		paint.setStrokeWidth(0);
		paint.setColor(textColor);
		paint.setTextSize(textSize);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
				
		if (textVisibility) {
			float textWidth = paint.measureText(progress + "²½");   
			canvas.drawText(progress + "²½",center - textWidth / 2,center - textSize/2,paint);
			
			textWidth = paint.measureText(progress/20 + "´ó¿¨");
			canvas.drawText(progress/20 + "Ç§¿¨",center - textWidth / 2,center + textSize*3/2,paint);
		}
		
		
	}
	
	public int getBaseColor() {
		return this.baseColor;
	}
	
	public void setBaseColor(int baseColor) {
		this.baseColor = baseColor;
		invalidate();
	}
	
	public int getForegroundColor() {
		return this.foregroundColor;
	}
	
	public void setForegroundColor(int foregroundColor) {
		this.foregroundColor = foregroundColor;
		invalidate();
	}
	
	public float getCircleWidth() {
		return this.circleWidth;
	}
	
	public void setCircleWidth(float circleWidth) {
		this.circleWidth = circleWidth;
		invalidate();
	}
	
	public int getTextColor() {
		return this.textColor;
	}
	
	public void setTextColor(int textColor) {
		this.textColor = textColor;
		invalidate();
	}
	
	public float getTextSize() {
		return this.textSize;
	}
	
	public void setTextSize(float textSize) {
		this.textSize = textSize;
		invalidate();
	}
	
	public int getCompleted() {
		return this.completed;
	}
	
	public synchronized void setCompleted(int completed) {
		this.completed = completed;
		invalidate();
	}
	
	public boolean getTextVisibility() {
		return this.textVisibility;
	}
	
	public void setTextVisibility(boolean textVisibility) {
		this.textVisibility = textVisibility;
		invalidate();
	}
	
	public int getProgress() {
		return this.progress;
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
		invalidate();
	}

}