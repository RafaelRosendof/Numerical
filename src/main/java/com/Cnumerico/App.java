package com.Cnumerico;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        System.out.println("TESTANDO O COMPILER");
    }
}

/*
 *Para compilar basta digitar no terminal:
 mvn exec:java -Dexec.mainClass="com.Cnumerico.App"


 ou para compilar 
 mvn compile 
 e para executar
 mvn package gera o jar
java -jar target/Numerical-1.0-SNAPSHOT.jar para executar o .jar
 */