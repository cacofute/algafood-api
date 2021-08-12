drop table if exists item_pedido;
drop table if exists pedido;

CREATE TABLE pedido(
	id                   bigint not null auto_increment,
	sub_total            double precision not null,
	taxa_frete           double precision not null,
	valor_total          double precision not null,
	data_criacao         datetime not null,
	data_confirmacao     datetime,
	data_cancelamento    datetime,
	data_entrega         datetime,
	status	             varchar(10) not null,
	cliente_id		     bigint not null,
	forma_pagamento_id   bigint not null,
	restaurante_id	     bigint not null,
	endereco_bairro      varchar(100),
    endereco_cep         varchar(14),
    endereco_complemento varchar(50),
    endereco_logradouro  varchar(100),
    endereco_numero      varchar(14),
    endereco_cidade_id	 bigint not null,
    primary key (id)
			  
);

CREATE TABLE item_pedido(
	id             bigint not null auto_increment,
	quantidade     bigint not null,
	preco_unitario double precision not null,
	preco_total    double precision not null,
	observacao	   varchar(100),
	produto_id	   bigint not null,
	pedido_id	   bigint not null,
	primary key(id)
);

alter table pedido 
	add constraint FK_pedido_cliente_usuario_01
	foreign key (cliente_id) references usuario (id);
	
alter table pedido
	add constraint FK_pedido_restaurante_02
	foreign key (restaurante_id) references restaurante (id);
	
alter table pedido
	add constraint FK_pedido_forma_pagamento_03
	foreign key (forma_pagamento_id) references forma_pagamento (id);
	
	
alter table item_pedido
	add constraint FK_item_pedido_produto_01
	foreign key (produto_id) references produto (id);
	
alter table item_pedido
	add constraint FK_item_pedido_pedido_02
	foreign key (pedido_id) references pedido (id);
	




