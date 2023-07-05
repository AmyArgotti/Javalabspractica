insert into products(p_name, p_price)
values ('apple',35.00),
       ('chips',120.00),
       ('yogurt',70.00),
       ('rice',250.00),
       ('pineapple',355.00),
       ('soap',120.00),
       ('juice',70.00),
       ('energy drink',85.00)
;

insert into customers( c_first_name, c_last_name, c_company)
values ('Sofia','Villalva','KFU'),
       ('Amy','Argotti','KFU'),
       ('Goethe','Ortega','KFU'),
       ('Roxanna','Rodriguez','KGMU'),
       ('Albaraa','Shagat','KFU'),
       ('Carlos','Sanchez','UTA'),
       ('Javier','Lopez','USFQ')
;

insert into stores(s_name, s_address)
values ('ashan','kazan mall'),
       ('lenta','Gabisheva, 32'),
       ('piat','akademika parina'),
       ('magnit','derevnia universiadi')
;

insert into list_products(stores_id, products_id)
values (1,2),
       (1,3),
       (2,3),
       (3,2),
       (1,4),
       (4,4),
       (2,5),
       (2,7),
       (3,4),
       (4,8),
       (4,6),
       (4,3)
;

insert into orders(stores_id, products_id, customers_id)
VALUES (1,1,1),
       (1,2,2),
       (2,3,3),
       (2,4,4),
       (1,4,5),
       (4,4,5),
       (2,5,6),
       (3,7,3),
       (3,4,4),
       (4,8,5),
       (4,6,6),
       (4,3,7)
;