package bello.andrea.viewloadertest;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;

public class ViewContainer implements AnimateContainer {

    View content;

    public ViewContainer(View content) {
        this.content = content;
    }

    @Override
    public void animate(int offset) {
        final ValueAnimator animator = ValueAnimator.ofInt(Color.WHITE, Color.GRAY);
        animator.setDuration(ANIMATION_DURATION);
        animator.setInterpolator(new GaussianInterpolator());
        animator.setStartDelay(offset);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int now = (int) animation.getAnimatedValue();
                //Log.i("a", "a");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    content.setBackground(new ColorDrawable(now));
                } else {
                    content.setBackgroundDrawable(new ColorDrawable(now));
                }
            }
        });
        animator.start();
    }
}
