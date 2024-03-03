INSERT INTO ADDRESS (STREET,STREET_NUMBER,CITY,ZIP_CODE,COUNTRY) VALUES
  ('Trust Road','926','Hastings','4178','New Zealand'),
  ('Somewhere Street',NULL,'New York','10005','United States'),
  ('Thames Street','25B','London','SE10 9DD','United Kingdom');

INSERT INTO CUSTOMER (NAME,ID_ADDRESS) VALUES
  ('John Doe',(select ID from ADDRESS where ZIP_CODE='4178')),
  ('Mike Myers',(select ID from ADDRESS where ZIP_CODE='10005')),
  ('Benny Hill',(select ID from ADDRESS where ZIP_CODE='SE10 9DD'));





