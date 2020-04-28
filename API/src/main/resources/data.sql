DROP TABLE IF EXISTS phone_number;
 
CREATE TABLE phone_number (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  phone_number VARCHAR(10) NOT NULL,
  parent_phone_number	VARCHAR(10) NOT NULL,
  UNIQUE KEY uk_phone_number (phone_number)
);
