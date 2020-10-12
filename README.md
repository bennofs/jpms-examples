# Examples for the Java Platform Module System (JPMS)

This is some example code to test features of the java module system.

## Modules

* `hello`: simple module with main class `example.Hello`
* `hello.conflict`: copy of `hello`, with different module name
* `lib`: simple library module, providing class `lib.Example`
* `lib.conflict`: copy of `lib` with different module name
* `lib.main`: module with main class depending on `lib`
* `conflict.uselibconflict`: library that requires `lib.conflict`
* `conflict.main`: module with main class requiring both `lib` and `conflict.uselibconflict`

## Examples

Running a simple hello world module:

``` sh
$ java --module-path $PWD/classes -m hello/example.Hello
hello world!
module java.prefs
module jdk.charsets
...
module hello
module jdk.compiler
...
```

Adding modules to the boot module layer:

```sh
$ java --module-path $PWD/classes --add-modules lib -m hello/example.Hello 
...
module lib
...
module hello
...
```

A runtime conflict due to the same package being present in multiple modules:

```sh
$ java --module-path $PWD/classes -m conflict.main/main.Main
Error occurred during initialization of boot layer
java.lang.LayerInstantiationException: Package lib in both module lib and module lib.conflict
```

