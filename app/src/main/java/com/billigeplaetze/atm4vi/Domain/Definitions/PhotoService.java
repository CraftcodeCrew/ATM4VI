package com.billigeplaetze.atm4vi.Domain.Definitions;

import android.content.Context;
import android.view.TextureView;

/**
 * Created by dannynator on 20.01.18.
 */

public interface PhotoService {

    void startService(TextureView textureView, Context context, IPhotoTakenUseCase iPhotoTakenUseCase);

}
