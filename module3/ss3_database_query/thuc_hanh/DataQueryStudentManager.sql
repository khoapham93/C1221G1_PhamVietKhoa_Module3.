USE student_management;

SELECT *
FROM student;

SELECT *
FROM student
WHERE status = true;

SELECT *
FROM subject
WHERE credit < 10;

-- hiển thị danh sách học viên lớp A1
SELECT S.student_id, S.student_name, C.class_name
FROM Student S join Class C on S.class_id = C.class_id
WHERE C.class_name = 'A1';

-- Hiển thị điểm môn CF của các học viên
SELECT S.student_id, S.student_name, Sub.subject_name, M.mark
FROM Student S join Mark M on S.student_id = M.subject_id join Subject Sub on M.subject_id = Sub.subject_id
WHERE Sub.subject_name = 'CF';