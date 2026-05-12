package com.NeuroIndex.entity.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project_conversation_docs")
public class ProjectConversationDoc extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "affiliated_email_id")
    private AffiliatedEmail affiliatedEmail;

    private String title;

    @Column(name = "doc_link")
    private String docLink;
}