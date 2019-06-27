package org.mutiming.configuration.log;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Helper class for http
 *
 * @author Wei.Zhou
 */
@Slf4j
class HttpHelper {
    /**
     * Get request body string from HttpServletRequest
     *
     * @param request
     * @return
     * @throws IOException
     */
    static String getBodyString(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = request.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            String errorMes = "HttpHelper.getBodyString.fail. request uri:%s. ";
            log.warn(String.format(errorMes, request.getRequestURI()), e);
        }
        return sb.toString();
    }
}
