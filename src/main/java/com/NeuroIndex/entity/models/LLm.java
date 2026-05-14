package com.NeuroIndex.entity.models;

import com.NeuroIndex.entity.enums.LlmTypes;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LLm extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LlmTypes provider;

    @OneToMany(mappedBy = "llm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AffiliatedEmail> affiliatedEmails;
}
