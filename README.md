[![Build Status](https://travis-ci.org/falkoschumann/java-eventbus.svg?branch=master)](https://travis-ci.org/falkoschumann/java-eventbus)
[![GitHub release](https://img.shields.io/github/release/falkoschumann/java-eventbus.svg)]()


Eventbus
========

Subscribe and publish events. Events are published in channels distinguished by
event type. Channels can be grouped using an event type hierarchy.

You can use the default event bus instance, which is a singleton or you can
create one or multiple instances.

Installation
------------

### Gradle

Add the the repository _jcenter_ to your `build.gradle`

    repositories {
        jcenter()
    }

and add the dependency

    compile 'de.muspellheim:eventbus:1.1.0'


### Maven

Add the the repository _jcenter_ to your `pom.xml`
    
    <repositories>
        <repository>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            <id>central</id>
            <name>bintray</name>
            <url>http://jcenter.bintray.com</url>
        </repository>
    </repositories>

and add the dependency

    <dependencies>
        <dependency>
            <groupId>de.muspellheim</groupId>
            <artifactId>eventbus</artifactId>
            <version>1.1.0</version>
        </dependency>
    </dependencies>


### Download

You can download JARs with binary, source and JavaDoc from GitHub under
https://github.com/falkoschumann/java-eventbus/releases.


Usage
-----

You can create an individual event bus by constructor or use the default event
bus as singleton.

    EventBus bus = EventBus.getDefault();

Subscribe to an event by type. You can use every class as event type.
Subscribing a super type works as subcribe to any sub type. 

    bus.subscribe(String.class, s -> System.out.println("String: " + s));
    bus.subscribe(Number.class, n -> System.out.println("Number: " + n));

Let's publish some events.

    bus.publish("Foo"); // String
    bus.publish(42);    // int
    bus.publish(2.718); // double

The example output:

    String: Foo
    Number: 42
    Number: 2.718


Contributing
------------

### Publish artifacts to Bintray

1.  Create file `gradle.properties` and set properties `bintrayUser` and
    `bintrayApiKey`.
2.  Run `./gradlew uploadArchives`.
3.  Check uploaded files and publish.

### Publish distribution to GitHub

1.  Run `./gradle distZip`.
2.  Upload created ZIP to GitHub releases.
