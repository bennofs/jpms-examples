package main;
import api.Api;
import java.lang.reflect.*;

public class Main
{
    public static void main(String[] args) throws Exception {
        final Api api = new Api();

        // this produces a build error
        // (package lib is declared in module lib, but module apinottransitive.main does not read it)
        //System.out.println(api.getExample().randomInt());

        // get class via provided method
        final Object o = api.getExampleClass().getConstructor().newInstance();
        System.out.println(o);

        // call the method via reflection
        Method randInt = api.getExampleClass().getDeclaredMethod("randomInt");
        System.out.println(randInt.invoke(api.getExample()));

        // can also get class dynamically
        Class<?> cls = Class.forName("lib.Example");
        System.out.println(cls.equals(api.getExampleClass()));

    }
}
