    drop table if exists reservation CASCADE;
    drop table if exists tennis_court CASCADE;
    drop table if exists player CASCADE; 

    create table player (
       id bigint generated by default as identity,
        name varchar(255),
        primary key (id)
    );

    create table tennis_court (
       id bigint generated by default as identity,
        name varchar(255),
        primary key (id)
    );

    create table reservation (
       id bigint generated by default as identity,
        date timestamp,
        player_id bigint,
        tennis_court_id bigint,
        primary key (id)
    );

    alter table reservation
       add constraint reservation_player_fk
       foreign key (player_id)
       references player;

    alter table reservation
       add constraint reservation_tennis_court_fk
       foreign key (tennis_court_id)
       references tennis_court;
