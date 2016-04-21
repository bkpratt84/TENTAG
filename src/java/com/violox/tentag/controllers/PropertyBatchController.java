package com.violox.tentag.controllers;

import com.violox.tentag.domain.Batch;
import com.violox.tentag.domain.DbContext;
import com.violox.tentag.domain.Key;
import com.violox.tentag.domain.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "propertyBatchController")
@ViewScoped
public class PropertyBatchController implements Serializable {

    @Inject
    private Key<Integer> obj_key;

    @Inject
    private DbContext dbcontext;

    private ArrayList<Batch> batches;
    private Batch batch;
    
    private boolean display;
    
    @PostConstruct
    public void init() {
        display = false;
        
        refreshData();
    }

    public void refreshData() {
        if (batches == null) {
            batches = new ArrayList<>();

            batches = dbcontext.Batch().get();

            for (Batch batch : batches) {
                batch.fillProperty(dbcontext);
                batch.fillPrinter(dbcontext);
            }
        }
    }
    
    public ArrayList<Batch> getBatches() {
        return batches;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}