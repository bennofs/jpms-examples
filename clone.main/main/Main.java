package main;

import java.lang.reflect.*;

public class Main
{
    public static void main(String[] args) throws Exception {
        final var uselibExample = Class.forName("conflict.uselib.Example").getConstructor().newInstance();
        final Object uselibCloneExample = Class.forName("conflict.uselib.clone.Example").getConstructor().newInstance();
        final Method randomIntMethod = uselibExample.getClass().getMethod("randomInt");
        final Method randomIntMethodClone = uselibCloneExample.getClass().getMethod("randomInt");
        System.out.println("uselib: " + randomIntMethod.invoke(uselibExample));
        System.out.println("uselib.clone: " + randomIntMethodClone.invoke(uselibCloneExample));

        System.out.println("main classloader: " + Main.class.getClassLoader());
        System.out.println("main module: " + Main.class.getModule());
        System.out.println("uselib classloader: " + uselibExample.getClass().getClassLoader());
        System.out.println("uselib module: " + uselibExample.getClass().getClassLoader());
        System.out.println("uselib.clone classloader: " + uselibCloneExample.getClass().getClassLoader());
        System.out.println("uselib.clone module: " + uselibCloneExample.getClass().getModule());
    }
}
