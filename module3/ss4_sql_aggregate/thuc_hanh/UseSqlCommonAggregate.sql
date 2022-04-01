USE student_management;

SELECT address, COUNT(student_id) AS 'Sum_of_student'
FROM student
GROUP BY address;

SELECT s.student_id, s.student_name, avg(m.mark)
FROM student s
         INNER JOIN mark m ON s.student_id = m.student_id
GROUP BY s.student_id, s.student_name;

SELECT s.student_id, s.student_name, avg(m.mark)
FROM student s
         INNER JOIN mark m ON s.student_id = m.student_id
GROUP BY s.student_id, s.student_name
HAVING avg(m.mark) >=  ALL (SELECT avg(mark) FROM mark GROUP BY mark.student_id);