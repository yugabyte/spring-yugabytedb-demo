
# Getting Started with Spring Boot Application  and Distributed SQL  with YugabyteDB

This app is a Spring Boot implementation backed by YugabyteDB SQL Query Language (YCQL). 

`spring-yugabytedb-demo` app uses the following components:

- Spring Data JPA
- YugabyteDB YSQL Cluster Aware Driver

App is a REST based application which exposes REST APIs for CRUD operations on a Customer domain, reveiew the following class `com.yugabyte.demo.springyugabytedbdemo.domain.customer`. 


# Environment Setup


## Step 1: Start the YugabyteDB cluster

You can do so using following command from YugabyteDB installation directory,


```
$ ./bin/yb-ctl destroy && ./bin/yb-ctl --rf 3 create --tserver_flags="cql_nodelist_refresh_interval_secs=10" --master_flags="tserver_unresponsive_timeout_ms=10000"
```

This will start a 3-node local cluster with replication factor (RF) 3. The flag cql_nodelist_refresh_interval_secs configures how often the drivers will get notified of cluster topology changes and the following flag tserver_unresponsive_timeout_ms is for the master to mark a node as dead after it stops responding (heartbeats) for 10 seconds.

Note: (Detailed installation instructions)[https://docs.yugabyte.com/latest/quick-start/install/#macos] for YugabyteDB on local workstation.

# Required Dependencies

Spring Data JPA provides repository support for YugabyteDB and we'll use Yugabyte YSQL driver for enabling cluster awareness in Spring Boot applications

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.yugabyte</groupId>
    <artifactId>ysql</artifactId>
    <version>42.2.7-yb-2</version>
</dependency>
```

# Build and Run the application

## Step 1: Build the spring boot application

To build the app, execute the following maven command from the project base directory:

```
$ ./mvnw clean pacakage -DskipTests
```

## Step 2: Start the application

you can do so by running the following command:

```
$ java -jar target/spring-yugabytedb-demo-0.0.1-SNAPSHOT.jar
```

# Working with REST endpoints

## Create a customer

You can create a customer as follows:
```
$ curl \
--data '{ "id": "customer1", "name": "Alice", "emailId": "alice@wonderland.com" }' \
  -v -X POST -H 'Content-Type:application/json' http://localhost:8080/customer/save
```

##  Retrieve the customer

```
$ curl http://localhost:8080/customers
```

