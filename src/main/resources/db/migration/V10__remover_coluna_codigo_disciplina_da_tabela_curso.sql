ALTER TABLE curso  
DROP FOREIGN KEY  curso_ibfk_2 ;
ALTER TABLE curso 
DROP COLUMN codigo_disciplina,
DROP INDEX codigo_disciplina ;