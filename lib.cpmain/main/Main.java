package main;

public class Main
{
    public static void main(String[] args) throws Exception {
        final var libExample = new lib.Example();
        System.out.println("value: " + libExample.randomInt());
        System.out.println("classloader equal: " + Main.class.getClassLoader()
                           .equals(libExample.getClass().getClassLoader()));
        System.out.println("main module: " + Main.class.getModule());
        System.out.println("lib module: " + libExample.getClass().getModule());
    }
}
