package com.NeuroIndex.entity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Vector;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
