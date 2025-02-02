package springapp.SecurityApp.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public CustomAuthenticationFailureHandler() {
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorParam = "unknown";
        if (exception instanceof LockedException) {
            errorParam = "locked";
        } else if (exception instanceof BadCredentialsException) {
            errorParam = "bad_credentials";
        }

        this.getRedirectStrategy().sendRedirect(request, response, "/auth/login?error=" + errorParam);
    }
}
