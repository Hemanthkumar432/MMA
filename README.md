**Application Setup Document: MMA Application Setup Guide**

Part A: Setting up the MMA Application

1. Introduction

This document provides a comprehensive guide for setting up the Mixed Martial Arts (MMA) application. The application utilizes a MySQL database to manage fighters, events, fights, and rankings.

2. Prerequisites

Before proceeding, ensure you have the following tools and resources:

- Java 17 or newer
- MySQL version 8.0.32
- Intellij IDEA integrated development environment



3.MySQL Database Setup

Open your MySQL client or command line.
Execute the following MySQL script to create the necessary tables:


 

CREATE TABLE Fighter (
    fighter_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL,
    nick_name VARCHAR(255) NOT NULL,
    age INT,
    weight_class VARCHAR(255),
    nationality VARCHAR(255),
    team_affiliation VARCHAR(100) DEFAULT 'MMA FACTORY',
    weight DECIMAL(5, 2),
    height DECIMAL(5, 2),
    last_fight DATE,
    total_fights INT DEFAULT 0,
    total_wins INT DEFAULT 0,
    total_losses INT DEFAULT 0,
    total_knockouts INT DEFAULT 0,
    total_submissions INT DEFAULT 0
);

 

CREATE TABLE Event (
    event_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    event_title VARCHAR(100) NOT NULL,
    event_date DATE,
    location VARCHAR(100),
    INDEX idx_event_date (event_date),  
    INDEX idx_location (location)
);


CREATE TABLE Fight (
    fight_id INT PRIMARY KEY AUTO_INCREMENT,
    fighter_id BIGINT,
    event_id BIGINT,
    opponent_id BIGINT,
    location VARCHAR(100),
    fight_outcome ENUM('NotStarted','Win', 'Loss', 'Draw','Knockouts') NOT NULL,
    fight_date DATE,
    round INT,
    timing TIME,
    FOREIGN KEY (fighter_id) REFERENCES Fighter(fighter_id),
    FOREIGN KEY (opponent_id) REFERENCES Fighter(fighter_id),
    FOREIGN KEY (event_id) REFERENCES Event(event_id),
    FOREIGN KEY (fight_date) REFERENCES Event(event_date),
    FOREIGN KEY (location) REFERENCES Event(location)
);


CREATE TABLE Rankings (
    ranking_id INT PRIMARY KEY AUTO_INCREMENT,
    fighter_id BIGINT,
    weight_class VARCHAR(20),
    rank_num INT,
    total_wins INT,
    recent_wins INT,
    total_loss INT,
    total_draws INT,
    total_knockouts INT,
    total_points INT,
    last_updated DATE,
    year INT,
    FOREIGN KEY (fighter_id) REFERENCES Fighter(fighter_id)
);



4. Project Cloning

Clone the project from the following repository URL: https://github.com/Hemanthkumar432/MMA

 Importing Project

1. Open Intellij IDEA.
2. Navigate to "File" > "Open" and choose the cloned project directory.
3. Click "Open."

5. Configuring MySQL

1. Locate the `application.properties` file within the project.
2. Update the MySQL connection properties:
   - `spring.datasource.url`: Set your MySQL database's JDBC URL.
   - `spring.datasource.username`: Enter your MySQL database username.
   - `spring.datasource.password`: Provide your MySQL database password.

6. Building the Solution

Build the project by using the "Build" button within Intellij IDEA or through command-line tools.

7. Running the Application

1. Find the `ContentSharingPlatformApplication.java` file in the project.
2. Click the Play button next to the `ContentSharingPlatformApplication` class.
3. The application will start running on port 9001.
 
Part B: API Endpoints

1. Fighter Endpoints

- Adding a Fighter (POST):
  - URL: http://localhost:9001/api/v1/fighter/add

  - Body:
    ```json
    {
        "fullName": "Maria Martinez",
        "nickName": "The Gladiator",
        "age": 27,
        "weightClass": "Flyweight",
        "weight": 125.0,
        "height": 160.0,
        "nationality": "Brazil",
        "teamAffiliation": "Nova Gym"
    }
    ```





 Retrieving Fighters (GET):
  - URL: http://localhost:9001/api/v1/fighter/all


- Editing Fighter Information (PUT):
  - URL: http://localhost:9001/api/v1/fighter/edit/1
  - Body:
    ```json
    {
         "fullName": "Hemanth",
        "nickName": "The Terminator",
        "age": 34,
        "weightClass": "Bantamweight",
        "weight": 135.0,
        "height": 172.0,
        "nationality": "Brazil",
        "teamAffiliation": "American Top Team"
    }


- Deleting a Fighter (DELETE):
  - URL: http://localhost:9001/api/v1/fighter/delete/2




2. Event Endpoints

- Retrieving Events (GET):
  - URL: http://localhost:9001/api/v1/event/all



- Adding an Event (POST):
  - URL: http://localhost:9001/api/v1/event/add
  - Body:
    ```json
    {
        "eventTitle": "UFC Clash",
        "eventDate": "2023-11-15",
        "location": "New York"
   }


- Editing an Event (PUT):
  - URL: http://localhost:9001/api/v1/event/edit/1
  - Body:
    ```json
    {
        "eventTitle": "UFC Conference",
        "eventDate": "2023-09-15",
        "location": "Bengaluru-560100"
    }
   

- Deleting an Event (DELETE):
  - URL: http://localhost:9001/api/v1/event/delete/{id}




3. Fight Endpoints
- Retrieving Fights (GET):
  - URL: http://localhost:9001/api/v1/fight/all

- Adding a Fight (POST):
  - URL: http://localhost:9001/api/v1/fight/add

  - Body:
    ```json
    {
        "fighterNickName": "The Terminator",
        "opponentNickName": "The Machine",
        "event": "UFC Conference",
        "round": 5,
        "timing": "22:30:00",
        "fightOutcome": "Loss"
    }

- Editing a Fight (PUT):
  - URL: http://localhost:9001/api/v1/fight/edit/1
  - Body:json
    {       "fighterNickName": "The Terminator",
        "opponentNickName": "The Lioness",
        "event": "UFC Conference",
        "round": 3,
        "timing": "22:00:00",
        "fightOutcome": "NotStarted"
   }
- Deleting a Fight (DELETE):
  - URL: http://localhost:9001/api/v1/fight/delete/{fightId}



4. Retrieving Fight Statistics and Upcoming Fights

- Retrieving Fight Statistics (GET):
  - URL: http://localhost:9001/api/v1/fight/fight-statistics/{id}

- Retrieving Upcoming Fights (GET):
  - URL: http://localhost:9001/api/v1/fight/upcoming-fights/{id}


5. Ranking Endpoints

- Updating All Rankings (GET):
  - URL: http://localhost:9001/api/v1/ranking/update-all

- Updating Specific Ranking (POST):
  - URL: http://localhost:9001/api/v1/ranking/update/{id}

Conclusion

You are now ready to proceed with your testing. By following these steps and using the provided API endpoints, you can seamlessly set up and engage with the MMA application. Best of luck with your testing efforts!
