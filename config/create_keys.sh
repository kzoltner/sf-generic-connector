#!/bin/bash

echo "Create private-key.pem"
openssl ecparam -name prime256v1 -genkey -noout -out private-key.pem

echo "Create public-key.pem"
openssl ec -in private-key.pem -pubout -out public-key.pem

echo "Create certificate"
openssl req -new -x509 -key private-key.pem -out cert.pem -days 360 -subj "/C=DE/ST=RLP/L=Kaiserslautern/O=Technologie-Initiative SmartFactory KL e. V./OU=Dataspace Connectors Squad/CN=smartfactory.de"

echo "Done"