// Generated code from Butter Knife. Do not modify!
package com.ofal.ihsan.oppomonitoring.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ofal.ihsan.oppomonitoring.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131230844;

  private View view2131230946;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.username = Utils.findRequiredViewAsType(source, R.id.et_nik, "field 'username'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'password'", EditText.class);
    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinator_login, "field 'coordinatorLayout'", CoordinatorLayout.class);
    view = Utils.findRequiredView(source, R.id.masuk, "method 'onViewClicked'");
    view2131230844 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tanya, "method 'onViewClicked'");
    view2131230946 = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.username = null;
    target.password = null;
    target.coordinatorLayout = null;

    view2131230844.setOnClickListener(null);
    view2131230844 = null;
    view2131230946.setOnClickListener(null);
    view2131230946 = null;
  }
}
