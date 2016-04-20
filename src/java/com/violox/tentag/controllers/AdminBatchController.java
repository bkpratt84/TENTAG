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

@Named(value = "adminBatchController")
@ViewScoped
public class AdminBatchController implements Serializable {

    @Inject
    private Key<Integer> obj_key;

    @Inject
    private DbContext dbcontext;

    private ArrayList<Batch> batches;

    @PostConstruct
    public void init() {
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

}