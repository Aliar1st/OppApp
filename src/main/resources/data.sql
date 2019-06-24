insert into role (id, name, authority) values
(1, 'User',          'ROLE_USER'),
(2, 'Administrator', 'ROLE_ADMIN');

insert into user (id, role_id, phone) values
(2, 1, '71111111111'),
(1, 1, '79518883210');