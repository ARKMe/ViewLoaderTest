package bello.andrea.viewloadertest;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.view.View;

public abstract class AnimateContainer {

    private Animator animator;

    long ANIMATION_DURATION = 4000;

    protected abstract ValueAnimator.AnimatorUpdateListener getUpdateListener();

    public final void animate(int offset){
        final ValueAnimator animator = ValueAnimator.ofInt(Color.WHITE, Color.GRAY);
        animator.setDuration(ANIMATION_DURATION);
        animator.setInterpolator(new GaussianInterpolator());
        animator.setStartDelay(offset);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(getUpdateListener());
        animator.start();
        this.animator = animator;
    }

    public final void stopAnimation(){
        if(animator != null && animator.isRunning()){
            animator.cancel();
        }
    }

    protected final void changeBackgroudColor(View view, int color){
        Drawable background = view.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable)background).getPaint().setColor(color);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable)background).setColor(color);
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable)background).setColor(color);
        }
    }

}
