#!/bin/bash

if [ ! -d ./logs ]; then
  mkdir -p ./logs;
fi

if [ ! -d ./tmp ]; then
  mkdir -p ./tmp;
fi

java -Dfile.encoding=UTF-8 -classpath ".:mongo-2.10.1.jar" Lemmatizer $@
