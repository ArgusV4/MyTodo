package com.argus.mytodo.config;

import java.util.List;

public class RoleEndpoints {
    public static final List<String> SUPERADMIN_ENDPOINTS = List.of(
            "/users/create",
            "/users/all-users",
            "/users/user/{id}"
            // Add other SUPERADMIN endpoints here
    );

    public static final List<String> ADMIN_ENDPOINTS = List.of(
            "/users/createClientByAdmin"
            // Add other ADMIN endpoints here
    );

    public static final List<String> CLIENT_ENDPOINTS = List.of(
            // Add other CLIENT endpoints here
    );
    public static final List<String> WhiteList_ENDPOINTS = List.of(
            "/users/generateToken"
            // Add other WhiteList endpoints here
    );
}