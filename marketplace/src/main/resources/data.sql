INSERT INTO users (confirmed_email, email, first_name, last_name, ip_address, password, username)
VALUES
    (true, 'user1@example.com', 'John', 'Doe', '192.168.0.1', 'John', 'john_doe'),
    (true, 'user2@example.com', 'Jane', 'Smith', '192.168.0.2', 'Jane', 'jane_smith'),
    (true, 'user3@example.com', 'Mike', 'Johnson', '192.168.0.3', 'Mike', 'mike_johnson'),
    (true, 'user4@example.com', 'Emily', 'Brown', '192.168.0.4', 'Emily', 'emily_brown'),
    (true, 'user5@example.com', 'Alex', 'Davis', '192.168.0.5', 'Alex', 'alex_davis'),
    (true, 'user6@example.com', 'Rachel', 'Miller', '192.168.0.6', 'Rachel', 'rachel_miller'),
    (true, 'user7@example.com', 'Tom', 'Wilson', '192.168.0.7', 'Tom', 'tom_wilson'),
    (true, 'user8@example.com', 'Emma', 'Taylor', '192.168.0.8', 'Emma', 'emma_taylor'),
    (true, 'user9@example.com', 'Chris', 'Jones', '192.168.0.9', 'Chris', 'chris_jones'),
    (true, 'user10@example.com', 'Sophia', 'Clark', '192.168.0.10', 'Sophia', 'sophia_clark');

INSERT INTO files (content_type, file_data, file_name)
VALUES
    ('image/png',LOAD_FILE('initImages/50145-3-dishwasher-png-image-high-quality.png'),'dishwasher.png'),
    ('image/png',LOAD_FILE('initImages/classic-chair-png-3.png'),'chair.png'),
    ('image/png',LOAD_FILE('initImages/kisspng-the-chow-chow-puppy-keeshond-akita-chow-chow-dog-5b160cb9660cd3.303269271528171705418.jpg'),'chowchow.png'),
    ('image/png',LOAD_FILE('initImages/png-clipart-table-furniture-wood-table-angle-simple.png'),'table.png'),
    ('image/png',LOAD_FILE('initImages/png-transparent-guitar-guitar-guitar-accessory-cuatro-desktop-wallpaper-thumbnail.png'),'guitar.png'),
    ('image/png',LOAD_FILE('initImages/png-transparent-microwave-ovens-home-appliance-odessa-microwave-kitchen-electronics-kitchen-appliance.png'),'microwave.png'),
    ('image/png',LOAD_FILE('initImages/png-transparent-pair-of-white-and-red-running-shoes-illustration-shoe-running-sneakers-clothing-nike-running-shoes-blue-white-sport-thumbnail.png'),'shoesNike.png'),
    ('image/png',LOAD_FILE('initImages/png-transparent-shoe-nike-air-max-sneakers-running-running-shoes-orange-outdoor-shoe-converse-thumbnail.png'),'shoesConverse.png'),
    ('image/png',LOAD_FILE('initImages/pngimg.com - fishing_pole_PNG10580.png'),'fishingRod.png'),
    ('image/png',LOAD_FILE('initImages/pod-igoto.jpg'),'podIgoto.png');

INSERT INTO items (description, name, price, uploaded_date, category_id, image_id, seller_id)
VALUES
    ('High-efficiency dishwasher', 'Dishwasher', 599.99, CURRENT_DATE, 1, 1, 3),
    ('Comfortable chair for your living room', 'Chair', 149.99, CURRENT_DATE, 1, 2, 4),
    ('Adorable Chow Chow plush toy', 'Chow Chow Plush Toy', 19.99, CURRENT_DATE, 4, 3, 5),
    ('Modern and sturdy table for your home', 'Table', 299.99, CURRENT_DATE, 1, 4, 6),
    ('Classic acoustic guitar for music enthusiasts', 'Acoustic Guitar', 399.99, CURRENT_DATE, 4, 5, 7),
    ('Microwave oven with advanced features', 'Microwave Oven', 129.99, CURRENT_DATE, 3, 6, 8),
    ('Nike sports shoes for running', 'Nike Running Shoes', 89.99, CURRENT_DATE, 2, 7, 9),
    ('Converse casual shoes for everyday wear', 'Converse Casual Shoes', 59.99, CURRENT_DATE, 5, 8, 10),
    ('High-quality fishing rod for fishing enthusiasts', 'Fishing Rod', 79.99, CURRENT_DATE, 2, 9, 11),
    ('Podcast microphone for quality recording', 'Podcasting Microphone', 129.99, CURRENT_DATE, 3, 10, 12),
    ('Unique and stylish item', 'Unique Item', 49.99, CURRENT_DATE, 5, 11, 13);

INSERT INTO users_role_entities (users_id, role_entities_id)
VALUES
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 2),
    (8, 2),
    (9, 2),
    (10, 2),
    (11, 2),
    (12, 2),
    (13, 2);