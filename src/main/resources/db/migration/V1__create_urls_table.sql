CREATE TABLE tb_url (
                        id BIGSERIAL PRIMARY KEY,
                        original_url VARCHAR(2048) NOT NULL,
                        short_code VARCHAR(20) NOT NULL,
                        created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                        CONSTRAINT uk_short_code UNIQUE (short_code)
);

CREATE INDEX idx_short_code ON tb_url(short_code);