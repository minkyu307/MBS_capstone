# MBS_capstone procect preview
### 프로젝트 설명

처음 SpringBoot와 JPA를 적용한 프로젝트로 컴퓨터 작업시 여러 소프트웨어나 툴을 옮겨가며 작업하는것이 귀찮고 비효율적이라 생각해 만들기 시작한 프로젝트.  

로그인이 필요한 웹사이트이며 채팅, 게시판(파일 업로드/다운로드), 메모, 조직도 등의 기능을 제공함.

화면을 2개 또는 4개로 분할해서 각 화면에 서로 다른 기능들을 띄워 사용할수 있도록 하여 편의성을 높임.

### 기술 스택

- 어플리케이션 API 서버 : SpringBoot / JPA(JPQL, NativeSQL) / JAVA 11
- DB 및 연결 : H2DB(개발중) / MySQL(배포시) / JPA(JPQL, NativeSQL)
- View화면 구성 : HTML, CSS, Javascript
- 언어 : JAVA 11
- 타겟 클라이언트 : Chrome, Edge
    
    

### 개발 일지

📚 **Tistory Blog**

[https://sbed307.tistory.com/category/MBS웹사이트 개발일지(Senior Project)](https://sbed307.tistory.com/category/MBS%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%20%EA%B0%9C%EB%B0%9C%EC%9D%BC%EC%A7%80%28Senior%20Project%29)

### 현시점 해결해야할 문제들

없는파일 올릴시 에러나게하기 - 해결

파일 삭제 - 해결

파일 올리기를 게시글작성할때 - 현상유지, 게시글 관리하기에 이게 더 편한듯

게시글 파일 업로드를 작성자만 - 

아이디 패스워드 검사 

패스워드 암호화

게시판 페이징

확장성있는 구조

대량 트랜잭션 대비

관리자 계정 및 페이지
