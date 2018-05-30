// Generated code from Butter Knife. Do not modify!
package com.ofal.ihsan.oppomonitoring.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ofal.ihsan.oppomonitoring.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MentoringAdapter$ViewHolder_ViewBinding implements Unbinder {
  private MentoringAdapter.ViewHolder target;

  @UiThread
  public MentoringAdapter$ViewHolder_ViewBinding(MentoringAdapter.ViewHolder target, View source) {
    this.target = target;

    target.txtNik = Utils.findRequiredViewAsType(source, R.id.txt_nik, "field 'txtNik'", TextView.class);
    target.txtNama = Utils.findRequiredViewAsType(source, R.id.txt_nama, "field 'txtNama'", TextView.class);
    target.txtTanggal = Utils.findRequiredViewAsType(source, R.id.txt_tanggal, "field 'txtTanggal'", TextView.class);
    target.txtAlamat = Utils.findRequiredViewAsType(source, R.id.txt_alamat, "field 'txtAlamat'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MentoringAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtNik = null;
    target.txtNama = null;
    target.txtTanggal = null;
    target.txtAlamat = null;
  }
}
