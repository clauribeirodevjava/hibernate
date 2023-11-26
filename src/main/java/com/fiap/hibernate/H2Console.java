package com.fiap.hibernate;
import org.h2.tools.Console;

public class H2Console {
    public static void main(String[] args) throws Exception {
        // Inicia o console do H2 (Web Server)
        Console.main("-web", "-browser");

        // Aguarda até que o console seja encerrado (pode não ser necessário)
        Thread.sleep(Long.MAX_VALUE);
    }
}