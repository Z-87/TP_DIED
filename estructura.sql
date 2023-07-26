
CREATE TABLE tp.Producto (
    id_producto character varying(20),
    nombre character varying(20),
    descripcion character varying(100),
    precio_unit real,
    precio_kg real,
    CONSTRAINT PK_producto PRIMARY KEY (id_producto)
);

CREATE TABLE tp.Centro_Logistico (
    id_logistico character varying(20),
    nombre character varying(20),
    estado character varying(20),
    horario_apertura character varying(20),
    horario_cierre character varying(20),
    CONSTRAINT PK_Centro_Logistico PRIMARY KEY (id_logistico)
);

CREATE TABLE tp.Centro (
    id_centro character varying(20),
    CONSTRAINT FK_Centro FOREIGN KEY (id_centro) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT PK_Centro PRIMARY KEY (id_centro)
);

CREATE TABLE tp.Puerto (
    id_puerto character varying(20),
    CONSTRAINT FK_Puerto FOREIGN KEY (id_puerto) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT PK_Puerto PRIMARY KEY (id_puerto)
);

CREATE TABLE tp.Sucursal(
    id_sucursal character varying(20),
    CONSTRAINT FK_Sucursal FOREIGN KEY (id_sucursal) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT PK_Sucursal PRIMARY KEY (id_sucursal)
);

CREATE TABLE tp.Stock (
    id_stock character varying(20),
    id_logistico character varying(20),
    id_producto character varying(20),
    cantidad_unit integer,
    cantidad_kg real,
    CONSTRAINT FK_Stock__Centro_Logistico FOREIGN KEY (id_logistico) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT FK_Stock__Producto FOREIGN KEY (id_producto) REFERENCES tp.Producto (id_producto),
    CONSTRAINT PK_Stock PRIMARY KEY (id_stock)
);

CREATE TABLE tp.Orden_Provision (
    id_orden character varying(20),
    fecha_orden Date,
    tiempo_esperado integer,
    estado character varying(20),
    sucursal_origen character varying(20),
    sucursal_destino character varying(20),
    CONSTRAINT FK_Orden_Provision__Sucursal_Origen FOREIGN KEY (sucursal_origen) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT FK_Orden_Provision__Sucursal_Destino FOREIGN KEY (sucursal_destino) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT PK_Orden_Provision PRIMARY KEY (id_orden)
);

CREATE TABLE tp.Cantidad (
    id_cantidad character varying(20),
    id_producto character varying(20),
    id_orden character varying(20),
    cantidad_unit integer,
    cantidad_kg real,
    CONSTRAINT FK_Cantidad__Producto FOREIGN KEY (id_producto) REFERENCES tp.Producto (id_producto),
    CONSTRAINT FK_Cantidad__Orden FOREIGN KEY (id_orden) REFERENCES tp.Orden_Provision (id_orden),
    CONSTRAINT PK_Cantidad PRIMARY KEY (id_cantidad)
);

CREATE TABLE tp.Ruta (
    id_ruta character varying(20),
    sucursal_origen character varying(20),
    sucursal_destino character varying(20),
    estado character varying(20),
    capacidad real,
    duracion integer,
    CONSTRAINT FK_Ruta__Sucursal_Origen FOREIGN KEY (sucursal_origen) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT FK_Ruta__Sucursal_Destino FOREIGN KEY (sucursal_destino) REFERENCES tp.Centro_Logistico (id_logistico),
    CONSTRAINT PK_Ruta PRIMARY KEY (id_ruta)
);