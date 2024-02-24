# Stackoverflow_api
## Description

This is a JavaFX application that allows users to fetch, view, and store a list of StackOverflow users, their reputation history, and bookmark their favorite users.

## Guide to Run the App

1. Clone the repository to your local machine.
2. Create database im MySQL.
3. Use this to make an table match the code 
`CREATE TABLE users (user_id INT PRIMARY KEY, display_name VARCHAR(255),reputation INT,account_id INT,
    location VARCHAR(255),user_type VARCHAR(50), profile_image VARCHAR(255), view_count INT, question_count INT, answer_count INT
);`
4. Navigate to the project directory to `src/main/java/View/` you will find the Main class run it.

## Architecture of the App

The application follows the MVP (Model-View-Presenter) architecture:

- **Model**: Represents the data and the business logic of the application. It includes classes for StackOverflow users and their reputation history.
- **View**: Contains the user interface of the application. It displays the list of users, their reputation history, and the bookmarked users.
- **Presenter**: Acts as an interface between Model and View. It processes all the business logic and incoming requests, manipulates data using the Model component, and interacts with the Views to render the final output.

## Personal Reasoning When Choosing a Third-Party Library
1. GSON library: used to convert Java Objects into their JSON representation or the opposite
2. JavaFX: is an open source, next generation client application platform for desktop, mobile and embedded systems built on Java.


