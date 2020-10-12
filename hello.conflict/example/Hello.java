package example;

import java.lang.*;

class Hello {
    public static void main(String[] args) {
        System.out.println("hello world (hello.conflict)!");

        // print some info about the boot layer
        for (Module module : ModuleLayer.boot().modules()) {
            System.out.println(module);
        }
    }
}
