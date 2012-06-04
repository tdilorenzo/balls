insert into Ball(id, description, ball_type) values (1,'Pigskin','FOOTBALL');
insert into Ball(id, description, ball_type) values (2,'For hoops','BASKETBALL');
insert into Ball(id, description, ball_type) values (3,'America''s pasttime', 'BASEBALL');
insert into Ball(id, description, ball_type) values (4,'Europe''s dumb sport','SOCCERBALL');

insert into Users(username, password, password_hash, display_name) values ('tdilorenzo', 'tdilorenzo', 'C1EA3542D7BB132FBC56F83267DA0B59', 'Todd');
insert into Users(username, password, password_hash, display_name) values ('lholmquist', 'lholmquist', '017436CEA5D87F7AF352014BEC3F2919', 'Luke');
insert into Users(username, password, password_hash, display_name) values ('dluck'     , 'dluck'     , 'D7933301EDFA591CB3D9D6D787FC204C', 'Dave');
insert into Users(username, password, password_hash, display_name) values ('jmahoney'  , 'jmahoney'  , '60510FD76DEFB81EB69D75973C38F80B', 'Jim');
insert into Users(username, password, password_hash, display_name) values ('user1'     , 'user1'     , '24C9E15E52AFC47C225B757E7BEE1F9D', 'User 1');
insert into Users(username, password, password_hash, display_name) values ('user2'     , 'user2'     , '7E58D63B60197CEB55A1C487989A3720', 'User 2');

insert into Roles(role_name, role_group) values ('BALLGAME_USER', 'default');
insert into Roles(role_name, role_group) values ('BALLGAME_ADMIN', 'admin');

insert into User_Roles(username, role_name) values ('tdilorenzo', 'BALLGAME_USER');
insert into User_Roles(username, role_name) values ('tdilorenzo', 'BALLGAME_ADMIN');
insert into User_Roles(username, role_name) values ('lholmquist', 'BALLGAME_USER');
insert into User_Roles(username, role_name) values ('lholmquist', 'BALLGAME_ADMIN');
insert into User_Roles(username, role_name) values ('jmahoney'  , 'BALLGAME_USER');
insert into User_Roles(username, role_name) values ('dluck'     , 'BALLGAME_USER');
insert into User_Roles(username, role_name) values ('user1'     , 'BALLGAME_USER');
insert into User_Roles(username, role_name) values ('user2'     , 'BALLGAME_USER');

insert into Game(id, ball_carrier, gameBall_id, start_time, round, end_time) values (1, 'tdilorenzo', 1, sysdate(), 1, null);
insert into User_Games(game_id, username) values (1, 'tdilorenzo');
insert into User_Games(game_id, username) values (1, 'lholmquist');
insert into User_Games(game_id, username) values (1, 'dluck');

insert into Game(id, ball_carrier, gameBall_id, start_time, round,  end_time) values (2, 'user1', 2, sysdate(), 1, null);
insert into User_Games(game_id, username) values (2, 'user1');
insert into User_Games(game_id, username) values (2, 'user2');