package com.pavan.web.spring.configs;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

      @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**")
            .addResourceLocations("/resources/")
                .setCachePeriod(31556926);
        }

       @Bean
        public ReloadableResourceBundleMessageSource messageSource() {
            ReloadableResourceBundleMessageSource slr = new ReloadableResourceBundleMessageSource();
            slr.setBasename("classpath:messages");
            return slr;
        }

       @Bean
        public ViewResolver jspViewResolver() {
            InternalResourceViewResolver bean = new InternalResourceViewResolver();
            bean.setPrefix("/WEB-INF/jsp/");
            bean.setSuffix(".jsp");
            return bean;
        }


        @Bean
        public LocaleResolver localeResolver() {
            CookieLocaleResolver slr = new CookieLocaleResolver();
            slr.setDefaultLocale(Locale.US);
            slr.setCookieMaxAge(1000); 
            slr.setCookieName("myAppLocaleCookie");
            return slr;
        }

        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
            LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
            lci.setParamName("locale");
            return lci;
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {         
            registry.addInterceptor(localeChangeInterceptor());         
        }


}