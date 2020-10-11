# chatIO
## 1. EchoServer & Client~
### Description
~~2 applications chat server and one client client.~~

~~Client can connect to the server using a socket and fixed ip.~~

~~Messages typed in client terminal will be captured and sent to server~~

~~Server receives messages and responds by sending the message back to client Client displays the message again as it is received from the server.~~

~~Written in JAVA only, no requirements of any fancy ui. All input output can be done with system println and readers in the terminal.~~

## 2. Chat Server and clients
### Description
3 applications, 1 chat server and 2x(or more) chat clients.

~~Same setup as before but server now accepts connections from 2 or more clients.~~

Messages written in client 1 are sent to all other clients.

Messages should appear in the same order in all clients.

Look into semaphores or monitor objects to achieve this.

Tip: It might be a good idea to name clients and display the name of the other client to keep track of what's from where.

## 3. Chat server with memory
### Description
Create a simple in memory history for the chat so that when a new client connects the last 5 messages is sent to the newly connected client
## 4. chat server with on file memory
### Description
Store all sent messages in a “log” file on disk so even if server is restarted last chat messages is sent

## 5. Store messages in a local db
### Description
Create a local database where messages are stored and read from
