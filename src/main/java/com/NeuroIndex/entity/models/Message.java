package com.NeuroIndex.entity.models;

import com.NeuroIndex.entity.domainObjects.SemanticUnit;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

//    @Column(columnDefinition = "TEXT")
//    private String prompt;

    @Column(columnDefinition = "TEXT")
    private String rawJsonContent;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<SemanticUnit> content;

    private String hash;

    private Instant instantTimestamp;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "message")
    private List<SemanticFragment> semanticFragments;

}
