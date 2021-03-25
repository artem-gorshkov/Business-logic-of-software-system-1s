INSERT INTO role (id, name)
VALUES (1, 'ROLE_USER');
INSERT INTO role (id, name)
VALUES (2, 'ROLE_MODERATOR');
INSERT INTO role (id, name)
VALUES (3, 'ROLE_ADMIN');
INSERT INTO role (id, name)
VALUES (4, 'ROLE_ADVERT');

INSERT INTO status (id, description, name)
VALUES (1, 'Complain is submitted', 'Submitted');
INSERT INTO status (id, description, name)
VALUES (2, 'Complain is accepted', 'Accepted');
INSERT INTO status (id, description, name)
VALUES (3, 'Complain is rejected', 'Rejected');

insert into users (id, blocked, login, password, when_blocked)
VALUES (-1, false, 'Вася', 'string', null);
insert into users_roles (user_id, roles_id)
VALUES (-1, 1);
insert into post (id, created, payload, title, author_id) VALUES (-1, null, 'А потом ...', 'В начале я не могла поверить, что это моя дочь...', -1);
insert into comment (id, created, deleted, payload, author_id, post_id) VALUES (-1, null, false, 'Отличная история!', -1, -1);