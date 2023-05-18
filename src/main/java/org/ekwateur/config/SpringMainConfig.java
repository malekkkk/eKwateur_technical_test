package org.ekwateur.config;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.ekwateur")
public class SpringMainConfig {

    @Bean()
    public TextIO textIO() {
        return TextIoFactory.getTextIO();
    }
}
