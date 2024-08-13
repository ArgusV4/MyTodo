package com.argus.mytodo.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleENUM {
    SUPERADMIN("SUPERADMIN"),
    ADMIN("ADMIN"),
    CLIENT("CLIENT");

    private String value;

    RoleENUM(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static RoleENUM fromValue(String value) {
        for (RoleENUM role : RoleENUM.values()) {
            if (role.getValue().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role value: " + value);
    }

}
