package com.argus.mytodo.Enums;

public enum RoleENUM {
    SUPERADMIN("SUPERADMIN"),
    ADMIN("ADMIN"),
    CLIENT("CLIENT");

    private String value;

    RoleENUM(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
