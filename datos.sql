
#-------------------------------------------------------------------------
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
INSERT INTO automoviles VALUES ("DEF456","Citroën","C5 Aircross","Blanco perla",38171208);
INSERT INTO automoviles VALUES ("GHI789","Citroën","DS3 Crossback E-Tense","",37171209);
INSERT INTO automoviles VALUES ("LMN098","Fiat","Toro Ranch","Negro",36171210);
INSERT INTO automoviles VALUES ("OPQ765","RAM","2500 Heavy Duty","Negro",35171211);
INSERT INTO automoviles VALUES ("RQY432","Jeep","GladiatoR","Gris Plata",34171212);
INSERT INTO automoviles VALUES ("ZWH192","Ford","F-150","Gris",33171213);
INSERT INTO automoviles VALUES ("KPO765","Honda","Pilot","Azul Electrico",32171214);
INSERT INTO automoviles VALUES ("NIS128","MINI","John Cooper Works GP","Rojo",31171215);



INSERT INTO tipo_tarjeta VALUES (Credito, 0.15);
INSERT INTO tipo_tarjeta VALUES (Debito, 0.10);



INSERT INTO tarjeta VALUES (1,120.00,"DEBITO","ABC123");
INSERT INTO tarjeta VALUES (2,123.00,"CREDITO","DEF456");
INSERT INTO tarjeta VALUES (3,378.50,"CREDITO","GHI789");
INSERT INTO tarjeta VALUES (4,467.60,"CREDITO","LMN098");
INSERT INTO tarjeta VALUES (5,434.79,"DEBITO","OPQ765");
INSERT INTO tarjeta VALUES (6,678.69,"DEBITO","RQY432");
INSERT INTO tarjeta VALUES (7,456.09,"CREDITO","ZWH192");
INSERT INTO tarjeta VALUES (8,345.37,"CREDITO","KPO765");
INSERT INTO tarjeta VALUES (9,123.09,"DEBITO","NIS128");




INSERT INTO inspectores VALUES (124,22171215,"Lisandro","Lopez","");
INSERT INTO inspectores VALUES (125,23171215,"Marcelo","Diaz","");
INSERT INTO inspectores VALUES (126,24171215,"Ivan","Pillud","");
INSERT INTO inspectores VALUES (127,25171215,"Gabriel","Arias","");
INSERT INTO inspectores VALUES (128,26171215,"Matias","Rojas","");
INSERT INTO inspectores VALUES (129,27171215,"Dario","Cvitanich","");
INSERT INTO inspectores VALUES (130,29171215,"Benjamin","Garre","");
INSERT INTO inspectores VALUES (131,28171215,"Nicolas","Reniero","");



INSERT INTO ubicaciones VALUES ("San Juan",2340,200);
INSERT INTO ubicaciones VALUES ("San Juan",2342,200);
INSERT INTO ubicaciones VALUES ("San Juan",2344,200);
INSERT INTO ubicaciones VALUES ("San Juan",2346,200);
INSERT INTO ubicaciones VALUES ("San Juan",2348,200);
INSERT INTO ubicaciones VALUES ("San Juan",2350,200);
INSERT INTO ubicaciones VALUES ("Trelew",341,200);
INSERT INTO ubicaciones VALUES ("Trelew",343,200);
INSERT INTO ubicaciones VALUES ("Trelew",345,200);
INSERT INTO ubicaciones VALUES ("Trelew",347,200);
INSERT INTO ubicaciones VALUES ("Trelew",349,200);
INSERT INTO ubicaciones VALUES ("Trelew",351,200);
INSERT INTO ubicaciones VALUES ("Terrada",233,200);
INSERT INTO ubicaciones VALUES ("Terrada",234,200);
INSERT INTO ubicaciones VALUES ("Terrada",235,200);
INSERT INTO ubicaciones VALUES ("Terrada",236,200);
INSERT INTO ubicaciones VALUES ("Terrada",237,200);
INSERT INTO ubicaciones VALUES ("Terrada",238,200);
INSERT INTO ubicaciones VALUES ("Terrada",239,200);
INSERT INTO ubicaciones VALUES ("Terrada",240,200);
INSERT INTO ubicaciones VALUES ("Terrada",241,200);
INSERT INTO ubicaciones VALUES ("Alem",123,200);
INSERT INTO ubicaciones VALUES ("Alem",124,200);
INSERT INTO ubicaciones VALUES ("Alem",125,200);
INSERT INTO ubicaciones VALUES ("Alem",126,200);
INSERT INTO ubicaciones VALUES ("Alem",127,200);
INSERT INTO ubicaciones VALUES ("Alem",128,200);
INSERT INTO ubicaciones VALUES ("Alem",129,200);
INSERT INTO ubicaciones VALUES ("Alem",130,200);
INSERT INTO ubicaciones VALUES ("Alem",131,200);
INSERT INTO ubicaciones VALUES ("Alem",132,200);
INSERT INTO ubicaciones VALUES ("Alem",133,200);
INSERT INTO ubicaciones VALUES ("Alem",134,200);



