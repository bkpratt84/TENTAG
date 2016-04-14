/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.violox.tentag.controllers;

import com.violox.tentag.domain.BatchStatusConverter;
import com.violox.tentag.domain.DbContext;
import com.violox.tentag.domain.PermitStatusConverter;
import com.violox.tentag.domain.StatusMapItem;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import javax.inject.Inject;

@Named(value = "statusWidgetController")
@ViewScoped
public class statusWidgetController implements Serializable {


    @Inject
    private DbContext context;

    private BatchStatusConverter batchStatusConverter;
    private PermitStatusConverter permitStatusConverter;
    
    private Integer batchStatInt;
    private Integer permitStatInt;
    
    private ArrayList<StatusMapItem> batchStatMap;
    private ArrayList<StatusMapItem> permitStatMap;

    public statusWidgetController() {
    }

    @PostConstruct
    public void init() {

        if(batchStatInt == null){
            batchStatInt = 0;
        }
        if(permitStatInt == null){
            permitStatInt = 0;
        }
        
        if (batchStatusConverter == null) {
            batchStatusConverter = new BatchStatusConverter(context);
        }
        if (permitStatusConverter == null) {
            permitStatusConverter = new PermitStatusConverter(context);
        }
    }

    public Integer getBatchStatInt() {
        this.batchStatInt = batchStatusConverter.getStatus(batchStatMap);
        return batchStatInt;
    }

    public void setBatchStatInt(Integer batchStatInt) {
        this.batchStatMap = batchStatusConverter.getStatusMap(batchStatInt);
        this.batchStatInt = batchStatInt;
    }

    public Integer getPermitStatInt() {
        this.permitStatInt = permitStatusConverter.getStatus(permitStatMap);
        return permitStatInt;
    }

    public void setPermitStatInt(Integer permitStatInt) {
        this.permitStatMap = permitStatusConverter.getStatusMap(permitStatInt);
        this.permitStatInt = permitStatInt;
    }

    
    
    
    public ArrayList<StatusMapItem> getBatchStatusMap() {
        batchStatMap = batchStatusConverter.getStatusMap(batchStatInt);
        return batchStatMap;
    }

    public ArrayList<StatusMapItem> getPermitStatusMap() {
        permitStatMap = permitStatusConverter.getStatusMap(permitStatInt);
        return permitStatMap;
    }

    public void setBatchStatusMap(ArrayList<StatusMapItem> batchStatusMap) {
        this.batchStatMap = batchStatusMap;
        this.batchStatInt = batchStatusConverter.getStatus(batchStatusMap);
        
    }

    public void setPermitStatusMap(ArrayList<StatusMapItem> permitStatusMap) {
        this.permitStatMap = permitStatusMap;
        this.permitStatInt = permitStatusConverter.getStatus(permitStatusMap);
    }
    
    public void something(){
        
        
    }

}
