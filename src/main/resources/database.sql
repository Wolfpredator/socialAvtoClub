-- Table: users
CREATE TABLE users (
  idFriend INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  surname  VARCHAR(255),
  nickname VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles (
  idFriend INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name     VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (idFriend),
  FOREIGN KEY (role_id) REFERENCES roles (idFriend),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;

-- Table: friends
CREATE TABLE friends (
  friend_one INT NOT NULL,
  friend_two INT NOT NULL,
  status     ENUM ('0', '1', '2') DEFAULT '0',
  PRIMARY KEY (friend_one, friend_two),
  FOREIGN KEY (friend_one) REFERENCES users (idFriend),
  FOREIGN KEY (friend_two) REFERENCES users (idFriend)
)
  ENGINE = InnoDB;

-- Table: chat_room
CREATE TABLE chat_room (
  idFriend    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  creator_id  INT NOT NULL,
  chat_name   VARCHAR(50),
  create_time DATE,

  FOREIGN KEY (creator_id) REFERENCES users (idFriend)
)
  ENGINE = InnoDB;

-- Table for mapping chat room and users: chat_users
CREATE TABLE chat_users (
  chat_id INT NOT NULL,
  user_id INT NOT NULL,

  FOREIGN KEY (chat_id) REFERENCES chat_room (idFriend),
  FOREIGN KEY (user_id) REFERENCES users (idFriend),

  UNIQUE (chat_id, user_id)
)
  ENGINE = InnoDB;

-- Table: chat_message
CREATE TABLE chat_messages (
  idFriend     INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  chat_id      INT  NOT NULL,
  message_text TEXT NOT NULL,
  writer       INT  NOT NULL,
  create_time  DATETIME,

  FOREIGN KEY (chat_id) REFERENCES chat_room (idFriend),
  FOREIGN KEY (writer) REFERENCES users (idFriend)

)
  ENGINE = InnoDB;

-- Table: group
CREATE TABLE groups (
  idFriend    INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  group_name  VARCHAR(50) NOT NULL,
  creater     INT         NOT NULL,
  create_time DATETIME
)
  ENGINE = InnoDB;

-- Table for mapping user and group: group_users
CREATE TABLE group_users (
  group_id INT NOT NULL,
  user_id  INT NOT NULL,

  FOREIGN KEY (group_id) REFERENCES groups (idFriend),
  FOREIGN KEY (user_id) REFERENCES users (idFriend),

  UNIQUE (group_id, user_id)
)
  ENGINE = InnoDB;

-- Table: group_messages
CREATE TABLE group_messages (
  idFriend     INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  group_id     INT  NOT NULL,
  creater      INT  NOT NULL,
  message_text TEXT NOT NULL,
  create_time  DATETIME,

  FOREIGN KEY (group_id) REFERENCES groups (idFriend),
  FOREIGN KEY (creater) REFERENCES users (idFriend)
)
  ENGINE = InnoDB;

CREATE TABLE group_message_comments (
  idFriend         INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  group_message_id INT  NOT NULL,
  creater          INT  NOT NULL,
  message_text     TEXT NOT NULL,
  create_time      DATETIME,

  FOREIGN KEY (group_message_id) REFERENCES group_messages (idFriend),
  FOREIGN KEY (creater) REFERENCES users (idFriend)
)
  ENGINE = InnoDB;

