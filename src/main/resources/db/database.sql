CREATE DATABASE IF NOT EXISTS HUMAN_RESOURCE;

-- Oluşturulan veritabanına bağlanacak kullanıcı oluşturma
CREATE USER  'hrapp'@'localhost' IDENTIFIED BY 'hrpassword';

-- Oluşturulan kullanıcıya oluşturulan veritabanına erişim izni verme
GRANT ALL PRIVILEGES ON HUMAN_RESOURCE.* TO 'hrapp'@'localhost';
FLUSH PRIVILEGES;

-- Veritabanı seçimi
USE HUMAN_RESOURCE;

CREATE TABLE `EMPLOYEE`
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

CREATE TABLE `LEAVE_TABLE`
(
    `ID`          INT AUTO_INCREMENT PRIMARY KEY,
    `START_DATE`  DATE                                                                                                             NOT NULL,
    `FINISH_DATE` DATE                                                                                                             NOT NULL,
    `TYPE`        ENUM ('SICK_LEAVE','CASUAL_LEAVE','MATERNITY_LEAVE','PATERNITY_LEAVE','LEAVE_WITHOUT_PAY','COMPASSIONATE_LEAVE') NOT NULL,
    `EXPLANATION` VARCHAR(250),
    `EMPLOYEE_ID` VARCHAR(36)                                                                                                      NOT NULL,
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE (ID)
);
