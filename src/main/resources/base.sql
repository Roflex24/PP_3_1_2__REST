INSERT INTO roles VALUES (1, 'ROLE_ADMIN'),
                         (2, 'ROLE_USER');

INSERT INTO users VALUES (1, 21, 'admin@gmail.com', 'admin', '$2a$10$d4480V2VfxwdogCm32VGe.mrrX4ZebHIg6OsfL6Lywc2cMoZOdJgK', 'admin'),
                         (2, 32, 'user@gmail.com', 'user', '$2a$10$oOt8EiE/8N3xri2MwC/hRO9.ZNxYQnAVO977RsuBDeTWCKW15.lYS', 'user');

INSERT INTO users_roles VALUES (1, 1),
                               (1, 2),
                               (2, 2);
