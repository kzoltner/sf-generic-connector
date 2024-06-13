# Generic Connector
This project contains a simple EDC Connector based on v0.3.1. 
This was created using a lot of trial and error and is by no means complete or right. It just worked for us.

# How to run a single connector
## Prepare
Before starting up, you need to use `create_keys.sh` to create a set of `.pem` files within the config folder.

## Run using docker
This project is aimed at being used as a container. 
So first, build the docker container:

```
docker build -t sf-generic-connector .
```

To run it, you need to mount the config folder as a volume into the container and use ports as specified within the properties file.

```
docker run -p 5011-5019:5011-5019 -it -v ./config/:/app/config/:ro sf-generic-connector
```

To check, if the connector is up and running, use the health endpoint:

```
curl -f http://localhost:5011/api/check/health
```

# How to create a dataspace
In order to create a dataspace you will need multiple connectors. 
Additionaly, you will need a few more components, which are not included in this repository:

 - DID-Components
 - Some sort of central registry for connectors to find each other