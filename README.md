# SK M& Service 코딩 과제

## 기능 구현 사항

### 1. 회원가입 기능
- 사용자 정보를 입력받아 회원가입을 처리합니다.
- 향후 SMTP와 Redis를 이용한 이메일 인증 로직 추가 가능

### 2. 로그인 기능
- 사용자 로그인 시, 이메일과 비밀번호를 확인하여 세션에 정보를 저장합니다.
- 쿠키를 사용한 세션 관리 기능 추가

### 3. 로그아웃 기능
- 로그인한 사용자는 로그아웃 기능을 통해 세션을 종료하고, 쿠키에 저장된 정보를 삭제합니다.

### 4. 게시판 목록 조회 기능
- 게시판에 저장된 게시물들을 조회할 수 있는 기능을 제공합니다.
- 게시물 목록은 정렬 방식에 따라 역순/정순 페이징 처리 가능

### 5. 게시판 세부 내용 보기 및 조회수 증가
- 게시물의 세부 정보를 조회하며, 조회수는 조회 시 자동으로 증가합니다.

### 6. 게시글 작성
- 로그인 후 게시판에 게시글을 작성할 수 있습니다.
- 게시글 작성 시, 로그인한 사용자의 정보가 자동으로 게시글에 연동됩니다.

### 7. 본인의 게시글 수정 및 삭제 기능
- 로그인한 사용자는 자신이 작성한 게시물을 수정하거나 삭제할 수 있습니다.
- 사용자 인증을 위해 쿠키를 이용한 인증 처리

### 8. 조건부 검색 기능
- 게시판에서 조건을 통해 게시물을 검색할 수 있는 기능을 추가했습니다.
- 예를 들어, 제목이나 내용에서 특정 단어를 검색할 수 있습니다.

### 9. MultiPartFile을 이용한 파일 업로드 및 다운로드 기능
- 게시물에 파일을 첨부할 수 있으며, 업로드된 파일을 다운로드할 수 있는 기능을 제공합니다.
- 외부 경로로 파일을 저장하여 파일 관리 시스템 구현
- 추후 AWS와 같은 Web Server에 저장 예정입니다.

### 10. DB와 Scheduling을 활용한 세션 처리
- 세션 관리는 DB와 Scheduling을 통해 주기적으로 처리됩니다.
- 세션 관련 데이터를 UserSession 테이블에 저장하여 관리합니다.

### 11. 추후 확장 가능성
- JWT를 사용한 세션 관리로 확장할 수 있습니다.
- Redis를 활용하여 TTL(Time-To-Live)을 설정하고 세션 관리를 개선할 수 있습니다.

### 12. Swagger-ui와 H2 DB 조회
- Swagger-ui를 사용하여 API를 테스트할 수 있습니다. 그러나, **쿠키 기반 및 MultipartFile 관련 로직이 제대로 동작하지 않는 것을 확인, PostMan으로 테스트 시 정상 작동**.
- H2 DB에 직접 접속하여 데이터를 조회할 수 있습니다.

## 미구현 사항

### 1. FrontEnd
- Frontend 부분은 선택사항이라 구현하지 않았습니다.
- 해당 부분은 HTML/CSS/JS 기반의 기본적인 페이지로 추가하거나, React나 Vue.js 등의 프레임워크로 구현할 수 있습니다.

## 참고 사항

- **PostMan**을 이용하여 구현한 기능들을 테스트한 결과 정상적으로 작동하는 것을 확인했습니다.
- **Jasypt 암호화**: 환경변수로 `jasypt.encryptor.password=dlrkddn`을 설정하고 실행 시 암호화된 값을 복호화하여 사용합니다.
- **파일 업로드**: `file.upload-dir`에 파일을 업로드할 PC의 경로를 설정해야 합니다.
- **로그인 서비스**: 로그인된 사용자의 세션 관리를 위해 **H2 DB**의 `UserSession` 테이블에서 `userId`와 매핑된 `sessionId`를 통해 API 요청을 처리합니다. 이를 통해 패킷이 노출되더라도 유저의 정보가 노출되지 않습니다.

## API 명세서

- [Swagger UI](http://localhost:8080/swagger-ui/index.html#/): API 문서 및 테스트

### 주요 API 기능
- **로그인**: 사용자가 이메일과 비밀번호로 로그인하여 JWT 토큰을 발급받고 이를 쿠키에 저장하여 인증을 유지합니다.
- **로그아웃**: 로그인된 사용자가 로그아웃하면, 세션을 종료하고 쿠키를 삭제합니다.
- **게시판 관리**: 게시물 작성, 수정, 삭제, 조회, 조회수 증가 등의 기능을 제공합니다.
- **파일 업로드/다운로드**: 게시물에 파일을 첨부하고, 업로드된 파일을 다운로드할 수 있습니다.

## 개선 사항 및 향후 계획

1. **세션 처리 개선**
   - 현재는 DB와 Scheduling을 활용한 세션 관리 방식이지만, Redis를 사용한 인메모리 방식으로 처리하면 더 효율적이고 확장 가능한 시스템을 구축할 수 있습니다.
   - JWT 방식으로 세션 관리 방식을 변경하여, 서버 부하를 줄이고 클라이언트 측에서 토큰을 관리할 수 있게 할 수 있습니다.

2. **이메일 인증**
   - 회원가입 시 이메일 인증 로직을 추가하려고 합니다. SMTP 서버와 Redis를 활용하여 이메일 인증 토큰을 발송하고 확인할 수 있습니다.
  
3. **배포**
   - 현재는 Jasypt 암호화로 서버에 application.properties를 업로드 하였지만, 추후 배포 환경에서는 Jenkins의 Credential을 이용해 서버에 올릴 필요 없이 파이프라인을 작성하여 해킹의 위험에 노출되지 않게 구현할 계획입니다.

## 실행 방법

1. **환경 설정**:
   - `application.properties` 파일의 설정을 확인하고, 필요한 환경변수를 설정하세요.
   - `jasypt.encryptor.password=dlrkddn`을 환경변수로 설정합니다.
   - `file.upload-dir`에 파일을 업로드할 경로를 설정합니다.

2. **Postman**과 **Swagger-UI**를 이용해 API 기능을 테스트할 수 있습니다.
   - Postman을 통해 로그인, 로그아웃, 게시글 작성/수정/삭제, 파일 업로드/다운로드 등을 확인할 수 있습니다.

## 결론

본 프로젝트는 백엔드 기능을 중점으로 구현하였으며, 기본적인 게시판 시스템을 제공합니다. 회원가입, 로그인, 게시판 기능 등 기본적인 CRUD 기능을 다루었고, 파일 업로드 및 다운로드 기능도 추가하여 실제 서비스에 필요한 기능들을 모두 갖추었습니다. 앞으로 Redis나 JWT 등을 활용하여 시스템의 확장성과 성능을 더욱 향상시킬 수 있을 것입니다.


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

