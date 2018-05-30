// This file was generated by PermissionsDispatcher. Do not modify!
package com.ofal.ihsan.oppomonitoring.view.base;

import android.support.v4.app.ActivityCompat;
import java.lang.Override;
import java.lang.String;
import java.lang.ref.WeakReference;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

final class BaseActivityPermissionsDispatcher {
  private static final int REQUEST_NEEDPERMISON = 0;

  private static final String[] PERMISSION_NEEDPERMISON = new String[] {"android.permission.ACCESS_FINE_LOCATION","android.permission.CAMERA","android.permission.WRITE_EXTERNAL_STORAGE","android.permission.USE_FINGERPRINT"};

  private BaseActivityPermissionsDispatcher() {
  }

  static void needPermisonWithPermissionCheck(BaseActivity target) {
    if (PermissionUtils.hasSelfPermissions(target, PERMISSION_NEEDPERMISON)) {
      target.needPermison();
    } else {
      if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_NEEDPERMISON)) {
        target.showRationaleNeedPermiison(new BaseActivityNeedPermisonPermissionRequest(target));
      } else {
        ActivityCompat.requestPermissions(target, PERMISSION_NEEDPERMISON, REQUEST_NEEDPERMISON);
      }
    }
  }

  static void onRequestPermissionsResult(BaseActivity target, int requestCode, int[] grantResults) {
    switch (requestCode) {
      case REQUEST_NEEDPERMISON:
      if (PermissionUtils.verifyPermissions(grantResults)) {
        target.needPermison();
      } else {
        if (!PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_NEEDPERMISON)) {
          target.showNeverAskNeedPermiison();
        } else {
          target.showDeniedNeedPermiison();
        }
      }
      break;
      default:
      break;
    }
  }

  private static final class BaseActivityNeedPermisonPermissionRequest implements PermissionRequest {
    private final WeakReference<BaseActivity> weakTarget;

    private BaseActivityNeedPermisonPermissionRequest(BaseActivity target) {
      this.weakTarget = new WeakReference<BaseActivity>(target);
    }

    @Override
    public void proceed() {
      BaseActivity target = weakTarget.get();
      if (target == null) return;
      ActivityCompat.requestPermissions(target, PERMISSION_NEEDPERMISON, REQUEST_NEEDPERMISON);
    }

    @Override
    public void cancel() {
      BaseActivity target = weakTarget.get();
      if (target == null) return;
      target.showDeniedNeedPermiison();
    }
  }
}
