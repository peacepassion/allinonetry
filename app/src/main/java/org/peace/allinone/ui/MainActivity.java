package org.peace.allinone.ui;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.util.LinkedList;
import java.util.List;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.r_v) RecyclerView rv;
  @InjectView(R.id.top) View topV;
  @InjectView(R.id.vg) ViewGroup vg;

  Dir direction;
  ObjectAnimator anim;

  RVAdapter adapter = new RVAdapter();
  GridLayoutManager manager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    init();
    initVG();
  }

  void init() {
    manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
    rv.setLayoutManager(manager);
    rv.setAdapter(adapter);
    List<String> contents = new LinkedList<>();
    for (int i = 0; i < 100; i++) {
      contents.add("item " + i);
    }
    adapter.setData(contents);
  }

  void initVG() {
    GestureDetector gd = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
      @Override
      public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (distanceY > 0) {
          direction = Dir.DOWN;
        }
        if (distanceY < 0) {
          direction = Dir.UP;
        }
        return false;
      };
    });

    rv.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        gd.onTouchEvent(event);
        return false;
      }
    });

    rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        AppLogger.d("rv dy: " + dy);
      }

      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        AppLogger.w("fire toggle");
        toggleStatusView(direction, manager.findFirstVisibleItemPosition() == 0);
      }
    });
  }

  void animateShow() {
    if (anim != null && anim.isRunning()) {
      return;
    }
    if (vg.getTranslationY() == -topV.getMeasuredHeight()) {
      anim = ObjectAnimator.ofFloat(vg, "translationY", -topV.getMeasuredHeight(), 0);
      anim.setDuration(500);
      anim.start();
    }
  }

  void animateHide() {
    if (anim != null && anim.isRunning()) {
      return;
    }
    if (vg.getTranslationY() == 0) {
      anim = ObjectAnimator.ofFloat(vg, "translationY", 0, -topV.getMeasuredHeight());
      anim.setDuration(500);
      anim.start();
    }
  }

  private void toggleStatusView(Dir direction, boolean isFirstItemShow) {
    if (Dir.DOWN.equals(direction)) {
      animateHide();
    } else if (Dir.UP.equals(direction) && isFirstItemShow) {
      animateShow();
    }
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      float ty = vg.getTranslationY();
      if (ty < -1) {
        animateShow();
      } else {
        animateHide();
      }
    }
  }

  enum Dir {
    UP, DOWN
  }
}
