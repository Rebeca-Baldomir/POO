CREATE TABLE FILME (

 ID			INT		PRIMARY KEY,
 NOME 		CHAR[50],
 DIRETOR 	CHAR[50],
 GENERO 	CHAR[30],
 ENREDO 	CHAR[500],
 ANO 		INT,	

)

CREATE TABLE USUARIO (

ID		INT		PRIMARY KEY,
NOME	CHAR[50],
EMAIL 	CHAR[50],

)

CREATE TABLE USUARIO_has_FILMES (

ID_FILME	INT		FOREIGN KEY,
ID_USUARIO  INT 	FOREIGN KEY,
PONTUACAO	INT, 

)

CREATE TABLE USUARIO_has_USUARIO (

ID_USUARIO INT FOREIGN KEY,
ID_USUARIO INT FOREIGN KEY,

)
