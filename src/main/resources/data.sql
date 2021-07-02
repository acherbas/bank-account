
-- CREATE CLIENT
INSERT INTO client (id, address, birth_date, full_name, phone, email) VALUES (1, '26 rue Victor Hugo', '1974-04-01', 'Martin Durant', '0611223344', 'jDurant@sg.fr');

-- CREATE BANK_ACCOUNT
INSERT INTO account (balance, creation_date, client_id) VALUES (500, NOW(), 1);

-- CREATE OPERATION
INSERT INTO account_op (amount, operation_date, operation_type, account_id) VALUES
    (1000, NOW(), 'DEPOSIT', 1),
    (-100, NOW(), 'WITHDRAWAL', 1),
    (-200, NOW(), 'WITHDRAWAL', 1);