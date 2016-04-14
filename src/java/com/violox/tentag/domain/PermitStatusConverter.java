package com.violox.tentag.domain;

import java.util.ArrayList;

public class PermitStatusConverter implements StatusConverter {

    private final ArrayList<PermitStatusBitMap> statusBitMap;

    public PermitStatusConverter(DbContext context) {
        statusBitMap = (ArrayList<PermitStatusBitMap>) context.PermitStatusBitMap().get();
    }

    @Override
    public ArrayList<StatusMapItem> getStatusMap(Integer status) {

        ArrayList<StatusMapItem> ret = new ArrayList<>();
        for (PermitStatusBitMap m : statusBitMap) {
            StatusMapItem i = new StatusMapItem();
            i.setStatusBit(m.getBit());
            i.setStatusName(m.getValue());
            i.setIsStatus(Boolean.TRUE);

            int x = i.getStatusBitValue() & status;
            if (i.getStatusBitValue() != x) {
                i.setIsStatus(Boolean.FALSE);
            }

            ret.add(i);
        }

        return ret;
    }

    @Override
    public Integer getStatus(ArrayList<StatusMapItem> statusMap) {
        Integer ret = 0;
        if (statusMap != null) {
            for (StatusMapItem i : statusMap) {
                ret += i.getStatusBitValue();
            }
        }
        return ret;
    }

}
