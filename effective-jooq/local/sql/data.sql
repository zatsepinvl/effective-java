insert into LANGUAGE (ID, CD, DESCRIPTION)
values (1, 'en', 'English');

insert into LANGUAGE (ID, CD, DESCRIPTION)
values (2, 'de', 'Deutsch');

insert into LANGUAGE (ID, CD, DESCRIPTION)
values (3, 'fr', 'Français');

insert into LANGUAGE (ID, CD, DESCRIPTION)
values (4, 'pt', 'Português');


insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, YEAR_OF_BIRTH)
values (1, 'George', 'Orwell', date '1903-06-26', 1903);

insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, YEAR_OF_BIRTH)
values (2, 'Paulo', 'Coelho', date '1947-08-24', 1947);

insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, YEAR_OF_BIRTH)
values (1, 1, '1984', 1948, 1);


insert into BOOK (ID, AUTHOR_ID, TITLE, PUBLISHED_IN, LANGUAGE_ID)
values (2, 1, 'Animal Farm', 1945, 1);

insert into BOOK (ID, AUTHOR_ID, TITLE, PUBLISHED_IN, LANGUAGE_ID)
values (3, 2, 'O Alquimista', 1988, 4);

insert into BOOK (ID, AUTHOR_ID, TITLE, PUBLISHED_IN, LANGUAGE_ID)
values (4, 2, 'Brida', 1990, 2);


insert into BOOK_STORE
values ('Orell Füssli');

insert into BOOK_STORE
values ('Ex Libris');

insert into BOOK_STORE
values ('Buchhandlung im Volkshaus');


insert into BOOK_TO_BOOK_STORE
values ('Orell Füssli', 1, 10);

insert into BOOK_TO_BOOK_STORE
values ('Orell Füssli', 2, 10);

insert into BOOK_TO_BOOK_STORE
values ('Orell Füssli', 3, 10);

insert into BOOK_TO_BOOK_STORE
values ('Ex Libris', 1, 1);

insert into BOOK_TO_BOOK_STORE
values ('Ex Libris', 3, 2);

insert into BOOK_TO_BOOK_STORE
values ('Buchhandlung im Volkshaus', 3, 1);