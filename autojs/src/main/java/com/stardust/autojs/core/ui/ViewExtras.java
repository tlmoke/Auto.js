package com.stardust.autojs.core.ui;

import android.view.View;

import com.stardust.autojs.R;
import com.stardust.autojs.core.ui.attribute.ViewAttributes;
import com.stardust.autojs.core.ui.inflater.ResourceParser;
import com.stardust.autojs.core.ui.nativeview.NativeView;
import com.stardust.autojs.runtime.ScriptRuntime;

import org.mozilla.javascript.Scriptable;

public class ViewExtras {

    private NativeView mNativeView;

    private ViewAttributes mViewAttributes;

    public static ViewExtras get(View view) {
        ViewExtras extras;
        Object tag = view.getTag(R.id.view_tag_view_extras);
        if (tag instanceof ViewExtras) {
            extras = (ViewExtras) tag;
        } else {
            extras = new ViewExtras();
            view.setTag(R.id.view_tag_view_extras, extras);
        }
        return extras;
    }


    public static ViewAttributes getViewAttributes(View view, ResourceParser parser) {
        ViewExtras extras = get(view);
        ViewAttributes attributes = extras.getViewAttributes();
        if (attributes == null) {
            attributes = new ViewAttributes(parser, view);
            extras.setViewAttributes(attributes);
        }
        return attributes;
    }


    public static NativeView getNativeView(Scriptable scope, View view, Class<?> staticType, ScriptRuntime runtime) {
        ViewExtras extras = get(view);
        NativeView nativeView = extras.getNativeView();
        if (nativeView == null) {
            nativeView = new NativeView(scope, view, staticType, runtime);
            extras.setNativeView(nativeView);
        }
        return nativeView;
    }

    public static NativeView getNativeView(View view) {
        ViewExtras extras = get(view);
        return extras.getNativeView();
    }

    public final NativeView getNativeView() {
        return mNativeView;
    }

    public final ViewAttributes getViewAttributes() {
        return mViewAttributes;
    }

    public final void setNativeView(NativeView nativeView) {
        mNativeView = nativeView;
    }

    public final void setViewAttributes(ViewAttributes viewAttributes) {
        mViewAttributes = viewAttributes;
    }
}