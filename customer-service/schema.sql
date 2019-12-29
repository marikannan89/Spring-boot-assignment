DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  customer_id INT NOT NULL AUTO_INCREMENT,
  ic VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  age VARCHAR(100) NOT NULL,
  dob VARCHAR(100) NOT NULL,
  address_line1 VARCHAR(100) NOT NULL,
  address_line2 VARCHAR(100) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_date TIMESTAMP,
  PRIMARY KEY (customer_id));
  
insert into customers (ic,name,age,dob,address_line1,address_line2,created_date)
values ('2324fdsfdf324','marikannan','30','26-04-1998','Jalan ipoh,Sentul','Kuala lumpur', current_timestamp());