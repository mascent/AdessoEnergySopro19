CREATE TABLE person (
  personID  varchar PRIMARY KEY CHECK personID NOT LIKE '%[^0-9]%',
  userName  varchar NOT NULL CHECK userName NOT LIKE '%[^0-9a-z]%',
  password  varchar NOT NULL CONSTRAINT safe_password CHECK (password LIKE '%[0-9]%' AND password LIKE '%[A-Z]%' collate Latin1_General_BIN2 AND password LIKE '%[!@#$%a^&*()-_+=.,;:"`~]%' AND len([password])>= (8)),
  role  varchar CHECK NOT NULL (role = "Admin" OR role = "User") ,
  createdAt  date NOT NULL,
  updatedAt  date,
  deletedAt  date
);

CREATE TABLE user (
  personID  varchar PRIMARY KEY CHECK personID NOT LIKE '%[^0-9]%',
  userNumber  varchar CHECK userNumber NOT LIKE '%[^0-9]%',
  name  varchar CHECK name NOT LIKE '%[^a-z]%',
  surname  varchar CHECK surname NOT LIKE '%[^a-z]%',
  emailAdress  varchar CHECK emailAdress NOT LIKE '%_@__%.__%',

);

CREATE TABLE issue (
  issueID  varchar CHECK issueID NOT LIKE '%[^0-9]%' PRIMARY KEY,
  name  varchar CHECK name NOT LIKE '%[^a-z]%',
  subject  varchar CHECK name NOT LIKE '%[^a-z]%',
  message  varchar CHECK name NOT LIKE '%[^a-z]%',
  closerID  varchar REFERENCES person CHECK issueID NOT LIKE '%[^0-9]%',
  IssuerID  varchar REFERENCES user CHECK issueID NOT LIKE '%[^0-9]%',
);

CREATE TABLE meter (
  meterID  varchar PRIMARY KEY CHECK meterID NOT LIKE '%[^0-9]%',
  meterNumber  varchar CHECK meterNumber NOT LIKE '%[^0-9]%',
  lengthOfReading  int,
  commaPosition  int,
  message  varchar CHECK message NOT LIKE '%[^a-z]%',
  adressID  varchar REFERENCES adress CHECK adressID NOT LIKE '%[^0-9]%',
  createdAt  date,
  updatedAt  date,
  deletedAt  date
);

CREATE TABLE adress (
  adressID  varchar PRIMARY KEY,
  street  varchar CHECK street NOT LIKE '%[^a-z]%',
  adressnumber  int,
  postalCode  int
);

CREATE TABLE reading (
  readingID  varchar CHECK readingID NOT LIKE '%[^0-9]%',
  meterID  varchar REFERENCES meter CHECK meterID NOT LIKE '%[^0-9]%'
);

CREATE TABLE readingValue (
readingValueID  varchar PRIMARY KEY CHECK readingValueID NOT LIKE '%[^0-9]%',
value  int,
readingDate  date,
changerID  varchar REFERENCES person CHECK changerID NOT LIKE '%[^0-9]%',
reason  varchar,
);

CREATE TABLE userMeterAssociation (
  aID  varchar PRIMARY KEY CHECK aID NOT LIKE '%[^0-9]%',
  beginOfAssociatiom  date,
  endOfAssociation  date,
  meterID  varchar REFERENCES meter CHECK meterID NOT LIKE '%[^0-9]%',
  personID  varchar REFERENCES user CHECK personID NOT LIKE '%[^0-9]%'
);
