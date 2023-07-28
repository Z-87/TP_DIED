INSERT INTO tp.Centro_Logistico(id_logistico, nombre, estado, horario_apertura, horario_cierre) VALUES 
  ('0', 'Puerto', 'OPERATIVA', '8:00', '15:00'),
  ('1', 'B', 'OPERATIVA', '8:00', '15:00'),
  ('2', 'C', 'OPERATIVA', '8:00', '15:00'),
  ('3', 'X', 'OPERATIVA', '8:00', '15:00'),
  ('4', 'D', 'OPERATIVA', '8:00', '15:00'),
  ('5', 'F', 'OPERATIVA', '8:00', '15:00'),
  ('6', 'Y', 'OPERATIVA', '8:00', '15:00'),
  ('7', 'E', 'OPERATIVA', '8:00', '15:00'),
  ('8', 'G', 'OPERATIVA', '8:00', '15:00'),
  ('9', 'Z', 'OPERATIVA', '8:00', '15:00'),
  ('10', 'Centro', 'OPERATIVA', '8:00', '15:00');

INSERT INTO tp.Centro(id_centro) VALUES ('10');
INSERT INTO tp.Puerto(id_puerto) VALUES ('0');
INSERT INTO tp.Sucursal(id_sucursal) VALUES ('1'), ('2'), ('3'), ('4'), ('5'), ('6'), ('7'), ('8'), ('9');

INSERT INTO tp.Ruta(id_ruta, sucursal_origen, sucursal_destino, estado, capacidad, duracion) VALUES
  ('0', '0', '1', 'OPERATIVA', 100.00, 120),
  ('1', '0', '2', 'OPERATIVA', 100.00, 150),
  ('2', '0', '3', 'OPERATIVA', 100.00, 180),
  ('3', '1', '4', 'OPERATIVA', 100.00, 150),
  ('4', '1', '2', 'OPERATIVA', 100.00, 150),
  ('5', '2', '4', 'OPERATIVA', 100.00, 150),
  ('6', '2', '5', 'OPERATIVA', 100.00, 150),
  ('7', '3', '2', 'OPERATIVA', 100.00, 150),
  ('8', '3', '8', 'OPERATIVA', 100.00, 150),
  ('9', '3', '6', 'OPERATIVA', 100.00, 150),
  ('10', '4', '7', 'OPERATIVA', 100.00, 150),
  ('11', '4', '5', 'OPERATIVA', 100.00, 150),
  ('12', '5', '8', 'OPERATIVA', 100.00, 150),
  ('13', '5', '6', 'OPERATIVA', 100.00, 150),
  ('14', '6', '9', 'OPERATIVA', 100.00, 150),
  ('15', '7', '10', 'OPERATIVA', 100.00, 150),
  ('16', '8', '7', 'OPERATIVA', 100.00, 150),
  ('17', '8', '10', 'OPERATIVA', 100.00, 150),
  ('18', '9', '10', 'OPERATIVA', 100.00, 150);
  