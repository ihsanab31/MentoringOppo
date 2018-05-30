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

public class MenuAdminActivity_ViewBinding implements Unbinder {
  private MenuAdminActivity target;

  private View view2131230833;

  private View view2131230760;

  private View view2131230759;

  @UiThread
  public MenuAdminActivity_ViewBinding(MenuAdminActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MenuAdminActivity_ViewBinding(final MenuAdminActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_menu, "method 'onViewClicked'");
    view2131230833 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_loc, "method 'onViewClicked'");
    view2131230760 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_list, "method 'onViewClicked'");
    view2131230759 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230833.setOnClickListener(null);
    view2131230833 = null;
    view2131230760.setOnClickListener(null);
    view2131230760 = null;
    view2131230759.setOnClickListener(null);
    view2131230759 = null;
  }
}
