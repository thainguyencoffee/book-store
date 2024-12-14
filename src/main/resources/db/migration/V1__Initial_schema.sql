create table book
(
  id    bigserial    not null,
  title varchar(255) not null,
  isbn  varchar(13)  not null,
  constraint fk_book primary key (id)
);

create table copy
(
  id        bigserial   not null,
  code      varchar(50) not null,
  book_id   bigint      not null,
  available boolean     not null,
  constraint fk_copy primary key (id)
);
