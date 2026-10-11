# Db Design
![Schema Design](./src/main/resources/static/images/Neuro%20Index-2.png)

this db design was made on dbdiagram.io, use the below code to regenerate the design

```
Table users {

  id bigint [primary key]

  username varchar

  email varchar [unique]

  password varchar

  created_at timestamp

  last_modified_at timestamp
}

// ======================================================

Table llms {

  id bigint [primary key]

  user_id bigint

  provider varchar

  created_at timestamp

  last_modified_at timestamp
}

// ======================================================

Table affiliated_emails {

  id bigint [primary key]

  llm_id bigint

  email varchar

  uuid varchar

  is_pro boolean

  created_at timestamp

  last_modified_at timestamp
}

// ======================================================

Table conversations {

  id bigint [primary key]

  affiliated_email_id bigint

  title varchar

  url text

  hash varchar [unique]

  created_at timestamp

  last_modified_at timestamp
}

// ======================================================

Table messages {

  id bigint [primary key]

  conversation_id bigint

  sequence_number integer

  role varchar
  // USER
  // ASSISTANT
  // SYSTEM

  content text

  hash varchar [unique]

  instant_timestamp timestamp

  created_at timestamp

  last_modified_at timestamp
}

// ======================================================

Table semantic_nodes {

  id bigint [primary key]

  label varchar

  centroid vector(1024)

  fragment_count integer

  avg_similarity float

  is_stable boolean

  created_at timestamp

  last_modified_at timestamp
}

// ======================================================

Table semantic_fragments {

  id bigint [primary key]

  message_id bigint

  semantic_node_id bigint

  fragment_order integer

  text text

  embedding vector(1024)

  keywords text[]

  confidence_score float

  hash varchar [unique]

  created_at timestamp
}

// ======================================================

Table semantic_edges {

  id bigint [primary key]

  source_node_id bigint

  target_node_id bigint

  similarity_score float

  edge_type varchar
  // SEMANTIC
  // TEMPORAL
  // CO_OCCURRENCE

  created_at timestamp
}

// ======================================================

Table ingestion_jobs {

  id bigint [primary key]

  affiliated_email_id bigint

  status varchar
  // PENDING
  // PROCESSING
  // COMPLETED
  // FAILED

  total_conversations integer

  processed_conversations integer

  created_at timestamp

  completed_at timestamp
}

// ======================================================
// Relationships
// ======================================================

Ref: llms.user_id > users.id

Ref: affiliated_emails.llm_id > llms.id

Ref: conversations.affiliated_email_id > affiliated_emails.id

Ref: messages.conversation_id > conversations.id

Ref: semantic_fragments.message_id > messages.id

Ref: semantic_fragments.semantic_node_id > semantic_nodes.id

Ref: semantic_edges.source_node_id > semantic_nodes.id

Ref: semantic_edges.target_node_id > semantic_nodes.id

Ref: ingestion_jobs.affiliated_email_id > affiliated_emails.id
```