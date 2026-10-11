package com.NeuroIndex.entity.models;

import com.NeuroIndex.entity.typeConversions.PGvectorType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "fragment_order")
    private Integer fragmentOrder;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(columnDefinition = "vector(768)")
    @Type(PGvectorType.class)
    private float[] embedding;

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

    @Column(name = "noun_phrase")
    private List<String> nounPhrases;

    @OneToMany(
            mappedBy = "fragment",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    @ToString.Exclude
    private Set<SemanticFragmentNode> nodeMappings = new HashSet<>();

    public void addNodeMapping(SemanticFragmentNode mapping) {
        nodeMappings.add(mapping);
        mapping.setFragment(this);
    }

    public void removeNodeMapping(SemanticFragmentNode mapping) {
        nodeMappings.remove(mapping);
        mapping.setFragment(null);
    }
}
