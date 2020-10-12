package api;

public class Api {
    public lib.Example getExample() {
        return new lib.Example();
    }

    public Class<?> getExampleClass() {
        return lib.Example.class;
    }
}
