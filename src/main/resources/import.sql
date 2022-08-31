-- Restaurants
INSERT INTO "public"."restaurant" ("id", "address", "name") VALUES ('1','Belem','Govinda');
INSERT INTO "public"."restaurant" ("id", "address", "name") VALUES ('2','Sao Paulo','Fogo de Chao');
INSERT INTO "public"."restaurant" ("id", "address", "name") VALUES ('3','Salvador','Origem');
INSERT INTO "public"."restaurant" ("id", "address", "name") VALUES ('4','Fortaleza','Casa Patua');

-- Products
INSERT INTO "public"."product" ("id", "name", "price", "restaurant_id") VALUES ('1','Manicoba vegana','25','1');
INSERT INTO "public"."product" ("id", "name", "price", "restaurant_id") VALUES ('2','Cordeiro Assado','40','2');
INSERT INTO "public"."product" ("id", "name", "price", "restaurant_id") VALUES ('3','Petit Gateau','28','2');
INSERT INTO "public"."product" ("id", "name", "price", "restaurant_id") VALUES ('4','Pato com Magret','32','3');
INSERT INTO "public"."product" ("id", "name", "price", "restaurant_id") VALUES ('5','Nacos de polvo','30','3');
INSERT INTO "public"."product" ("id", "name", "price", "restaurant_id") VALUES ('6','Bolinho de feijoada','22','4');
INSERT INTO "public"."product" ("id", "name", "price", "restaurant_id") VALUES ('7','Parmegiana de frango','38','4');
INSERT INTO "public"."product" ("id", "name", "price", "restaurant_id") VALUES ('8','Chocolatudo','26','4');
