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
@Table(name = "semantic_node")
public class SemanticNode extends BaseModel{
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "semanticNode")
    private List<SemanticFragment> semanticFragments;

    private String label;

    @Column(columnDefinition = "TEXT")
    private String centroid;

    private Integer fragmentCount;

    private Float avgSimilarity;

    private Boolean isStable;
}
