# NeuroIndexEntityService

Shared library of JPA entities, enums, and constants for the NeuroIndex services. It is not a runnable service; [NeuroIndexParserService](https://github.com/Ayush-Thakur-geek/NeuroIndexParserService) depends on it.

## Database diagram

```mermaid
erDiagram
    USERS ||--o{ L_LM : owns
    L_LM ||--o{ AFFILIATED_EMAIL : has
    AFFILIATED_EMAIL ||--o{ CONVERSATION : has
    CONVERSATION ||--o{ MESSAGE : contains
    MESSAGE ||--o{ SEMANTIC_FRAGMENTS : "split into"
    SEMANTIC_FRAGMENTS ||--o{ SEMANTIC_FRAGMENT_KEYWORDS : has
    SEMANTIC_FRAGMENTS ||--o{ SEMANTIC_FRAGMENT_NODE : "linked via"
    SEMANTIC_NODE ||--o{ SEMANTIC_FRAGMENT_NODE : "linked via"
    SEMANTIC_NODE ||--o{ SEMANTIC_EDGE : "source"
    SEMANTIC_NODE ||--o{ SEMANTIC_EDGE : "target"

    USERS {
        bigint id PK
        varchar username
        varchar email
        varchar password
    }
    L_LM {
        bigint id PK
        bigint user_id FK
        varchar provider "CHAT_GPT, CLAUDE, GEMINI"
    }
    AFFILIATED_EMAIL {
        bigint id PK
        bigint llm_id FK
        varchar uuid
        varchar email
        boolean is_pro
    }
    CONVERSATION {
        bigint id PK
        bigint affiliated_email_id FK
        varchar title
        varchar url
        varchar hash UK
    }
    MESSAGE {
        bigint id PK
        bigint conversation_id FK
        int sequence_number
        varchar role
        text raw_json_content
        jsonb content "list of SemanticUnit"
        varchar hash
        timestamp instant_timestamp
    }
    SEMANTIC_FRAGMENTS {
        bigint id PK
        bigint message_id FK
        bigint user_id
        int fragment_order
        text text
        vector_768 embedding
        float confidence_score
        varchar hash UK
    }
    SEMANTIC_FRAGMENT_KEYWORDS {
        bigint semantic_fragment_id FK
        varchar keyword
    }
    SEMANTIC_FRAGMENT_NODE {
        bigint id PK
        bigint semantic_fragment_id FK
        bigint semantic_node_id FK
        float weight
    }
    SEMANTIC_NODE {
        bigint id PK
        bigint user_id
        varchar label "unique per user"
        text centroid
        int fragment_count
        float avg_similarity
        boolean is_stable
    }
    SEMANTIC_EDGE {
        bigint id PK
        bigint source_semantic_node_id FK
        bigint target_semantic_node_id FK
        bigint cooccurrence_count
        int shared_keyword_count
        float shared_keyword_idf
        float npmi
        float fused_weight
        varchar relation_kind
    }
```

**Reading the diagram**

- Ownership chain: `users → l_lm → affiliated_email → conversation → message → semantic_fragments`.
- `semantic_fragment_node` is the join table that makes fragments and nodes many-to-many. `weight` is the node's relevance within the fragment, and (`semantic_fragment_id`, `semantic_node_id`) is unique.
- `semantic_edge` links two `semantic_node` rows and carries the edge weights used for graph traversal.
- All tables also have `created_date` and `last_modified_date` (from `BaseModel`). Some columns are omitted here for readability; see [`schema.dbml`](schema.dbml) for the complete schema.

To get an interactive version, paste [`schema.dbml`](schema.dbml) into [dbdiagram.io](https://dbdiagram.io).