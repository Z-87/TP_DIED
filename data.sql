INSERT INTO tp.Centro_Logistico(id_logistico, nombre, estado, horario_apertura, horario_cierre) VALUES 
  ('0', 'Puerto', 'OPERATIVA', '8:00', '15:00'),
  ('1', 'A', 'OPERATIVA', '8:00', '15:00'),
  ('2', 'B', 'OPERATIVA', '8:00', '15:00'),
  ('3', 'C', 'OPERATIVA', '8:00', '15:00'),
  ('4', 'Centro', 'OPERATIVA', '8:00', '15:00');

INSERT INTO tp.Centro(id_centro) VALUES ('4');
INSERT INTO tp.Puerto(id_puerto) VALUES ('0');
INSERT INTO tp.Sucursal(id_sucursal) VALUES ('1'), ('2'), ('3');

INSERT INTO tp.Ruta(id_ruta, sucursal_origen, sucursal_destino, estado, capacidad, duracion) VALUES
  ('0', '0', '1', 'OPERATIVA', 100.00, 120),
  ('1', '1', '2', 'OPERATIVA', 100.00, 150),
  ('2', '2', '4', 'OPERATIVA', 100.00, 180);
  