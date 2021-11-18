DROP DATABASE IF EXISTS ExcellentVoyage;

CREATE DATABASE ExcellentVoyage;

-- USE ExcellentVoyage;

-- CREATE TABLE `Usuario` (
-- 	`id` INT NOT NULL AUTO_INCREMENT,
-- 	`nome` varchar(255) NOT NULL,
-- 	`email` varchar(255) NOT NULL UNIQUE,
-- 	`senha` varchar(255) NOT NULL,
-- 	`tipo` ENUM('admin','cliente','agencia') NOT NULL,
-- 	PRIMARY KEY (`id`)
-- );

-- CREATE TABLE `Cliente` (
-- 	`cpf` varchar(255) NOT NULL UNIQUE,
-- 	`telefone` varchar(255) NOT NULL,
-- 	`sexo` ENUM('masculino','feminino') NOT NULL,
-- 	`dataNascimento` TIMESTAMP NOT NULL,
-- 	`idUsuario` INT NOT NULL,
-- 	PRIMARY KEY (`idUsuario`)
-- );

-- CREATE TABLE `Agencia` (
-- 	`cnpj` varchar(255) NOT NULL UNIQUE,
-- 	`descricao` varchar(255) NOT NULL,
-- 	`idUsuario` INT NOT NULL,
-- 	PRIMARY KEY (`idUsuario`)
-- );

-- CREATE TABLE `pacoteTuristico` (
-- 	`id` INT NOT NULL AUTO_INCREMENT,
-- 	`cnpjAgencia` varchar(255) NOT NULL,
-- 	`destinoCidade` varchar(255) NOT NULL,
-- 	`destinoEstado` varchar(255) NOT NULL,
-- 	`destinoPais` varchar(255) NOT NULL,
-- 	`dataPartida` TIMESTAMP NOT NULL,
-- 	`duracaoDias` INT NOT NULL,
-- 	`valor` FLOAT NOT NULL,
-- 	`descricao` varchar(255) NOT NULL,
-- 	`qtdFoto` varchar(255) NOT NULL,
-- 	PRIMARY KEY (`id`)
-- );

-- CREATE TABLE `Foto` (
--     `id` INT NOT NULL AUTO_INCREMENT,
-- 	`idPacote` INT NOT NULL,
-- 	`url` varchar(255) NOT NULL
-- );

-- CREATE TABLE `Compra` (
-- 	`id` INT NOT NULL AUTO_INCREMENT,
-- 	`id_cliente` INT NOT NULL,
-- 	`id_pacote` INT NOT NULL,
-- 	`data_reuniao` TIMESTAMP NOT NULL,
-- 	`link_reuniao` varchar(255) NOT NULL,
-- 	PRIMARY KEY (`id`)
-- );

-- ALTER TABLE `cliente` ADD CONSTRAINT `cliente_fk0` FOREIGN KEY (`id_usuario`) REFERENCES `usuario`(`id`) ON DELETE CASCADE;

-- ALTER TABLE `agencia` ADD CONSTRAINT `agencia_fk0` FOREIGN KEY (`id_usuario`) REFERENCES `usuario`(`id`) ON DELETE CASCADE;

-- ALTER TABLE `pacote_turistico` ADD CONSTRAINT `pacote_turistico_fk0` FOREIGN KEY (`cnpj_agencia`) REFERENCES `agencia`(`cnpj`);

-- ALTER TABLE `foto` ADD CONSTRAINT `foto_fk0` FOREIGN KEY (`id_pacote`) REFERENCES `pacote_turistico`(`id`);

-- ALTER TABLE `compra` ADD CONSTRAINT `compra_fk0` FOREIGN KEY (`id_cliente`) REFERENCES `cliente`(`id_usuario`);

-- ALTER TABLE `compra` ADD CONSTRAINT `compra_fk1` FOREIGN KEY (`id_pacote`) REFERENCES `pacote_turistico`(`id`);

-- INSERt INTO usuario(nome, email, senha, tipo) values ('administrador', 'admin@admin.com', 'admin', 'admin');
