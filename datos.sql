# Carga de datos de Prueba
 
INSERT INTO conductores VALUES (39171207, "Ignacio", "Lopez Bidart", "San juan 77" ,"2923696662",42000);
INSERT INTO conductores VALUES (38171208, "Luciano", "Baschiera", "Los Aroldos 22" ,"2936696662",42001);
INSERT INTO conductores VALUES (37171209, "Mariano", "Grimoldi", "San damian 21" ,"2926696662",42002);
INSERT INTO conductores VALUES (36171210, "Gabriel", "Ceña", "Los Chaniares 89" ,"291696662",4200003);
INSERT INTO conductores VALUES (35171211, "Lucas", "Monzón", "San martin 80" ,"2913693462",42004);
INSERT INTO conductores VALUES (34171212, "Ian", "Salamanca", "Teodoro Torre 73" ,"2913236662",42005);
INSERT INTO conductores VALUES (33171213, "Joaquin", "Vigier", "San Felipe 900" ,"2913697862",42006);
INSERT INTO conductores VALUES (32171214, "Alejo", "Ferreras", "Terrada 1200" ,"2123694562",42007);
INSERT INTO conductores VALUES (31171215, "Belen", "Gauto", "Trelew 140" ,"2123694562",42007);


INSERT INTO automoviles VALUES ("ABC123","Chevrolet","Onix Premier","Rojo",39171207);
INSERT INTO automoviles VALUES ("ABC124","Chevrolet","Onix Premier","Rojo",39171207);
INSERT INTO automoviles VALUES ("ABC125","Chevrolet","Onix Premier","Rojo",39171207);
INSERT INTO automoviles VALUES ("DEF456","Citroen","C5 Aircross","Blanco perla",38171208);
INSERT INTO automoviles VALUES ("GHI789","Citroen","DS3 Crossback E-Tense","",37171209);
INSERT INTO automoviles VALUES ("LMN098","Fiat","Toro Ranch","Negro",36171210);
INSERT INTO automoviles VALUES ("OPQ765","RAM","2500 Heavy Duty","Negro",35171211);
INSERT INTO automoviles VALUES ("RQY432","Jeep","GladiatoR","Gris Plata",34171212);
INSERT INTO automoviles VALUES ("ZWH192","Ford","F-150","Gris",33171213);
INSERT INTO automoviles VALUES ("KPO765","Honda","Pilot","Azul Electrico",32171214);
INSERT INTO automoviles VALUES ("NIS128","MINI","John Cooper Works GP","Rojo",31171215);


INSERT INTO tipos_tarjeta VALUES ("CREDITO",0.15);
INSERT INTO tipos_tarjeta VALUES ("DEBITO",0.10);


INSERT INTO tarjetas VALUES (1,120.00,"DEBITO","ABC123");
INSERT INTO tarjetas VALUES (2,300.00,"DEBITO","ABC124");
INSERT INTO tarjetas VALUES (3,450.00,"DEBITO","ABC124");
INSERT INTO tarjetas VALUES (4,123.00,"CREDITO","DEF456");
INSERT INTO tarjetas VALUES (5,378.50,"CREDITO","GHI789");
INSERT INTO tarjetas VALUES (6,467.60,"CREDITO","LMN098");
INSERT INTO tarjetas VALUES (7,434.79,"DEBITO","OPQ765");
INSERT INTO tarjetas VALUES (8,678.69,"DEBITO","RQY432");
INSERT INTO tarjetas VALUES (9,456.09,"CREDITO","ZWH192");
INSERT INTO tarjetas VALUES (10,345.37,"CREDITO","KPO765");
INSERT INTO tarjetas VALUES (11,123.09,"DEBITO","NIS128");


INSERT INTO inspectores VALUES (124,22171215,"Lisandro","Lopez",md5("lisandrito"));
INSERT INTO inspectores VALUES (125,23171215,"Marcelo","Diaz",md5("marcelito"));
INSERT INTO inspectores VALUES (126,24171215,"Ivan","Pillud",md5("invansito"));
INSERT INTO inspectores VALUES (127,25171215,"Gabriel","Arias",md5("gabrielsito"));
INSERT INTO inspectores VALUES (128,26171215,"Matias","Rojas",md5("matute"));
INSERT INTO inspectores VALUES (129,27171215,"Dario","Cvitanich",md5("darito"));
INSERT INTO inspectores VALUES (130,29171215,"Benjamin","Garre",md5("benjita"));
INSERT INTO inspectores VALUES (131,28171215,"Nicolas","Reniero",md5("nico"));


