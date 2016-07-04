package bello.andrea.viewloadertest;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;

public class ViewContainer extends AnimateContainer {

    View content;

    public ViewContainer(View content) {
        this.content = content;
    }

    @Override
    protected ValueAnimator.AnimatorUpdateListener getUpdateListener() {
        return new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int now = (int) animation.getAnimatedValue();
                //Log.i("a", "a");
                changeBackgroudColor(content, now);
            }
        };
    }
}
