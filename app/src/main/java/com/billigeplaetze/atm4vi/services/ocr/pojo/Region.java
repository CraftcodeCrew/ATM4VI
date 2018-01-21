
package com.billigeplaetze.atm4vi.services.ocr.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Region {

    @SerializedName("boundingBox")
    @Expose
    private String boundingBox;
    @SerializedName("lines")
    @Expose
    private List<Line> lines = null;

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(boundingBox).append(lines).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Region) == false) {
            return false;
        }
        Region rhs = ((Region) other);
        return new EqualsBuilder().append(boundingBox, rhs.boundingBox).append(lines, rhs.lines).isEquals();
    }

}
