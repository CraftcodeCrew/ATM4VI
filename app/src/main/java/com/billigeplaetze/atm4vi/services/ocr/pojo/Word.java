
package com.billigeplaetze.atm4vi.services.ocr.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Word {

    @SerializedName("boundingBox")
    @Expose
    private String boundingBox;
    @SerializedName("text")
    @Expose
    private String text;

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("boundingBox", boundingBox).append("text", text).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(text).append(boundingBox).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Word) == false) {
            return false;
        }
        Word rhs = ((Word) other);
        return new EqualsBuilder().append(text, rhs.text).append(boundingBox, rhs.boundingBox).isEquals();
    }

}
