delete from restaurante_forma_pagamento;
delete from usuario_grupo;
delete from grupo_permissao;
delete from produto;
delete from restaurante;
delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from permissao;
delete from usuario;
 
 
INSERT INTO cozinha (id, nome)VALUES(1001, "Tailandesa" );
INSERT INTO cozinha (id, nome)VALUES(1002, "Irlandesa"); 

INSERT INTO forma_pagamento(id, descricao)VALUES(1001, 'DINHEIRO');
INSERT INTO forma_pagamento(id, descricao)VALUES(1002, 'CARTÂO');
INSERT INTO forma_pagamento(id, descricao)VALUES(1003, 'CHEQUE');

INSERT permissao(nome, descricao)VALUES('PERMISSAO_CONSULTA', 'Permissão para consultar cadastros');
INSERT permissao(nome, descricao)VALUES('PERMISSAO_INCLUSAO', 'Permissão para incluir cadastros');

INSERT INTO estado(id, nome)VALUES(1001, "São Paulo");
INSERT INTO estado(id, nome)VALUES(1002, "Rio de Janeiro");
INSERT INTO estado(id, nome)VALUES(1003, "Rio Grande do Sul");
INSERT INTO estado(id, nome)VALUES(1004, "Minas Gerais");

INSERT INTO cidade(id, nome, estado_id)VALUES(1001, "Cidade 1", 1001);
INSERT INTO cidade(id, nome, estado_id)VALUES(1002, "Cidade 2", 1001);
INSERT INTO cidade(id, nome, estado_id)VALUES(1003, "Cidade 3", 1002);
INSERT INTO cidade(id, nome, estado_id)VALUES(1004, "Cidade 4", 1003);

INSERT INTO restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao)VALUES(1001, 'Almanara', 25550, 1001,   utc_timestamp, utc_timestamp);
INSERT INTO restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao)VALUES(1002, 'Burger King', 1000, 1002, utc_timestamp, utc_timestamp);
INSERT INTO restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_logradouro, endereco_cep, endereco_bairro, endereco_complemento, endereco_numero, endereco_cidade_id)VALUES(1003, 'Burger King', 1000, 1002, utc_timestamp, utc_timestamp, 'Rua Ernest Renam 954', '05659595', 'Paraisópolis', 'bloco 1', '156', 1001);
 
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1001, 1001);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1001, 1002);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1002, 1003);


INSERT INTO produto(id, nome, preco, ativo, restaurante_id)VALUES(1001, 'Xbox one', 2450.99, true, 1001);
INSERT INTO produto(id, nome, preco, ativo, restaurante_id)VALUES(1002, 'Panela de inox', 323.50, true, 1001);
INSERT INTO produto(id, nome, preco, ativo, restaurante_id)VALUES(1003, 'Pasta de dente', 7.50, true, 1002);





