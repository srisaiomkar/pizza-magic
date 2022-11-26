create table if not exists Pizza_Order (
    order_id identity PRIMARY KEY,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);
create table if not exists Pizza (
    pizza_id identity PRIMARY KEY,
    name varchar(50) not null,
    order_id bigint not null,
    sequence_num bigint not null,
    created_at timestamp not null
);
create table if not exists Pizza_Ingredient_Mapping (
    pizza_id bigint not null,
    ingredient_id varchar(4) not null,
    sequence_num bigint not null
);
create table if not exists Ingredient (
    ingredient_id varchar(4) PRIMARY KEY,
    name varchar(25) not null,
    type varchar(10) not null
);
alter table Pizza
add foreign key (order_id) references Pizza_Order(order_id);

alter table Pizza_Ingredient_Mapping
add foreign key (pizza_id) references Pizza(pizza_id);

alter table Pizza_Ingredient_Mapping
add foreign key (ingredient_id) references Ingredient(ingredient_id);