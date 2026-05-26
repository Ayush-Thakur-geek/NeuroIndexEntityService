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
public class Conversation extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "affiliated_email_id")
    private AffiliatedEmail affiliatedEmail;

    private String title;

    private String url;

    @Column(unique = true)
    private String hash;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "conversation")
    private List<Message> messages;
}
