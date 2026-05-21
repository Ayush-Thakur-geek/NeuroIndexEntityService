package com.NeuroIndex.entity.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Message extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    private Integer sequence_number;

    private String role;

    @Column(columnDefinition = "TEXT")
    private String prompt;

    @Column(columnDefinition = "TEXT")
    private String rawJsonContent;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(unique = true)
    private String hash;

    private Instant instantTimestamp;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "message")
    private List<SemanticFragment> semanticFragments;

}
