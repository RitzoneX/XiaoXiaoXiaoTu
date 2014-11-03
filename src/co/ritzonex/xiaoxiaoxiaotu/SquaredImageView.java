package co.ritzonex.xiaoxiaoxiaotu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/** An image view which always remains square with respect to its width. */
public class SquaredImageView extends ImageView {
	public SquaredImageView(Context context) {
		super(context);
	}

	public SquaredImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	  Drawable d = getDrawable();

      if(d!=null){
              // ceil not round - avoid thin vertical gaps along the left/right edges
              int width = MeasureSpec.getSize(widthMeasureSpec);
              int height =  width *  d.getIntrinsicHeight() /  d.getIntrinsicWidth();
              setMeasuredDimension(width, height);
      }else{
              super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      }
  }
}
