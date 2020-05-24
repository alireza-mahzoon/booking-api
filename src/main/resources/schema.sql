CREATE TABLE Hotel (
    Id BIGSERIAL PRIMARY KEY,
    Name TEXT NOT NULL,
    Address TEXT NOT NULL,
    City TEXT NOT NULL,
    Country TEXT NOT NULL,
    Registered TIMESTAMP NOT NULL
);