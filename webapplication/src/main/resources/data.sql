INSERT INTO USER
    (version,email,enabled,locked,password,username)
VALUES
    (0, 'admin@test.com', true, false, '{noop}123456', 'admin');


INSERT INTO user_role
    (role,user_id)
VALUES
    ( '0', '1'),
    ( '1', '1'),
    ( '2', '1');