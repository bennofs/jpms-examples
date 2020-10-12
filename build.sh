#!/usr/bin/env bash
set -euo pipefail

MODULES="hello hello.conflict lib lib.conflict lib.main conflict.uselib conflict.uselibconflict conflict.main apinottransitive apinottransitive.main clone.main"
# build modules
for module in $MODULES; do
    mkdir -p classes/$module
    find $module -name "*.java" | xargs javac --module-path classes/ -d classes/$module
done

# build non-modular libs
find conflict.uselib.clone -name '*.java' | xargs javac -cp classes/lib -d classes/conflict.uselib.clone

# cleanup stale class files
comm -23 \
    <(find classes -name '*.class' | sort) \
    <(find . -name '*.java' | sed -e 's@^./@classes/@;s@.java$@.class@' | sort) \
  | xargs rm -f
