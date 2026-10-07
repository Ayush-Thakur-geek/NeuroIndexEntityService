package com.NeuroIndex.entity.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "semantic_node",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_semantic_node_user_label",
                        columnNames = {"user_id", "label"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_semantic_node_user",
                        columnList = "user_id"
                ),
                @Index(
                        name = "idx_semantic_node_user_label",
                        columnList = "user_id, label"
                )
        }
)
public class SemanticNode extends BaseModel {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "label" ,nullable = false, length = 500)
    private String label;

    /**
     * Keep this as TEXT for now if the centroid is stored as JSON.
     * You can migrate this to pgvector later.
     */
    @Column(columnDefinition = "TEXT")
    private String centroid;

    @Builder.Default
    @Column(name = "fragment_count", nullable = false)
    private Integer fragmentCount = 0;

    @Builder.Default
    @Column(name = "avg_similarity")
    private Float avgSimilarity = 0.0f;

    @Builder.Default
    @Column(name = "is_stable", nullable = false)
    private Boolean isStable = true;

    @OneToMany(
            mappedBy = "node",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    @ToString.Exclude
    private Set<SemanticFragmentNode> fragmentMappings = new HashSet<>();

    public void addFragmentMapping(SemanticFragmentNode mapping) {
        fragmentMappings.add(mapping);
        mapping.setNode(this);
    }

    public void removeFragmentMapping(SemanticFragmentNode mapping) {
        fragmentMappings.remove(mapping);
        mapping.setNode(null);
    }
}
