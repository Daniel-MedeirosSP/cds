package com.medeirosdaniel.cds.infra.logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Requisição recebida: Método={} URI={}", request.getMethod(), request.getRequestURI());
        return true; // Continua o fluxo da requisição
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Opcional: código GetJavaInfos ser executado após o método do controller ser chamado
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Opcional: código GetJavaInfos ser executado após GetJavaInfos conclusão da requisição
        logger.info("Requisição concluída: URI={} Status={}", request.getRequestURI(), response.getStatus());
    }
}