create table Hotel (
    Id bigserial primary key,
    Name TEXT not null,
    Address text not null,
    City text not null,
    Country text not null,
    Registered timestamp not null,
    Updated timestamp not null
);

create table Room (
    Id bigserial primary key,
    HotelId bigint references hotel (id),
    Number int not null,
    PhoneNumber text not null,
    Floor int not null,
    NumberOfGuests int not null,
    Registered timestamp not null,
    Updated timestamp not null
);

create table RoomType (
    Id bigserial primary key,
    HotelId bigint references hotel (id),
    Name TEXT NOT NULL,
    Description text not null,
    Capacity int not null,
    Registered timestamp not null,
    Updated timestamp not null
);

create table HotelAmenities (
    Id bigserial primary key,
    HotelId bigint references hotel (id),
    Name TEXT not null,
    Description TEXT not null,
    Pricing TEXT not null,
    Registered timestamp not null,
    Updated timestamp not null
);

create table "User" (
    Id bigserial primary key,
    FirstName TEXT not null,
    LastName TEXT not null,
    Birthday DATE not null,
    Email TEXT not null,
    Registered timestamp not null,
    Updated timestamp not null
)

