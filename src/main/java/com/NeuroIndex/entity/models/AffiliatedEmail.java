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
public class AffiliatedEmail extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "llm_id")
    private LLm llm;

    @Column(nullable = false)
    private String uuid;

    private String email;

    @Column(name = "is_pro")
    private boolean isPro;

    @Column(name = "conversation_doc_link")
    private String ConversationDocLink;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "affiliatedEmail")
    private List<Conversation> conversations;
}
