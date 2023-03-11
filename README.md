# Reactive chats scanner - MICROSERVICES (BACKEND)

Reactive chats scanner is a backend web app for analyzing chat lines using a complete reactive, non-blocking, asyncronous processing with backpressure.
A chat generator simulates a real system (social media, educational platform, company chats, etc), sending records every 200ms to the MONGO database table, 
once they are inserted in the chats table, they are taken by the reader microservice, which reads every second just 10 chat lines (records), 
this can be changed but memory have to be modified as well. The reader will try to send the records to the ANALYZER microservice, using the web client, 
the latter will point to the ANALYZER handlers, and whenever the analyzer respond with a successful or not successful message, the record on the chats table 
will be marked as NOT AVAILABLE (to avoid duplicity). Then the analyzer will apply its rules and determine if the chat lines contains suspicious words 
(present in the forbidden csv files loaded in start up), consequently it will consume the REPORTER microservice suspicious handler (endpoint), ONLY if it is
found SUSPICIOUS, but before that and ALWAYS, it will consumer the UPPDATER handler of the same REPORTER microservice to update the chat line record STATE 
(checked to true). From the REPORTER microservice perspective,  it will update the record, therefore the chat line will get modified 
(available and checked fields) and basically will send if applies, the record to the SUSPICIOUS table.
This WEB APP's backend was developed by Luis Espinosa Llanos using a microservice approach and it was used the following technologies and tools: 

<table style="width:100%">
  <tr>
    <td>
  	Core	
    </td>
    <td>
  	Java 11, Spring Boot 2, Data JPA, Hibernate, Loombok, Jackson Databinding, Spring Webflux (reactive).
    </td>
  </tr>
  <tr>
    <td>
  	Databases
    </td>
    <td>
  	MongoDB.
    </td>
  </tr>
  <tr>
    <td>
  	Virtualization
    </td>
    <td>
  	Docker, Docker compose
    </td>
  </tr>
  <tr>
    <td>
  	Server	
    </td>
    <td>
  	Apache Tomcat Embebido (Spring Boot)
    </td>
  </tr>
  <tr>
    <td>
  	IDE	
    </td>
    <td>
  	Intellij IDEA
    </td>
  </tr>
  <tr>
    <td>
  	Executable	
    </td>
    <td>
  	Jar
    </td>
  </tr>
</table>


## Video
A video exposing the functionality of the proyect in local environment on a Desktop screen.

1. https://youtu.be/uYKqn6tiYQM

## Development Resources
I provide the following resources:

<table style="width:100%">
  <tr>
    <td>
  	Resources mentioned in the video
    </td>
    <td>
	In the RESOURCES folder
    </td>
</table>


## Pictures
Some pictures of the project on a local environment respectively:


<table style="width:100%">
  <tr>
    <td>
  		<img width="450" alt="Image" src="https://user-images.githubusercontent.com/56041525/224507956-ea5ea710-cbe6-4171-9e05-a89956d1b7fc.png">
	  </td>
    <td>
  	<img width="450" alt="Image" src="https://user-images.githubusercontent.com/56041525/224508118-c48f9a13-4372-42bf-a706-396ee9a90876.PNG">
    </td>
  </tr>
</table>

<table style="width:100%">
  <tr>
    <td>
  		<img width="450" alt="Image" src="https://user-images.githubusercontent.com/56041525/224508128-94911939-b46d-43af-afae-5dd674542743.PNG">
	  </td>
    <td>
	<img width="450" alt="Image" src="https://user-images.githubusercontent.com/56041525/224508129-e88ad931-98b5-4839-8a52-28e5f2d569d0.PNG">
    </td>
  </tr>
</table>


<table style="width:100%">
  <tr>
    <td>
  		<img width="450" alt="Image" src="https://user-images.githubusercontent.com/56041525/224508149-28f40a31-2a3b-4576-a7c0-ba87d6905959.PNG">
	  </td>
    <td>
	<img width="450" alt="Image" src="https://user-images.githubusercontent.com/56041525/224508154-26645b9a-63d8-4330-9f4d-074169821c7f.PNG">
    </td>
  </tr>
</table>


## Installation

This proyect should be installed using the following command:
```bash
# GENERATOR
docker build -t msv-chat-generator:1.0 . -f .\msv-chat-generator\Dockerfile
docker tag msv-chat-generator:1.0 luisllanos8/msv-chat-generator:1.0
docker push luisllanos8/msv-chat-generator:1.0

# READER
docker build -t msv-reader:1.0 . -f .\msv-reader\Dockerfile
docker tag msv-reader:1.0 luisllanos8/msv-reader:1.0
docker push luisllanos8/msv-reader:1.0
docker-compose -f .\docker-compose-reader.yml up -d

# ANALYZER
docker build -t msv-analyzer:1.0 . -f .\msv-analyzer\Dockerfile
docker tag msv-analyzer:1.0 luisllanos8/msv-analyzer:1.0
docker push luisllanos8/msv-analyzer:1.0
docker-compose -f .\docker-compose-analyzer.yml up -d

# REPORTER
docker build -t msv-reporter:1.0 . -f .\msv-reporter\Dockerfile
docker tag msv-reporter:1.0 luisllanos8/msv-reporter:1.0
docker push luisllanos8/msv-reporter:1.0
docker-compose -f .\docker-compose-reporter.yml up -d

# AFTER BUILDING DOCKER IMAGES
docker-compose -f .\docker-compose.yml up -d
```

## Usage
The recommendation by now is to import it in your favority IDE. And run the project the way I did.


## Contributing
This proyect is quite simple, and is part of my personal portfolio, so it is not intended to receive contributions.


## License
It is free.


