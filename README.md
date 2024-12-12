#Real-Time Event Ticketing System
This project uses Spring Boot to create a real-time event ticketing system. The system uses multithreading to simulate the simultaneous release and purchasing of event tickets. 
It has tools for managing ticket pools, setting up ticketing parameters, and monitoring vendor and customer activity.
The program uses thread-safe resource management techniques and adheres to the Object-Oriented Programming (OOP) paradigm.

#Key Features
1.Configure Input Manger
  Use Getmap anotation to get user data from front end local host 3000 using API .
  Total tickets, ticket release rate, customer retrieval rate, and maximum ticket capacity
  are the input vales that Taken from front end ans save the data using Gson in Json format.
2.TiketPool manament
  Vendoers will Add tickets and customers will buy tickets .This event happening with concurret baised so 
  I used vectoer as a Ticket pool to be it thread safe way.
3.Multithreding
  Customers and Venders act as Separate threads and Concurrently ,In this project I have used the way of
  manualy creating Threds using Runnable interface.
4.Logging System
  Customer and Vender action will added to Logger Added in a thredsafe Queu and those data will return 
  to Logcntroller and those data will asses by front end using API fetch.
5.DataIntergration
  Data of Tickets,Venders, customers and ticket pool will saved in data bales As Entities(Tables)




#Technilogies Used
Java
Spring Boot
Spring Data JPA
Gson (JSON handling)
Multithreading
ConcurrentLinkedDeque (thread-safe logging)
Vector (thread-safe ticket pool)
