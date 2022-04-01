USE student_management;

-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất
SELECT subject_name, credit
FROM `subject`
WHERE credit = (SELECT max(credit) FROM `subject`);


-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
SELECT s.subject_id, s.subject_name, m.mark
FROM subject s
         INNER JOIN mark m ON s.subject_id = m.subject_id
WHERE m.mark = (SELECT max(mark) FROM mark);


-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
SELECT s.student_id, s.student_name, avg(mark) AS avg_mark
FROM student s
         INNER JOIN mark m ON s.student_id = m.student_id
GROUP BY s.student_id, s.student_name
ORDER BY avg(mark) DESC;