// Generated code from Butter Knife. Do not modify!
package com.ofal.ihsan.oppomonitoring.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ofal.ihsan.oppomonitoring.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FingerPrintActivity_ViewBinding implements Unbinder {
  private FingerPrintActivity target;

  private View view2131230833;

  private View view2131230762;

  @UiThread
  public FingerPrintActivity_ViewBinding(FingerPrintActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FingerPrintActivity_ViewBinding(final FingerPrintActivity target, View source) {
    this.target = target;

    View view;
    target.textView = Utils.findRequiredViewAsType(source, R.id.errorText, "field 'textView'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_menu, "method 'onViewClicked'");
    view2131230833 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_tambah, "method 'onViewClicked'");
    view2131230762 = view;
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
    FingerPrintActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textView = null;

    view2131230833.setOnClickListener(null);
    view2131230833 = null;
    view2131230762.setOnClickListener(null);
    view2131230762 = null;
  }
}
