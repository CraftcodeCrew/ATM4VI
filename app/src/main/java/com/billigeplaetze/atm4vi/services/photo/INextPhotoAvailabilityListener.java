package com.billigeplaetze.atm4vi.services.photo;

/**
 * Created by dannynator on 20.01.18.
 */

public interface INextPhotoAvailabilityListener {

    void nextImage(byte[] image);

    void nextImageFaild(Exception ex);
}
