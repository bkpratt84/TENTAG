package com.violox.tentag.domain;

public class StatusMapItem {

    private Integer statusBit;
    private String statusName;
    private Boolean isStatus;

    public Integer getStatusBit() {
        return statusBit;
    }

    public void setStatusBit(Integer statusBit) {
        this.statusBit = statusBit;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Boolean getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Boolean isStatus) {
        this.isStatus = isStatus;
    }

    public Integer getStatusBitValue() {
        if (isStatus) {
            Double d = Math.pow(2, getStatusBit().doubleValue());
            return d.intValue();
        } else {
            return 0;
        }
    }

}
