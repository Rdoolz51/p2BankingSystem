  INSERT INTO roles (title) VALUES ('Admin'), ('Customer');

  INSERT INTO states (name) VALUES
      ('Alabama'),
      ('Alaska'),
      ('Arizona'),
      ('Arkansas'),
      ('California'),
      ('Colorado'),
      ('Connecticut'),
      ('Delaware'),
      ('Florida'),
      ('Georgia'),
      ('Hawaii'),
      ('Idaho'),
      ('Illinois'),
      ('Indiana'),
      ('Iowa'),
      ('Kansas'),
      ('Kentucky'),
      ('Louisiana'),
      ('Maine'),
      ('Maryland'),
      ('Massachusetts'),
      ('Michigan'),
      ('Minnesota'),
      ('Mississippi'),
      ('Missouri'),
      ('Montana'),
      ('Nebraska'),
      ('Nevada'),
      ('New Hampshire'),
      ('New Jersey'),
      ('New Mexico'),
      ('New York'),
      ('North Carolina'),
      ('North Dakota'),
      ('Ohio'),
      ('Oklahoma'),
      ('Oregon'),
      ('Pennsylvania'),
      ('Rhode Island'),
      ('South Carolina'),
      ('South Dakota'),
      ('Tennessee'),
      ('Texas'),
      ('Utah'),
      ('Vermont'),
      ('Virginia'),
      ('Washington'),
      ('West Virginia'),
      ('Wisconsin'),
      ('Wyoming');


  INSERT INTO zip_codes (zip_code) VALUES
      (73460),
      (70317),
      (88460),
      (28754),
      (66642),
      (77368),
      (57366),
      (73834),
      (83917),
      (11295),
      (53638),
      (89212),
      (44750),
      (36684),
      (29344),
      (53603),
      (26835),
      (57746),
      (45286),
      (20592),
      (67624),
      (50481),
      (36545),
      (13536),
      (36247),
      (98692),
      (94762),
      (34911),
      (88718),
      (31220),
      (70177),
      (59013),
      (68929),
      (27517),
      (33039),
      (28419),
      (60970),
      (68438),
      (81655),
      (21571),
      (35197),
      (98213),
      (26618),
      (52477),
      (13396),
      (70948),
      (73821),
      (41881),
      (77546),
      (24446);


  INSERT INTO addresses (city, street, state_state_id, zip_zip_id) VALUES
      ('Perpignan', '28409 Dovetail Way', 1, 1),
      ('Shizukuishi', '65377 Independence Point', 2, 2),
      ('Caopie', '6984 John Wall Street', 3, 3),
      ('Pradera', '45 Mccormick Center', 4, 4),
      ('Próti', '5887 Esch Drive', 5, 5),
      ('Jiangdong', '284 Sunfield Alley', 6, 6),
      ('Votuporanga', '220 Dawn Drive', 7, 7),
      ('Puerto Plata', '7104 Daystar Parkway', 8, 8),
      ('Wierzchowo', '4247 Fieldstone Lane', 9, 9),
      ('Armação de Pêra', '88935 Westport Alley', 10, 10),
      ('Arendal', '3232 Caliangt Road', 11, 11),
      ('Batujaran', '2765 Scott Alley', 12, 12),
      ('Banawang', '78141 Shasta Terrace', 13, 13),
      ('Uummannaq', '96817 Lillian Trail', 14, 14),
      ('Uddiawan', '8602 Algoma Circle', 15, 15),
      ('Benavila', '968 Schiller Road', 16, 16),
      ('Prusice', '4 Thompson Center', 17, 17),
      ('Huangliu', '20085 Chinook Crossing', 18, 18),
      ('Nizhniy Kurkuzhin', '93 Stuart Circle', 19, 19),
      ('Phultala', '69 Merchant Junction', 20, 20);

  INSERT INTO addresses (city, street, state_state_id, zip_zip_id)
  VALUES ('Sporg', '123 Sprog Lane', 2, 2);

  INSERT INTO users (email, first_name, last_name, password, phone_number, income, role_role_id, address_address_id)
  VALUES
      ('qhortop0@indiatimes.com', 'Quinlan', 'Hortop', 'nxznohip,$$', '8434816389', '3370.2', 2, 1),
      ('fevetts1@parallels.com', 'Finlay', 'Evetts', 'eabuszwgg<P_P~', '3011373299', '6532.32', 2, 2),
      ('sdicarlo2@myspace.com', 'Selestina', 'Di Carlo', 'qroksnsiHz', '4675001098', '1794.16', 2, 3),
      ('gnavarro3@nydailynews.com', 'Gale', 'Navarro', 'taldvinra{<J>X9', '5884678983', '4099.85', 2, 4),
      ('bphillot4@youtube.com', 'Brander', 'Phillot', 'hpjyoqrk', '6235513439', '1959.22', 2, 5),
      ('trockhall5@imageshack.us', 'Thorstein', 'Rockhall', 'xfazkwic%l%|1', '1674586990', '6317.94', 2, 6),
      ('rbaggaley6@aol.com', 'Ruddie', 'Baggaley', 'rcwyqsovK}|#V', '7254280687', '802.52', 2, 7),
      ('sgullen7@wired.com', 'Shanta', 'Gullen', 'byqvgawnh=U|wiT*', '5582783799', '1941.27', 2, 8),
      ('aarman8@mozilla.com', 'Aldrich', 'Arman', 'mqxfwsoe21Wj&Be', '7338490248', '5556.44', 2, 9),
      ('jpedrielli9@stanford.edu', 'Josie', 'Pedrielli', 'ukdgaihy', '3288654946', '2938.84', 2, 10),
      ('hkantora@cocolog-nifty.com', 'Herman', 'Kantor', 'vbfvnauljY"@/e', '2484618841', '5283.39', 2, 11),
      ('kburdikinb@java.com', 'Kippar', 'Burdikin', 'creavbjz(b6{C?', '9856155141', '5536.53', 2, 12),
      ('twalfordc@seesaa.net', 'Travers', 'Walford', 'ucjijaxj', '8836819011', '7853.3', 2, 13),
      ('kkatzd@last.fm', 'Karia', 'Katz', 'ybfxkndeMFBsgE+', '9531044194', '4550.31', 2, 14),
      ('tleabeatere@delicious.com', 'Theo', 'Leabeater', 'dkuiweqka', '5802793907', '8819.88', 2, 15),
      ('mcarrivickf@quantcast.com', 'Minta', 'Carrivick', 'zohtufplyE', '3588774528', '1801.97', 2, 16),
      ('cblaesg@yelp.com', 'Carlina', 'Blaes', 'fucckvvwv)Z', '3406545280', '2872.3', 2, 17),
      ('bsalth@weebly.com', 'Bette-ann', 'Salt', 'nclsqfhxG"@4"bZ', '9009785150', '420.1', 2, 18),
      ('chavesidesi@a8.net', 'Cassy', 'Havesides', 'rqsvjzatEz', '8881237048', '6558.2', 2, 19),
      ('bvlasyukj@auda.org.au', 'Bil', 'Vlasyuk', 'depqxyxo', '9438034230', '6869.99', 2, 20),
      ('admin@admin.com', 'Mr.', 'Admin', 'password', '1234567687', NULL, 1, 21);


  UPDATE users SET "password" = '$2a$12$xOMv4nukmMExAh3aAr1pLOJdhGY1z/7MNLnlyMHdU0DLr9ErbEbK.';

  INSERT INTO loan_types (type) VALUES
      ('Personal'),
      ('Business'),
      ('Home'),
      ('Vehicle');


  INSERT INTO loans (interest_rate, loan_amount, loan_balance, type_type_id, user_user_id) VALUES
      ('0.05', '1000.00', '1000.00', 3, 3),
      ('0.07', '2000.00', '1800.00', 2, 7),
      ('0.06', '1500.00', '1400.00', 1, 11),
      ('0.08', '3000.00', '2700.00', 2, 2),
      ('0.04', '2500.00', '2000.00', 1, 15),
      ('0.07', '4000.00', '3800.00', 4, 10),
      ('0.05', '1800.00', '1800.00', 3, 4),
      ('0.09', '3500.00', '3200.00', 1, 18),
      ('0.06', '2200.00', '2000.00', 2, 13),
      ('0.07', '5000.00', '4700.00', 1, 9),
      ('0.05', '4000.00', '3800.00', 3, 6),
      ('0.08', '6000.00', '5500.00', 4, 8),
      ('0.06', '3200.00', '3000.00', 2, 5),
      ('0.07', '4500.00', '4200.00', 1, 13),
      ('0.04', '1800.00', '1500.00', 2, 14),
      ('0.08', '7000.00', '6500.00', 3, 12),
      ('0.05', '2800.00', '2600.00', 4, 4),
      ('0.09', '5200.00', '4800.00', 1, 1),
      ('0.06', '3800.00', '3500.00', 2, 20),
      ('0.07', '5500.00', '5000.00', 3, 17);


  INSERT INTO credit_cards (annual_fee, balance, card_expiration, card_number, credit_limit, interest_rate, user_user_id) VALUES
      ('75.00', '500.00', '12/23', '1234567890123456', '2500.00', '0.15', 3),
      ('90.00', '1000.00', '10/25', '2345678901234567', '8000.00', '0.12', 7),
      ('60.00', '800.00', '07/25', '3456789012345678', '12000.00', '0.18', 11),
      ('80.00', '300.00', '06/25', '4567890123456789', '4000.00', '0.14', 2),
      ('50.00', '200.00', '06/29', '5678901234567890', '2500.00', '0.16', 15),
      ('95.00', '1000.00', '01/28', '6789012345678901', '7000.00', '0.13', 10),
      ('70.00', '700.00', '10/23', '7890123456789012', '8000.00', '0.17', 4),
      ('85.00', '400.00', '10/23', '8901234567890123', '5000.00', '0.19', 18),
      ('55.00', '200.00', '09/28', '9012345678901234', '10000.00', '0.15', 13),
      ('65.00', '900.00', '09/28', '0123456789012345', '7000.00', '0.16', 9);

  INSERT INTO statuses (status) VALUES ('Pending'), ('Approved'), ('Denied');

  UPDATE loans SET status_status_id = 2;
  UPDATE credit_cards SET status_status_id = 2;

  INSERT INTO account_types ("type")
  VALUES ('Savings'),
         ('Checking'),
         ('Travel');

  INSERT INTO accounts (type_type_id, balance, pin, user_user_id) VALUES
      (1, '5000.00', '1234', 1),
      (2, '2500.00', '5678', 2),
      (3, '1000.00', '9012', 3),
      (1, '0.00', '3456', 4),
      (2, '2000.00', '7890', 5),
      (3, '4000.00', '2345', 6),
      (1, '1500.00', '6789', 7),
      (2, '3000.00', '0123', 8),
      (3, '0.00', '4567', 9),
      (1, '5000.00', '8901', 10),
      (2, '1800.00', '2345', 11),
      (3, '3200.00', '6789', 12),
      (1, '2800.00', '0123', 13),
      (2, '4500.00', '4567', 14),
      (3, '0.00', '8901', 15),
      (1, '7000.00', '2345', 16),
      (2, '2600.00', '6789', 17),
      (3, '4800.00', '0123', 18),
      (1, '3500.00', '4567', 19),
      (2, '5000.00', '8901', 20);


  INSERT INTO transaction_types ("type")
  VALUES ('Withdrawal'),
         ('Deposit'),
         ('Fee'),
         ('Debit'),
         ('Credit');

  INSERT INTO transactions (amount, transaction_date, type_type_id, sender_account_account_id, receiver_account_id) VALUES
      ('1000.00', CURRENT_DATE - INTERVAL '2 months', 1, 3, 7),
      ('500.00', CURRENT_DATE - INTERVAL '4 months', 2, 7, 10),
      ('2000.00', CURRENT_DATE - INTERVAL '3 months', 3, 5, 12),
      ('800.00', CURRENT_DATE - INTERVAL '5 months', 1, 4, 9),
      ('1500.00', CURRENT_DATE - INTERVAL '1 month', 2, 2, 15),
      ('300.00', CURRENT_DATE - INTERVAL '2 months', 3, 10, 18),
      ('1200.00', CURRENT_DATE - INTERVAL '3 months', 1, 1, 8),
      ('600.00', CURRENT_DATE - INTERVAL '5 months', 2, 18, 5),
      ('2500.00', CURRENT_DATE - INTERVAL '1 month', 3, 13, 2),
      ('1800.00', CURRENT_DATE - INTERVAL '4 months', 1, 9, 20),
      ('900.00', CURRENT_DATE - INTERVAL '6 months', 2, 6, 14),
      ('4000.00', CURRENT_DATE - INTERVAL '3 months', 3, 8, 17),
      ('700.00', CURRENT_DATE - INTERVAL '2 months', 1, 5, 6),
      ('350.00', CURRENT_DATE - INTERVAL '5 months', 2, 16, 11),
      ('2200.00', CURRENT_DATE - INTERVAL '4 months', 3, 14, 19),
      ('1000.00', CURRENT_DATE - INTERVAL '1 month', 1, 12, 16),
      ('550.00', CURRENT_DATE - INTERVAL '3 months', 2, 19, 3),
      ('2800.00', CURRENT_DATE - INTERVAL '6 months', 3, 6, 1),
      ('1300.00', CURRENT_DATE - INTERVAL '4 months', 1, 20, 4),
      ('400.00', CURRENT_DATE - INTERVAL '2 months', 2, 17, 13);

  INSERT INTO accounts (type_type_id, balance, pin, user_user_id)
  VALUES (2, '4500.00', '1234', 1);