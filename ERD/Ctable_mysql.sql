DROP TABLE IF EXISTS exam_coupon;

CREATE TABLE exam_coupon
(
	cp_id int PRIMARY KEY AUTO_INCREMENT, -- id
	cp_name varchar(15) NOT NULL, -- 제목
	cp_kind varchar(3),
	cp_sno varchar(10)
	
)