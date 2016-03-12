package com.psu.est.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danielkarkee on 3/5/16.
 */
@Component

public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger logger = LogManager.getLogger(SuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.info("Cannot redirect");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /*
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */
    protected String determineTargetUrl(Authentication authentication) {
        String url;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        if (isTechnician(roles)) {
            url = "/technician";
        } else {
            url = "/accessDenied";
        }
        return url;
    }

    private boolean isTechnician(List<String> roles) {
        return roles.contains("TECHNICIAN");
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
