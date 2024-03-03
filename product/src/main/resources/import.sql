

INSERT INTO CATEGORY (NAME) VALUES('Fragile'),('Hi-Tech'),('Outdoor'),('Kitchenware');
  
INSERT INTO PRODUCT (NAME) VALUES
  ('Picnic Forks'),
  ('Smartphone'),
  ('Autonomous Lawn Mower'),
  ('Mirror');
  
INSERT INTO PRODUCT_CATEGORIES (ID_PRODUCT,ID_CATEGORY) VALUES
  ((select ID from PRODUCT where NAME='Picnic Forks'),(select ID from CATEGORY where NAME='Kitchenware')),
  ((select ID from PRODUCT where NAME='Picnic Forks'),(select ID from CATEGORY where NAME='Outdoor')),
  ((select ID from PRODUCT where NAME='Smartphone'),(select ID from CATEGORY where NAME='Hi-Tech')),
  ((select ID from PRODUCT where NAME='Smartphone'),(select ID from CATEGORY where NAME='Fragile')),
  ((select ID from PRODUCT where NAME='Autonomous Lawn Mower'),(select ID from CATEGORY where NAME='Outdoor')),
  ((select ID from PRODUCT where NAME='Autonomous Lawn Mower'),(select ID from CATEGORY where NAME='Hi-Tech')),
  ((select ID from PRODUCT where NAME='Mirror'),(select ID from CATEGORY where NAME='Fragile'));
