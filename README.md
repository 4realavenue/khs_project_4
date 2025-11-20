# 1. API 명세서

https://documenter.getpostman.com/view/49692973/2sB3WyJwHr#944dc561-279f-4e2e-a237-2252073e8883

# 2. ERD

https://www.erdcloud.com/d/ku2ySzaDYp4k4YCSn

---
---

# Description

Java 언어를 사용한 Spring boot를 활용해서 만든 "일정 관리 앱 Develop" 버전이다.

일정 생성, 일정 전체 조회, 일정 단건 조회, 일정 수정, 일정 삭제의 기능만 제공했던 이전 버전과 달리

이번 프로젝트에서는
>일정의 [생성], [전체조회], [단건조회], [수정], [삭제] 기능과
유저의 [생성], [전체조회], [단건조회], [수정], [삭제], [로그인], [로그아웃] 의 기능이 추가 되었다.

Session과 Cookie를 이용하여 로그인 기능을 구현하는 것을 목표로, 
로그인이 되어 있지 않을 때, 일정의 작업을 수행하려고 하면 예외 처리를 이용하였다.


--- 

# Environment

Windows 환경에서 Java 버전은 17 버전으로 작업 했다.

프로젝트는 Sprint Boot로 설정했으며,
Spring Boot 설정 시 Dependencies(종속)은
>- Lombok
- MySQL Driver
- Spring Data JPA
- Spring Web

위 4개를 추가하고 프로젝트를 시작했다


---

# Usage
"`기능명 [요청Mapping] [주소] (Body에 입력할 내용 (JSON타입))`"

> 이 프로젝트의 기본은 `로그인`이다.

1. `유저 생성[POST] [/users] (userName, userEmail, userPassword))` 
후 
2. `로그인[POST] [/users/login] (userEmail, userPassword))` 하면 
3. `일정의 생성[POST] [/schedules] (title, content)` , 
4. `일정 전체 조회[GET] [/schedules] ()` ,
5. `일정 단건 조회[GET] [/schedules/{scheduleId}] ()` ,
6. `일정 수정[PUT] [/schedules/{scheduleId}] (title, content)` ,
7. `일정 삭제[DELETE] [/schedules/{scheduleId}] ()`
를 이용할 수 있다.

유저 관리 또한
생성을 비롯한

8. `유저 전체 조회[GET][/users] ()` ,
9. `유저 단건 조회[GET][/users/{userId}] ()` ,
10. `유저 정보 수정[PUT][/users/{userId}] (userName, userEmail, userPassword)` ,
11. `유저 정보 삭제[DELETE] [/users/{userId}] ()`
기능을 이용할 수 있으며

12. `로그아웃[POST] [/users/logout] ()` 기능을 이용하여 로그아웃 할 수 있다.
