-- Insert user data
-- Here we add a user which will be associated with the comic book entries.
-- WARNING: In a real application, ensure that this password is hashed!
INSERT INTO users (username, password) VALUES ('gaebar', 'testPassword');

-- Insert comic book data
-- These entries are linked to the user inserted above (assuming the first user gets an ID of 1).
-- We specify the comic book details along with the foreign key that points to the user table.
INSERT INTO comics (title, release_date, author, user_id) VALUES ('The Amazing Spider-Man', '1962-03-01', 'Stan Lee', 1);
INSERT INTO comics (title, release_date, author, user_id) VALUES ('Batman', '1939-05-01', 'Bob Kane', 1);
