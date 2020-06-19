create table Hotel (
    Id bigserial primary key,
    Name TEXT not null,
    Address text not null,
    City text not null,
    Country text not null,
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

create table Room (
    Id bigserial primary key,
    HotelId bigint references hotel (id),
    Number int not null,
    PhoneNumber text not null,
    Floor int not null,
    Registered timestamp not null,
    Updated timestamp not null,
    RoomTypeId bigint references roomType (id)
);


create table HotelAmenity (
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
);

create table RoomAmenity (
    Id bigserial primary key,
    RoomTypeId bigint references roomType (id),
    Name TEXT not null,
    Description TEXT not null,
    Pricing TEXT not null,
    Registered timestamp not null,
    Updated timestamp not null
);

create table Booking (
    Id bigserial primary key,
    UserId bigint references "User" (id),
    CheckInDate date not null,
    CheckOutDate date not null,
    HotelId bigint references hotel (id),
    RoomId bigint references room (id),
    Registered timestamp not null,
    Updated timestamp not null
);

