insert into towns(name, population)
values ('Sofia', 1265211),
       ('Plovdiv', 346790),
       ('Varna', 335578),
       ('Burgas', 193017),
       ('Ruse', 146609);

INSERT INTO items (description, name, picture_url, price, category_id, uploaded_date)
VALUES
    ('Comfortable and stylish sofa for your living room.', 'Sofa', 'http://example.com/sofa.jpg', 299.99, 1, NOW()),
    ('Ideal for your daily run or workout sessions.', 'Running Shoes', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/27d6d436-b35e-43f1-9a33-e41485d9006a/omni-multi-court-older-indoor-court-shoes-T0b6gm.png', 89.99, 2, NOW()),
    ('A high-quality smartphone with the latest features.', 'Smartphone', 'http://example.com/smartphone.jpg', 599.99, 3, NOW()),
    ('A complete painting set for your artistic endeavors.', 'Painting Set', 'http://example.com/paintingset.jpg', 19.99, 4, NOW()),
    ('Brew the perfect cup of coffee with this coffee maker.', 'Coffee Maker', 'http://example.com/coffeemaker.jpg', 49.99, 1, NOW()),
    ('Enjoy playing basketball with this durable basketball.', 'Basketball', 'https://www.google.com/imgres?imgurl=https%3A%2F%2Ftarget.scene7.com%2Fis%2Fimage%2FTarget%2FGUEST_20affc7e-e0d7-4eb6-a6f3-68d13520f8be%3Fwid%3D488%26hei%3D488%26fmt%3Dpjpeg&tbnid=Kvm9AGTXccOD7M&vet=12ahUKEwjtr7y1uq2CAxVSiv0HHaHQD9gQMygBegQIARBC..i&imgrefurl=https%3A%2F%2Fwww.target.com%2Fp%2Fwilson-nba-size-7-basketball%2F-%2FA-82153660&docid=fCt07MVPQF-M1M&w=488&h=488&q=basketball&ved=2ahUKEwjtr7y1uq2CAxVSiv0HHaHQD9gQMygBegQIARBC', 29.99, 2, NOW()),
    ('A powerful laptop for all your computing needs.', 'Laptop', 'http://example.com/laptop.jpg', 999.99, 3, NOW()),
    ('Start your musical journey with this high-quality guitar.', 'Guitar', 'http://example.com/guitar.jpg', 199.99, 4, NOW()),
    ('Make delicious toasts with this reliable toaster.', 'Toaster', 'http://example.com/toaster.jpg', 39.99, 1, NOW()),
    ('Get ready for your outdoor adventures with this hiking gear.', 'Hiking Gear', 'http://example.com/hikinggear.jpg', 129.99, 5, NOW());