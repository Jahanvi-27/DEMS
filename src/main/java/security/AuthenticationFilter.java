package security;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter("*.xhtml")
public class AuthenticationFilter
implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException,
                   ServletException {

        HttpServletRequest req =
        (HttpServletRequest) request;

        HttpServletResponse res =
        (HttpServletResponse) response;

        HttpSession session =
        req.getSession(false);

        boolean loggedIn =
        session != null &&
        session.getAttribute("user") != null;

        boolean  loginPage =
        req.getRequestURI()
        .contains("login.xhtml");

        if(loggedIn || loginPage)
        {
            chain.doFilter(request,response);
        }
        else
        {
            res.sendRedirect(
            req.getContextPath()
            + "/login.xhtml");
        }
    }
}