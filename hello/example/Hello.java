package example;

import java.lang.*;

class Hello {
    public static void main(String[] args) {
        System.out.println("hello world!");

        // print our class loader
        System.out.println(Hello.class.getClassLoader());

        // print some info about the boot layer
        for (Module module : ModuleLayer.boot().modules()) {
            System.out.println(module);
        }
    }
}
