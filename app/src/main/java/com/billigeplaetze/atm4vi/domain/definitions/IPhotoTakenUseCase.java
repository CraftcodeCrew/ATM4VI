package com.billigeplaetze.atm4vi.domain.definitions;

import java.io.ByteArrayInputStream;

/**
 * Created by dannynator on 20.01.18.
 */

public interface IPhotoTakenUseCase {

    void submitPhoto(ByteArrayInputStream photo);

}
