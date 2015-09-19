package org.peace.allinone.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by peacepassion on 15/9/19.
 */
public class RVItemAnimator extends DefaultItemAnimator {


  @Override public boolean animateAdd(final RecyclerView.ViewHolder holder) {
    final RVVH rvHolder = (RVVH) holder;
    rvHolder.tv.animate().rotationY(180).setDuration(2000).setListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
      }

      @Override public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        dispatchAddFinished(holder);
      }
    }).start();
    return true;
  }

  @Override public boolean animateRemove(RecyclerView.ViewHolder holder) {
    RVVH rvHolder = (RVVH) holder;
    rvHolder.tv.animate().rotationX(40).setDuration(2000).start();
    return true;
  }
}
