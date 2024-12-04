# SK M& Service 코딩 과제

## 구현 완료
- 회원가입 기능
- 로그인 기능
- 로그아웃 기능
- 게시판 목록 조회 기능
- 게시판 세부내용 보기 및 조회수 증가
- 쿠키를 통한 로그인 시 게시판 작성
- 쿠키를 통한 본인의 게시글 수정 및 삭제
- 조건부 검색기능
- 게시글에 MultiPartFile로 외부 경로에 파일 업로드 및 다운로드 기능
- DB와 Scheduling을 활용한 세션 처리 -> JWT나 Redis로 추후 확장 가능
## 미구현(선택사항)
- FrontEnd

# Swagger-ui와 H2 DB를 조회하며 API 동작 테스트 가능

## 참고사항
- PostMan으로 구현한 기능 정상 작동 테스트 완료.
- 실행 시 application.properties의 노출을 막기위한 Jasypt 암호화가 걸려있어 환경변수로 jasypt.encryptor.password=dlrkddn 로 설정 후 실행.
- file.upload-dir에 파일 업로드할 PC의 경로 설정.
- 로그인이 필요한 서비스의 경우, H2 DB의 UserSession 테이블에서 userId와 매핑된 sessionId를 통해 API요청 필수.

## 화면 (API 명세서)
- http://localhost:8080/swagger-ui/index.html#/
<img width="791" alt="API명세서" src="https://github.com/user-attachments/assets/f6736262-bd1f-4d07-b61a-4c48a080ff4b">

# 기능 소개

## 로그인
- Postman
![image](https://github.com/user-attachments/assets/60c5e551-6607-4c40-bd79-53f4491f2753)
- DB 상태
![image](https://github.com/user-attachments/assets/ffd8392d-0f30-47da-b6c0-b7187c5a9c1e)
![image](https://github.com/user-attachments/assets/1a4da058-7e91-4382-850b-923495b2ecf8)

## 로그아웃
- Postman
![image](https://github.com/user-attachments/assets/321a2981-5794-445c-9ba2-bc20e78ee5a8)
- DB 상태

![image](https://github.com/user-attachments/assets/e3ca42c3-1357-44d5-8007-2083b233bcc1)



## 파일 업로드, 다운로드

- 게시글에 파일 등록
<img width="851" alt="파일 첨부" src="https://github.com/user-attachments/assets/40efd4bf-d1c5-457e-b3df-bf9cbccc84fa">


- 파일 업로드
<img width="299" alt="파일 업로드" src="https://github.com/user-attachments/assets/3f18e7df-58d9-4df4-8cae-f009158baa10">


-파일 다운로드

<img width="858" alt="파일 다운로드" src="https://github.com/user-attachments/assets/5c339076-6361-43d5-8e36-6e30a511fef7">



## 개선 사항
- 현재는 Session이 Scheduled로 주기적으로 실행되지만 Redis를 이용해 TTL을 설정하는 인메모리 DB방식으로 저장한다면 효율을 더욱 개선가능할 것 같다.
- 세션 방식은 서버의 부담이 매우 커지므로 추후에 JWT방식으로 구현하는 것도 가능할 것 같다.
- 회원가입 시 이메일을 받는 이유는 추후에 SMTP와 Redis를 이용한 이메일 인증 로직을 넣을 수 있다고 판단하였기 떄문이다.

