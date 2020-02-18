delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;
delete from Ingredient;
delete from Delivery_Box;

insert into Ingredient (id, name, type) values ('FLTO','Flour Tortilla','WRAP');
insert into Ingredient (id, name, type) values ('NEW1','Yellow Corn','WRAP');
insert into Ingredient (id, name, type) values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type) values ('GRBF', 'Ground Beef', 'PROTEIN'); 
insert into Ingredient (id, name, type) values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type) values ('NEWP', 'Pork', 'PROTEIN');
insert into Ingredient (id, name, type) values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type) values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type) values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type) values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type) values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type) values ('SRCR', 'Sour Cream', 'SAUCE');

insert into Delivery_Box (id, color, size) values ('Red small box', 'Red small box', 'small');
insert into Delivery_Box (id, color, size) values ('Green small box', 'Green small box', 'small');
insert into Delivery_Box (id, color, size) values ('Red medium box', 'Red medium box', 'medium');
insert into Delivery_Box (id, color, size) values ('Green medium box', 'Green medium box', 'medium');
insert into Delivery_Box (id, color, size) values ('Red big box', 'Red big box', 'big');
insert into Delivery_Box (id, color, size) values ('Green big box', 'Green big box', 'big');
