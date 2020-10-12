# Examples for the Java Platform Module System (JPMS)

This is some example code to test features of the java module system.

## Modules

* `hello`: simple module with main class `example.Hello`
* `hello.conflict`: copy of `hello`, with different module name
* `lib`: simple library module, providing class `lib.Example`
* `lib.main`: module with main class depending on `lib` module
* `lib.reflectmain`: main class that reflectively loads `lib.Example`
* `lib.cpmain`: non-modular main class that is compiled against `lib.Example` on the classpath
* `libconflict`: copy of `lib` with different module name
* `conflict.uselib`: library that requires `lib`
* `conflict.main`: module with main class requiring both `conflict.uselib` and `libconflict`

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

A runtime conflict due to the same package being present in multiple modules
(each `ModuleLayer`, including the boot module layer, enforces that a package is defined in at most one module):

```sh
$ java --module-path $PWD/classes -m conflict.main/main.Main
Error occurred during initialization of boot layer
java.lang.LayerInstantiationException: Package lib in both module lib and module libconflict
```

Non-modular applications can access modules in two different ways.
First, we can place the module on the classpath (then the `module-info.class` is ignored and the classes just get added to the unnamed module):

```sh
# modules can be placed on the classpath (then their module-info.class is ignored)
$ java -classpath classes/lib.cpmain:classes/lib main.Main
value: 4
classloader equal: true
main module: unnamed module @198e2867
lib module: unnamed module @198e2867
```

The second way is to explictly or implictly load the module:

```sh
# explictly load the module
$ java -classpath classes/lib.cpmain --module-path classes/ --add-modules lib main.Main
value: 4
classloader equal: true
main module: unnamed module @90f6bfd
lib module: module lib

# load the module by way of transitive requirements (conflict.uselib requires lib)
$ java -classpath classes/lib.cpmain --module-path classes/ --add-modules conflict.uselib main.
Main
value: 4
classloader equal: true
main module: unnamed module @90f6bfd
lib module: module lib
```

If both are combined, the module takes precendence over the classpath:

```sh
# lib.Example from libconflict is used, even though classes/lib is also on the classpath
$ java -classpath classes/lib.cpmain:classes/lib --module-path classes/ --add-modules libconfli
ct main.Main
value: 1
classloader equal: true
main module: unnamed module @4157f54e
lib module: module libconflict
```

