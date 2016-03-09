package com.psu.est.initializer;

import com.psu.est.config.SpringRootConfig;
import com.psu.est.config.SpringWebConfig;
import com.psu.est.filter.CorsFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by danielkarkee on 2/3/16.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                SpringRootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                SpringWebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {
                "/"
        };
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{ new CorsFilter() };
    }
}
