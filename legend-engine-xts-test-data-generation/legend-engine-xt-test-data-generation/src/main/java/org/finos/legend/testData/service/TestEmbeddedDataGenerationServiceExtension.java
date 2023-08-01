//  Copyright 2023 Goldman Sachs
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.

package org.finos.legend.testData.service;

import org.eclipse.collections.api.block.function.Function0;
import org.finos.legend.engine.language.pure.compiler.toPureGraph.PureModel;
import org.finos.legend.engine.protocol.pure.v1.extension.ProtocolSubTypeInfo;
import org.finos.legend.engine.protocol.pure.v1.model.data.EmbeddedData;
import org.finos.legend.testData.model.TestEmbeddedDataGenerationInput;

import java.util.Collections;
import java.util.List;

public interface TestEmbeddedDataGenerationServiceExtension
{
    default List<EmbeddedData> generateTestEmbeddedData(TestEmbeddedDataGenerationInput input, PureModel pureModel)
    {
        return Collections.emptyList();
    }

    default List<Function0<List<ProtocolSubTypeInfo<?>>>> getExtraProtocolSubTypeInfoCollectors()
    {
        return Collections.emptyList();
    }
}
