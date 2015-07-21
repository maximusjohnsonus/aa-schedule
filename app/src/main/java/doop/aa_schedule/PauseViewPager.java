package doop.aa_schedule;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class PauseViewPager extends ViewPager {
    boolean pagingAllowed = true;

    public PauseViewPager(Context context) {
        super(context);
    }

    public PauseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return this.pagingAllowed && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        return this.pagingAllowed && super.onInterceptTouchEvent(event);
    }

    public void setPagingAllowed(boolean pagingAllowed){
        this.pagingAllowed = pagingAllowed;
    }
}
