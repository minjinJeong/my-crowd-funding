# 프로젝트 소개
텀블벅이나 와디즈와 같은 크라우드 펀딩 플랫폼의 API 서버를 구축했습니다.


# 주요 관심사
## 목표
- 객체지향으로 설계하기
- 헥사고날 아키텍처 사용하기
- 클린 코드 유지하기
- 테스트 커버리지 70% 이상
- 대용량 트래픽 처리 해보기

## 브랜치 관리
모든 브랜치는 Pull Request하여 리뷰 후 merge 합니다.
- main : 제품으로 출시될 수 있는 브랜치
- develop : 다음 출시 버전을 개발하는 브랜치
- Feature : 기능을 개발하는 브랜치 (Git Flow 사용)

Reference
- [우아한 형제들 기술 블로그](https://techblog.woowahan.com/2553/)

## 기능
구현된 기능 목록입니다.
- 회원가입/회원탈퇴
- 회원정보 조회
- 배송지 주소/결제수단 등록
- 펀딩 등록/수정/삭제
- 펀딩 창작자/아이템/리워드 등록
- 펀딩 심사진행/심사완료/취소
- 후원하기
- 배송시작/배송중/배송완료


# 프로젝트 구조

## ERD
![Funding(2)](https://github.com/f-lab-edu/my-crowd-funding/assets/31675724/5cbf05b5-9bac-4f60-974b-12a06ca0c724)

## 기술 스택
- Spring Boot 3.2.0
- Java 17
- Spring Data JPA
- 실사용 DB: MySQL
- 테스트용 DB: H2


# 테스트
- Spring REST Docs를 위해 MockMVC로 컨트롤러 테스트
- JUnit5로 단위 테스트

## 테스트 문서


# 개발 이슈 및 해결
1. [Enum과 DB 매핑하기](https://velog.io/@p0tat0_chip/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-JPA-Enum-Convert)
2. [Timestamp와 JPA Entity](https://velog.io/@p0tat0_chip/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-TimeZone)
3. [MessageConverter 적용하기](https://velog.io/@p0tat0_chip/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-MessageConverter)
4. [MapStruct 적용하기](https://velog.io/@p0tat0_chip/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-MapStruct-%EC%A0%81%EC%9A%A9)
5. [Spring REST Docs 적용하기](https://velog.io/@p0tat0_chip/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-Spring-REST-Docs-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0)
6. [Spring Rest Docs 문서 분리하기](https://velog.io/@p0tat0_chip/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-Spring-Rest-Docs-%EB%AC%B8%EC%84%9C-%EB%B6%84%EB%A6%AC%ED%95%98%EA%B8%B0)
7. [커밋 메시지 수정하기](https://velog.io/@p0tat0_chip/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%BB%A4%EB%B0%8B-%EB%A9%94%EC%8B%9C%EC%A7%80-%EC%88%98%EC%A0%95%ED%95%98%EA%B8%B0)
8. [boolean is prefix 문제](https://velog.io/@p0tat0_chip/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-boolean-is-prefix-%EB%AC%B8%EC%A0%9C)
