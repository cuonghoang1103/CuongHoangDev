package dev.cuonghoang.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

/**
 * Thymeleaf Configuration
 * Cấu hình Thymeleaf Template Engine
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Configuration
public class ThymeleafConfig {

    /**
     * Template Resolver Configuration
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false); // Set to true in production
        templateResolver.setOrder(1);
        return templateResolver;
    }

    /**
     * Template Engine Configuration
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);

        // Add Spring Security dialect
        templateEngine.addDialect(new SpringSecurityDialect());

        // Add Layout dialect for layout:decorate and layout:fragment
        templateEngine.addDialect(new LayoutDialect());

        return templateEngine;
    }

    /**
     * View Resolver Configuration
     */
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(1);
        return viewResolver;
    }
}
