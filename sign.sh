#!/bin/sh
cd app/build/libs/
auto=0
for jarName in *.jar;do
    echo "$jarName"
    jarsigner -verbose -storetype pkcs12 -keystore ../../../libihatelogs.p12 -signedjar "$jarName" "$jarName" libihatelogs -tsa http://timestamp.sectigo.com
    jarsigner -verify -verbose -certs "$jarName"
done
