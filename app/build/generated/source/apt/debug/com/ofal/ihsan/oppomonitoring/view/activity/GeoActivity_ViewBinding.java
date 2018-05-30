// Generated code from Butter Knife. Do not modify!
package com.ofal.ihsan.oppomonitoring.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ofal.ihsan.oppomonitoring.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GeoActivity_ViewBinding implements Unbinder {
  private GeoActivity target;

  private View view2131230822;

  @UiThread
  public GeoActivity_ViewBinding(GeoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GeoActivity_ViewBinding(final GeoActivity target, View source) {
    this.target = target;

    View view;
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
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230822.setOnClickListener(null);
    view2131230822 = null;
  }
}
