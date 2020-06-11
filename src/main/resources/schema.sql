CREATE TABLE Hotel (
    Id BIGSERIAL PRIMARY KEY,
    Name TEXT NOT NULL,
    Address TEXT NOT NULL,
    City TEXT NOT NULL,
    Country TEXT NOT NULL,
    Registered TIMESTAMP NOT NULL
);

CREATE TABLE Room (
    Id BIGSERIAL PRIMARY KEY,
    HotelId BIGINT REFERENCES hotel (id),
    Number INT NOT NULL,
    PhoneNumber TEXT NOT NULL,
    Floor INT NOT NULL,
    NumberOfGuests INT NOT NULL,
    Registered TIMESTAMP NOT NULL
);

