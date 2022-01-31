# idus

### 프로젝트 구성

1. idus project

### 서비스 구동방법

1. 아래 Mysql DataBase 스키마를 실행합니다.

```sql
create database idus;
use idus;

create table idus_user
(
	id bigint auto_increment
		primary key,
	name varchar(20) not null,
	nickname varchar(30) not null,
	password varchar(1000) not null,
	mobile varchar(20) not null,
	email varchar(100) not null,
	gender varchar(10) null,
	created_date timestamp default CURRENT_TIMESTAMP not null,
	modified_date timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
charset=utf8mb4;

create table idus_order
(
	id bigint auto_increment
		primary key,
	order_key varchar(12) not null,
	user_id bigint not null,
	name varchar(100) not null,
	created_date timestamp default CURRENT_TIMESTAMP not null,
	modified_date timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	constraint order_user_id_fk
		foreign key (user_id) references idus_user (id)
)
charset=utf8mb4;
```

1. idus project를 실행합니다.

### API 리스트

Swagger : [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) (로그인, 로그아웃은 아래 설명 참조)

1. 회원 로그인(인증)
    1. POST localhost:8080/login
    2. ReuqestBody
        
        ```json
        {
            "email" : "1112@a.com",
            "password" : "!aAssdfsdfsdfsdf3"
        }
        ```
        
    3. ResponseBody
        
        ```json
        Header :
        	Authorization : Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTEyQGEuY29tIiwiZXhwIjoxNjQ0NTI1Nzc3LCJ1c2VybmFtZSI6IjExMTJAYS5jb20ifQ.cCZx08EKTrYFSx3gVtGuJUTBsadbllNT-6eS-V8yLycMjLhNwsnntJoYTEXXSMNST75ZRw6yhIFWWbyXGAvGyQ
        ```
        
    4. 회원가입, 로그인, 로그아웃을 제외한 모든 API는 Header에 Authorization 값을 입력하여 인증처리 후 사용할 수 있습니다.
        
        
2. 회원 로그아웃
    
    JWT으로 인증처리를 하여 회원 로그아웃을 따로 만들지 않았습니다.
    로그아웃시 클라이언트에서 JWT를 삭제하는것으로 대체합니다.
