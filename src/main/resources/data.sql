-- Populate players table with sample data
INSERT INTO players (email, level, age, gender) VALUES
('player1@example.com', 7, 25, 'Male'),
('player2@example.com', 10, 30, 'Female'),
('player3@example.com', 5, 20, 'Male'),
('player4@example.com', 6, 28, 'Female'),
('player5@example.com', 10, 35, 'Male'),
('player6@example.com', 5, 21, 'Male');


-- Populate sports table with sample data
INSERT INTO sports (name) VALUES
('tennis'),
('soccer'),
('basketball'),
('badminton');

-- Establish many-to-many relationship between players and sports
-- Player 1 plays tennis, basketball, badminton
INSERT INTO Players_Sports (player_id, sport_id) VALUES
((SELECT player_id FROM players WHERE email = 'player1@example.com'), (SELECT sport_id FROM Sports WHERE name = 'tennis')),
((SELECT player_id FROM players WHERE email = 'player1@example.com'), (SELECT sport_id FROM Sports WHERE name = 'basketball')),
((SELECT player_id FROM players WHERE email = 'player1@example.com'), (SELECT sport_id FROM Sports WHERE name = 'badminton'));

-- Player 2 plays soccer, basketball
INSERT INTO Players_Sports (player_id, sport_id) VALUES
((SELECT player_id FROM players WHERE email = 'player2@example.com'), (SELECT sport_id FROM Sports WHERE name = 'soccer')),
((SELECT player_id FROM players WHERE email = 'player2@example.com'), (SELECT sport_id FROM Sports WHERE name = 'basketball'));

-- Player 3 plays tennis, soccer, badminton
INSERT INTO Players_Sports (player_id, sport_id) VALUES
((SELECT player_id FROM players WHERE email = 'player3@example.com'), (SELECT sport_id FROM Sports WHERE name = 'tennis')),
((SELECT player_id FROM players WHERE email = 'player3@example.com'), (SELECT sport_id FROM Sports WHERE name = 'soccer')),
((SELECT player_id FROM players WHERE email = 'player3@example.com'), (SELECT sport_id FROM Sports WHERE name = 'badminton'));

-- Player 4 plays tennis
INSERT INTO Players_Sports (player_id, sport_id) VALUES
((SELECT player_id FROM players WHERE email = 'player4@example.com'), (SELECT sport_id FROM Sports WHERE name = 'tennis'));

-- Player 5 plays tennis, soccer, basketball, badminton
INSERT INTO Players_Sports (player_id, sport_id) VALUES
((SELECT player_id FROM players WHERE email = 'player5@example.com'), (SELECT sport_id FROM Sports WHERE name = 'tennis')),
((SELECT player_id FROM players WHERE email = 'player5@example.com'), (SELECT sport_id FROM Sports WHERE name = 'soccer')),
((SELECT player_id FROM players WHERE email = 'player5@example.com'), (SELECT sport_id FROM Sports WHERE name = 'basketball')),
((SELECT player_id FROM players WHERE email = 'player5@example.com'), (SELECT sport_id FROM Sports WHERE name = 'badminton'));