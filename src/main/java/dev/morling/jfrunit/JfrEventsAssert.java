/**
 *  Copyright 2020 The JfrUnit authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package dev.morling.jfrunit;

import java.time.Duration;
import java.util.Map.Entry;

import org.assertj.core.api.AbstractAssert;

import jdk.jfr.consumer.RecordedEvent;

public class JfrEventsAssert extends AbstractAssert<JfrEventsAssert, JfrEvents> {

  // 2 - Write a constructor to build your assertion class with the object you want make assertions on.
  public JfrEventsAssert(JfrEvents actual) {
    super(actual, JfrEventsAssert.class);
  }

  // 3 - A fluent entry point to your specific assertion class, use it with static import.
  public static JfrEventsAssert assertThat(JfrEvents actual) {
    return new JfrEventsAssert(actual);
  }

  public JfrEventsAssert contains(ExpectedEvent event) {
      isNotNull();

      boolean found = false;
      for (RecordedEvent recordedEvent : actual.getEvents()) {
        if (recordedEvent.getEventType().getName().equals(event.getName())) {

            if (!event.getProps().isEmpty()) {
                for (Entry<String, Object> prop : event.getProps().entrySet()) {
                    if (recordedEvent.hasField(prop.getKey())) {
                        if (prop.getValue() instanceof Duration) {
                            if (!recordedEvent.getDuration(prop.getKey()).equals(prop.getValue())) {
                            }
                            else {
                                found = true;
                                break;
                            }
                        }
                    }

                }
            }
            else {
                found = true;
                break;
            }

        }
      }

      if (!found) {
          failWithMessage("No JFR event of type <%s> and with attributes <%s>", event.getName(), event.getProps());
      }

      return this;
  }

  public static ExpectedEvent event(String name) {
      return new ExpectedEvent(name);
  }
}