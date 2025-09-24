-- Script para criação da tabela de categoria
create table categoria(
	id				uuid			primary key,
	nome			varchar(25)		not null unique
);

-- Script para criação da tabela de tarefa
create table tarefa(
	id				uuid			primary key,
	nome			varchar(100)	not null,
	data			date			not null,
	prioridade		varchar(10)		not null,
	finalizado		boolean			not null,
	categoria_id	uuid			not null,
	foreign key(categoria_id)
		references categoria(id)
);

-- Script para cadastrar algumas categorias no banco de dados
insert into categoria(id, nome) values(gen_random_uuid(), 'Trabalho');
insert into categoria(id, nome) values(gen_random_uuid(), 'Família');
insert into categoria(id, nome) values(gen_random_uuid(), 'Estudo');
insert into categoria(id, nome) values(gen_random_uuid(), 'Lazer');
insert into categoria(id, nome) values(gen_random_uuid(), 'Saúde');
insert into categoria(id, nome) values(gen_random_uuid(), 'Outros');

-- Script para consultar os dados gravados na tabela de categoria
select id, nome from categoria
order by nome;






