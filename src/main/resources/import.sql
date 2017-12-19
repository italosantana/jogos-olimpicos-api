-- Modalidades

INSERT INTO modalidade (id,nome) VALUES ('1', 'Futebol');
INSERT INTO modalidade (id,nome) VALUES ('2', 'Handbol');
INSERT INTO modalidade (id,nome) VALUES ('3', 'Basquete');
INSERT INTO modalidade (id,nome) VALUES ('4', 'Boxe');
INSERT INTO modalidade (id,nome) VALUES ('5', 'Volei');
INSERT INTO modalidade (id,nome) VALUES ('6', 'Ciclismo');
INSERT INTO modalidade (id,nome) VALUES ('7', 'Karate');
INSERT INTO modalidade (id,nome) VALUES ('8', 'Ginastica');
INSERT INTO modalidade (id,nome) VALUES ('9', 'Judô');
INSERT INTO modalidade (id,nome) VALUES ('10', 'Natação');

--Local
INSERT INTO local (id,nome) VALUES ('1', 'GINÁSIO NACIONAL PASTEL DE FLANGO');
INSERT INTO local (id,nome) VALUES ('2', 'GINÁSIO DE TÓQUIO');
INSERT INTO local (id,nome) VALUES ('3', 'GINÁSIO DE TÓQUIO MINI');
INSERT INTO local (id,nome) VALUES ('4', 'ARENA TÓQUIO');


--Competidor
INSERT INTO competidor (id,nome) VALUES (1,'Brasil');
INSERT INTO competidor (id,nome) VALUES (2,'Estados Unidos');
INSERT INTO competidor (id,nome) VALUES (3,'China');
INSERT INTO competidor (id,nome) VALUES (4,'Canada');
INSERT INTO competidor (id,nome) VALUES (5,'Argentina');
INSERT INTO competidor (id,nome) VALUES (6,'França');
INSERT INTO competidor (id,nome) VALUES (7,'Paraguai');
INSERT INTO competidor (id,nome) VALUES (8,'Suiça');
INSERT INTO competidor (id,nome) VALUES (9,'Holanda');
INSERT INTO competidor (id,nome) VALUES (10,'Espanha');


--Competições
INSERT INTO competicao (id, idmodalidade, idlocal, competidor_a_id, competidor_b_id, etapa, data_inicio, data_termino) VALUES (1, 9, 4, 1, 2, 'OITAVAS', '2020-07-15 09:00:00', '2020-07-15 10:30:00');
INSERT INTO competicao (id, idmodalidade, idlocal, competidor_a_id, competidor_b_id, etapa, data_inicio, data_termino) VALUES (2, 1, 2, 7, 9, 'SEMIFINAL', '2020-07-17 10:30:00', '2020-07-17 12:30:00');
INSERT INTO competicao (id, idmodalidade, idlocal, competidor_a_id, competidor_b_id, etapa, data_inicio, data_termino) VALUES (3, 7, 1, 5, 4, 'QUARTAS', '2020-07-20 07:00:00', '2020-07-19 08:30:00');
