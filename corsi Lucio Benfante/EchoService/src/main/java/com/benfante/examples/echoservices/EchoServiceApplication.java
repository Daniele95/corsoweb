package com.benfante.examples.echoservices;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for bootstrapping the services.
 *
 * @author <a href="mailto:lucio.benfante@gmail.com">Lucio Benfante</a>
 */
@SpringBootApplication
public class EchoServiceApplication {

    private static Log log = LogFactory.getLog(EchoServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EchoServiceApplication.class, args);
    }
    
}
