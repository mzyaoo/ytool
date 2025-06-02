package com.mzyao.ytool.config;

import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@org.springframework.context.annotation.Configuration
public class FreeMarkerConfig {

    @Bean(name = "freemarkerConfiguration")
    public freemarker.template.Configuration freemarkerConfiguration() throws IOException {
        Configuration config = new Configuration(Configuration.VERSION_2_3_31);
        config.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates/freemarker");
        config.setDefaultEncoding("UTF-8");
        return config;
    }

}
