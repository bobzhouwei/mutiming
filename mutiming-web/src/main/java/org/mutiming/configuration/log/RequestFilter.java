package org.mutiming.configuration.log;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Customize the request filter
 *
 * @author Wei.Zhou
 */
public class RequestFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper((HttpServletRequest) request);
        }

        // Putting the stream back to the new request. and passing the new request by chain.doFilter
        if (requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
