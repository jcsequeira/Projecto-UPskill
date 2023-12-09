

DROP USER 'rest_api'@'localhost';
DROP DATABASE IF EXISTS explorart;

-- Create Database
CREATE DATABASE IF NOT EXISTS explorart;

-- Use the Database
USE explorart;


CREATE TABLE Cidade
(
  id_Cidade INT NOT NULL auto_increment,
  Nome_Cidade VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_Cidade)
);
INSERT INTO Cidade (Nome_Cidade) VALUES
    ('SystemCityGalery');

CREATE TABLE Pais
(
  Codigo_Pais INT NOT NULL auto_increment,
  Nome_Pais VARCHAR(100) NOT NULL,
  Nacionalidade VARCHAR(100) NOT NULL,
  PRIMARY KEY (Codigo_Pais)
);
INSERT INTO Pais (Nome_Pais, Nacionalidade) VALUES
    ('SystemCountry', 'SystemNacionality');

CREATE TABLE Movimento
(
  id_Estilo INT NOT NULL auto_increment,
  Nome_Movimento VARCHAR(500) NOT NULL,
  PRIMARY KEY (id_Estilo)
);

CREATE TABLE Tecnica
(
  id_Tecnica INT NOT NULL auto_increment,
  Tipo_Tecnica VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_Tecnica)
);

CREATE TABLE Materiais
(
  id_Material INT NOT NULL auto_increment,
  Tipo_Material VARCHAR(2000) NOT NULL,
  PRIMARY KEY (id_Material)
);

CREATE TABLE Colaborador
(
  id_colaborador INT NOT NULL auto_increment,
  Nome_Colaborador VARCHAR(50) NOT NULL,
  Email VARCHAR(50) NOT NULL,
  Telefone VARCHAR(20) NOT NULL,
  Codigo_Pais INT NOT NULL,
  PRIMARY KEY (id_colaborador, Codigo_Pais),
  FOREIGN KEY (Codigo_Pais) REFERENCES Pais(Codigo_Pais)
);
INSERT INTO Colaborador (Nome_Colaborador, Email, Telefone, Codigo_Pais) VALUES
    ('System0', 'system@email.com', '123-456-7890', 1);
INSERT INTO Colaborador (Nome_Colaborador, Email, Telefone, Codigo_Pais) VALUES
    ('System1Galerista', 'system@email.com', '123-456-7890', 1);
INSERT INTO Colaborador (Nome_Colaborador, Email, Telefone, Codigo_Pais) VALUES
    ('SystemAdmin', 'system@email.com', '123-456-7890', 1);


CREATE TABLE Administrador
(
  password VARCHAR(200) NOT NULL,
  id_colaborador INT NOT NULL,
  PRIMARY KEY (id_colaborador),
  FOREIGN KEY (id_colaborador) REFERENCES Colaborador(id_colaborador)
);
INSERT INTO Administrador (password, id_colaborador) VALUES
    ('adminpass', 3);

CREATE TABLE Galerista
(
  Data_Inicio_Atividade DATE,
  password VARCHAR(200) NOT NULL,
  id_colaborador INT NOT NULL,
  PRIMARY KEY (id_colaborador),
  FOREIGN KEY (id_colaborador) REFERENCES Colaborador(id_colaborador)
);
INSERT INTO Galerista (Data_Inicio_Atividade, password, id_colaborador) VALUES
    ('2022-01-01', 'passw0rd', 2);

CREATE TABLE Artista
(
  id_artista int not null auto_increment,
  nome_artista VARCHAR(500),
  Data_Nascimento DATE,
  Biografia VARCHAR(10000),
  Data_Morte DATE,
  Nacionalidade VARCHAR(100),
  IsArtsy tinyint default 0,
  CHECK (IsArtsy IN (0, 1)),
  PRIMARY KEY (id_artista)
);

