/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020 - 2021 The JfrUnit authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ${package};

import ${package}.model.*;
import org.moditect.jfrunit.Attribute;
import org.moditect.jfrunit.JfrEventType;
import java.util.Objects;

/**
 * ${event.description}
 */
public class ${event.name} extends JfrEventType {
	public static final ${event.name} INSTANCE = new ${event.name}();
	public static final String EVENT_NAME = "jdk.${event.name}";
	<#list event.attributes as attribute>
	public static final Attribute<${event.name}, ${attribute.objectType}> ${attribute.nameUpperCase} = new Attribute("${attribute.name}");
	</#list>

	public ${event.name}() {
		super(EVENT_NAME);
	}

	<#list event.attributes as attribute>
    public ${event.name} ${attribute.methodName}(${attribute.objectType} ${attribute.name}) {
		getPredicates().add(
			new org.moditect.jfrunit.JfrPredicate(
				input ->
                		matches(${attribute.name}, "${attribute.name}", input),
                "${attribute.name}",
                ${attribute.name}.toString()
			)
		);

		return this;
    }

    </#list>
}
