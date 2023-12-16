CREATE DATABASE IF NOT EXISTS HUMAN_RESOURCE;

CREATE USER 'hrapp'@'localhost' IDENTIFIED BY 'hrpassword';

GRANT ALL PRIVILEGES ON HUMAN_RESOURCE.* TO 'hrapp'@'localhost';
FLUSH PRIVILEGES;
# defult değer ekle tablolara
USE HUMAN_RESOURCE;

CREATE TABLE IF NOT EXISTS `EMPLOYEE`
(
    `ID`         VARCHAR(36) PRIMARY KEY,
    `FIRST_NAME` VARCHAR(45)                         NOT NULL,
    `LAST_NAME`  VARCHAR(45)                         NOT NULL,
    `EMAIL`      VARCHAR(45) UNIQUE                  NOT NULL,
    `GENDER`     ENUM ('MALE','FEMALE','OTHER')      NOT NULL,
    `ROLE`       ENUM ('HUMAN_RESOURCE', 'EMPLOYEE') NOT NULL,
    `USERNAME`   VARCHAR(45) UNIQUE                  NOT NULL,
    `PASSWORD`   VARCHAR(45)                         NOT NULL
);

INSERT INTO EMPLOYEE (ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, ROLE, USERNAME, PASSWORD)
VALUES
    ('9acdbaab-0263-4738-835c-02f9d2a4804c', 'John', 'Doe', 'john@example.com', 'MALE', 'EMPLOYEE', 'john_doe', 'password123'),
    ('d9723d6f-9886-4ce9-b8eb-9c0f1bc80685', 'Jane', 'Smith', 'jane@example.com', 'FEMALE', 'HUMAN_RESOURCE', 'jane_smith', 'securedpw');


CREATE TABLE IF NOT EXISTS  `LEAVE_TABLE`
(
    `ID`          INT AUTO_INCREMENT PRIMARY KEY,
    `START_DATE`  DATE                                                                                                             NOT NULL,
    `FINISH_DATE` DATE                                                                                                             NOT NULL,
    `TYPE`        ENUM ('SICK_LEAVE','CASUAL_LEAVE','MATERNITY_LEAVE','PATERNITY_LEAVE','LEAVE_WITHOUT_PAY','COMPASSIONATE_LEAVE') NOT NULL,
    `EXPLANATION` VARCHAR(250),
    `EMPLOYEE_ID` VARCHAR(36)                                                                                                      NOT NULL,
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE (ID)
);

INSERT INTO LEAVE_TABLE (START_DATE, FINISH_DATE, TYPE, EXPLANATION, EMPLOYEE_ID)
VALUES
    ('2023-01-01', '2023-01-05', 'CASUAL_LEAVE', 'Family vacation', '9acdbaab-0263-4738-835c-02f9d2a4804c'),
    ('2023-02-10', '2023-02-12', 'SICK_LEAVE', NULL, 'd9723d6f-9886-4ce9-b8eb-9c0f1bc80685');
