package dev.cuonghoang.config.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Web MVC Configuration
 * Cấu hình Web MVC
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

        @Value("${app.upload.path:uploads/}")
        private String uploadPath;

        /**
         * Configure static resource handling
         */
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // Static resources
                registry.addResourceHandler("/static/**")
                                .addResourceLocations("classpath:/static/")
                                .setCachePeriod(3600);

                // CSS resources
                registry.addResourceHandler("/css/**")
                                .addResourceLocations("classpath:/static/css/")
                                .setCachePeriod(3600);

                // JavaScript resources
                registry.addResourceHandler("/js/**")
                                .addResourceLocations("classpath:/static/js/")
                                .setCachePeriod(3600);

                // Image resources
                registry.addResourceHandler("/img/**")
                                .addResourceLocations("classpath:/static/img/")
                                .setCachePeriod(3600);

                // Font resources
                registry.addResourceHandler("/fonts/**")
                                .addResourceLocations("classpath:/static/fonts/")
                                .setCachePeriod(3600);

                // Upload resources
                registry.addResourceHandler("/uploads/**")
                                .addResourceLocations("file:" + uploadPath)
                                .setCachePeriod(3600);

                // Favicon
                registry.addResourceHandler("/favicon.ico")
                                .addResourceLocations("classpath:/static/img/favicon.ico")
                                .setCachePeriod(86400);
        }

        /**
         * Configure view controllers
         */
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
                // Error pages
                registry.addViewController("/error/404").setViewName("error/404");
                registry.addViewController("/error/500").setViewName("error/500");
                registry.addViewController("/error/access-denied").setViewName("error/access-denied");
        }

        /**
         * Configure CORS
         */
        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                                .allowedOrigins("http://localhost:3000", "http://localhost:8080",
                                                "https://cuonghoang.dev")
                                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                                .allowedHeaders("*")
                                .allowCredentials(true)
                                .maxAge(3600);
        }

        /**
         * Locale Resolver Bean
         */
        @Bean
        public LocaleResolver localeResolver() {
                SessionLocaleResolver localeResolver = new SessionLocaleResolver();
                localeResolver.setDefaultLocale(new Locale("vi", "VN"));
                return localeResolver;
        }

        /**
         * Locale Change Interceptor Bean
         */
        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
                LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
                interceptor.setParamName("lang");
                return interceptor;
        }

        /**
         * Add interceptors
         */
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(localeChangeInterceptor());
        }

        /**
         * Configure content negotiation
         */
        @Override
        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                configurer
                                .favorParameter(true)
                                .parameterName("mediaType")
                                .ignoreAcceptHeader(false)
                                .useRegisteredExtensionsOnly(false)
                                .defaultContentType(org.springframework.http.MediaType.TEXT_HTML)
                                .mediaType("html", org.springframework.http.MediaType.TEXT_HTML)
                                .mediaType("json", org.springframework.http.MediaType.APPLICATION_JSON)
                                .mediaType("xml", org.springframework.http.MediaType.APPLICATION_XML);
        }
}
