

-- ***	FUNCTION 1  ***

create or replace function get_airline_by_username(_username text)
returns table("Id" integer, "Name" text, "Country_Id" integer,
			  "User_Id" bigint , "Username" text, "Password" text,
			  "Email" text,"User_Role" integer )
language plpgsql
AS
$$
 	begin 
 		return query
 			select 
 			"Airline_Companies"."Id",
 			"Airline_Companies"."Name",
 			"Airline_Companies"."Country_Id",
 			"Airline_Companies"."User_Id",
			"Users"."Username",
			"Users"."Password",
			"Users"."Email",
			"Users"."User_Role"
 			from "Airline_Companies" 
 			inner join  "Users"
 			On "Airline_Companies"."User_Id" = "Users"."Id"
		 where "Users"."Username" = _username;
 	end;
$$

select * from get_airline_by_username('AirFrance')


-- ***	FUNCTION 2  ***

create or replace function get_customer_by_username(_username text)
returns table("Id" integer , "First_Name" text , "Last_Name" text ,"Address" text,
			  "Phone_No" text, "Credit_Card_No" text, "User_Id" bigint, "Username" text, 
			  "Password" text, "Email" text, "User_Role" integer)
language plpgsql
AS
$$
 	begin 
 		return query			
			select 
			"Customers"."Id",
			"Customers"."First_Name",
			"Customers"."Last_Name",
			"Customers"."Address",
			"Customers"."Phone_No",
			"Customers"."Credit_Card_No",
			"Customers"."User_Id",
			"Users"."Username",
			"Users"."Password",
			"Users"."Email",
			"Users"."User_Role"
			from "Customers"
			join "Users"
			on "Customers"."User_Id" = "Users"."Id"
			where "Users"."Username"=_username;
	end;
$$


select * from get_customer_by_username('HenryB')



-- ***	FUNCTION 3  ***

create or replace function get_user_by_username(_username text)
returns table ("Id" integer, "Username" text, "Password" text, "Email" text, "User_Role" integer)
language plpgsql
AS
$$
 	begin 
 		return query			
			select * from "Users" where "Users"."Username" = _username;
	end;
$$


select * from get_user_by_username('AvaM')


-- ***	FUNCTION 4  ***

create or replace function get_flights_by_parameters(_origin_counry_id int, _detination_country_id int, _date timestamp without time zone)
returns table ("Id" integer ,"Airline_Company_Id" bigint , "Origin_Country_Id" integer , "Destination_Country_Id" integer,
			  "Departure_Time" timestamp without time zone , "Landing_Time" timestamp without time zone , "Remaining_Tickets" integer)
language plpgsql
AS
$$
 	begin 
 		return query	
			select *
			from "Flights"
			where "Flights"."Origin_Country_Id" = _origin_counry_id
			and "Flights"."Destination_Country_Id" = _detination_country_id
			and "Flights"."Departure_Time" = _date;
end;
$$


select * from get_flights_by_parameters(49,56,'2022-10-10 10:30:00')


-- ***	FUNCTION 5  ***
create or replace function get_flights_by_airline_id(_airline_id bigint)
returns table ("Id" integer ,"Airline_Company_Id" bigint , "Origin_Country_Id" integer , "Destination_Country_Id" integer,
			  "Departure_Time" timestamp without time zone , "Landing_Time" timestamp without time zone , "Remaining_Tickets" integer)
language plpgsql
AS
$$
 	begin 
 		return query
			select 
			"Flights"."Id", 
			"Flights"."Airline_Company_Id",
			"Flights"."Origin_Country_Id",
			"Flights"."Destination_Country_Id",
			"Flights"."Departure_Time",
			"Flights"."Landing_Time",
			"Flights"."Remaining_Tickets"
			from "Flights"
			join "Airline_Companies"
			on "Flights"."Airline_Company_Id" = "Airline_Companies"."Id"
			where "Airline_Companies"."Id" = _airline_id;
end;
$$

select * from get_flights_by_airline_id(1)


-- ***	FUNCTION 6  ***

create or replace function get_arrival_flights(_country_id int)
returns table ("Id" integer ,"Airline_Company_Id" bigint , "Origin_Country_Id" integer , "Destination_Country_Id" integer,
			  "Departure_Time" timestamp without time zone , "Landing_Time" timestamp without time zone , "Remaining_Tickets" integer)
language plpgsql
AS
$$
 	begin 
 		return query		
			select * from "Flights" 
			where
			("Flights"."Landing_Time"
				BETWEEN
					(select Now()::timestamp)
						and
					(select Now()::timestamp+ interval '12 hours'))
			and 
			"Flights"."Origin_Country_Id" = _country_id;
end;
$$


select * from get_arrival_flights(48)



-- ***	FUNCTION 7  ***

create or replace function get_departure_flights(_country_id int)
returns table ("Id" integer ,"Airline_Company_Id" bigint , "Origin_Country_Id" integer , "Destination_Country_Id" integer,
			  "Departure_Time" timestamp without time zone , "Landing_Time" timestamp without time zone , "Remaining_Tickets" integer)
language plpgsql
AS
$$
 	begin 
 		return query		
			select * from "Flights" 
			where
			("Flights"."Departure_Time"
				BETWEEN
					(select Now()::timestamp)
						and
					(select Now()::timestamp+ interval '12 hours'))
			and 
			"Flights"."Origin_Country_Id" = _country_id;
end;
$$


select * from get_departure_flights(7)


-- ***	FUNCTION 8  ***

create or replace function get_tickets_by_customer(_customer_id bigint)
returns table ("Id" integer , "Flight_Id" bigint , "Customer_Id" bigint )
language plpgsql
AS
$$
 	begin 
 		return query	
			   select "Tickets"."Id",
				"Tickets"."Flight_Id",
				"Tickets"."Customer_Id"
				from "Tickets" 
				join "Customers"
				on "Tickets"."Customer_Id" = "Customers"."Id"
				where "Customers"."Id" = _customer_id;
end;
$$


select * from get_tickets_by_customer(3)


-- **********	get flight by customer funcion	*************

create or replace function get_flights_by_customer(_costumer_id integer)
returns table ("Id" integer ,"Airline_Company_Id" bigint , "Origin_Country_Id" integer , "Destination_Country_Id" integer,
			  "Departure_Time" timestamp without time zone , "Landing_Time" timestamp without time zone , "Remaining_Tickets" integer)
language plpgsql
AS
$$
 	begin 
 		return query
			select 
			"Flights"."Id", 
			"Flights"."Airline_Company_Id",
			"Flights"."Origin_Country_Id",
			"Flights"."Destination_Country_Id",
			"Flights"."Departure_Time",
			"Flights"."Landing_Time",
			"Flights"."Remaining_Tickets"
			from "Flights"
			join
				(select * from "Tickets"
				join "Customers"
				on "Tickets"."Customer_Id" = "Customers"."Id" where "Customers"."Id" = _costumer_id) as "newTable"
				on "newTable"."Flight_Id" = "Flights"."Id";
end;
$$


select * from get_flights_by_customer(2)
