
CREATE TABLE tp.Producto (
    id_producto serial,
    nombre character varying(20),
    descripcion character varying(100),
    precio real,
    CONSTRAINT PK_producto PRIMARY KEY (id_producto)
);

ALTER SEQUENCE tp.producto_id_producto_seq MINVALUE 0 RESTART WITH 0 INCREMENT BY 1;

CREATE TABLE tp.Centro_Logistico (
    id_logistico serial,
    nombre character varying(20),
    estado character varying(20),
    horario_apertura character varying(20),
    horario_cierre character varying(20),
    pageRank real,
    CONSTRAINT PK_Centro_Logistico PRIMARY KEY (id_logistico)
);

ALTER SEQUENCE tp.centro_logistico_id_logistico_seq MINVALUE 0 RESTART WITH 0 INCREMENT BY 1;

CREATE TABLE tp.Centro (
    id_centro integer,
    CONSTRAINT FK_Centro FOREIGN KEY (id_centro) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT PK_Centro PRIMARY KEY (id_centro)
);


CREATE TABLE tp.Puerto (
    id_puerto integer,
    CONSTRAINT FK_Puerto FOREIGN KEY (id_puerto) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT PK_Puerto PRIMARY KEY (id_puerto)
);

CREATE TABLE tp.Sucursal(
    id_sucursal integer,
    CONSTRAINT FK_Sucursal FOREIGN KEY (id_sucursal) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT PK_Sucursal PRIMARY KEY (id_sucursal)
);

CREATE TABLE tp.Stock (
    id_stock serial,
    id_logistico integer,
    id_producto integer,
    cantidad real,
    unidad character varying(20),
    CONSTRAINT FK_Stock__Centro_Logistico FOREIGN KEY (id_logistico) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT FK_Stock__Producto FOREIGN KEY (id_producto) REFERENCES tp.Producto (id_producto),
    CONSTRAINT PK_Stock PRIMARY KEY (id_stock)
);

ALTER SEQUENCE tp.stock_id_stock_seq MINVALUE 0 RESTART WITH 0 INCREMENT BY 1;

CREATE TABLE tp.Ruta (
    id_ruta serial,
    sucursal_origen integer,
    sucursal_destino integer,
    estado character varying(20),
    capacidad real,
    duracion integer,
    CONSTRAINT FK_Ruta__Sucursal_Origen FOREIGN KEY (sucursal_origen) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT FK_Ruta__Sucursal_Destino FOREIGN KEY (sucursal_destino) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT PK_Ruta PRIMARY KEY (id_ruta)
);

ALTER SEQUENCE tp.ruta_id_ruta_seq MINVALUE 0 RESTART WITH 0 INCREMENT BY 1;

CREATE TABLE tp.Recorrido (
    id_recorrido serial,
    peso real,
    duracion integer,
    CONSTRAINT PK_Recorrido PRIMARY KEY (id_recorrido)
);

ALTER SEQUENCE tp.recorrido_id_recorrido_seq MINVALUE 0 RESTART WITH 0 INCREMENT BY 1;

CREATE TABLE tp.Rutas_Recorrido (
    id_recorrido integer,
    id_ruta integer,
    orden integer,
    CONSTRAINT FK_Rutas_Recorrido__Recorrido FOREIGN KEY (id_recorrido) REFERENCES tp.Recorrido (id_recorrido),
    CONSTRAINT FK_Rutas_Recorrido__Ruta FOREIGN KEY (id_ruta) REFERENCES tp.Ruta (id_ruta),
    CONSTRAINT PK_Rutas_Recorrido PRIMARY KEY (id_recorrido, id_ruta)
);

CREATE TABLE tp.Orden_Provision (
    id_orden serial,
    fecha_orden Date,
    tiempo_esperado real,
    estado character varying(20),
    sucursal_origen integer,
    sucursal_destino integer,
    id_recorrido integer,
    CONSTRAINT FK_Orden_Provision__Sucursal_Origen FOREIGN KEY (sucursal_origen) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT FK_Orden_Provision__Sucursal_Destino FOREIGN KEY (sucursal_destino) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT FK_Orden_Provision__Recorrido FOREIGN KEY (id_recorrido) REFERENCES tp.Recorrido (id_recorrido),
    CONSTRAINT PK_Orden_Provision PRIMARY KEY (id_orden)
);
ALTER TABLE tp.Orden_Provision ALTER COLUMN sucursal_origen DROP NOT NULL; 
ALTER TABLE tp.Orden_Provision ALTER COLUMN id_recorrido DROP NOT NULL; 
ALTER SEQUENCE tp.orden_provision_id_orden_seq MINVALUE 0 RESTART WITH 0 INCREMENT BY 1;

CREATE TABLE tp.Cantidad (
    id_cantidad serial,
    id_producto integer,
    id_orden integer,
    cantidad real,
    unidad character varying(20),
    CONSTRAINT FK_Cantidad__Producto FOREIGN KEY (id_producto) REFERENCES tp.Producto (id_producto),
    CONSTRAINT FK_Cantidad__Orden FOREIGN KEY (id_orden) REFERENCES tp.Orden_Provision (id_orden),
    CONSTRAINT PK_Cantidad PRIMARY KEY (id_cantidad)
);

ALTER SEQUENCE tp.cantidad_id_cantidad_seq MINVALUE 0 RESTART WITH 0 INCREMENT BY 1;
