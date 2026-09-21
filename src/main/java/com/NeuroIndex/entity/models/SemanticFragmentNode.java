package com.NeuroIndex.entity.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "semantic_fragment_node",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_fragment_node",
                        columnNames = {"semantic_fragment_id", "semantic_node_id"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_fragment_node_fragment",
                        columnList = "semantic_fragment_id"
                ),
                @Index(
                        name = "idx_fragment_node_node",
                        columnList = "semantic_node_id"
                )
        }
)
public class SemanticFragmentNode extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "semantic_fragment_id",
            nullable = false
    )
    @ToString.Exclude
    private SemanticFragment fragment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "semantic_node_id",
            nullable = false
    )
    @ToString.Exclude
    private SemanticNode node;

    /**
     * Relevance of this phrase node inside this fragment.
     * This could initially be BM25 score, keyword mass, or 1.0.
     */
    @Builder.Default
    @Column(nullable = false)
    private Float weight = 1.0f;
}
