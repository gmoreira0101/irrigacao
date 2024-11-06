
CREATE TABLE if not exists local(
    id_local NUMBER(10) PRIMARY KEY,
    ds_endereco VARCHAR2(225));


CREATE TABLE if not exists clima (
    id_clima NUMBER(10) PRIMARY KEY,
    temperatura NUMBER(5,2),
    umidade NUMBER(5,2),
    id_local NUMBER(10),
    ds_chuva VARCHAR2(3),
    data_clima TIMESTAMP,
    CONSTRAINT fk_local
        FOREIGN KEY (id_local)
        REFERENCES local(id_local)
);

CREATE TABLE if not exists torneira (
    id_torneira NUMBER(10) PRIMARY KEY,
    status_torneira VARCHAR2(15),
    id_local NUMBER(10),
    CONSTRAINT fk_local_torneira
        FOREIGN KEY (id_local)
        REFERENCES local(id_local)
);

CREATE TABLE if not exists historico_torneira(
    codigo NUMBER(10),
    hora TIMESTAMP,
    operacao VARCHAR2(10),
    status VARCHAR2(10),
    id_local NUMBER(10),
    CONSTRAINT fk_local_historico
        FOREIGN KEY (id_local)
        REFERENCES local(id_local)
);

CREATE TABLE if not exists RESERVA_AGUA(
    id_reserva NUMBER(10),
    nr_litros NUMBER(100),
    id_local NUMBER(10),
    CONSTRAINT fk_local_historico
        FOREIGN KEY (id_local)
        REFERENCES local(id_local)
);

CREATE SEQUENCE IF NOT EXISTS SQ_LOCAL START WITH 1

create or replace TRIGGER tr_local_seq
BEFORE INSERT ON local
FOR EACH ROW
DECLARE
    v_id_local local.id_local%type;
BEGIN
    SELECT sq_local.nextval INTO v_id_local FROM dual;
    :NEW.id_local := v_id_local;
END;

CREATE SEQUENCE IF NOT EXISTS torneira_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS SQ_HISTORICO_TORNEIRA START WITH 1
create or replace TRIGGER tr_historico_torneira_seq
BEFORE INSERT ON historico_torneira
FOR EACH ROW
DECLARE
    v_codigo historico_torneira.codigo%type;
BEGIN
    SELECT sq_historico_torneira.nextval INTO v_codigo FROM dual;
    :NEW.codigo := v_codigo;
END;

create or replace PROCEDURE registra
 (p_operacao IN  VARCHAR2,
  p_id_local in NUMBER) AS
  PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
  INSERT INTO historico_torneira (hora, operacao,id_local)
  VALUES (SYSTIMESTAMP, p_operacao, p_id_local);
  COMMIT;
END;

create or replace PROCEDURE reduz_agua
 (p_id_local in NUMBER) AS
 PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
  UPDATE reserva_agua
  SET nr_litros = nr_litros - 10
  WHERE id_reserva = p_id_local;
  COMMIT;
END;

create or replace TRIGGER tr_reduz_agua
AFTER INSERT ON historico_torneira
FOR EACH ROW
BEGIN
    reduz_agua(p_id_local => :NEW.id_local);
END;

create or replace TRIGGER tr_reposicao_agua
AFTER UPDATE ON reserva_agua
FOR EACH ROW
BEGIN
    IF :NEW.nr_litros = 50 or :NEW.nr_litros < 50 THEN
        DBMS_OUTPUT.PUT_LINE('Precisa de reabastecimento');
    END IF;
END;

create or replace TRIGGER tr_verifica_clima_quente
AFTER INSERT ON clima
FOR EACH ROW
DECLARE
    v_id_torneira torneira.id_torneira%TYPE;
BEGIN
    IF :NEW.temperatura > 24 AND :NEW.ds_chuva = 'Nao' THEN
        SELECT id_torneira INTO v_id_torneira
        FROM torneira
        WHERE id_local = :NEW.id_local
        AND ROWNUM = 1;
        registra(p_operacao => 'Ligado', p_id_local => :NEW.id_local);
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        NULL;
END;