CREATE DATABASE IF NOT EXISTS taskdb DEFAULT CHARACTER SET utf8mb4;
USE taskdb;

CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(50) UNIQUE NOT NULL,
                      password VARCHAR(100) NOT NULL,
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE task (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(200) NOT NULL,
                      description TEXT,
                      priority INT DEFAULT 2 COMMENT '1高 2中 3低',
                      deadline DATETIME,
                      user_id BIGINT NOT NULL,
                      status INT DEFAULT 0 COMMENT '0未完成 1已完成',
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

-- 测试数据
INSERT INTO user(username, password) VALUES('admin', '123456');
INSERT INTO task(title, description, priority, deadline, user_id)
VALUES('完成GitHub作业', 'project-24项目提交', 1, '2025-01-15 23:59:59', 1);