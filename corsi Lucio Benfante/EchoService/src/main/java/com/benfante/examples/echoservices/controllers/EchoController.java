package com.benfante.examples.echoservices.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * An echo service for using in http examples.
 *
 * @author <a href="mailto:lucio.benfante@gmail.com">Lucio Benfante</a>
 */
@RestController
@RequestMapping("v1/echo")
public class EchoController {

    private static final Logger log = LoggerFactory.getLogger(EchoController.class);

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getEcho(@RequestParam("nome") String nome, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        log.info("**************************");
        log.info("** Received GET request **");
        log.info("**************************");
        log.info("Parametro nome = {}", nome);
        logRequestHeaders(req);
        logResponseHeaders(res);
        logParameters(req);
        if( req.getParameter( "redirect" ) != null ) {
        	res.sendRedirect( req.getParameter( "redirect" ) );
        }
            return "OK";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String postEcho(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("**************************");
        log.info("** Received POST request **");
        log.info("**************************");
        logRequestHeaders(req);
        logResponseHeaders(res);
        logParameters(req);
        if( req.getParameter( "redirect" ) != null ) {
        	res.sendRedirect( req.getParameter( "redirect" ) );
        }
        return "OK";
    }
    
    private void logParameters(HttpServletRequest req) {
        Enumeration<String> parameterNames = req.getParameterNames();
        log.info("** HTTP Parameters ***");
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String[] parameter = req.getParameterValues(parameterName);
            for (String value : parameter) {
                log.info("  {} = {}", parameterName, value);                                
            }
        }
    }

    private void logResponseHeaders(HttpServletResponse res) {
        Collection<String> headerNames = res.getHeaderNames();
        log.info("** HTTP Response Headers ***");
        for (String headerName : headerNames) {
            Collection<String> headers = res.getHeaders(headerName);
            for (String header : headers) {
                log.info("  {} = {}", headerName, header);                
            }
        }
    }

    private void logRequestHeaders(HttpServletRequest req) {
        Enumeration<String> headerNames = req.getHeaderNames();
        log.info("** HTTP Request Headers ***");
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headers = req.getHeaders(headerName);
            while (headers.hasMoreElements()) {
                String header = headers.nextElement();
                log.info("  {} = {}", headerName, header);
            }
        }
    }

}
