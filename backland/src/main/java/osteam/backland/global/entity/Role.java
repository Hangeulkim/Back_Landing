package osteam.backland.global.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

    ROLE_GUEST("guest"),
    ROLE_USER("user"),
    ROLE_ADMIN("admin");

    private final String key;
}
