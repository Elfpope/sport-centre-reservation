insert into tennis_court (id, name)
values (1, 'Hard Court'),
       (2, 'Clay Court'),
       (3, 'Grass Court');

insert into player (id, name)
values (1, 'Anna'),
       (2, 'Jerry'),
       (3, 'Karen');

insert into reservation (id, date, player_id, tennis_court_id)
values (1, '2020-12-30', 1, 3),
       (2, '2020-12-30', 2, 3),
       (3, '2020-12-30', 3, 3);

