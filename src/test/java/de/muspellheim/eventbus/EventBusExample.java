/*
 * Copyright (c) 2017 Falko Schumann
 * Released under the terms of the MIT License.
 */

package de.muspellheim.eventbus;

public class EventBusExample {

    public void usage() {
        EventBus bus = EventBus.getDefault();

        bus.subscribe(String.class, s -> System.out.println("String: " + s));
        bus.subscribe(Number.class, n -> System.out.println("Number: " + n));

        bus.publish("Foo");
        bus.publish(42);
        bus.publish(2.718);
    }

}
