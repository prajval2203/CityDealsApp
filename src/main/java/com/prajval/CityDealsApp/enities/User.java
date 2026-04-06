package com.prajval.CityDealsApp.enities;

import com.prajval.CityDealsApp.enities.enums.Role;
import com.prajval.CityDealsApp.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    @ManyToOne
    private City city;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        role.forEach(
            rol -> {
                Set<SimpleGrantedAuthority> permissions = PermissionMapping.getAuthoritiesForRole(rol);
                authorities.addAll(permissions);
                authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.name()));
            }
        );
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
