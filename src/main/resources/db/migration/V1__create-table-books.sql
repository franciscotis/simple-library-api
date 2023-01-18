create table books(
      id bigint not null auto_increment,
      title varchar(100) not null,
      author varchar(100) not null,
      pages int not null,
      isbn varchar(100) not null,
      finished tinyint not null,
      rate float,

      primary key(id)
);