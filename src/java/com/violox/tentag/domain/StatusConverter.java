package com.violox.tentag.domain;

import java.util.ArrayList;

public interface StatusConverter {

    public ArrayList<StatusMapItem> getStatusMap(Integer status);

    public Integer getStatus(ArrayList<StatusMapItem> statusMap);

}
