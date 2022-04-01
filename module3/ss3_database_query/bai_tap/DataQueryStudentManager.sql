USE student_management;
-- hiển thị các sinh viên có tên bắt đầu bằng ký tự ‘h’
select student_id, student_name
from student
where student_name like 'h%';

-- Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12
select *
from class
where month(start_date) = 12;

-- Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5.
select *
from subject where credit between 3 and 5;

-- Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2.
update student
set class_id = 2
where student_name = 'Hung';
-- Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.
SELECT s.student_name, sub.subject_name, m.mark
from student as s
         inner join mark m on s.student_id = m.student_id
         inner join subject sub on sub.subject_id = m.subject_id
order by m.mark desc, s.student_name asc ;