package com.pruebatecnica.openapi.api;

import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.servlet.http.HttpServletResponse;

public class ApiUtil {

    private ApiUtil() {
    }

    public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
        if (req == null || contentType == null || example == null) {
            return;
        }

        HttpServletResponse response = req.getNativeResponse(HttpServletResponse.class);
        if (response == null) {
            return;
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.parseMediaType(contentType).toString());
        try {
            response.getWriter().write(example);
        } catch (Exception ignored) {
            // no-op
        }
    }
}
