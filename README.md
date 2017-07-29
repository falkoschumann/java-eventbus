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

TODO


Usage
-----

See tests in `src/test/java/de/muspellheim/eventbus/EventBusTest` for usage
examples.


Contributing
------------

### Publish to Bintray

1.  Create file `gradle.properties` and set properties `bintrayUser` and
    `bintrayApiKey`.
2.  Run `./gradlew uploadArchives`.
3.  Check uploaded files and publish.
