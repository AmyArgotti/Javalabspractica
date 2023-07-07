

insert into student(first_name, last_name, age) values ('Amy' , 'Argotti', '21');
insert into student(first_name, last_name, age) values ('Sofia' , 'Villalva', '21');
insert into student(first_name, last_name, age) values ('Albaraa' , 'Shagat', '20');
insert into student(first_name, last_name, age) values ('Roxanna' , 'Rodriguez', '20');


insert into course(title, start_date, finish_date) values ('Java', '2022-02-02','2023-02-02');
insert into course(title, start_date, finish_date) values ('SQL', '2022-04-12','2023-04-12');
insert into course(title, start_date, finish_date) values ('Phyton', '2022-05-05','2023-05-05');
insert into course(title, start_date, finish_date) values ('C++', '2022-08-08','2023-08-08');


insert into lesson (name, start_time, finish_time, course_id)
values ('Generics','12:00','13:00',1),
       ('Objects','13:00','14:00',1),
       ('Create','14:00','15:00',2),
       ('Lists','15:00','16:00',3)
;

insert into student_course(student_id, course_id)
values (1, 1), (1, 2), (1, 3), (2, 3), (3, 1), (3, 3);