CREATE TABLE Obra_Arte
(
  id_Obra_Arte INT NOT NULL auto_increment,
  Titulo VARCHAR(2000) NOT NULL,
  Link_Imagem VARCHAR(200),
  Ano_Criacao DATE,
  Preco FLOAT,
  altura FLOAT,
  Largura FLOAT,
  Profundidade FLOAT,
  Diametro FLOAT,
  IsActive TINYINT(1) NOT NULL,
  id_artista INT NOT NULL,
  id_Tecnica INT NOT NULL,
  id_Estilo INT NOT NULL,
  IsArtsy tinyint default 0,
  CHECK (IsArtsy IN (0, 1)),
  PRIMARY KEY (id_Obra_Arte),
  FOREIGN KEY (id_artista) REFERENCES Artista(id_artista),
  FOREIGN KEY (id_Tecnica) REFERENCES Tecnica(id_Tecnica),
  FOREIGN KEY (id_Estilo) REFERENCES Movimento(id_Estilo)
);

CREATE TABLE Galeria
( -- Algumas colunas estão opcionais devido ao artsy, mas serao obrigatorias na app javaFX
  id_Galeria INT NOT NULL auto_increment,
  Nome_Galeria VARCHAR(50),
  Morada VARCHAR(100),
  Website VARCHAR(500),
  Email VARCHAR(500),
  Telefone VARCHAR(20),
  id_Cidade INT,
  id_colaborador INT,
  IsArtsy tinyint default 0,
  CHECK (IsArtsy IN (0, 1)),
  PRIMARY KEY (id_Galeria),
  FOREIGN KEY (id_Cidade) REFERENCES Cidade(id_Cidade),
  FOREIGN KEY (id_colaborador) REFERENCES Galerista(id_colaborador)
);

CREATE TABLE Evento -- exposicao
(
  id_Expo INT NOT NULL auto_increment,
  Nome VARCHAR(1000) NOT NULL default 'To Be Announced',
  Data_inicio DATE NOT NULL,
  Data_Fim DATE NOT NULL,
  Descricao VARCHAR(1000) NOT NULL default 'To Be Announced',
  id_Galeria INT NOT NULL,
  IsArtsy tinyint default 0,
  CHECK (IsArtsy IN (0, 1)),
  PRIMARY KEY (id_Expo),
  FOREIGN KEY (id_Galeria) REFERENCES Galeria(id_Galeria)
);

CREATE TABLE Emprestimo_Obra_Galeria
(
  id_Emprestimo INT NOT NULL auto_increment,
  Data_Entrada DATE NOT NULL,
  Data_Saida DATE,
  id_Galeria INT NOT NULL,
  id_Obra_Arte INT NOT NULL,
  PRIMARY KEY (id_Emprestimo),
  FOREIGN KEY (id_Galeria) REFERENCES Galeria(id_Galeria),
  FOREIGN KEY (id_Obra_Arte) REFERENCES Obra_Arte(id_Obra_Arte)
);

CREATE TABLE Obra_Exposicao
(
  id_Obra_Arte INT NOT NULL,
  id_Expo INT NOT NULL,
  PRIMARY KEY (id_Obra_Arte, id_Expo),
  FOREIGN KEY (id_Obra_Arte) REFERENCES Obra_Arte(id_Obra_Arte),
  FOREIGN KEY (id_Expo) REFERENCES Evento(id_Expo)
);

CREATE TABLE Obra_Materiais
(
  id_Material INT NOT NULL,
  id_Obra_Arte INT NOT NULL,
  PRIMARY KEY (id_Material, id_Obra_Arte),
  FOREIGN KEY (id_Material) REFERENCES Materiais(id_Material),
  FOREIGN KEY (id_Obra_Arte) REFERENCES Obra_Arte(id_Obra_Arte)
  ON DELETE cascade on Update cascade
);

-- Create User with All Privileges
CREATE USER 'rest_api'@'localhost' IDENTIFIED BY 'P@ssw0rd';
GRANT ALL PRIVILEGES ON explorart.* TO 'rest_api'@'localhost';
FLUSH PRIVILEGES;
