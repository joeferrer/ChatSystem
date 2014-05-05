#ChatSystem
<br>
##About
ChatSystem is a basic java program made by Joe Ferrer that allows multiple users to chat through a private server hosted by an arbitrary host/one of the users. Through the Client-Server architecture, and assuming a ChatSystem-server is running, all the client needs to do is to connect to the server's public/local IP and the server will connect the client to the private chatroom and voila, your chat session can begin!

##Development
ChatSystem is originally for a school individual machine project/assignment under sir Mario Carreon's CS 145 'Networking' class in UPD and is now under continued development. Currently, the only developer for this project is the creator, Joe Ferrer.Comments/Suggestions/Pull Requests are most welcome.

##Usage

#####Installation
To use ChatSystem, you must first download the ZIP file of this repository or pull this repository from github. If your system supports .exe files then you can just use the .exe files and disregard the rest. If your system does not support .exe files, then you need to install JRE(Java Runtime Environment) to run the .jar files instead. 

To successfully create a private chat session, a ChatSystem-server must first be running. If you wish to host the chat session, you must first run the MyServer.exe or MyServer.jar. Then clients/users may now connect. Each user should just need to double click their MyClient.exe or MyClient.jar and enter the server's public/local IP (depending on where they are connected) and they're now part of the chat session.

#####Basic Functionalities in Chat System:
>1.) __Send__ button will send the text in the text area at the left of this button to the server to other users/specific users
> <br>
> 2.) Typing and sending the following will trigger the ff commands:
> <br>
> - __/changestatus__ _status_ __ will change the 'Available' status beside the client's name at the rightmost text area to _status_ . 
> <br>
> - __/changename__ _name_ will change the client's name at the rightmost text area to _name_ .
> <br>
> - __/whisper__ _clientname_ will send your message in the the message text area at the left side of the send button to the specific user named _clientname_ .
><br>
>- __/quit__ will terminate your connection as a user and close your chat window.