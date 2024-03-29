package com.example.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.log.LogMessage;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Custom {@link PermissionEvaluator} implementation
 *
 * @author Deepak Katariya
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public boolean hasPermission(Authentication authentication, Object accessType, Object permission) {
        this.logger.debug(LogMessage.of(() -> "Authorizing " + authentication.getName()) + " with " + accessType.toString() + "  " + permission.toString());
        if (authentication != null && accessType instanceof String) {
            if ("hasAccess".equalsIgnoreCase(String.valueOf(accessType))) {
                boolean hasAccess = validateAccess(String.valueOf(permission));
                return hasAccess;
            }
            return false;
        }
        return false;
    }

    private boolean validateAccess(String permission) {
        // ideally should be checked with user role, permission in database
        if ("READ".equalsIgnoreCase(permission)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String targetType,
                                 Object permission) {
        return false;
    }

}