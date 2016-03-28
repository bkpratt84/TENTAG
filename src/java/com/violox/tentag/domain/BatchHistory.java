package com.violox.tentag.domain;

import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class BatchHistory {

    private Integer sequence;
    private Batch batch;
    @NotNull(message = "From Status is blank.")
    private Integer fromStatus;
    @NotNull(message = "To Status is blank.")
    private Integer toStatus;
    @NotNull(message = "Date cannot be blank.")
    private Date changeDateTime;

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Integer getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(Integer fromStatus) {
        this.fromStatus = fromStatus;
    }

    public Integer getToStatus() {
        return toStatus;
    }

    public void setToStatus(Integer toStatus) {
        this.toStatus = toStatus;
    }

    public Date getChangeDateTime() {
        return changeDateTime;
    }

    public void setChangeDateTime(Date changeDateTime) {
        this.changeDateTime = changeDateTime;
    }
    
    
    
    

}