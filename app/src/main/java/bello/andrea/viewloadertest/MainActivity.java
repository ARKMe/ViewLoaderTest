package bello.andrea.viewloadertest;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Let's change background's color from blue to red.
        ColorDrawable[] color = {new ColorDrawable(Color.BLUE), new ColorDrawable(Color.RED)};
        TransitionDrawable trans = new TransitionDrawable(color);
        //This will work also on old devices. The latest API says you have to use setBackground instead.
        btn.setBackgroundDrawable(trans);
        trans.startTransition(5000);
        */


        final AnimateContainer[] views = new AnimateContainer[4];
        views[0] = new GroupContainer(findViewById(R.id.textview1_1), findViewById(R.id.textview1_2));
        views[1] = new ViewContainer(findViewById(R.id.textview2));
        views[2] = new ViewContainer(findViewById(R.id.textview3));
        views[3] = new ViewContainer(findViewById(R.id.textview4));

        for(int i=0; i<views.length; i++)
            views[i].animate(i*300);
    }

    public void animate2(final View v, int offset){
        final ValueAnimator animator = ValueAnimator.ofInt(Color.WHITE, Color.GRAY);
        animator.setDuration(4000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int now = (int) animation.getAnimatedValue();
                //Log.i("VAL", String.valueOf(now));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.setBackground(new ColorDrawable(now));
                } else {
                    v.setBackgroundDrawable(new ColorDrawable(now));
                }
            }
        });
        animator.setInterpolator(new GaussianInterpolator());
        animator.setStartDelay(offset);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    public void animate(View v, int offset){
        ObjectAnimator anim = ObjectAnimator.ofInt(v, "backgroundColor", Color.WHITE, Color.GRAY);
        anim.setDuration(4000);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setInterpolator(new GaussianInterpolator());
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setStartDelay(offset);
        anim.start();
    }

    public class GaussianInterpolator implements Interpolator {

        double preCalculatedPart;

        public GaussianInterpolator() {
            preCalculatedPart = 2*Math.pow(-0.108101, 2);
        }

        public float getInterpolation(float t) {
            return (float)Math.pow(Math.E, (-Math.pow((t - 0.5),2)/preCalculatedPart));
        }
    }


    private static int blendColors(int color1, int color2, float ratio) {
        final float inverseRatio = 1f - ratio;
        float a = (Color.alpha(color1) * inverseRatio) + (Color.alpha(color2) * ratio);
        float r = (Color.red(color1) * inverseRatio) + (Color.red(color2) * ratio);
        float g = (Color.green(color1) * inverseRatio) + (Color.green(color2) * ratio);
        float b = (Color.blue(color1) * inverseRatio) + (Color.blue(color2) * ratio);
        return Color.argb((int) a, (int) r, (int) g, (int) b);
    }

    private static float lerp(float startValue, float endValue, float fraction, Interpolator interpolator) {
        if (interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }
        return lerp(startValue, endValue, fraction);
    }

    static float lerp(float startValue, float endValue, float fraction) {
        return startValue + (fraction * (endValue - startValue));
    }

    static int lerp(int startValue, int endValue, float fraction) {
        return startValue + Math.round(fraction * (endValue - startValue));
    }
}