#NO SE SI NUMERO Y ID ESTAN BIEN
INSERT INTO parquimetros VALUES (1,1,"San Juan",236);
INSERT INTO parquimetros VALUES (2,2,"Trelew",345);
INSERT INTO parquimetros VALUES (3,3,"Terrada",234);
INSERT INTO parquimetros VALUES (4,4,"Terrada",235);
INSERT INTO parquimetros VALUES (5,5,"Alem",128);
INSERT INTO parquimetros VALUES (6,6,"Alem",129);



INSERT INTO estacionamientos VALUES (,,"2020/05/24",13:00,"2020/05/24",16:30);
INSERT INTO estacionamientos VALUES (,,"2020/05/24",11:00,"2020/05/24",14:55);
INSERT INTO estacionamientos VALUES (,,"2020/05/24",13:00,"2020/05/24",17:50);
INSERT INTO estacionamientos VALUES (,,"2020/05/25",14:00,"2020/05/25",16:30);
INSERT INTO estacionamientos VALUES (,,"2020/05/25",11:40,"2020/05/25",17:00);
INSERT INTO estacionamientos VALUES (,,"2020/05/25",15:30,NULL,NULL);
INSERT INTO estacionamientos VALUES (,,"2020/05/25",17:00,NULL,NULL);
INSERT INTO estacionamientos VALUES (,,"2020/05/25",18:00,NULL,NULL);
INSERT INTO estacionamientos VALUES (,,"2020/05/25",21:00,NULL,NULL);


INSERT INTO accede VALUES (124,1,"2020/01/29","17:34");
INSERT INTO accede VALUES (124,1,"2020/02/29","17:23");
INSERT INTO accede VALUES (124,1,"2020/03/29","17:31");
INSERT INTO accede VALUES (124,1,"2020/04/29","17:50");
INSERT INTO accede VALUES (124,1,"2020/05/29","16:30");
INSERT INTO accede VALUES (125,2,"2020/01/29","16:10");
INSERT INTO accede VALUES (125,2,"2020/02/29","19:42");
INSERT INTO accede VALUES (125,2,"2020/03/29","16:30");
INSERT INTO accede VALUES (125,2,"2020/04/29","19:32");
INSERT INTO accede VALUES (125,2,"2020/05/29","16:00");
INSERT INTO accede VALUES (126,3,"2020/01/29","16:12");
INSERT INTO accede VALUES (126,4,"2020/01/29","16:05");
INSERT INTO accede VALUES (126,3,"2020/02/29","16:25");
INSERT INTO accede VALUES (126,4,"2020/02/29","16:45");
INSERT INTO accede VALUES (126,3,"2020/03/29","16:02");
INSERT INTO accede VALUES (126,4,"2020/03/29","16:05");
INSERT INTO accede VALUES (126,3,"2020/04/29","16:05");
INSERT INTO accede VALUES (126,4,"2020/04/29","16:25");
INSERT INTO accede VALUES (126,3,"2020/03/29","16:02");
INSERT INTO accede VALUES (126,4,"2020/03/29","16:05");
INSERT INTO accede VALUES (126,3,"2020/04/29","16:05");
INSERT INTO accede VALUES (126,4,"2020/04/29","16:25");
INSERT INTO accede VALUES (126,3,"2020/05/29","16:15");
INSERT INTO accede VALUES (126,4,"2020/05/29","16:35");
INSERT INTO accede VALUES (127,5,"2020/01/29","18:02");
INSERT INTO accede VALUES (127,6,"2020/01/29","18:05");
INSERT INTO accede VALUES (127,5,"2020/02/29","18:07");
INSERT INTO accede VALUES (127,6,"2020/02/29","18:25");
INSERT INTO accede VALUES (127,5,"2020/03/29","18:15");
INSERT INTO accede VALUES (127,6,"2020/03/29","18:35");
INSERT INTO accede VALUES (127,5,"2020/04/29","18:35");
INSERT INTO accede VALUES (127,6,"2020/04/29","16:15");
INSERT INTO accede VALUES (127,5,"2020/05/29","18:35");
INSERT INTO accede VALUES (127,6,"2020/05/29","18:35");


#duda el id_asociado_con
INSERT INTO asociado_con (321,125,"Terrada","Do","13:54","T");
INSERT INTO asociado_con (322,125,"Terrada","Do","14:44","T");
INSERT INTO asociado_con (323,126,"San Juan","Mi","11:35","M");
INSERT INTO asociado_con (324,126,"Alem","Sa","10:04","M");
INSERT INTO asociado_con (325,127,"Alem","Do","13:34","T");



INSERT INTO multa (4322,"2020/03/1","14:44",322);
INSERT INTO multa (4321,"2020/03/29","13:54",321);
INSERT INTO multa (4323,"2020/04/22","14:35",323);
INSERT INTO multa (4324,"2020/05/2","15:04",324);
INSERT INTO multa (4325,"2020/05/3","13:34",325);