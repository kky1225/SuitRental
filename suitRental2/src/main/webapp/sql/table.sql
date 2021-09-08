/*회원관리*/
create table xmember(
	mem_num number not null,
	id varchar2(12) unique not null,
	auth number(1) default 2 not null,	/* 회원등급 : 0(탈퇴) 대여금지회원(1) 회원(2) 관리자(3) */
	constraint xmember_pk primary key (mem_num)
);

/* Q&A 게시판 - 질문 */
create table xboard_qna(
	qna_num number not null,
	title varchar2(150) not null,
	q_content clob not null,
	a_content clob,
	hit number default 0 not null,
	reg_date date default sysdate not null,
	answer_date date,
	filename varchar2(150),
	ip varchar2(40) not null,
	mem_num number not null,
	constraint xquestion_pk primary key (qna_num),
	constraint xquestion_fk foreign key (mem_num) references xmember (mem_num)
);

create sequence xboard_qna_seq;
