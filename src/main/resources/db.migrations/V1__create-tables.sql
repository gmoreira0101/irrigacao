CREATE TABLE if not exists lugar(
    id_lugar int PRIMARY KEY AUTO_INCREMENT,
    ds_endereco varchar(225));


CREATE TABLE if not exists clima (
    id_clima int PRIMARY KEY AUTO_INCREMENT,
    temperatura int,
    umidade int,
    id_lugar int,
    ds_chuva varchar(3),
    data_clima TIMESTAMP,
    CONSTRAINT fk_lugar
        FOREIGN KEY (id_lugar)
        REFERENCES lugar(id_lugar)
);

CREATE TABLE if not exists torneira (
    id_torneira int PRIMARY KEY AUTO_INCREMENT,
    status_torneira varchar(15),
    id_lugar int(10),
    CONSTRAINT fk_lugar_torneira
        FOREIGN KEY (id_lugar)
        REFERENCES lugar(id_lugar)
);

CREATE TABLE if not exists historico_torneira(
    codigo int PRIMARY KEY AUTO_INCREMENT,
    hora TIMESTAMP,
    operacao int(10),
    status int(10),
    id_lugar int(10),
    CONSTRAINT fk_lugar_historico
        FOREIGN KEY (id_lugar)
        REFERENCES lugar(id_lugar)
);

CREATE TABLE if not exists RESERVA_AGUA(
    id_reserva int PRIMARY KEY AUTO_INCREMENT,
    nr_litros int(100),
    id_lugar int(10),
    CONSTRAINT fk_lugar_reserva
        FOREIGN KEY (id_lugar)
        REFERENCES lugar(id_lugar)
);

INSERT INTO lugar (ds_endereco) VALUES ('São Paulo');
INSERT INTO torneira (status_torneira, id_lugar) values ('Desligada', 1);