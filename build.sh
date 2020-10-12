#!/usr/bin/env bash
set -euo pipefail

rm -rf classes
MODULES="hello hello.conflict lib lib.conflict lib.main conflict.uselibconflict conflict.main"
for module in $MODULES; do
    mkdir -p classes/$module
    find $module -name "*.java" | xargs javac --module-path classes/ -d classes/$module
done
