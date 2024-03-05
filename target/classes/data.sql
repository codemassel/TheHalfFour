INSERT INTO CITIES(zipcode, city) VALUES('55385', 'Bornheim');
--INSERT INTO CUSTOMER(first_name, last_name, email_address, password, zipcode, street, house_number) VALUES(
--                     'Massel', 'DaDon', 'masseldadon@gmail.com', 'test123', '55385', 'Auf dem Berg', '1');
INSERT INTO shopitem (id, item_Name, item_Price, description, is_visible) VALUES
(1, 'Billoyacht mit Insel für Klimakleber', 70000, 'Für Klimakleber, die ein grünes Herz haben', true),
(2, 'Bornheim 385 Ghetto-Bitchgrabbler', 5000000, 'Yacht ist Yacht. Wenn ihr einfach nur flexen wollt um heiße Frauen klarzumachen.', true),
(3, 'Versunkene Titanic Yacht', 10000000, 'Als die Titanic unterging, gab es Gerüchte über eine Yacht, die der Titanic hinterher segelte. Mit diesem Sammlerstück erweitern Sie Ihre Yachtsammlung um ein unabdingbares Sammlerstück.', true),
(4, 'Die nachbebaute Yacht von Jeffrey Bezos', 499999999, 'Amazon ist Kult. Unsere Schiffsbauer haben kein Detail ausgelassen. Für Kultinspirierte Amazon Amateure ist diese Yacht ein passender Einstieg in die Welt der Multi-Milliardäre!', true),
(5, 'Luxus Yacht für Schildkrötenliebhaber', 10000000, 'Diese Yacht ist speziell für Schildkrötenliebhaber konzipiert. Sie bietet luxuriöse Unterkünfte für Ihre Schildkrötenfreunde und verfügt über einen eigenen Schildkrötenpool.', true),
(6, 'Raumschiff Yacht "Enterprise"', 200000000, 'Diese Yacht ist nicht von dieser Welt! Mit ihrem futuristischen Design und ihren fortschrittlichen Funktionen ist sie bereit, neue Galaxien zu erkunden. Beamen Sie sich an Bord und erleben Sie das Abenteuer Ihres Lebens!', true),
(7, 'Yacht des untoten Kapitäns', 6666666, 'Diese Yacht ist das perfekte Versteck für untote Kapitäne und ihre Crew. Mit ihrem geheimen Piratenschatz an Bord können Sie in den Sonnenuntergang segeln und nach verborgenen Schätzen suchen.', true),
(8, 'Yacht der Superhelden', 9999999, 'Diese Yacht ist der geheime Hauptstützpunkt der Superheldenliga! Mit ihrem High-Tech-Labor und ihrem Trainingsraum ist sie bereit, die Welt vor jeder Bedrohung zu retten. Werden Sie Teil des Teams und kämpfen Sie für Gerechtigkeit!', true);

--INSERT INTO ORDERS(customer_id, shopitems, priority, status, discount, creation_date, shipment_date, completion_date)
--VALUES(1, 2, 1, 'RECEIVED', 0.0, '2024-02-29 11:00:00', '2024-03-01 11:00:00', '2024-03-02 00:00:00')