package com.NeuroIndex.entity.models;

import com.NeuroIndex.entity.enums.NodeRelations;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "semantic_edge")
public class SemanticEdge extends BaseModel {

    private Long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_semantic_node_id", nullable = false)
    private SemanticNode sourceNode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_semantic_node_id", nullable = false)
    private SemanticNode targetNode;

    @Column(nullable = false)
    private long cooccurrenceCount;

    @Column(nullable = false)
    private int sharedKeywordCount;

    @Column(nullable = false)
    private float sharedKeywordIdf;

    @Column(nullable = false)
    private int learnedConfirmations;

    private Float npmi;
    private Float overlapScore;
    private Float fusedWeight;
    private Long weightsEpoch;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation_kind", insertable = false, updatable = false)
    private NodeRelations relationKind;
}
