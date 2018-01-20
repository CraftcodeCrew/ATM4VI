
package com.billigeplaetze.atm4vi.services.ocr.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Line {

    @SerializedName("boundingBox")
    @Expose
    private String boundingBox;
    @SerializedName("words")
    @Expose
    private List<Word> words = null;

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("boundingBox", boundingBox).append("words", words).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(boundingBox).append(words).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Line) == false) {
            return false;
        }
        Line rhs = ((Line) other);
        return new EqualsBuilder().append(boundingBox, rhs.boundingBox).append(words, rhs.words).isEquals();
    }

}
