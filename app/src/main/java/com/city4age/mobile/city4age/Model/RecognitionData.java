package com.city4age.mobile.city4age.Model;

import java.util.Date;

/**
 * Created by Srle on 12/17/2017.
 */

public class RecognitionData {
    private Date timestamp;
    private String type;

    public RecognitionData(Date timestamp, String type) {
        this.timestamp = timestamp;
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }
}
