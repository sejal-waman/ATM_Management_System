package main.com.java.Interceptor;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();

        // Allow access to login, registration, and static resources
		if (requestURI.equals("/") || requestURI.equals("/index.html") || requestURI.equals("/login")
				|| requestURI.equals("/register") || requestURI.equals("/register_data")
				|| requestURI.equals("/login_data")) 
		{
			return true; // Allow public access to index and login pages
		}


        if (session != null && session.getAttribute("user") != null) {
            return true; // User is authenticated
        }

        response.sendRedirect("/login?error=Please log in first.");
        return false;
    }
}
