package com.test.league.football.auth.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.league.football.auth.ApiKeyAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class ApiKeyAuthenticationFilter implements Filter {

    private static final String AUTH_METHOD = "api-key";

    private static final String VALID_API_KEY = "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            String apiKey = getApiKey((HttpServletRequest) request);
            if (apiKey != null) {
                if (apiKey.equals(VALID_API_KEY)) {
                    ApiKeyAuthenticationToken apiToken = new ApiKeyAuthenticationToken(apiKey, AuthorityUtils.NO_AUTHORITIES);
                    SecurityContextHolder.getContext().setAuthentication(apiToken);
                } else {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.setStatus(401);
                    httpResponse.getWriter().write(
                            "{\n" +
                            "    \"status\": 401,\n" +
                            "    \"error\": \"Unauthorized\",\n" +
                            "    \"message\": \"invalid api-key\"\n" +
                            "}");
                    return;
                }
            }
        }

        chain.doFilter(request, response);

    }

    private String getApiKey(HttpServletRequest httpRequest) {
        String apiKey = null;

        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null) {
            authHeader = authHeader.trim();
            if (authHeader.toLowerCase().startsWith(AUTH_METHOD + " ")) {
                apiKey = authHeader.substring(AUTH_METHOD.length()).trim();
            }
        }

        return apiKey;
    }
}