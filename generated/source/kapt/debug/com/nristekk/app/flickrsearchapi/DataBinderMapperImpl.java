package com.nristekk.app.flickrsearchapi;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.nristekk.app.flickrsearchapi.databinding.ActivityMainBindingImpl;
import com.nristekk.app.flickrsearchapi.databinding.FragmentHistoryBindingImpl;
import com.nristekk.app.flickrsearchapi.databinding.FragmentMainBindingImpl;
import com.nristekk.app.flickrsearchapi.databinding.ItemHistoryBindingImpl;
import com.nristekk.app.flickrsearchapi.databinding.ItemNetworkStateBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_FRAGMENTHISTORY = 2;

  private static final int LAYOUT_FRAGMENTMAIN = 3;

  private static final int LAYOUT_ITEMHISTORY = 4;

  private static final int LAYOUT_ITEMNETWORKSTATE = 5;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(5);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nristekk.app.flickrsearchapi.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nristekk.app.flickrsearchapi.R.layout.fragment_history, LAYOUT_FRAGMENTHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nristekk.app.flickrsearchapi.R.layout.fragment_main, LAYOUT_FRAGMENTMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nristekk.app.flickrsearchapi.R.layout.item_history, LAYOUT_ITEMHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nristekk.app.flickrsearchapi.R.layout.item_network_state, LAYOUT_ITEMNETWORKSTATE);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHISTORY: {
          if ("layout/fragment_history_0".equals(tag)) {
            return new FragmentHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_history is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMAIN: {
          if ("layout/fragment_main_0".equals(tag)) {
            return new FragmentMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMHISTORY: {
          if ("layout/item_history_0".equals(tag)) {
            return new ItemHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_history is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMNETWORKSTATE: {
          if ("layout/item_network_state_0".equals(tag)) {
            return new ItemNetworkStateBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_network_state is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(5);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "history");
      sKeys.put(2, "historyViewModel");
      sKeys.put(3, "historyViewmodel");
      sKeys.put(4, "serviceViewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(5);

    static {
      sKeys.put("layout/activity_main_0", com.nristekk.app.flickrsearchapi.R.layout.activity_main);
      sKeys.put("layout/fragment_history_0", com.nristekk.app.flickrsearchapi.R.layout.fragment_history);
      sKeys.put("layout/fragment_main_0", com.nristekk.app.flickrsearchapi.R.layout.fragment_main);
      sKeys.put("layout/item_history_0", com.nristekk.app.flickrsearchapi.R.layout.item_history);
      sKeys.put("layout/item_network_state_0", com.nristekk.app.flickrsearchapi.R.layout.item_network_state);
    }
  }
}
