
insert into user(`id`, `name`, `email`, `create_at`, `update_at`) values (1, 'seheon', '1@naver.com', now(), now());

insert into user(`id`, `name`, `email`, `create_at`, `update_at`) values (2, 'jiwo', '2@naver.com', now(), now());

insert into user(`id`, `name`, `email`, `create_at`, `update_at`) values (3, 'hyontack', '3@naver.com', now(), now());

insert into user(`id`, `name`, `email`, `create_at`, `update_at`)  values (4, 'sonhyon', '4@naver.com', now(), now());

insert into user(`id`, `name`, `email`, `create_at`, `update_at`) values (5, 'seheon', '5@naver.com', now(), now());

insert into publisher(`id`, `name`) values (1, '패스트캠퍼스');
insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (1, 'JPA초격차', 1, false, 100);
insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (2, 'spring security', 1, false, 200);
insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (3, '올인원 패키지', 1, true, 100);

insert into review(`id`, `title`, `content`, `score`, `book_id`, `user_id`) values (1, '내 인생을 바꾼 책', '너무너무 좋았어요', 5.0, 1, 1);
insert into review(`id`, `title`, `content`, `score`, `book_id`, `user_id`) values (2, '너무 진도가 빨라요', '별로였어요', 3.0, 2, 2);
insert into comment(`id`, `comment`, `review_id`) values (1, '저도 좋았어요', 1);
insert into comment(`id`, `comment`, `review_id`) values (2, '저는 별로였는데요?', 1);
insert into comment(`id`, `comment`, `review_id`) values (3, '저도 그냥 그랬어요', 2);