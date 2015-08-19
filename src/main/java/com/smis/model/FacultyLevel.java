package com.smis.model;

public class FacultyLevel extends FacultyLevelKey {
    private String parentTid;

    public String getParentTid() {
        return parentTid;
    }

    public void setParentTid(String parentTid) {
        this.parentTid = parentTid == null ? null : parentTid.trim();
    }
}