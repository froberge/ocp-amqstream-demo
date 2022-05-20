# Order-consumer Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

:warning:Currently, to be able to run in dev mode, a valid kafka bootstraps URL need to be specify in the application.xml file.

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```

To package the applicaiton to run with podman add
```
-Dquarkus.native.container-runtime=podman
```

To run in linux mode add ( needed if building in a MacOs environment )
```
-Dquarkus.native.container-build=true
```

> ./mvnw compile package -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=podman


It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/order-consumer-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- SmallRye Reactive Messaging - Kafka Connector ([guide](https://quarkus.io/guides/kafka-reactive-getting-started)): Connect to Kafka with Reactive Messaging

## Build/Run container image with podman

* Build conainfor jvm
    ```
        podman build -f src/main/docker/Dockerfile.jvm -t order-consumer .
    ```

* Run the container with podman
    ```
    podman run -i --rm -p 8080:8080 localhost/order-producer:[RELEASE]
    ```