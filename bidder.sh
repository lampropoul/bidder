#!/bin/bash

usage() {
    echo
    echo "Usage:    ./bidder -p <port>  Specify hosts' port in which the web application container will be exposed."
    echo
}

wrong_format() {
    echo
    echo "Error: Port number is not acceptable."
    echo
}

if test "$#" -ne 2 || test "$1" != "-p"; then
    usage
    exit 0
fi

regex='^[0-9]+$'
if ! [[ $2 =~ $regex ]] || test "$2" -gt 65535; then
    wrong_format
    exit 1
fi

echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
echo "Starting docker container..."
echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
docker run -it --rm --name bidder-container -v "$PWD":/usr/src/mymaven -w /usr/src/mymaven -p "$2":8080 openjdk:11 ./mvnw test spring-boot:run
