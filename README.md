# Hotel Management System
### Result (Home Page) :
![](https://github.com/PavanSaiSheshetti/HotelManagementSystem_Angular/blob/master/src/assets/img/image.png)
### Note :
  This project is only spring project and please find related angular project [here](https://github.com/PavanSaiSheshetti/HMS_angular_admin). You will find complete 
  description of running project in Installation and Run Section
## Project Description

<p> Title of our application is Hotel Management System</p>

<p>The Application should allow the users to book the hotel rooms for few days which should include accomodation and food during the stay.</p>
Admin allows the Receptionist activations and add Hotel rooms and the services offered by the Hotels.
Receptionist allow to display what are the rooms are available & services are being offered.
Customers need to register in order to book a room in a hotel from 1 day to 10 days maximum. Customer can also have the facilities of dropping/picking up from/to nearby location.


## Technologies Used

<ul><li> Core Java
  <li>Microservices
  <li>Spring Boot
   <li>Spring Data
  <li>Angular 10
   <li>Spring AOP
  <li>PostgreSql
  <li>AwS RDS
  <li> AWS EC2
   <li> Docker
    <li>Jenkins
</ul>


## Features
<h4>Admin:</h4>
<ul>
  <li> Admin should login before accessing the system</li>
  <li>Admin can add new receptionist when he/she joins the hotel. The login credentials for the receptionist is sent through a mail.</li>
  <li>Admin can view all the receptionists or by receptionist id.</li>
  <li> Admin can add available rooms in the hotel and view all the rooms that he has added.</li>
  <li>Admin can add new offers that varies with festivals/seasons, view all the offers and he can change the offers accordingly.<li>
  <li>Admin can view customers feedback and booking history.</li>
</ul>
<h4>Receptionist:</h4>
<ul>
  <li>Receptionist should log in to his/her account to perform operations.</li>
  <li>Receptionist allocates room numbers to the customers based on their chosen requirementst</li>
  <li>Payment will be done by customer while booking hotel room,during check in and check out.</li>
  <li>If customer cancels his/her booking the amount will be refunded based on customer check in and cancellation date. While customer vacates, bill gets generated to the customer and balance amount should be cleared by the customer so that he can check out.</li>
</ul>
<h4>Customer:</h4>
<ul>
  <li>Customer can signup if he/she visits website for the first time or if the customer is already having an account then the customer can log in to the website and directly book a room.</li>
  <li>Customer can edit their details and update their password if they forgot.</li>
  <li>Customer can book a room on the basis of room type,offers,number of members. At the time of booking customer has to pay 10% amount in advance.Customer can update the booking details and cancel booking at a valid time for valid reasons so that they can get a refund accordingly.
<li>Customer can utilize pick drop facilities.
  <li>Customer can pay the final bill through card,net banking and cash also.

</ul>

### Technical Aspect:
<ul>
<li>Used Spring Data for connecting to postgreSQL database </li>
<li>Used DAO design pattern in the project
<li>Created Interactive Webpages using Angular and Bootstrap for the website
<li>All the client side and server side validations were done using TypeScript and Database
<li>Exception or Error Handling is done in both  java and angular(With HttpClient)
<li>Tested all the functionalities using JUnit4 
<li>Implemented Logging by Log4j
</ul>

## Getting Started

After running this project , run the angular project (import angular project [here](https://github.com/PavanSaiSheshetti/HMS_angular_admin)). Home page will be displayed where you can see the about,facilities and login section for Admin, Receptionist, Customer.

After login they can view their respective dashboard with all the functionalities.

## Usage

### Step1 :
Import this project by a Github Desktop(click [here](https://desktop.github.com/) to install) or by downloading the Zip file . If you downloaded the zip file then you need to unzip or extract it and import this in eclipse(file-->import-->select the project)
change the database configuration in application.properties file (Driver,Dialect,username and password).

### Step2 :
Import the angular Project (find [here](https://github.com/PavanSaiSheshetti/HMS_angular_admin))by a Github Desktop or by downloading the Zip file. If you downloaded the zip file then you need to unzip or extract it and open the project folder in visual studio code or any other code editor(file-->select folder-->select the project)

That's all you can run this project and see the results.

### Note :
<ol>
<li>you can install eclipse IDE <a href="https://www.eclipse.org/downloads/">here</a></li>
<li>you can install visual studio code <a href="https://code.visualstudio.com/download">here</a></li>
</ol>

## Contributors :
This Project is a batch project. Entire batch of 25 members worked on each modules and functionalities collaboratively.

### Bug / Feature Request:

If you find a bug (the website couldn't handle the query and / or gave undesired results), kindly open an issue [here](https://github.com/PavanSaiSheshetti/HotelManagementSystem/issues/new) by including your search query and the expected result.

