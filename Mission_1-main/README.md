# Zerobase_Mission_1

## 와이파이 북마크서비스

- 제로베이스 mission1 북마크서비스 미션을 구현한 저장소 입니다.

<br>

### 사용기술

- tomcat 8.5
- intellij
- maven
- java(jdk_1.8)
- javascript
- jsp
- sqlite

### 사용 라이브러리

- okhttp
- gson
- lombok
- sqlite-jdbc
- servelet

### 프로젝트구조

#### package

- util
  - DatabaseUtil : 데이터베이스 사용유틸로 데이터베이스 접근과 쿼리에 사용됩니다.
  - OpenApiUtil : OpenApi 관련 외부 http 프로토콜로 OPENAPI 관련 데이터를 접근하는데 사용합니다.
  - ContextListener : JSP 초기실행시 서블릿 컨텍스트를 사용하여 실행되어야할 코드를 위해 사용됩니다.
- BOOKMARK
- BOOKMARKGROUP
- LOCATIONHISTORY
- WIFIINFO

```
 *DAO : 관련 데이터베이스 접근 관련 메소드가 작성되어있습니다.
 *DTO : 데이터베이스에대한 데이터를 담는 자료형입니다.
```

- web
  - css
  - js
    - common.js : 좌표불러오기 코드가 작성되어있습니다. (공통 코드)
    - toIndex.js : 인덱스 파일로 이동
  - sql
    - 설정용 sql 파일이 작성되어있습니다.
  - WEB-INF
    - web.xml 을 수정하여 초기실행 코드를 추가하였습니다.
  - pages..

<br>

```
pages:
각 파일은 파일명에따른 작업을합니다.
ex) bookmark_add.jsp => 북마크 추가관련 페이지

*_add => 데이터 추가
*_delete=> 데이터삭제
*_edit => 데이터의 수정
*_table => 관련 테이블 표로 출력
*_submit => add, edit 등 form을 작업하는페이지

index => 초기 시작위치
bookmark => 북마크 관련페이지
bookmark_group => 북마크그룹 관련페이지
location / s => 위치정보 관련 페이지
menu => 메뉴 파일


```
