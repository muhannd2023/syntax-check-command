CREATE TABLE "USERS" (
                         id INT NOT NULL AUTO_INCREMENT,
                         email VARCHAR(50),
                         password VARCHAR(80),
                         firstName VARCHAR(50),
                         lastName VARCHAR(50),
                         registrationDateTime TIMESTAMP,
                         PRIMARY KEY (id)
);

CREATE TABLE "SESSION" (
                           id INT NOT NULL AUTO_INCREMENT,
                           userId INT,
                           startTime TIMESTAMP,
                           endTime TIMESTAMP,
                           sessionKey VARCHAR(80),
                           PRIMARY KEY (id),
                           FOREIGN KEY(userId) REFERENCES USERS(id)
);

CREATE TABLE "QUESTION_CATEGORY" (
                                     id INT NOT NULL AUTO_INCREMENT,
                                     categoryName VARCHAR(250),
                                     categoryExamLength INT,
                                     maxMark INT,
                                     questionNumber INT,
                                     PRIMARY KEY (id)
);

CREATE TABLE "QUESTION_OPTION" (
                                   id INT NOT NULL AUTO_INCREMENT,
                                   optionText VARCHAR(250),
                                   correct BIT,
                                   PRIMARY KEY (id)
);

CREATE TABLE "QUESTION" (
                            id INT NOT NULL AUTO_INCREMENT,
                            questionText VARCHAR(250),
                            categoryId INT,
                            questionMark INT,
                            answer VARCHAR(100),
                            questionOrder INT,
                            PRIMARY KEY (id)
);

CREATE TABLE "OPTIONS_TO_QUESTIONS" (
                                        id INT NOT NULL AUTO_INCREMENT,
                                        optionId INT,
                                        questionId INT,
                                        FOREIGN KEY (optionId) REFERENCES QUESTION_OPTION(id),
                                        FOREIGN KEY (questionId) REFERENCES QUESTION(id)
);

CREATE TABLE "EXAM" (
                        id INT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(100),
                        categoryId INT,
                        mark INT,
                        startTime TIMESTAMP,
                        endTime TIMESTAMP,
                        questions INT,
                        PRIMARY KEY (id)
);

CREATE TABLE "QUESTIONS_TO_EXAM" (
                                     id INT NOT NULL AUTO_INCREMENT,
                                     examId INT,
                                     questionId INT,
                                     FOREIGN KEY (examId) REFERENCES EXAM(id),
                                     FOREIGN KEY (questionId) REFERENCES QUESTION(id)
);

CREATE TABLE "EXAM_RESULT" (
                               id INT NOT NULL AUTO_INCREMENT,
                               userId INT,
                               examId INT,
                               mark INT,
                               maxMark INT,
                               PRIMARY KEY (id),
                               FOREIGN KEY (userId) REFERENCES USERS(id),
                               FOREIGN KEY (examId) REFERENCES EXAM(id)
);