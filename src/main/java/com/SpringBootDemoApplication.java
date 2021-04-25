package com;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import static com.demo.utils.Constants.DEFAULT_DATE_FORMAT;
import static org.joda.time.DateTime.now;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.boot.WebApplicationType.SERVLET;

/**
 * @Author Sherlock.shen
 * @Date 2021/4/23 14:49
 * @Description:启动类
 */
@SpringBootApplication
public class SpringBootDemoApplication extends SpringBootServletInitializer {
    private static final Logger LOGGER = getLogger(SpringBootServletInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootDemoApplication.class);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootDemoApplication.class).web(SERVLET).run();
        final String now = now().toString(DEFAULT_DATE_FORMAT);
        LOGGER.info("(SpringBootDemo)于{}启动成功...............", now);
    }
}
