# EnglishSentences
https://youtu.be/MrSG2Cq1Phk

#### CRUD app, Angular + Springboot + PostgreSQL.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Todos](#todos)
* [Setup](#setup)

## General info
Main purpose of this application is to practice CRUD full stack technologies and help me with studying English by displaying random sentences. Logged in users have the ability to mark sentences as favorites, display random sentences, or their favorites. There are 3 user states: not logged, regular user and admin
I used Java 17 features like records. Login in is implemented by JWT



Not log in. User can dislpey random sentences
![image](https://github.com/Jakub686/EnglishSentences/assets/80157748/3ff07904-17af-4a8b-87f2-d3591d43ef66)


Login in, User can use toggle switch to display all sentences or only its favorites sentences, also can add to favorite by clicking a star.
![image](https://github.com/Jakub686/EnglishSentences/assets/80157748/a21e0ee3-cf50-47a8-a43d-64530a6858e2)


User sentences list, where can manege favorite sentences, display sentence details, and search sentences.
![image](https://github.com/Jakub686/EnglishSentences/assets/80157748/5c2ebc4a-57b9-4deb-82c3-82ff3840f38e)


Admin, can delete, or update sentences.
![image](https://github.com/Jakub686/EnglishSentences/assets/80157748/c76f0237-d680-4fdc-9750-2ec2f86d10bf)


There are 2 roles of users.
![image](https://github.com/Jakub686/EnglishSentences/assets/80157748/e66f0824-a4d3-4888-bda6-f36f4a4d2665)


Integration tests
![image](https://github.com/Jakub686/EnglishSentences/assets/80157748/a94b82aa-1931-43cb-8998-15e3b565882b)


Unit tests
![image](https://github.com/Jakub686/EnglishSentences/assets/80157748/7c984b13-bcdc-4b08-80f8-f685aaca23a2)



## Technologies
Project is created with:
* Java 17
* SpringBoot 3
* Angular 15
* PostgreSQL / H2

## Setup
docker run --name english-sentence-docker -p 5432:5432 -e POSTGRES_PASSWORD=root -d postgres
	
	
## Todos
Technologies to implement

* [x] login - JWT
* [x] unit tests - Mockito
* [x] flyway - migration
* [x] integration tests - JUnit
* [ ] docker
* [ ] deploy
