create table Hotel (
    Id bigserial primary key,
    Name TEXT NOT NULL,
    Address text not null,
    City text not null,
    Country text not null,
    Registered timestamp not null
);

create table Room (
    Id bigserial primary key,
    HotelId bigint references hotel (id),
    Number int not null,
    PhoneNumber text not null,
    Floor int not null,
    NumberOfGuests int not null,
    Registered timestamp not null
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



