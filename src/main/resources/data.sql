DELETE FROM order_details;
DELETE FROM orders;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM roles;

-- Roles
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('CLIENT');
INSERT INTO roles (name) VALUES ('DELIVERY');

-- Usuarios: password = "melola"
INSERT INTO users (username, password, full_name, email, role_id) VALUES
                                                                      ('admin', '$2a$10$IKp9rdPtsq4/L28Ivj85yOI0nyTRwKX1fHZfXDAKRePHQUD2vATGK', 'Admin Root', 'admin@foodexpress.com', 1),
                                                                      ('juan', '$2a$10$IKp9rdPtsq4/L28Ivj85yOI0nyTRwKX1fHZfXDAKRePHQUD2vATGK', 'Juan Pérez', 'juan@correo.com', 2),
                                                                      ('maria', '$2a$10$IKp9rdPtsq4/L28Ivj85yOI0nyTRwKX1fHZfXDAKRePHQUD2vATGK', 'María López', 'maria@correo.com', 2),
                                                                      ('repa1', '$2a$10$IKp9rdPtsq4/L28Ivj85yOI0nyTRwKX1fHZfXDAKRePHQUD2vATGK', 'Repartidor Uno', 'repa1@correo.com', 3);

-- Restaurantes
INSERT INTO restaurants (name, address, phone) VALUES
                                                   ('Burger Planet', 'Calle Luna 45', '600111222'),
                                                   ('Pasta Nova', 'Av. Italia 12', '600222333'),
                                                   ('Sushi Go', 'Calle Japón 3', '600333444');


-- Platos (unos 15 para paginar)
INSERT INTO dishes (name, price, category, restaurant_id) VALUES
                                                              ('Cheeseburger', 8.50, 'HAMBURGUESAS', 1),
                                                              ('Doble Bacon', 10.90, 'HAMBURGUESAS', 1),
                                                              ('Veggie Burger', 9.20, 'HAMBURGUESAS', 1),
                                                              ('Spaghetti Carbonara', 11.50, 'PASTA', 2),
                                                              ('Lasagna Bolognesa', 12.00, 'PASTA', 2),
                                                              ('Fetuccine Alfredo', 10.75, 'PASTA', 2),
                                                              ('Sushi Maki', 13.50, 'SUSHI', 3),
                                                              ('Nigiri Salmón', 12.90, 'SUSHI', 3),
                                                              ('Tempura', 9.80, 'ENTRANTE', 3),
                                                              ('Patatas Deluxe', 4.50, 'ENTRANTE', 1),
                                                              ('Tiramisú', 5.90, 'POSTRE', 2),
                                                              ('Helado Matcha', 6.20, 'POSTRE', 3),
                                                              ('Onigiri', 7.80, 'ENTRANTE', 3),
                                                              ('Tagliatelle Pesto', 10.20, 'PASTA', 2),
                                                              ('Chicken Burger', 9.80, 'HAMBURGUESAS', 1);


-- Pedidos
-- Pedidos de ejemplo para probar /api/orders

INSERT INTO orders (order_date, status, user_id, restaurant_id) VALUES
                                                                    ('2025-10-01 12:00:00', 'ENTREGADO',   2, 1),
                                                                    ('2025-10-02 13:00:00', 'PREPARANDO',  3, 2),
                                                                    ('2025-10-03 20:15:00', 'ENTREGADO',   2, 3),

-- usuario 1 en varios restaurantes
                                                                    ('2025-09-15 11:00:00', 'CREADO',      1, 1),
                                                                    ('2025-09-16 12:30:00', 'PREPARANDO',  1, 2),
                                                                    ('2025-09-17 19:45:00', 'EN_CAMINO',   1, 3),
                                                                    ('2025-09-18 21:10:00', 'ENTREGADO',   1, 3),
                                                                    ('2025-09-19 09:20:00', 'CANCELADO',   1, 1),

-- usuario 2
                                                                    ('2025-09-20 14:00:00', 'CREADO',      2, 2),
                                                                    ('2025-09-21 15:30:00', 'PREPARANDO',  2, 2),
                                                                    ('2025-09-22 18:10:00', 'EN_CAMINO',   2, 1),
                                                                    ('2025-09-22 20:50:00', 'ENTREGADO',   2, 1),
                                                                    ('2025-09-23 22:15:00', 'CANCELADO',   2, 3),

-- usuario 3
                                                                    ('2025-08-10 10:00:00', 'CREADO',      3, 3),
                                                                    ('2025-08-10 10:30:00', 'PREPARANDO',  3, 3),
                                                                    ('2025-08-10 11:15:00', 'EN_CAMINO',   3, 2),
                                                                    ('2025-08-10 12:00:00', 'ENTREGADO',   3, 2),

-- más combinaciones para filtros por restaurante
                                                                    ('2025-07-01 18:00:00', 'ENTREGADO',   1, 1),
                                                                    ('2025-07-02 18:15:00', 'ENTREGADO',   2, 1),
                                                                    ('2025-07-03 18:30:00', 'CANCELADO',   3, 1),
                                                                    ('2025-07-04 12:20:00', 'CREADO',      1, 2),
                                                                    ('2025-07-05 14:40:00', 'PREPARANDO',  3, 2),

-- algunos más antiguos para probar fechas y listados largos
                                                                    ('2025-06-10 17:00:00', 'EN_CAMINO',   2, 3),
                                                                    ('2025-06-11 19:20:00', 'PREPARANDO',  1, 2),
                                                                    ('2025-06-12 21:10:00', 'ENTREGADO',   3, 1),
                                                                    ('2025-06-13 11:55:00', 'CREADO',      2, 3),
                                                                    ('2025-06-14 13:30:00', 'PREPARANDO',  1, 3),
                                                                    ('2025-06-15 15:45:00', 'ENTREGADO',   3, 2),
                                                                    ('2025-06-16 10:15:00', 'CANCELADO',   2, 1);


-- Detalles pedidos
-- id_order (PK), id_dish (PK), cantidad, subtotal (precio plato x cantidad)
INSERT INTO order_details VALUES (1, 1, 2, 17.00); -- pedidoBurguerKing1, Whopper(1), 2 whopper (cantidad), precio total (8,45 cada whopper)
INSERT INTO order_details VALUES (1, 10, 1, 4.50); -- pedidoBurguerKing1, krispy chicken(10), 1 (cantidad), precio total (4.5)

INSERT INTO order_details VALUES (2, 4, 1, 11.50);
INSERT INTO order_details VALUES (2, 5, 1, 12.00);

INSERT INTO order_details VALUES (3, 7, 1, 13.50);
INSERT INTO order_details VALUES (3, 8, 1, 12.90);
INSERT INTO order_details VALUES (3, 12, 1, 6.20);