package com.meditrack.prs.interceptor;

import com.meditrack.prs.constant.ErrorCode;
import com.meditrack.prs.exception.PrsInvalidRequestException;
import com.meditrack.prs.service.HeaderReadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {


    private final HeaderReadService headerReadService;

    /**
     * Create a new WebRequestHandlerInterceptorAdapter for the given WebRequestInterceptor.
     *
     * @param requestInterceptor the WebRequestInterceptor to wrap
     */
    public HeaderInterceptor(WebRequestInterceptor requestInterceptor, HeaderReadService headerReadService) {
        this.headerReadService = headerReadService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Extract details from the header
        String hospital = request.getHeader("x-hospital");
        String user = request.getHeader("x-user");
        // Set the details to the context
        try {
            headerReadService.setHeaderDetailsToContext(Long.parseLong(hospital), user);
        } catch (Exception e) {
            log.error("Error while setting the tenant details to the context.", e);
            String headerPath = request.getRequestURI();
            if (headerPath.contains("/prs/swagger-ui.html") || headerPath.contains("/prs/v3/api-docs") || headerPath.contains("/prs/swagger-ui/")) {
                return true;
            }
            throw new PrsInvalidRequestException(ErrorCode.PRM_003002, "Invalid header details/ header details are missing. x-hospital and x-user are mandatory headers.");
        }
        return true;
    }
}

