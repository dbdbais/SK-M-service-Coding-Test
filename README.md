# SK M& Service 코딩 과제

- PostMan으로 구현한 기능 정상 작동 테스트 완료
- 실행 시 Jasypt 암호화가 걸려있어 환경변수로 jasypt.encryptor.password 설정 후 실행

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
- http://localhost:8080/swagger-ui/index.html#/

<img width="791" alt="API명세서" src="https://github.com/user-attachments/assets/f6736262-bd1f-4d07-b61a-4c48a080ff4b">

