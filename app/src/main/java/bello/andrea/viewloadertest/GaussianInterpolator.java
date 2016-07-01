package bello.andrea.viewloadertest;

import android.view.animation.Interpolator;

public class GaussianInterpolator implements Interpolator {

    double preCalculatedPart;

    public GaussianInterpolator() {
        preCalculatedPart = 2*Math.pow(-0.108101, 2);
    }

    public float getInterpolation(float t) {
        return (float)Math.pow(Math.E, (-Math.pow((t - 0.5),2)/preCalculatedPart));
    }
}