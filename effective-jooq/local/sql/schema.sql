create table LANGUAGE
(
    ID          numeric(7) not null,
    CD          char(2)    not null,
    DESCRIPTION varchar(50),
    primary key (ID)
);

create table AUTHOR
(
    ID            numeric(7)  not null,
    FIRST_NAME    varchar(50),
    LAST_NAME     varchar(50) not null,
    DATE_OF_BIRTH date,
    YEAR_OF_BIRTH numeric(7),
    DISTINGUISHED numeric(1),
    primary key (ID)
);

create table BOOK
(
    ID           numeric(7)   not null,
    AUTHOR_ID    numeric(7)   not null,
    TITLE        varchar(400) not null,
    PUBLISHED_IN numeric(7)   not null,
    LANGUAGE_ID  numeric(7)   not null,
    primary key (ID),
    constraint FK_BOOK_AUTHOR
        foreign key (AUTHOR_ID)
            references AUTHOR (ID),
    constraint FK_BOOK_LANGUAGE
        foreign key (LANGUAGE_ID)
            references LANGUAGE (ID)
);

create table BOOK_STORE
(
    NAME varchar(400) not null,
    unique (NAME)
);

create table BOOK_TO_BOOK_STORE
(
    NAME    varchar(400) not null,
    BOOK_ID int          not null,
    STOCK   int,
    primary key (NAME, BOOK_ID),
    constraint FK_B2BS_BOOK_STORE
        foreign key (NAME)
            references BOOK_STORE (NAME) on delete cascade,
    constraint FK_B2BS_BOOK
        foreign key (BOOK_ID)
            references BOOK (ID) on delete cascade
);