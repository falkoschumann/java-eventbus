[![Build Status](https://travis-ci.org/falkoschumann/java-eventbus.svg?branch=master)](https://travis-ci.org/falkoschumann/java-eventbus)
[![GitHub release](https://img.shields.io/github/release/falkoschumann/java-eventbus.svg)]()

Simple event bus implementation
===============================

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

    compile 'de.muspellheim:eventbus:1.0.0'


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
            <version>1.0.0</version>
        </dependency>
    </dependencies>


### Download

You can download JARs with binary, source and JavaDoc from GitHub under
https://github.com/falkoschumann/java-eventbus/releases.


Usage
-----

See tests in `src/test/java/de/muspellheim/eventbus/EventBusTest` for usage
examples.


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
