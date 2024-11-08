// Copyright 2020 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.protocol.pure.v1.model.valueSpecification.raw.packageableElement;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.finos.legend.engine.protocol.pure.v1.PureProtocolObjectMapperFactory;
import org.finos.legend.engine.protocol.pure.v1.model.SourceInformation;
import org.finos.legend.engine.protocol.pure.v1.model.type.GenericType;
import org.finos.legend.engine.protocol.pure.v1.model.type.PackageableType;
import org.finos.legend.engine.protocol.pure.v1.model.valueSpecification.ValueSpecification;
import org.finos.legend.engine.protocol.pure.v1.model.valueSpecification.ValueSpecificationVisitor;
import org.finos.legend.engine.protocol.pure.v1.model.valueSpecification.raw.One;

import java.io.IOException;

@JsonDeserialize(using = GenericTypeInstance.GenericTypeInstanceDeserializer.class)
public class GenericTypeInstance extends One
{
    protected static ObjectMapper om = PureProtocolObjectMapperFactory.getNewObjectMapper();

    public GenericType genericType;

    public GenericTypeInstance()
    {
    }

    public GenericTypeInstance(GenericType genericType)
    {
        this.genericType = genericType;
    }

    public GenericTypeInstance(String packageableType)
    {
        this.genericType = new GenericType(new PackageableType(packageableType));
    }

    @Override
    public <T> T accept(ValueSpecificationVisitor<T> visitor)
    {
        return visitor.visit(this);
    }

    public static class GenericTypeInstanceDeserializer extends JsonDeserializer<ValueSpecification>
    {
        @Override
        public ValueSpecification deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
        {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            JsonNode values = node.get("fullPath");
            ValueSpecification result;
            if (values != null)
            {
                result = new GenericTypeInstance(new GenericType(new PackageableType(values.asText())));
            }
            else
            {
                result = new GenericTypeInstance(om.treeToValue(node.get("genericType"), GenericType.class));
            }
            JsonNode sourceInformation = node.get("sourceInformation");
            if (sourceInformation != null)
            {
                result.sourceInformation = om.treeToValue(sourceInformation, SourceInformation.class);
            }
            return result;
        }
    }
}
