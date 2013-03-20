package net.luszczyk.mdbv.web.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: luszczyk
 * Date: 02.11.12
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class BaseFilterBean extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("db") == null && !httpServletRequest.getRequestURL().toString().contains("/resources/")
                && !httpServletRequest.getRequestURL().toString().contains("/types/")
                && !httpServletRequest.getRequestURL().toString().endsWith(httpServletRequest.getContextPath() + "/index")
                && !httpServletRequest.getRequestURL().toString().endsWith(httpServletRequest.getContextPath() + "/db/connect.json")) {
            httpServletRequest.getRequestDispatcher("/index").forward(httpServletRequest, httpServletResponse);
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
