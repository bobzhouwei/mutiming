package org.mutiming.configuration.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * @author: Wei.Zhou
 */
@Component
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {
    private static final String REQUEST_BODY_TAG = "requestBody";
    private static final String REQUEST_BEGIN_TIME_TAG = "beginTime";

    private static final List<String> IGNORE_URLS = new ArrayList<>();

    static {
        IGNORE_URLS.add("/error");
        IGNORE_URLS.add("/webjars");
        IGNORE_URLS.add("/csrf");
    }

    @Override
    public final boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handler)
            throws ServletException, IOException {

        if (StringUtils.isEmpty(httpServletRequest.getRequestURI())) {
            return true;
        }

        for (String ignoreUrl : IGNORE_URLS) {
            if (httpServletRequest.getRequestURI().startsWith(ignoreUrl)) {
                return true;
            }
        }

        try {
            String bodyStr = HttpHelper.getBodyString(httpServletRequest);
            httpServletRequest.setAttribute(REQUEST_BODY_TAG, bodyStr);
            httpServletRequest.setAttribute(REQUEST_BEGIN_TIME_TAG, System.nanoTime());
        } catch (Exception e) {
            log.warn("LogInterceptor.preHandle", e);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {

        if (StringUtils.isEmpty(request.getRequestURI())) {
            return;
        }
        for (String ignoreUrl : IGNORE_URLS) {
            if (request.getRequestURI().startsWith(ignoreUrl)) {
                return;
            }
        }

        try {
            String requestBody = request.getAttribute(REQUEST_BODY_TAG).toString();
            ResponseModel responseModel = ResponseModel.getResponseModel();
            ResponseModel.removeRequestModel();

            String responseBody = responseModel != null ? responseModel.getResponseBody() : "";
            String uri = request.getRequestURI().replace("/", "_");

            Enumeration<String> requestHeadNames = request.getHeaderNames();
            StringBuilder requestHead = new StringBuilder();
            while (requestHeadNames.hasMoreElements()) {
                String headName = requestHeadNames.nextElement();
                String headValue = request.getHeader(headName);
                if (StringUtils.isNotEmpty(requestHead.toString())) {
                    requestHead.append(",");
                }
                requestHead.append("\"").append(headName).append("\":\"").append(headValue).append("\"");
            }
            String requestHeadJson = String.format("{%s}", requestHead.toString());

            Collection<String> responseHeadNames = response.getHeaderNames();
            StringBuilder responseHead = new StringBuilder();
            for (String responseHeadName : responseHeadNames) {
                if (StringUtils.isNotEmpty(responseHead.toString())) {
                    responseHead.append(",");
                }
                String headValue = response.getHeader(responseHeadName);
                responseHead.append("\"").append(responseHeadName).append("\":\"").append(headValue).append("\"");
            }
            String responseHeadJson = String.format("{%s}", responseHead.toString());

            long interval = (System.nanoTime() - Long.valueOf(request.getAttribute(REQUEST_BEGIN_TIME_TAG).toString())) / 1000000;

            log.info("[ServiceLog] uri: {}; interval: {}; requestHead: {}; requestBody: {}; responseHead: {}; responseBody: {}",
                    uri, interval, requestHeadJson, requestBody, responseHeadJson, responseBody);

        } catch (Exception e) {
            log.warn("LogInterceptor.postHandle", e);
        }
    }
}
