/*
 * Copyright (c) 2017 Falko Schumann
 * Released under the terms of the MIT License.
 */

package de.muspellheim.eventbus;

import org.junit.*;

import java.util.*;
import java.util.function.*;

import static org.junit.Assert.*;

public class EventBusTest {

    private List<Object> events = new ArrayList<>();

    private void consume(Object event) {
        events.add(event);
    }

    @Test
    public void testSubscribePublish() {
        EventBus bus = EventBus.getDefault();
        bus.subscribe(String.class, this::consume);

        bus.publish("Foo");
        bus.publish("Bar");

        assertEquals(Arrays.asList("Foo", "Bar"), events);
    }

    @Test(expected = NullPointerException.class)
    public void testSubscribe_EventTypeNull() {
        EventBus bus = EventBus.getDefault();
        bus.subscribe(null, this::consume);
    }

    @Test(expected = NullPointerException.class)
    public void testSubscribe_ConsumerNull() {
        EventBus bus = EventBus.getDefault();
        bus.subscribe(String.class, null);
    }

    @Test(expected = NullPointerException.class)
    public void testPublish_EventNull() {
        EventBus bus = EventBus.getDefault();
        bus.subscribe(String.class, this::consume);

        bus.publish(null);
    }

    @Test
    public void testSubscribeUnscribe() {
        EventBus bus = EventBus.getDefault();
        Consumer<String> consumer = this::consume;
        bus.subscribe(String.class, consumer);

        bus.publish("Foo");
        bus.unsubscribe(consumer);
        bus.publish("Bar");

        assertEquals(Arrays.asList("Foo"), events);
    }

    @Test(expected = NullPointerException.class)
    public void testUnsubscribe_ConsumerNull() {
        EventBus bus = EventBus.getDefault();
        bus.unsubscribe(null);
    }

}
