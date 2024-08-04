CREATE TABLE `Libraries`
(
    `id`   int          NOT NULL AUTO_INCREMENT,
    `logo` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Writers`
(
    `id`        int     NOT NULL AUTO_INCREMENT,
    `firstname` varchar(255) NOT NULL,
    `lastname`  varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Books`
(
    `id`          int         NOT NULL AUTO_INCREMENT,
    `cover`      varchar(255) NOT NULL,
    `rating`      float DEFAULT NULL,
    `title`       varchar(255) NOT NULL,
    `writer_id` int   DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Books_Libraries`
(
    `book_id`  int DEFAULT NULL,
    `library_id` int DEFAULT NULL
);

INSERT INTO `Libraries`(`id`, `name`, `logo`)
VALUES (1, 'British Library', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/BritishLibrary.svg/800px-BritishLibrary.svg.png'),
       (2, 'Library of Congress', 'https://upload.wikimedia.org/wikipedia/commons/b/be/Logo_of_the_United_States_Library_of_Congress.svg'),
       (3, 'Biblioteka Narodowa', 'https://upload.wikimedia.org/wikimedia/pl/7/7a/Biblioteka_Narodowa.jpg'),
       (4, 'National Diet Library', 'https://www.ndl.go.jp/default/images/logo.png');

INSERT INTO `Writers`(`id`, `firstname`, `lastname`)
VALUES (1, 'George R. R.', 'Martin'),
       (2, 'Nisio', 'Isin'),
       (3, 'Stephen', 'King'),
       (4, 'J.K.', 'Rowling');

INSERT INTO `Books`(`id`, `title`, `cover`, `writer_id`, `rating`)
VALUES (1, 'A Game of Thrones', 'https://upload.wikimedia.org/wikipedia/en/9/93/AGameOfThrones.jpg', 1, 4.1),
       (2, 'A Storm of Swords', 'https://upload.wikimedia.org/wikipedia/en/2/24/AStormOfSwords.jpg', 1, 4.7),
       (3, 'Bakemonogatari', 'https://upload.wikimedia.org/wikipedia/en/4/4a/Bakemonogatari_Up.png', 2, 4.6),
       (4, 'Katanagatari', 'https://upload.wikimedia.org/wikipedia/en/a/a9/Katanagatari_volume_one.jpg', 2, 4.0),
       (5, 'The Shining', 'https://upload.wikimedia.org/wikipedia/commons/0/09/The_Shining_%281977%29_front_cover%2C_first_edition.jpg', 3, 4.8),
       (6, 'It', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/It_%281986%29_front_cover%2C_first_edition.jpg/800px-It_%281986%29_front_cover%2C_first_edition.jpg', 3, 4.7),
       (7, 'Harry Potter and the Philosopher''s Stone', 'https://upload.wikimedia.org/wikipedia/en/6/6b/Harry_Potter_and_the_Philosopher%27s_Stone_Book_Cover.jpg', 4, 4.2),
       (8, 'Harry Potter and the Prisoner of Azkaban', 'https://ecsmedia.pl/c/noc-listopadowa-b-iext64788740.jpg', 4, 4.5);

INSERT INTO book_library (`book_id`, `library_id`)
VALUES (1, 1),
       (2, 1),
       (3, 4),
       (4, 4),
       (5, 2),
       (6, 2),
       (7, 4),
       (8, 4),
       (2, 3),
       (6, 3),
       (8, 3),
       (1, 3);

CREATE TABLE `Users`
(
    id       int primary key auto_increment,
    username VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE `Roles`
(
    id       int primary key auto_increment,
    username VARCHAR(255),
    role     VARCHAR(255)
);

INSERT INTO `Users`(`username`, `password`)
VALUES ('dbuser1', '123'),
       ('dbuser2', '123'),
       ('dbuser3', '123');


INSERT INTO `Roles`(`username`, `role`)
VALUES ('dbuser1', 'ROLE_ADMIN'),
       ('dbuser2', 'ROLE_AUTHOR_ADMIN'),
       ('dbuser3', 'ROLE_BOOK_ADMIN');




