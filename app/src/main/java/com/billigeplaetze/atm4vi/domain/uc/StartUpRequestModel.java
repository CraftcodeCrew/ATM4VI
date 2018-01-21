package com.billigeplaetze.atm4vi.domain.uc;

import android.app.Activity;
import android.content.Context;
import android.view.TextureView;

/**
 * Created by dannynator on 21.01.18.
 */

public class StartUpRequestModel {

    private final Context context;
    private final TextureView textureView;
    private final Activity mainActivity;

    public Context getAppContext() {
        return appContext;
    }

    private final Context appContext;

    public StartUpRequestModel(Context context, TextureView textureView, Activity mainActivity, Context appContext) {
        this.context = context;
        this.textureView = textureView;
        this.mainActivity = mainActivity;
        this.appContext = appContext;
    }

    public Context getContext() {
        return context;
    }

    public TextureView getTextureView() {
        return textureView;
    }

    public Activity getMainActivity() {
        return mainActivity;
    }
}
