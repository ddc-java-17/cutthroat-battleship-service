create sequence fleet_seq start with 1 increment by 50;
create sequence game_seq start with 1 increment by 50;
create sequence ship_seq start with 1 increment by 50;
create sequence shot_seq start with 1 increment by 50;
create sequence user_game_seq start with 1 increment by 50;
create sequence user_seq start with 1 increment by 50;
create table fleet
(
    fleet_id     bigint not null,
    game_id      bigint not null,
    user_game_id bigint not null unique,
    primary key (fleet_id)
);
create table game
(
    player_count integer                     not null,
    created      timestamp(6) with time zone not null,
    game_id      bigint                      not null,
    external_key UUID                        not null unique,
    board_pool   varchar(255),
    primary key (game_id)
);
create table ship
(
    x_coord  integer not null,
    y_coord  integer not null,
    fleet_id bigint  not null,
    ship_id  bigint  not null,
    primary key (ship_id)
);
create table shot
(
    x_coord                integer                     not null,
    y_coord                integer                     not null,
    from_user_user_game_id bigint                      not null,
    game_id                bigint                      not null,
    shot_id                bigint                      not null,
    timestamp              timestamp(6) with time zone not null,
    to_user_user_game_id   bigint                      not null,
    primary key (shot_id)
);
create table user
(
    created         timestamp(6) with time zone not null,
    modified        timestamp(6) with time zone not null,
    user_profile_id bigint                      not null,
    external_key    UUID                        not null unique,
    oauth_key       varchar(30)                 not null unique,
    display_name    varchar(50)                 not null unique,
    primary key (user_profile_id)
);
create table user_game
(
    game_game_id bigint not null,
    user_game_id bigint not null,
    user_id      bigint not null,
    primary key (user_game_id)
);
create index IDXhhhv7bckqbl0ewc0qltst2dy5 on fleet (fleet_id, user_game_id);
create index IDXigpaayljh3v58b1iqb9aaer6n on game (game_id);
create index IDXide5uhl1u50ceppbply3659ju on ship (ship_id);
create index IDXcfvjwbji3vjuetj741civphy0 on shot (shot_id, timestamp);
create index IDXqq60d6id4r5fpif66xhe48ibe on user_game (user_game_id, user_id);
alter table if exists fleet
    add constraint FKjqdr2h16g6gvo39028oi7as7d foreign key (game_id) references game;
alter table if exists fleet
    add constraint FKb0lapaglm1kkn2mo4igs38ec7 foreign key (user_game_id) references user_game;
alter table if exists ship
    add constraint FK15484itub4s0m69etdg9jn0md foreign key (fleet_id) references fleet;
alter table if exists shot
    add constraint FKjtih6vj9353g8ybig3wakbfmf foreign key (from_user_user_game_id) references user_game;
alter table if exists shot
    add constraint FK6b3paiqh4tclttwrcs0tpqgj3 foreign key (game_id) references game;
alter table if exists shot
    add constraint FKb11x20fwryfusujc0rktaclww foreign key (to_user_user_game_id) references user_game;
alter table if exists user_game
    add constraint FK90teninibasptpfjckfb0avu1 foreign key (game_game_id) references game;
alter table if exists user_game
    add constraint FK119tttdkgsb3r72i6l557a6f5 foreign key (user_id) references user;
