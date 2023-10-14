package com.aos.infrustructure.config;


import com.aos.core.constants.BaseConstant;
import com.aos.core.response.SimpleResponse;
import com.aos.core.message.MessageSourceService;
import com.aos.core.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthExceptionHandler implements AuthenticationEntryPoint {

    @Autowired
    private MessageSourceService messageSourceService;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        WebUtils.response(response, new SimpleResponse(
                false,
                HttpServletResponse.SC_FORBIDDEN,
                messageSourceService.getMessage(authException.getMessage()),
                BaseConstant.SHOW_TYPE_ERROR_MESSAGE
        ));
    }

}
