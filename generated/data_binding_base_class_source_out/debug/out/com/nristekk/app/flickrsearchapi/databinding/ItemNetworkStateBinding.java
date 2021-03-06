// Generated by data binding compiler. Do not edit!
package com.nristekk.app.flickrsearchapi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.nristekk.app.flickrsearchapi.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemNetworkStateBinding extends ViewDataBinding {
  @NonNull
  public final TextView errorMsg;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final Button retryButton;

  protected ItemNetworkStateBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView errorMsg, ProgressBar progressBar, Button retryButton) {
    super(_bindingComponent, _root, _localFieldCount);
    this.errorMsg = errorMsg;
    this.progressBar = progressBar;
    this.retryButton = retryButton;
  }

  @NonNull
  public static ItemNetworkStateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_network_state, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemNetworkStateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemNetworkStateBinding>inflateInternal(inflater, R.layout.item_network_state, root, attachToRoot, component);
  }

  @NonNull
  public static ItemNetworkStateBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_network_state, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemNetworkStateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemNetworkStateBinding>inflateInternal(inflater, R.layout.item_network_state, null, false, component);
  }

  public static ItemNetworkStateBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ItemNetworkStateBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemNetworkStateBinding)bind(component, view, R.layout.item_network_state);
  }
}
