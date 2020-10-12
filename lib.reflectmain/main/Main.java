package main;

import java.lang.reflect.*;

public class Main
{
    public static void main(String[] args) throws Exception {
        final var libExample = Class.forName("lib.Example").getConstructor().newInstance();
        final Method randomIntMethod = libExample.getClass().getMethod("randomInt");
        System.out.println("value: " + randomIntMethod.invoke(libExample));
        System.out.println("classloader equal: " + Main.class.getClassLoader()
                           .equals(libExample.getClass().getClassLoader()));
        System.out.println("main module: " + Main.class.getModule());
        System.out.println("lib module: " + libExample.getClass().getModule());
    }
}
