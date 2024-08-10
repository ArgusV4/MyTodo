package com.argus.mytodo.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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

    @JsonCreator
    public static RoleENUM fromValue(String value) throws JsonProcessingException {
        for (RoleENUM role : RoleENUM.values()) {
            if (role.getValue().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new JsonMappingException("Invalid role value: " + value);
    }
}
