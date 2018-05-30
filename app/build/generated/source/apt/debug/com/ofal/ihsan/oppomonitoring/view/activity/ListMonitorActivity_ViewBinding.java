// Generated code from Butter Knife. Do not modify!
package com.ofal.ihsan.oppomonitoring.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ofal.ihsan.oppomonitoring.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListMonitorActivity_ViewBinding implements Unbinder {
  private ListMonitorActivity target;

  private View view2131230822;

  @UiThread
  public ListMonitorActivity_ViewBinding(ListMonitorActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ListMonitorActivity_ViewBinding(final ListMonitorActivity target, View source) {
    this.target = target;

    View view;
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    target.swipe = Utils.findRequiredViewAsType(source, R.id.swipe, "field 'swipe'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.ic_menu, "method 'onViewClicked'");
    view2131230822 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ListMonitorActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvList = null;
    target.swipe = null;

    view2131230822.setOnClickListener(null);
    view2131230822 = null;
  }
}
