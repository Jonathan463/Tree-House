package com.example.treehouse.model;

import com.google.common.collect.Lists;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.treehouse.model.Permission.*;

public enum ROLE_DETAIL {
    ANONYMOUS(Lists.newArrayList()),

    ADMIN(Lists.newArrayList(USER_WRITE, USER_READ, PRODUCT_READ, PRODUCT_WRITE, USERS_READ, USERS_WRITE));


    private final List<Permission> permissions;

    ROLE_DETAIL(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority((permission.getPermission())))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
