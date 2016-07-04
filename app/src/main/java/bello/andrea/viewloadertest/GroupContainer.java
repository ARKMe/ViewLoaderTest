package bello.andrea.viewloadertest;

import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.view.View;

public class GroupContainer extends AnimateContainer {

    View[] content;

    public GroupContainer(View ... content) {
        this.content = content;
    }

    @Override
    protected ValueAnimator.AnimatorUpdateListener getUpdateListener() {
        return new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int now = (int) animation.getAnimatedValue();
                //Log.i("a", "a");
                for(View view : content){
                    Drawable background = view.getBackground();
                    if (background instanceof ShapeDrawable) {
                        ((ShapeDrawable)background).getPaint().setColor(now);
                    } else if (background instanceof GradientDrawable) {
                        ((GradientDrawable)background).setColor(now);
                    } else if (background instanceof ColorDrawable) {
                        ((ColorDrawable)background).setColor(now);
                    }
                }
            }
        };
    }
}
