package restApi3;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

import static restApi3.ApplicationUserPermissions.*;

public enum ApplicationUserRoles {
    STUDENT(Sets.newHashSet(STUDENT_READ)), ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermissions> permission;

    ApplicationUserRoles(Set<ApplicationUserPermissions> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPermissions> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermission().stream().
                map(permission -> new SimpleGrantedAuthority(permission.getPermission())).
                collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority(("ROLE_" + this.name())));
        return permissions;
    }
}
