/*회원관리*/
create table xmember(
	mem_num number not null,
	id varchar2(12) unique not null,
	auth number(1) default 2 not null,	/* 회원등급 : 0(탈퇴) 대여금지회원(1) 회원(2) 관리자(3) */
	constraint xmember_pk primary key (mem_num)
);
 
/* Q&A 게시판 */
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


/*제품 등록*/
create table suit(
    x_code number not null,
    x_name varchar2(20) not null,
    x_price number(10) not null,  
    x_stock number(10) not null, 
    x_size varchar2(20) not null, 
    x_brand varchar2(20) not null, 
    x_rental_count number(10) default 0 not null, 
    x_gender varchar2(20) not null, 
    x_hit number default 0 not null,  
    x_like number(18) default 0 not null, 
    x_reg_date date default sysdate not null, 
    x_purchase number(10) default 0 not null,
    x_type varchar2(20) not null,
    CONSTRAINT suit_pk PRIMARY KEY(x_code)
);

create sequence suit_seq;
