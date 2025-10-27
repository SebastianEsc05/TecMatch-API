USE tecmatch;

SELECT
	u.nombre,
    u.carrera,
    COUNT(l.id) AS 'Likes Totales' 
FROM usuarios AS u
INNER JOIN likes AS l
	ON u.id = l.liked_id
GROUP BY u.id, u.nombre, u.carrera;

DELIMITER $$

CREATE PROCEDURE listarPorLikes(IN p_offset INT)
BEGIN

    SELECT
        u.id,
        u.nombre,
        u.carrera,
        u.correo,
        u.contrasenia,
        u.descripcion,
        u.sexo,
        u.telefono,
        COUNT(l.id) AS total_likes
    FROM usuarios AS u
    INNER JOIN likes AS l
        ON u.id = l.liked_id
    GROUP BY 
        u.id, u.nombre, u.carrera, u.correo, 
        u.contrasenia, u.descripcion, u.sexo, u.telefono
    ORDER BY total_likes DESC
    LIMIT 15 OFFSET p_offset;
END $$

DELIMITER ;


CALL listarPorLikes(0);

DELIMITER $$

CREATE PROCEDURE listarPorDislikes(IN p_offset INT)
BEGIN
	SELECT
		u.id,
        u.nombre,
        u.carrera,
        u.correo,
        u.contrasenia,
        u.descripcion,
        u.sexo,
        u.telefono,
        COUNT(d.id) AS total_dislikes
	FROM usuarios AS u
	INNER JOIN dislikes AS d
		ON u.id = d.disliked_id
	GROUP BY u.id, u.nombre, u.carrera, u.correo, 
        u.contrasenia, u.descripcion, u.sexo, u.telefono
	ORDER BY total_dislikes
    LIMIT 15
    OFFSET p_offset;
	
END $$

DELIMITER ;

CALL listarPorDislikes(1);



    
DELIMITER $$

CREATE PROCEDURE listarAlumnosPorHobbies(IN p_descripcion VARCHAR(50), IN p_offset INT)
BEGIN
	SELECT 
		u.id,
        u.nombre,
        u.carrera,
        u.correo,
        u.contrasenia,
        u.descripcion,
        u.sexo,
        u.telefono
FROM usuarios AS u
INNER JOIN hobbies_usuarios AS hu
	ON u.id=hu.usuario_id
INNER JOIN hobbies AS h
	ON h.id=hu.hobbie_id
WHERE h.descripcion=p_descripcion
ORDER BY u.id
LIMIT 15
OFFSET p_offset;
END $$

DELIMITER ;

CALL listarAlumnosPorHobbies('Fútbol',1);


DELIMITER $$

CREATE PROCEDURE listarAlumnosPorIntereses(IN p_descripcion VARCHAR(50),IN p_offset INT)
BEGIN
	SELECT
		u.id,
        u.nombre,
        u.carrera,
        u.correo,
        u.contrasenia,
        u.descripcion,
        u.sexo,
        u.telefono
	FROM intereses AS i
	INNER JOIN intereses_usuarios AS iu
		ON i.id= iu.interes_id
	INNER JOIN usuarios AS u
		ON u.id= iu.usuario_id
	WHERE i.descripcion=p_descripcion
	GROUP BY u.id,u.nombre,u.carrera
    LIMIT 15
    OFFSET p_offset;
    
END $$

DELIMITER ;

CALL listarAlumnosPorIntereses('Deportes',1);

DELIMITER $$

CREATE PROCEDURE listarMatches (IN p_usuarioId INT,IN p_offset INT)
BEGIN
	SELECT 
		u2.id,
        u2.nombre,
        u2.carrera,
        u2.correo,
        u2.contrasenia,
        u2.descripcion,
        u2.sexo,
        u2.telefono
	FROM usuarios AS u1
	INNER JOIN matches AS m
		ON u1.id=m.usuario1_id
	INNER JOIN usuarios AS u2
		ON u2.id=m.usuario2_id
	WHERE u1.id=p_usuarioId
	LIMIT 15
    OFFSET p_offset;
END $$

DELIMITER ;

SELECT 
	u.id,
	u.nombre,
	u.carrera,
	u.correo,
	u.contrasenia,
	u.descripcion,
	u.sexo,
	u.telefono
FROM usuarios AS u
INNER JOIN likes as l
	ON u.id = l.liker_id
WHERE l.liked_id=2;

DELIMITER $$

CREATE PROCEDURE listarLikes(IN p_usuarioId INT, IN p_offset INT)
BEGIN
	SELECT 
		u.id,
		u.nombre,
		u.carrera,
		u.correo,
		u.contrasenia,
		u.descripcion,
		u.sexo,
		u.telefono
	FROM usuarios AS u
	INNER JOIN likes as l
		ON u.id = l.liker_id
	WHERE l.liked_id=p_usuarioId
    LIMIT 15
    OFFSET p_offset;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE listarDislikes(IN p_usuarioId INT, IN p_offset INT)
BEGIN
	SELECT 
		u.id,
		u.nombre,
		u.carrera,
		u.correo,
		u.contrasenia,
		u.descripcion,
		u.sexo,
		u.telefono
	FROM usuarios AS u
	INNER JOIN dislikes as d
		ON u.id = d.disliker_id
	WHERE d.disliked_id=p_usuarioId
    LIMIT 15
    OFFSET p_offset;
END $$

DELIMITER ;

-- SP pendiente

DELIMITER $$

CREATE PROCEDURE listarPorHobbiesEIntereses(IN descripcionHobbie VARCHAR(50),
											IN descripcionInteres VARCHAR(50),
                                            IN p_offset INT)
BEGIN
	SELECT
		u.id,
		u.nombre,
		u.carrera,
		u.correo,
		u.contrasenia,
		u.descripcion,
		u.sexo,
		u.telefono
	FROM usuarios AS u
	INNER JOIN (
		SELECT 
			hu.id,
			hu.usuario_id,
			hu.hobbie_id,
			h.descripcion
		FROM hobbies AS h
		INNER JOIN hobbies_usuarios AS hu
			ON hu.hobbie_id=h.id)AS sc1
		ON u.id=sc1.usuario_id
	INNER JOIN(
		SELECT 
			iu.id,
			iu.usuario_id,
			iu.interes_id,
			i.descripcion
		FROM intereses AS i
		INNER JOIN intereses_usuarios AS iu
			ON iu.interes_id=i.id)AS sc2
		ON u.id=sc2.usuario_id
	WHERE sc1.descripcion = descripcionHobbie AND sc2.descripcion=descripcionInteres;
END $$

DELIMITER ;

CALL listarMatches(1,0);
CALL listarLikes(2,0);
CALL listarDislikes(10,0);
CALL ListarPorHobbiesEIntereses('Fútbol','Deportes',0);

-- Listar las SPs en base de datos
SHOW PROCEDURE STATUS WHERE Db = 'tecmatch';

DROP PROCEDURE IF EXISTS listarPorLikes;
DROP PROCEDURE IF EXISTS listarPorDislikes;
DROP PROCEDURE IF EXISTS listarAlumnosPorHobbies;
DROP PROCEDURE IF EXISTS listarAlumnosPorIntereses;
DROP PROCEDURE IF EXISTS listarMatches;
DROP PROCEDURE IF EXISTS ListarLikes;
DROP PROCEDURE IF EXISTS ListarDislikes;
DROP PROCEDURE IF EXISTS ListarPorHobbiesEIntereses;