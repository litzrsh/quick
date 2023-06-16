package me.litzrsh.commons.utils;

import jakarta.servlet.http.HttpServletRequest;
import me.litzrsh.commons.Constants;
import me.litzrsh.user.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

public abstract class SessionUtils {

    public static String getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            HttpServletRequest request = getCurrentRequest();
            if (request != null) {
                return request.getSession().getId();
            }
            return UUID.randomUUID().toString();
        }
    }

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return (User) authentication.getPrincipal();
        } else {
            HttpServletRequest request = getCurrentRequest();
            User anonymous = new User();
            anonymous.setId(request != null ? request.getSession().getId() : UUID.randomUUID().toString());
            anonymous.setName(Constants.SYSTEM_NAME_ANONYMOUS);
            anonymous.setNickname(Constants.SYSTEM_NAME_ANONYMOUS);
            return anonymous;
        }
    }

    public static HttpServletRequest getCurrentRequest() {
        RequestAttributes attributes = RequestContextHolder.currentRequestAttributes();
        if (ServletRequestAttributes.class.isAssignableFrom(attributes.getClass())) {
            return ((ServletRequestAttributes) attributes).getRequest();
        }
        return null;
    }
}
