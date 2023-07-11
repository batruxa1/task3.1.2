INSERT INTO roles (role_id, role) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
INSERT INTO users (user_id, username, lastname, email, password) VALUES (1, 'Ivan', 'Ivanov', 'admin@nikak.net', '1234');
INSERT INTO user_roles VALUES (1, 2);