// Generated code from Butter Knife. Do not modify!
package com.ofal.ihsan.oppomonitoring.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ofal.ihsan.oppomonitoring.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailActivity_ViewBinding implements Unbinder {
  private DetailActivity target;

  private View view2131230761;

  @UiThread
  public DetailActivity_ViewBinding(DetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailActivity_ViewBinding(final DetailActivity target, View source) {
    this.target = target;

    View view;
    target.textView2 = Utils.findRequiredViewAsType(source, R.id.textView2, "field 'textView2'", TextView.class);
    target.icMenu = Utils.findRequiredViewAsType(source, R.id.ic_menu, "field 'icMenu'", ImageView.class);
    target.searchContainer = Utils.findRequiredViewAsType(source, R.id.search_container, "field 'searchContainer'", RelativeLayout.class);
    target.txtNikDetail = Utils.findRequiredViewAsType(source, R.id.txt_nik_detail, "field 'txtNikDetail'", TextView.class);
    target.txtNamaDetail = Utils.findRequiredViewAsType(source, R.id.txt_nama_detail, "field 'txtNamaDetail'", TextView.class);
    target.txtTanggalDetail = Utils.findRequiredViewAsType(source, R.id.txt_tanggal_detail, "field 'txtTanggalDetail'", TextView.class);
    target.txtAlamatDetail = Utils.findRequiredViewAsType(source, R.id.txt_alamat_detail, "field 'txtAlamatDetail'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_selesai, "method 'onViewClicked'");
    view2131230761 = view;
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
    DetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textView2 = null;
    target.icMenu = null;
    target.searchContainer = null;
    target.txtNikDetail = null;
    target.txtNamaDetail = null;
    target.txtTanggalDetail = null;
    target.txtAlamatDetail = null;

    view2131230761.setOnClickListener(null);
    view2131230761 = null;
  }
}
