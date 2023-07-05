create table products(
                         id serial primary key,
                         p_name varchar(20),
                         p_price int

);

create table customers (
                           id serial primary key,
                           c_first_name varchar(20),
                           c_last_name varchar(20),
                           c_company varchar(20)
);

create table stores (
                        id serial primary key,
                        s_name varchar(20),
                        s_address varchar(20)

);
create table list_products (
                               stores_id integer,
                               foreign key  (stores_id)references stores(id),
                               products_id integer,
                               foreign key  (products_id)references products(id)
);
create table orders (
                        stores_id integer,
                        foreign key  (stores_id)references stores(id),
                        products_id integer,
                        foreign key  (products_id)references products(id),
                        customers_id integer,
                        foreign key  (customers_id)references customers(id)

);