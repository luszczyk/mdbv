#!/bin/bash

mvn clean install; cd web; mvn cargo:redeploy; cd ..

