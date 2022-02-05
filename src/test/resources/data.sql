create sequence hibernate_sequence;

call next value for hibernate_sequence;
insert into user (name, email, updated_at, created_at) values('Brian', 'Brian@test.com', now(), now());

call next value for hibernate_sequence;
insert into user (name, email, updated_at, created_at) values('Brian', 'Brian@test.com', now(), now());

call next value for hibernate_sequence;
insert into user (name, email, updated_at, created_at) values('Brian', 'Brian@test.com', now(), now());