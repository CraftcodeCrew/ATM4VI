
package com.billigeplaetze.atm4vi.services.ocr.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ReceivedData {

    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("textAngle")
    @Expose
    private Double textAngle;
    @SerializedName("orientation")
    @Expose
    private String orientation;
    @SerializedName("regions")
    @Expose
    private List<Region> regions = null;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getTextAngle() {
        return textAngle;
    }

    public void setTextAngle(Double textAngle) {
        this.textAngle = textAngle;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(orientation).append(textAngle).append(language).append(regions).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ReceivedData) == false) {
            return false;
        }
        ReceivedData rhs = ((ReceivedData) other);
        return new EqualsBuilder().append(orientation, rhs.orientation).append(textAngle, rhs.textAngle).append(language, rhs.language).append(regions, rhs.regions).isEquals();
    }

}
