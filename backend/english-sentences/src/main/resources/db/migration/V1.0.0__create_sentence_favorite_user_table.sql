-- Create the sentences table
CREATE TABLE sentences (
    id BIGINT PRIMARY KEY,
    text_en TEXT,
    text_pl TEXT,
    timestamp TIMESTAMP
);

-- Create the foreign key constraint for the Sentence-Favorite relationship
ALTER TABLE favorite
ADD CONSTRAINT fk_sentence_id
FOREIGN KEY (sentence_id)
REFERENCES sentences(id);

-- Create the favorite table
CREATE TABLE favorite (
    id BIGINT PRIMARY KEY,
    timestamp TIMESTAMP,
    sentence_id BIGINT,
    user_id BIGINT
);

-- Create the user table
CREATE TABLE _user (
    id BIGINT PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255)
);
