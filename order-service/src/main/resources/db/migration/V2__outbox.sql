CREATE TABLE outbox_events (
 id UUID PRIMARY KEY,
 aggregate_id UUID NOT NULL,
 event_type VARCHAR(100) NOT NULL,
 payload TEXT NOT NULL,
 published BOOLEAN NOT NULL DEFAULT FALSE,
 created_at TIMESTAMPTZ NOT NULL
);
CREATE INDEX idx_outbox_unpublished ON outbox_events(published,created_at);