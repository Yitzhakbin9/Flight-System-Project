-- ***	CREATING THE TABLES	***

CREATE TABLE "Flights"(
	"Id" SERIAL PRIMARY KEY,
	"Airline_Company_Id" bigint,
	"Origin_Country_Id" int,
	"Destination_Country_Id" int,
	"Departure_Time" timestamp,
	"Landing_Time" timestamp,
	"Remaining_Tickets" int
)

CREATE TABLE "Countries"(
	"Id" SERIAL PRIMARY KEY,
	"Name" text UNIQUE
)


CREATE TABLE "Airline_Companies"(
	"Id" SERIAL PRIMARY KEY,
	"Name" text UNIQUE,
	"Country_Id" int,
	"User_Id" bigint UNIQUE
)

CREATE TABLE "Tickets"(
	"Id" SERIAL PRIMARY KEY,
	"Flight_Id" bigint,
	"Customer_Id" bigint,
	UNIQUE ("Flight_Id","Customer_Id")
)


CREATE TABLE "Users"(
	"Id" SERIAL PRIMARY KEY,
	"Username" text UNIQUE,
	"Password" text,
	"Email" text UNIQUE,
	"User_Role" int
)


CREATE TABLE "Customers"(
	"Id" SERIAL PRIMARY KEY,
	"First_Name" text,
	"Last_Name" text,
	"Address" text,
	"Phone_No" text UNIQUE,
	"Credit_Card_No" text UNIQUE,
	"User_Id" bigint UNIQUE
)


CREATE TABLE "User_Roles"(
	"Id" SERIAL PRIMARY KEY,
	Role_Name text UNIQUE
)

CREATE TABLE "Administrators"(
	"Id" SERIAL PRIMARY KEY,
	"First_Name" text,
	"Last_Name" text,
	"User_Id" bigint UNIQUE
)


-- ***	ADDING FOREIGN KEYS	***

ALTER TABLE "Flights"
ADD CONSTRAINT fk_Flights_Airline_Companies FOREIGN KEY ("Airline_Company_Id") REFERENCES "Airline_Companies" ("Id");
	
	
ALTER TABLE "Flights"
ADD CONSTRAINT fk_Flights_Countries FOREIGN KEY ("Origin_Country_Id") REFERENCES "Countries" ("Id");	
	
	
ALTER TABLE "Flights"
ADD CONSTRAINT fk_Flights_CountriesDes FOREIGN KEY ("Destination_Country_Id") REFERENCES "Countries" ("Id");	
	
	
ALTER TABLE "Airline_Companies"
ADD CONSTRAINT fk_Airline_Companies_Users FOREIGN KEY ("User_Id") REFERENCES "Users" ("Id");
	
	
ALTER TABLE "Users"
ADD CONSTRAINT fk_Users_User_Roles FOREIGN KEY ("User_Role") REFERENCES "User_Roles" ("Id");		
	
	
ALTER TABLE "Tickets"
ADD CONSTRAINT fk_Tickets_Flights FOREIGN KEY ("Flight_Id") REFERENCES "Flights" ("Id");
	
	
ALTER TABLE "Tickets"
ADD CONSTRAINT fk_Tickets_Customers FOREIGN KEY ("Customer_Id") REFERENCES "Customers" ("Id");	

	
ALTER TABLE "Customers"
ADD CONSTRAINT fk_Customers_Users FOREIGN KEY ("User_Id") REFERENCES "Users" ("Id");		
	
	
ALTER TABLE "Administrators"
ADD CONSTRAINT fk_Administrators_Users FOREIGN KEY ("User_Id") REFERENCES "Users" ("Id");
	

