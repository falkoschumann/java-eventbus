/*
 * Copyright (c) 2017 Falko Schumann
 * Released under the terms of the MIT License.
 */

package de.muspellheim.eventbus;

import org.junit.*;

import java.util.*;
import java.util.function.*;

import static org.junit.Assert.*;

/**
 * Unit tests, integration tests, acceptance tests and examples.
 * <p>
 * Not enough stuff to separate it.
 */
public class EventBusTest {

    private List<String> stringEvents = new ArrayList<>();
    private List<Integer> integerEvents = new ArrayList<>();
    private List<Number> numberEvents = new ArrayList<>();
    private List<Number> doubleEvents = new ArrayList<>();

    private void consumeString(String event) {
        stringEvents.add(event);
    }

    private void consumeInt(int event) {
        integerEvents.add(event);
    }

    private void consumeNumber(Number event) {
        numberEvents.add(event);
    }

    private void consumeDouble(Number event) {
        doubleEvents.add(event);
    }

    @Test(expected = NullPointerException.class)
    public void subscribe_EventTypeNull() {
        EventBus bus = EventBus.getDefault();
        bus.subscribe(null, this::consumeString);
    }

    @Test(expected = NullPointerException.class)
    public void subscribe_SubscriberNull() {
        EventBus bus = EventBus.getDefault();
        bus.subscribe(String.class, null);
    }

    @Test(expected = NullPointerException.class)
    public void unsubscribe_EventTypeNull() {
        EventBus bus = EventBus.getDefault();
        bus.unsubscribe(null, this::consumeString);
    }

    @Test(expected = NullPointerException.class)
    public void unsubscribe_SubscriberNull() {
        EventBus bus = EventBus.getDefault();
        bus.unsubscribe(null);
    }

    @Test(expected = NullPointerException.class)
    public void publish_EventNull() {
        EventBus bus = EventBus.getDefault();

        bus.publish(null);
    }

    @Test
    public void publish() {
        EventBus bus = EventBus.getDefault();
        bus.subscribe(String.class, this::consumeString);
        bus.subscribe(Integer.class, this::consumeInt);

        bus.publish("Foo");
        bus.publish(42);
        bus.publish("Bar");

        assertEquals("string events", Arrays.asList("Foo", "Bar"), stringEvents);
        assertEquals("integer events", Arrays.asList(42), integerEvents);
    }

    @Test
    public void unsubscribe() {
        EventBus bus = EventBus.getDefault();
        Consumer<String> subscriber = this::consumeString;
        bus.subscribe(String.class, subscriber);

        bus.publish("Foo");
        bus.unsubscribe(subscriber);
        bus.publish("Bar");

        assertEquals(Arrays.asList("Foo"), stringEvents);
    }

    @Test
    public void eventTypeHierarchy() {
        EventBus bus = EventBus.getDefault();
        Consumer<Integer> intSubscriber = this::consumeInt;
        bus.subscribe(Integer.class, intSubscriber);
        Consumer<Number> numberSubscriber = this::consumeNumber;
        bus.subscribe(Number.class, numberSubscriber);
        Consumer<Number> doubleSubscriber = this::consumeDouble;
        bus.subscribe(Double.class, doubleSubscriber);

        bus.publish(0.815);
        bus.publish(42);
        bus.unsubscribe(Number.class, numberSubscriber);
        bus.unsubscribe(Number.class, doubleSubscriber);
        bus.publish(2.718);
        bus.unsubscribe(Integer.class, intSubscriber);
        bus.publish(7);

        assertEquals("integer events", Arrays.asList(42), integerEvents);
        assertEquals("number events", Arrays.asList(0.815, 42), numberEvents);
        assertEquals("double events", Arrays.asList(0.815), doubleEvents);
    }

}
