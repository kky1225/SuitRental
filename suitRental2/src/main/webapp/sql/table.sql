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
    x_contents VARCHAR2(300) not null, 
    CONSTRAINT suit_pk PRIMARY KEY(x_code)
);

create sequence suit_seq;

/* 회원 정보 - 경규영*/
CREATE TABLE xmember(
	mem_num NUMBER PRIMARY KEY,
	id VARCHAR2(12) UNIQUE NOT NULL,
	auth NUMBER(1) DEFAULT 2 NOT NULL
);

CREATE SEQUENCE xmember_seq;

/* 회원 상세 정보 - 경규영 */
CREATE TABLE xmember_detail(
	mem_num NUMBER PRIMARY KEY,
	name VARCHAR2(30) NOT NULL,
	passwd VARCHAR2(35) NOT NULL,
	phone VARCHAR2(15) UNIQUE NOT NULL,
	email VARCHAR2(50) UNIQUE NOT NULL,
	zipcode VARCHAR2(5) NOT NULL,
	address1 VARCHAR2(90) NOT NULL,
	address2 VARCHAR2(90) NOT NULL,
	gender VARCHAR2(20) NOT NULL,
	rental_total NUMBER(10) DEFAULT 0 NOT NULL,
	rental_now NUMBER(10) DEFAULT 0 NOT NULL,
    non_return NUMBER(10) DEFAULT 0 NOT NULL,
	reg_date DATE DEFAULT SYSDATE NOT NULL,
	modify_date DATE DEFAULT SYSDATE NOT NULL,
    
	CONSTRAINT gender1 CHECK (gender IN ('male', 'female')),
	CONSTRAINT xmember_detail_fk FOREIGN KEY (mem_num) REFERENCES xmember(mem_num);
	
);

/* 리뷰 게시판 테이블 - 경규영 */
CREATE TABLE xreview(
	review_num NUMBER PRIMARY KEY,
	title VARCHAR2(150) NOT NULL,
	content CLOB NOT NULL,
	hit NUMBER(18) DEFAULT 0 NOT NULL,
	reg_date DATE DEFAULT SYSDATE NOT NULL,
	modify_date DATE,
	filename VARCHAR2(150),
	ip VARCHAR2(30) NOT NULL,
    mem_num NUMBER,
	
	CONSTRAINT xreview_mem_num_fk FOREIGN KEY (mem_num) REFERENCES xmember(mem_num)
);

CREATE SEQUENCE xreview_seq;
