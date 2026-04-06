package com.prajval.CityDealsApp.utils;

import com.prajval.CityDealsApp.enities.enums.Permissions;
import com.prajval.CityDealsApp.enities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.prajval.CityDealsApp.enities.enums.Permissions.*;

public class PermissionMapping {

    private static final Map<Role, Set<Permissions>> map = Map.of(

            Role.USER, Set.of(USER_VIEW),
            Role.SHOP_OWNER, Set.of(Permissions.SHOP_CREATE, Permissions.SHOP_UPDATE, Permissions.SHOP_VIEW),
            Role.ADMIN, Set.of(SHOP_DELETE, USER_DELETE, USER_VIEW)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role){
        return map.get(role)
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toSet());
    }
}
