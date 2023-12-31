package com.ain.fourbyfour.config.commonLogging;


import com.p6spy.engine.spy.P6SpyLoadableOptions;
import com.p6spy.engine.spy.P6SpyOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlLoggingConfig {

    @PostConstruct
    public void setLogMessageFormat(){
        P6SpyLoadableOptions options = P6SpyOptions.getActiveInstance();
        options.setLogMessageFormat(P6spySqlFormatter.class.getName());
    }
}
