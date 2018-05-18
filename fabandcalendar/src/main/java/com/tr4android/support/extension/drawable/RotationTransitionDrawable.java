/*
 * Copyright (C) 2014 Jerzy Chalupski
 * Copyright (C) 2016 Thomas Robert Altstidl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tr4android.support.extension.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import com.tr4android.support.extension.animation.AnimationUtils;
import com.tr4android.support.extension.animation.ValueAnimatorCompat;

/**
 * A drawable that animates the rotation and alpha value of the wrapped drawable
 */
public class RotationTransitionDrawable extends LayerDrawable {
    private float mRotation;
    private float mMaxRotation;

    private boolean mHasSecondDrawable;

    // Animator
    private ValueAnimatorCompat mAnimator;
    private Interpolator mStartInterpolator = new OvershootInterpolator();
    private Interpolator mReverseInterpolator = new AnticipateInterpolator();

    public RotationTransitionDrawable(Drawable drawable) {
        this(drawable, null);
    }

    public RotationTransitionDrawable(Drawable drawable, Drawable closeDrawable) {
        super(closeDrawable == null ? new Drawable[]{drawable} : new Drawable[]{drawable, closeDrawable});
        mHasSecondDrawable = closeDrawable != null;

        // The animator used to animate the transition
        mAnimator = AnimationUtils.createAnimator();
        mAnimator.setUpdateListener(new ValueAnimatorCompat.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimatorCompat animator) {
                setRotation(animator.getAnimatedFloatValue());
            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        if (mHasSecondDrawable) {
            int alpha = Math.min(Math.max(0, Math.round(mRotation / mMaxRotation * 255)), 255);
            canvas.rotate(mRotation, getBounds().centerX(), getBounds().centerY());
            getDrawable(0).setAlpha(255 - alpha);
            getDrawable(0).draw(canvas);
            canvas.rotate(-mMaxRotation, getBounds().centerX(), getBounds().centerY());
            getDrawable(1).setAlpha(alpha);
            getDrawable(1).draw(canvas);
        } else {
            canvas.rotate(mRotation, getBounds().centerX(), getBounds().centerY());
            super.draw(canvas);
        }
        canvas.restore();
    }

    public Interpolator getStartInterpolator() {
        return mStartInterpolator;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        mStartInterpolator = interpolator;
    }

    public Interpolator getReverseInterpolator() {
        return mReverseInterpolator;
    }

    public void setReverseInterpolator(Interpolator interpolator) {
        mReverseInterpolator = interpolator;
    }

    public float getRotation() {
        return mRotation;
    }

    public void setRotation(float rotation) {
        mRotation = rotation;
        invalidateSelf();
    }

    public float getMaxRotation() {
        return mMaxRotation;
    }

    public void setMaxRotation(float rotation) {
        mMaxRotation = rotation;
    }

    public void startTransition(int duration) {
        mAnimator.cancel();
        mAnimator.setFloatValues(mRotation, mMaxRotation);
        mAnimator.setDuration(duration);
        mAnimator.setInterpolator(mStartInterpolator);
        mAnimator.start();
    }

    public void reverseTransition(int duration) {
        mAnimator.cancel();
        mAnimator.setFloatValues(mRotation, 0f);
        mAnimator.setDuration(duration);
        mAnimator.setInterpolator(mReverseInterpolator);
        mAnimator.start();
    }
}