INSERT INTO ubicaciones VALUES ("San Juan", 2340, 200.55);
INSERT INTO ubicaciones VALUES ("San Juan", 2342, 220.55);
INSERT INTO ubicaciones VALUES ("San Juan", 2344, 240.05);
INSERT INTO ubicaciones VALUES ("San Juan", 2346, 180.35);
INSERT INTO ubicaciones VALUES ("San Juan", 2348, 150.25);
INSERT INTO ubicaciones VALUES ("San Juan", 2350, 235.12);
INSERT INTO ubicaciones VALUES ("Trelew", 341, 198.33);
INSERT INTO ubicaciones VALUES ("Trelew", 343, 189.90);
INSERT INTO ubicaciones VALUES ("Trelew", 345, 121.32);
INSERT INTO ubicaciones VALUES ("Trelew", 347, 100);
INSERT INTO ubicaciones VALUES ("Trelew", 349, 15.1);
INSERT INTO ubicaciones VALUES ("Trelew", 351, 18.5);
INSERT INTO ubicaciones VALUES ("Terrada", 233, 23.5);
INSERT INTO ubicaciones VALUES ("Terrada", 234, 29.73);
INSERT INTO ubicaciones VALUES ("Terrada", 235, 10.2);
INSERT INTO ubicaciones VALUES ("Terrada", 236, 33.53);
INSERT INTO ubicaciones VALUES ("Terrada", 237, 9.13);
INSERT INTO ubicaciones VALUES ("Terrada", 238, 93.1);
INSERT INTO ubicaciones VALUES ("Terrada", 239, 100);
INSERT INTO ubicaciones VALUES ("Terrada", 240, 200);
INSERT INTO ubicaciones VALUES ("Terrada", 241, 200);
INSERT INTO ubicaciones VALUES ("Alem", 123, 200);
INSERT INTO ubicaciones VALUES ("Alem", 124, 200);
INSERT INTO ubicaciones VALUES ("Alem", 125, 200);
INSERT INTO ubicaciones VALUES ("Alem", 126, 200);
INSERT INTO ubicaciones VALUES ("Alem", 127, 200);
INSERT INTO ubicaciones VALUES ("Alem", 128, 200);
INSERT INTO ubicaciones VALUES ("Alem", 129, 200);
INSERT INTO ubicaciones VALUES ("Alem", 130, 200);
INSERT INTO ubicaciones VALUES ("Alem", 131, 200);
INSERT INTO ubicaciones VALUES ("Alem", 132, 200);
INSERT INTO ubicaciones VALUES ("Alem", 133, 200);
INSERT INTO ubicaciones VALUES ("Alem", 134, 200);
INSERT INTO ubicaciones VALUES ("Vieytes", 200, 190.3);
INSERT INTO ubicaciones VALUES ("Vieytes", 250, 1.93);
INSERT INTO ubicaciones VALUES ("Vieytes", 400, 22.22);

INSERT INTO parquimetros(id_parq,numero,calle,altura) VALUES (1, 1, "Vieytes", 200);
INSERT INTO parquimetros(id_parq,numero,calle,altura) VALUES (2, 1, "San Juan", 2340);
INSERT INTO parquimetros(id_parq,numero,calle,altura) VALUES (3, 1, "Trelew", 345);
INSERT INTO parquimetros(id_parq,numero,calle,altura) VALUES (4, 1, "Terrada", 234);
INSERT INTO parquimetros(id_parq,numero,calle,altura) VALUES (5, 1, "Terrada", 235);
INSERT INTO parquimetros(id_parq,numero,calle,altura) VALUES (6, 1, "Alem", 128);
INSERT INTO parquimetros(id_parq,numero,calle,altura) VALUES (7, 1, "Alem", 134);
INSERT INTO parquimetros(id_parq,numero,calle,altura) VALUES (8, 1, "Vieytes", 250);
INSERT INTO parquimetros(id_parq,numero,calle,altura) VALUES (9, 1, "Vieytes", 400);


INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (1,124,"Vieytes",200,"Lu","t");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (2,124,"Vieytes",200,"Ma","t");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (3,124,"Vieytes",200,"Mi","t");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (4,124,"Vieytes",200,"Ju","t");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (5,124,"Vieytes",200,"Vi","t");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (6,124,"Vieytes",200,"Sa","t");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (7,124,"Vieytes",200,"Do","t");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (8,125,"Terrada",233,"Ma","t");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (9,125,"Terrada",234,"Ju","t");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (10,126,"San Juan",2340,"Lu","m");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (11,126,"San Juan",2340,"Ma","m");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (12,126,"San Juan",2340,"Mi","m");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (13,126,"San Juan",2340,"Ju","m");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (14,126,"San Juan",2340,"Vi","m");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (15,126,"San Juan",2340,"Sa","m");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (16,126,"San Juan",2340,"Do","m");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (17,126,"Alem",123,"Sa","m");
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno) VALUES (18,127,"Alem",124,"Do","t");


INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (1,1,"2020/05/24","13:00:00","2020/05/24","16:30:00");
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (2,1,"2020/05/28","15:00:00",NULL,NULL);
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (3,2,"2020/05/24","11:00:00","2020/05/24","14:55:00");
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (4,3,"2020/05/24","13:00:00","2020/05/24","17:50:00");
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (5,4,"2020/05/25","14:00:00","2020/05/25","16:30:00");
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (6,5,"2020/05/25","11:40:00","2020/05/25","17:00:00");
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (7,6,"2020/05/25","15:30:00",NULL,NULL);
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (8,7,"2020/05/25","17:00:00",NULL,NULL);
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (9,8,"2020/05/25","18:00:00",NULL,NULL);
INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (10,9,"2020/05/25","21:00:00",NULL,NULL);



