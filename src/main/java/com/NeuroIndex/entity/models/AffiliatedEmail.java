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

    @Column(name = "normal_conversation_doc_link")
    private String normalConversationDocLink;

    @OneToMany(mappedBy = "affiliatedEmail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectConversationDoc> projectConversationDocs;
}
