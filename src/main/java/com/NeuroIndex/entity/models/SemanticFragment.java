package com.NeuroIndex.entity.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "semantic_fragments")
public class SemanticFragment extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;

    private Long userId;

    private Integer fragment_order;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(columnDefinition = "TEXT")
    private String embedding;

    private Float confidenceScore;

    @Column(unique = true)
    private String hash;

    @ElementCollection
    @CollectionTable(
            name = "semantic_fragment_keywords",
            joinColumns = @JoinColumn(name = "semantic_fragment_id")
    )
    @Column(name = "keyword")
    private List<String> keywords;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semantic_node_id")
    private SemanticNode semanticNode;
}
