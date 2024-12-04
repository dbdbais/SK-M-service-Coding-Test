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
- PostMan으로 구현한 기능 정상 작동 테스트 완료
- 실행 시 Jasypt 암호화가 걸려있어 환경변수로 jasypt.encryptor.password 설정 후 실행
- file.upload-dir에 파일 업로드할 PC의 경로 설정할 것
- 로그인이 필요한 서비스의 경우, H2 DB의 UserSession 테이블에서 userId와 매핑된 sessionId를 통해 API요청을 보내야 함.

## 화면 (API 명세서)
- http://localhost:8080/swagger-ui/index.html#/
<img width="791" alt="API명세서" src="https://github.com/user-attachments/assets/f6736262-bd1f-4d07-b61a-4c48a080ff4b">

## 개선 사항
- 현재는 Session이 Scheduled로 주기적으로 실행되지만 Redis를 이용해 TTL을 설정하는 인메모리 DB방식으로 저장한다면 효율을 더욱 개선가능할 것 같다.
- JWT방식으로 구현하는 것도 가능하다.
- 회원가입 시 이메일을 받는 이유는 추후에 SMTP와 Redis를 이용한 이메일 인증 로직을 넣을 수 있다고 판단하였음.
