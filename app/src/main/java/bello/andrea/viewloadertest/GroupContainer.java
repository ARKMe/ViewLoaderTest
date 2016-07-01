package bello.andrea.viewloadertest;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;

public class GroupContainer implements AnimateContainer {

    View[] content;

    public GroupContainer(View ... content) {
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
                    for(View view : content)
                        view.setBackground(new ColorDrawable(now));
                } else {
                    for(View view : content)
                        view.setBackgroundDrawable(new ColorDrawable(now));
                }
            }
        });
        animator.start();
    }
}
