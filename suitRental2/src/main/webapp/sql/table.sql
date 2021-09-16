/*제품 등록*/
create table suit(
    x_code number not null,
    x_name varchar2(30) not null,
    x_price number(10) not null,  
    x_stock number(10) not null, 
    x_size varchar2(5) not null, 
    x_brand varchar2(30) not null, 
    x_rental_count number(10) default 0 not null, 
    x_gender varchar2(20) not null, 
    x_hit number default 0 not null,  
    x_like number(18) default 0 not null, 
    x_reg_date date default sysdate not null, 
    x_purchase number(10) default 0 not null,
    x_type varchar2(20) not null,
    x_contents CLOB not null, 
    x_file VARCHAR(150) NOT NULL,
    
    CONSTRAINT suit_pk PRIMARY KEY(x_code)
);

create sequence suit_seq;


/*대여*/
create table rental(
	rent_num NUMBER primary key,
	x_code NUMBER not null,
	mem_num NUMBER not null ,
	rental_type VARCHAR2(20) not null,
	return_type VARCHAR2(20) not null,
	rental_date VARCHAR2(40)not null,
	return_date VARCHAR2(40)not null,
	
	CONSTRAINT rental_fk FOREIGN KEY (x_code) REFERENCES suit (x_code),
	CONSTRAINT rental_fk2 FOREIGN KEY (mem_num) REFERENCES xmember (mem_num)
	);
	
create sequence rental_seq;


/*대여 상세*/
create table rental_detail(
  rentd_num NUMBER NOT NULL, 
  rent_num NUMBER NOT NULL, 
  x_code  NUMBER NOT NULL, 
  CONSTRAINT rental_detail_pk PRIMARY KEY (rentd_num),
  CONSTRAINT rental_detail_fk FOREIGN KEY (rent_num) REFERENCES rental (rent_num),
  CONSTRAINT rental_detail_fk2 FOREIGN KEY (x_code) REFERENCES suit (x_code)
);


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
	phone VARCHAR2(15),
	email VARCHAR2(50),
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
    x_code NUMBER,
	
	CONSTRAINT xreview_mem_num_fk FOREIGN KEY (mem_num) REFERENCES xmember(mem_num),
	CONSTRAINT xreview_x_code_fk FOREIGN KEY (x_code) REFERENCES suit(x_code)
);

CREATE SEQUENCE xreview_seq;

/* 좋아요 테이블 - 경규영 */
CREATE TABLE xlikey(
	likey_num NUMBER PRIMARY KEY,
	x_code NUMBER,
	mem_num NUMBER,
	
	CONSTRAINT xlikey_x_code_fk FOREIGN KEY (x_code) REFERENCES suit(x_code),
	CONSTRAINT xlikey_mem_num_fk FOREIGN KEY (mem_num) REFERENCES xmember(mem_num)
);

CREATE SEQUENCE xlikey_seq;

/* 기부 게시판 */
create table xdonation(
	donation_num number not null,
	title varchar2(150) not null,
	content clob not null,
	hit number default 0 not null,
	reg_date date default sysdate not null,
	modify_date date default sysdate not null,
	filename varchar2(150),
	ip varchar2(40) not null,
	mem_num number not null,
	
	constraint xdonation_pk primary key (donation_num),
	constraint xdonation_fk foreign key (mem_num) references xmember (mem_num)
);

create sequence xdonation_seq;


/* 기부 게시판 댓글 */
create table xdonation_reply(
re_num number not null,
re_content varchar2(900) not null,
re_date date default sysdate not null,
re_modifydate date default sysdate not null,
re_ip varchar2(40) not null,
donation_num number not null,
mem_num number not null,

constraint donation_reply_pk primary key (re_num),
constraint donation_reply_fk foreign key (donation_num) references xdonation (donation_num),
constraint donation_reply_fk2 foreign key (mem_num) references xmember (mem_num)
);

create sequence donation_reply_seq;

/* 공지사항 게시판 */
create table xnotice(
	notice_num number not null,
	title varchar2(150) not null,
	content clob not null,
	hit number default 0 not null,
	reg_date date default sysdate not null,
	modify_date date default sysdate not null,
	filename varchar2(150),
	ip varchar2(40) not null,
	mem_num number not null,
	
	constraint xnotice_pk primary key (notice_num),
	constraint xnotice_fk foreign key (mem_num) references xmember (mem_num)
);

create sequence xnotice_seq;

/* 공지사항 댓글 */
create table xnotice_reply(
re_num number not null,
re_content varchar2(900) not null,
re_date date default sysdate not null,
re_modifydate date default sysdate not null,
re_ip varchar2(40) not null,
notice_num number not null,
mem_num number not null,

constraint notice_reply_pk primary key (re_num),
constraint notice_reply_fk foreign key (notice_num) references xnotice (notice_num),
constraint notice_reply_fk2 foreign key (mem_num) references xmember (mem_num)
);

create sequence notice_reply_seq;

/* Q&A 게시판 */
create table xboard_qna(
	qna_num number not null,
	title varchar2(150) not null,
	q_content clob not null,
	a_content clob,
	hit number default 0 not null,
	reg_date date default sysdate not null,
	ans_date date,
	filename varchar2(150),
	ip varchar2(40) not null,
	mem_num number not null,
	constraint xquestion_pk primary key (qna_num),
	constraint xquestion_fk foreign key (mem_num) references xmember (mem_num)
);

create sequence xboard_qna_seq;
