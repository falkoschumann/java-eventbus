/*
 * Copyright (c) 2017 Falko Schumann
 * Released under the terms of the MIT License.
 */

package de.muspellheim.eventbus;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class EventBus {

    private static EventBus INSTANCE = new EventBus();

    private final List<Consumer> consumers = new CopyOnWriteArrayList<>();

    public static EventBus getDefault() {
        return INSTANCE;
    }

    public <T> void subscribe(Class<T> eventType, Consumer<T> consumer) {
        Objects.requireNonNull(eventType, "eventType");
        Objects.requireNonNull(consumer, "consumer");
        consumers.add(consumer);
    }

    public void unsubscribe(Consumer<?> consumer) {
        Objects.requireNonNull(consumer, "consumer");
        consumers.remove(consumer);
    }

    public void publish(Object event) {
        Objects.requireNonNull(event, "event");
        consumers.forEach(consumer -> {
            try {
                consumer.accept(event);
            } catch (Exception e) {
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
            }
        });
    }

}
