package com.billigeplaetze.atm4vi.domain.definitions;

import android.content.Context;
import android.view.TextureView;

import com.billigeplaetze.atm4vi.domain.uc.IPhotoTakenUseCase;

/**
 * Created by dannynator on 20.01.18.
 */

public interface PhotoService {

    void startService(TextureView textureView, Context context, IPhotoTakenUseCase iPhotoTakenUseCase);

}
