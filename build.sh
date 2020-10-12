#!/usr/bin/env bash
set -euo pipefail

MODULES="hello hello.conflict lib libconflict lib.main lib.reflectmain conflict.uselib conflict.main apinottransitive apinottransitive.main"
# build modules
for module in $MODULES; do
    mkdir -p classes/$module
    find $module -name "*.java" | xargs javac --module-path classes/ -d classes/$module
done

# build non-modular main
mkdir -p classes/lib.cpmain
find lib.cpmain -name '*.java' | xargs javac -cp classes/lib -d classes/lib.cpmain

# cleanup stale class files
comm -23 \
    <(find classes -name '*.class' | sort) \
    <(find . -name '*.java' | sed -e 's@^./@classes/@;s@.java$@.class@' | sort) \
  | xargs rm -f
