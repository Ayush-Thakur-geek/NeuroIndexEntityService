package com.NeuroIndex.entity.domainObjects;

import com.NeuroIndex.entity.enums.SemanticContentType;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SemanticUnit {
    private SemanticContentType semanticContentType;
    private JsonNode rawContent;
    private String extractedText;
    private Metadata metadata;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Metadata {
        private String language;
        private String artifactType;
    }
}
