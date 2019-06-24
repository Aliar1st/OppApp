package loc.aliar.oppapp.config;

import loc.aliar.oppapp.domain.ResourceTypes;
import loc.aliar.oppapp.domain.User;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Objects;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    /**
     * @param auth authenticated user. Not null.
     * @param targetObject the object for which permissions should be checked. Not null.
     * @param permission type of permission. Not null.
     * @return true if the permission is granted, false otherwise
     */
    @Override
    public boolean hasPermission(Authentication auth, Object targetObject, Object permission) {
        if (auth == null || targetObject == null || permission == null) {
            return false;
        }

        if (auth.getAuthorities().stream()
                .anyMatch(o -> o.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }

        User u = (User) auth.getPrincipal();
        Methods m = Methods.valueOf(permission.toString().toUpperCase());

        switch (ResourceTypes.valueOf(targetObject.getClass().getSimpleName().toUpperCase())) {
            case USER: return hasPermissionToUser(u, (User) targetObject, m);
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    private boolean hasPermissionToUser(User auth, User user, Methods method) {
        switch (method) {
            case SAVE:
                return Objects.equals(auth.getId(), user.getId());
        }

        return false;
    }

    private enum Methods {
        SAVE, DELETE, DELETE_BY_ID
    }
}
