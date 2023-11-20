-- 기존테이블 삭제
DELETE FROM exam_coupon;
ALTER TABLE exam_coupon AUTO_INCREMENT = 1;
-- 샘플 글
-- 위 delete 말고 얘만 실행하는 거임 !
INSERT INTO exam_coupon (cp_name, cp_kind, cp_sno) VALUES
('치킨', '할인권', 'chi8-s5-66'),
('헬스 PT', '정기권', 'hel9-se-55'),
('핸드크림', '할인권', 'han-55-df'),
('스타벅스', '정기권', 'star-5d-4s')
;