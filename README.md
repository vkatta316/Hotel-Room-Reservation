# Hotel-Room-Reservation

Project Summary:

Hotel Management System is a software used in hospitality industry. A hotel reservation system enables guests to schedule dates and length of stay, room selection, and payment all in one place.
In this application, for user book a room in a hotel, a customer must check the application to see if there is an empty room, the duration of the reservation and the overall cost.
The database provides information about the many types of rooms that are offered by that specific hotel, such as single and double bed rooms, rooms with and without air conditioning, and rooms with both. Also, it doesn’t allow users to book the room that is already occupied by other client.
Implemented functionality using Enterprise JavaBeans (EJBs), which provide features like declarative transactions and security via annotations.
For all the pages like Home, Booking, My Reservations , Manage Rooms we used the similar reusable template named pageTemplate and loginTemplate for login page. I used booking and room composite components for viewing all client reservations and manage rooms by the manager. A customer can easily select a date, a suitable room, checks pricing, and then submit his request.

Design:

The goal of this project is to create a simple website for hotel reservations using the Jakarta EE platform. A number of other technologies like EJB, JSF, and Payara for server applications are used. In addition, I used Bootstrap and CSS to create an aesthetically pleasing website, and since we also needed a database, I used a MYSQL database.
A web application to book a room in a hotel, a customer must check the application to see if there is an empty room by providing check-in and checkout dates, the duration of the reservation and the overall cost.

Features:

This website has following features,
Sign Up System
Login System
Manager Panel
Create Rooms
Manage Rooms(View, Edit, Delete)
Client Panel
Hotel Room Booking System
View All Client Reservations (View, Modify and Cancel Reservation)
All Pages are Validated on server-side and client side.
Well designed using bootstrap and CSS

Screen Captures:

![SignUp](https://user-images.githubusercontent.com/113000548/236511940-1c707ce1-5ec9-40c9-91b0-805e565f347a.png)

![Login Screen](https://user-images.githubusercontent.com/113000548/236511909-788ce387-5988-4634-a411-f2fc7900bed4.png)

![Customer Home Page](https://user-images.githubusercontent.com/113000548/236512113-ee4dcad6-aea8-430c-b8fa-608f0589a162.png)

![Date Validations](https://user-images.githubusercontent.com/113000548/236511996-07a87f96-4864-4f0a-be57-b276502e0e0f.png)

![Booking Dates Duration](https://user-images.githubusercontent.com/113000548/236512027-5b1fc708-03a0-4cc5-a8bf-85aca77cf501.png)

![Available Rooms For Selected Dates](https://user-images.githubusercontent.com/113000548/236512044-be5f281d-2d90-4f4a-b007-8e0b645deefc.png)

![Reservation Details](https://user-images.githubusercontent.com/113000548/236512206-a51b6378-0404-467c-acdb-ad78cdb8432b.png)

![My Reservations](https://user-images.githubusercontent.com/113000548/236512235-123bb5fd-2c04-4298-adb2-38723f7f46cb.png)

![View Reservation](https://user-images.githubusercontent.com/113000548/236512284-443b38a5-179e-45b6-adb7-48bdc7580036.png)

![Modify Reservation](https://user-images.githubusercontent.com/113000548/236512325-99a2980d-1e81-4f8f-bda6-ecdb9903ff11.png)

![Cancel Reservation](https://user-images.githubusercontent.com/113000548/236512362-d41d18d1-c153-4376-9d7a-dd02128eb61d.png)

![Create Room](https://user-images.githubusercontent.com/113000548/236512422-b2bfb003-7224-4d5d-babf-a363848df3c9.png)

![Manage Rooms](https://user-images.githubusercontent.com/113000548/236512455-5c93cacc-a8c2-4b69-8ef7-5656d19e6b6a.png)

Following are the entities representing data that can be persisted to database
Hotel, Guest, Room, Booking, Payment are considered as entities in this web application.

![Data Model](https://user-images.githubusercontent.com/113000548/223309225-754a2e32-d064-4420-80e1-75889d4df336.png)


Rest API End Points:

Get
![Get Multiple Records](https://user-images.githubusercontent.com/113000548/236510697-37325b83-711e-456b-ace5-df0f0a4cc858.png)

Get Single Record
![Get Single Record](https://user-images.githubusercontent.com/113000548/236510752-02fee840-3caf-45a5-83c9-20c04b3c861c.png)

Post
![Post EndPoint](https://user-images.githubusercontent.com/113000548/236510831-01a65381-e16d-4c55-9f1d-6406f6a53ee1.png)

DB entry
![Record Created From POST DB](https://user-images.githubusercontent.com/113000548/236510882-16b5c1ad-ce52-4256-9a48-1a54d540cce7.png)

Put
![Update EndPoint](https://user-images.githubusercontent.com/113000548/236511677-07c442d7-5388-447f-890f-a2399187a360.png)

DB Updated
![DB Updated ](https://user-images.githubusercontent.com/113000548/236511744-74a44aef-90be-45ba-a88d-58d795d24f9b.png)

Delete
![Delete EndPoint](https://user-images.githubusercontent.com/113000548/236511794-25cf29d8-08fe-46ae-baf0-420da67fd60b.png)

Deleted from DB
![Deleted From DB](https://user-images.githubusercontent.com/113000548/236511853-d67d2dc4-9b33-4cc8-bcfa-644051c30488.png)
