package com.findMyGuide.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class LoginSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication)
        throws ServletException, IOException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String id = userDetails.getUsername();

        HttpSession session = request.getSession();
        session.setAttribute("id", id);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
