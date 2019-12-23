CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(100),
	numero VARCHAR(10),
	complemento VARCHAR(100),
	bairro VARCHAR(50),
	cep VARCHAR(10),
	cidade VARCHAR(50),
	estado VARCHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome,ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Cleison Vasconcelos',true,'QD 15 Cj 10','29','casa','Setor Resid Leste','73355510','Planaltina','DF');
INSERT INTO pessoa (nome,ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Clezia Oliveira',true,'QD 99 Cj 10','29','apartamento','Setor Resid Leste','73355510','Planaltina','DF');
INSERT INTO pessoa (nome,ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Maria Santos',true,'QD 11 Cj 10','29','casa','Dois Irmaos','73355510','Planaltina','DF');
INSERT INTO pessoa (nome,ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Jose Silva',true,'QD 12 Cj 10','29','apartamento','Sem Bairro','73355510','Planaltina','DF');
INSERT INTO pessoa (nome,ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Eduardo Soares',true,'QD 14 Cj 10','29','casa','Jesus da Lapa','73355510','Planaltina','DF');
INSERT INTO pessoa (nome,ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Joana Magalhaes',true,'QD 17 Cj 10','29','apartamento','Onofre','73355510','Planaltina','DF');
INSERT INTO pessoa (nome,ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Roberto Pena',false,'QD 1 Cj 10','29','casa','Maoa','73355510','Planaltina','DF');
INSERT INTO pessoa (nome,ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Maruza Swizto',true,'QD 5 Cj 10','29','lote','Setor das Quintas','73355510','Planaltina','DF');
INSERT INTO pessoa (nome,ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Damiao Aruajo',false,'QD 09 Cj 10','29','casa','Setor Central','73355510','Planaltina','DF');
INSERT INTO pessoa (nome,ativo,logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Mauricio Rosa',true,'QD 10 Cj 10','29','lote','Setor Resid Sul','73355510','Planaltina','DF');
