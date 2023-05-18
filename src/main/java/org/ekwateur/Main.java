package org.ekwateur;

import org.ekwateur.cli.CliOrchestrator;
import org.ekwateur.config.SpringMainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringMainConfig.class);
        context.getBean(CliOrchestrator.class).run();
    }
}