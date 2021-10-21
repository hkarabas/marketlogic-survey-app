# Architecture

The Survey application has a microservice architecture.
It is written using the [Eventuate Client Framework for Java](http://eventuate.io/docs/java/eventuate-client-framework-for-java.html), which provides an [event sourcing based programming model](http://eventuate.io/whyeventsourcing.html).
The following diagram shows the Survey List application architecture:


* Spring service - a Java and Spring Boot-based service that has a HATEOAS-style REST API for creating, updating and querying survey app's list items.
It uses Eventuate to persist aggregates using event sourcing.
* Survey view service - a Java and Spring Boot-based service that provides a REST API for querying Surveys.
It implements a [Command Query Responsibility Segregation (CQRS)](http://microservices.io/patterns/data/cqrs.html) view of Surveys using MySQL.
MySQL is kept up to date by subscribing to events produced by the Survey service.
* MySQL database - stores the CQRS view of Survey list items.

Note: for simplicity, the Survey list application can be deployed as a monolithic application.

Note: you do not need to install Gradle since it will be downloaded automatically.
You just need to have Java 8 installed.

# Building and running the application

The steps for building both versions of the application are identical.

## Building and running using Eventuate Local

First, build the application

```
./gradlew assemble -P eventuateDriver=local
```

Next, launch the services using [Docker Compose](https://docs.docker.com/compose/):

```
export DOCKER_HOST_IP=...
./gradlew  mysqlbinlogComposeBuild
./gradlew mysqlbinlogComposeUp
```

Where `database-mode` is one of:

* `mysqlbinlog` - use MySQL with Binlog-based event publishing


Note: You need to set `DOCKER_HOST_IP` before running Docker Compose.
This must be an IP address or resolvable hostname.
It cannot be `localhost`.
See this [guide to setting `DOCKER_HOST_IP`](http://eventuate.io/docs/usingdocker.html) for more information.



# Using the Eventuate Local console

You can also use the Eventuate Local console to view aggregates and watch the stream of events.
Visit the URL `http://${DOCKER_HOST_IP}:8085`



