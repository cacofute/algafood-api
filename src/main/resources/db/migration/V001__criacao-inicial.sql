
drop table if exists restaurante_forma_pagamento;
drop table if exists usuario_grupo;
drop table if exists grupo_permissao;
drop table if exists produto;
drop table if exists restaurante;
drop table if exists cidade;
drop table if exists cozinha;
drop table if exists estado;
drop table if exists forma_pagamento;
drop table if exists grupo;
drop table if exists permissao;
drop table if exists usuario;

create table cidade (
    id        bigint not null auto_increment,
    nome      varchar(255),
    estado_id bigint,
    primary key (id)
) engine=InnoDB default charset=utf8 auto_increment = 1;

 
create table cozinha (
    id   bigint not null auto_increment,
    nome varchar(255) not null,
    primary key (id)
) engine=InnoDB default charset=utf8 auto_increment = 1;
 
    
create table estado (
    id   bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
) engine=InnoDB default charset=utf8 auto_increment = 1;

    
create table forma_pagamento (
    id        bigint not null auto_increment,
    descricao varchar(255) not null,
    primary key (id)
) engine=InnoDB default charset=utf8 auto_increment = 1;


create table grupo (
    id   bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
) engine=InnoDB default charset=utf8 auto_increment = 1;

    
create table grupo_permissao (
    grupo_id     bigint not null,
    permissao_id bigint not null,
    primary key(grupo_id, permissao_id)
) engine=InnoDB default charset=utf8 auto_increment = 1;

    
create table permissao (
    id        bigint not null auto_increment,
    descricao varchar(255),
    nome      varchar(255),
    primary key (id)
) engine=InnoDB default charset=utf8 auto_increment = 1;

    
create table produto (
    id             bigint not null auto_increment,
    ativo          bit not null,
    nome           varchar(255),
    preco          double precision,
    restaurante_id bigint,
    primary key (id)
) engine=InnoDB default charset=utf8 auto_increment = 1;

    
create table restaurante (
    id                   bigint not null auto_increment,
    data_atualizacao     datetime not null,
    data_cadastro        datetime not null,
    endereco_bairro      varchar(255),
    endereco_cep         varchar(255),
    endereco_complemento varchar(255),
    endereco_logradouro  varchar(255),
    endereco_numero      varchar(255),
    nome 				 varchar(255) not null,
    taxa_frete 	         decimal(19,2) not null,
    cozinha_id           bigint not null,
    endereco_cidade_id   bigint,
    primary key (id)
) engine=InnoDB default charset=utf8 auto_increment = 1;
  
    
create table restaurante_forma_pagamento (
    restaurante_id      bigint not null,
    forma_pagamento_id  bigint not null,
    primary key (restaurante_id, forma_pagamento_id)
) engine=InnoDB default charset=utf8 auto_increment = 1;
    
    
create table usuario (
    id            bigint not null auto_increment,
    data_cadastro datetime,
    email         varchar(255),
    nome          varchar(255),
    senha         varchar(255),
    primary key (id)
) engine=InnoDB default charset=utf8 auto_increment = 1;
     
    
create table usuario_grupo (
    usuario_id bigint not null,
    grupo_id   bigint not null,
    primary key(usuario_id, grupo_id)
) engine=InnoDB default charset=utf8 auto_increment = 1;

    
alter table cidade 
   add constraint FK_cidade_estado_01
   foreign key (estado_id) 
   references estado (id);
    
alter table grupo_permissao 
   add constraint FK_grupo_permissao_permissao_01
   foreign key (permissao_id) 
   references permissao (id);


alter table grupo_permissao 
   add constraint FK_grupo_permissao_grupo_01
   foreign key (grupo_id) 
   references grupo (id);

    
alter table produto 
   add constraint FK_produto_restaurante_01
   foreign key (restaurante_id) 
   references restaurante (id);

alter table restaurante 
   add constraint FK_restaurante_cozinha_01
   foreign key (cozinha_id) 
   references cozinha (id);
    
alter table restaurante 
   add constraint FK_restaurante_cidade_02
   foreign key (endereco_cidade_id) 
   references cidade (id);


alter table restaurante_forma_pagamento 
   add constraint FK_restaurante_forma_pagamento_forma_pagamento_01
   foreign key (forma_pagamento_id) 
   references forma_pagamento (id);
       
    
alter table restaurante_forma_pagamento 
   add constraint FK_restaurante_forma_pagamento_restaurante_02
   foreign key (restaurante_id) 
   references restaurante (id);


alter table usuario_grupo 
   add constraint FK_usuario_grupo_grupo_01
   foreign key (grupo_id) 
   references grupo (id);
        
     
alter table usuario_grupo 
   add constraint FK_usuario_grupo_usuario_02
   foreign key (usuario_id) 
   references usuario (id);
       
       