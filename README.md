<h1>Try it</h1>
<div  align="center">
  <img width="50%" src="https://github.com/user-attachments/assets/b2992947-940a-40b5-a424-4f8005d95f71" alt="LOGO">
</div>
</br>
<h3 align="center">Spring 단기심화 2기 Ch.3 프로젝트</h3>

- **팀 명 :**  연봉1조
- **프로젝트 명 :** Try it
- **프로젝트 기간 :** 2025.01.02 - 2025.01.27
- **한줄 소개 :** 새로운 제품을 출시하는 기업과 체험하고 싶은 사용자를 연결하는 선착순 체험단 플랫폼
- **팀원 :** 손예지(리더), 신영한(부리더), 김주한, 하남규, 조승아
- **팀노션 링크 :** [💁🏻 노션 링크](https://teamsparta.notion.site/1-1-1672dc3ef51480ceb16ff07841605e7b)
- **배포 링크 :** [📮 링크](http://gateway-lb-1883933206.ap-northeast-2.elb.amazonaws.com)

## 📝 프로젝트 개요
`Try-it` 서비스는 새로운 제품을 출시하여 고객 반응을 얻고자 하는 기업과 신제품을 체험해보고 싶어하는 다양한 사람들을 이어주는 서비스를 개발하고자 하였습니다. </br>

상품을 체험해보고 싶은 사람이라면 누구나 신청이 가능하며, SNS의 팔로워 수나 기타 영상채널의 구독자 수와는 상관없이 모두가 선착순으로 체험이 가능하여 보다 솔직한 실제 사용 후기를 플랫폼 내의 모두가 공유할 수 있는 서비스를 만들어보고자 하였습니다. </br>
또한 고객들의 리뷰를 기반으로 하는 통계 기능을 제공하여 기업들에게 인사이트를 제공하고자 합니다. </br>

## 🥅 프로젝트 목표
- 대규모 트래픽이 예상되는 인기 체험단 모집 시 신청자 대기열을 안정적으로 관리합니다.
- Kafka 를활용해 체험단 상태변경을 실시간으로 감지하고 이벤트 기반으로 알림을 발행하는 시스템 구축합니다.
- 자주 조회되는 정보를 캐싱해 데이터베이스의 부하를 감소시킵니다.
- 필요한 기능들에 대해 생성, 수정과 관련된 항목의 테스트코드를 작성합니다.

## 👨‍👩‍👧‍👦 Team
| 손예지<br> | 신영한<br> | 김주한<br> | 하남규<br> | 조승아<br> |
| :---: | :---: | :---: |:---:| :---: |
| <img alt="예지" src="https://ca.slack-edge.com/T06B9PCLY1E-U07QEFTESGP-e2b23afd15a7-512" height="100" width="100"> | <img alt="영한" src="https://ca.slack-edge.com/T06B9PCLY1E-U07S875EUBV-g9ae29003de3-512" height="100" width="100"> | <img alt="주한" src="https://ca.slack-edge.com/T06B9PCLY1E-U06QUQQ5UU8-f0cd5f6b2a58-512" height="100" width="100"> | <img alt="남규" src="https://ca.slack-edge.com/T06B9PCLY1E-U07RKNHN003-db789eae7cdc-512" height="100" width="100"> | <img alt="승아" src="https://ca.slack-edge.com/T06B9PCLY1E-U07QQLBVDDJ-1001a73273bb-512" height="100" width="100"> |
| [handyejee](https://github.com/handyejee) | [syhan7516](https://github.com/syhan7516) | [Hany-Kim](https://github.com/Hany-Kim) |     [Namgyu11](https://github.com/Namgyu11)    | [hiimsajo](https://github.com/hiimsajo) | 
|<p align="left">- 알림기능 <br/>- 아키텍쳐 설계 및 초기구성<br/>- 일정관리</p>|<p align="left">- 리뷰기능<br/>- 통계기능</p>|<p align="left">- 상품, 이미지 업로드 기능<br/>- 체험단 신청기능<br/>- AWS 배포</p>|<p align="left">- 체험단 모집<br/>- 모집상태 관리</p>|<p align="left">- 사용자 기능<br/>- JWT 기반 인증 기능<br/>- Gateway 설정</p>|

## 📺 프로젝트 설계
### 🔗API 명세서</br>
<details>
  <summary>사용자 API</summary>
  <img alt="" src="https://github.com/user-attachments/assets/b3b42806-0558-4764-98ab-35a2a1400e46"  width="800">
</details>
<details>
  <summary>상품 API</summary>
  <img alt="" src="https://github.com/user-attachments/assets/b3b42806-0558-4764-98ab-35a2a1400e46"  width="800">
</details>

### 🔗테이블 명세서</br>
<img alt="" src="https://github.com/user-attachments/assets/b3b42806-0558-4764-98ab-35a2a1400e46"  width="800">

### 🔗ERD</br>
<img alt="" src="https://github.com/user-attachments/assets/0afea585-b5d6-4c56-a7a2-7341f1075f67"  width="800">

### 🔗인프라 설계도</br>
<img alt="" src="https://github.com/user-attachments/assets/7f469c41-4999-444f-94cf-96441c8f1e43"  width="800">

### 🔗카프카 구조도</br>
<img alt="" src="https://github.com/user-attachments/assets/346a7fe1-f7a3-4bc4-abb4-13b28b3a8f66"  width="800">

### 🔗WBS 일정관리</br>
<img alt="" src="https://github.com/user-attachments/assets/b3b42806-0558-4764-98ab-35a2a1400e46"  width="800">


## ➡️User flow
<img width="2800" alt="User flow" src="https://github.com/user-attachments/assets/cd22c0d1-8f4a-44e7-aea0-35a53ebd0f64">

</br>

## 🔧Tech Stack
### Tools
| Git | Github | Slack |
| :---: | :---: | :---: |
| <img alt="git logo" src="https://git-scm.com/images/logos/downloads/Git-Icon-1788C.svg" width="65" height="65" > | <img alt="github logo" src="https://techstack-generator.vercel.app/github-icon.svg" width="65" height="65"> | <img alt="Slack logo" src="https://github.com/user-attachments/assets/708bcf8f-f064-40e9-b060-a7b3438107da" height="65" width="65">|
### Back-end
| Java | PostgreSQL | Docker | AWS | Spring | Spring<br>Boot | Kafka | Redis | Zipkin |
| :---: | :---: | :---: | :---: | :---: | :---: |-------|-------|--------|
| <div style="display: flex; align-items: flex-start;"><img src="https://techstack-generator.vercel.app/java-icon.svg" alt="icon" width="65" height="65" /></div> | <div style="display: flex; align-items: flex-start;"><img src="https://github.com/user-attachments/assets/3a2dd96d-0989-4ce0-b178-84bf23b2e60c" alt="PostgreSQL logo" width="65" height="65" /></div>| <div style="display: flex; align-items: flex-start;"><img src="https://techstack-generator.vercel.app/docker-icon.svg" alt="icon" width="55"/></div> | <div style="display: flex; align-items: flex-start;"><img src="https://techstack-generator.vercel.app/aws-icon.svg" alt="icon" width="65" height="65" /></div> | <img alt="spring logo" src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" height="50" width="50" > | <img alt="spring-boot logo" src="https://t1.daumcdn.net/cfile/tistory/27034D4F58E660F616" width="65" height="65" > |   <div style="display: flex; align-items: flex-start;"><img src="https://github.com/user-attachments/assets/6f1f5691-2609-4e77-a628-e84f3a54854fc" alt="Kafka logo" width="65" height="65" /></div>    |    <div style="display: flex; align-items: flex-start;"><img src="https://github.com/user-attachments/assets/88e14b10-c73e-491c-8f9a-dd43abf49b8f" alt="Redis logo" width="65" height="65" /></div>   |   <div style="display: flex; align-items: flex-start;"><img src="https://github.com/user-attachments/assets/66d6cfd6-6b67-4ecc-b9f9-5ea6c7cb4352" alt="Zipkin logo" width="65" height="65" /></div>     |
<br/>

## 🌲 Git
### Branch
- `main` : 서비스 운영 브랜치입니다.
- `dev` : 개발 환경 브랜치입니다. 개별적으로 작업했던 내용을 합치고 검토합니다.
- `feat/(서비스명)/...` : (서비스이름) 서비스별 세부 브랜치입니다.
### Commit & Pull-Request Message
|    Division    | Message |
|:--------------:| :--- |
|   "feat: ~ "   | 새로운 기능 추가 |
|   "fix: ~ "    | 버그 수정 |
| "refactor: ~ " | 기능이나 성능 개선 및 수정 |
|   "add: ~ "    | 파일이나 코드 추가 |
|  "remove: ~ "  | 파일을 삭제만 한 경우 |

## 📁 프로젝트 파일 구조
1. 마이크로서비스 아키텍처 구조
프로젝트는 Microservices Architecture와 DDD(Domain-Driven Design) 기반의 Layered Architecture를 적용하여 구성되어 있습니다.
```text
├─ com.trillionares.tryit.*  // 각 마이크로서비스
│  ├─ auth                   // 인증/인가 서비스
│  ├─ config                 // Config server
│  ├─ gateway                // API Gateway
│  ├─ image-manage          // 이미지 관리 서비스
│  ├─ notification          // 알림 서비스
│  ├─ product               // 상품, 체험단 신청 서비스
│  ├─ review                // 리뷰 서비스
│  ├─ server                // Eureka Server
│  ├─ statistics            // 통계 서비스
│  └─ trial                 // 체험단 등록 
│
├─ environment              // 환경 설정
├─ loki                     // 로깅 시스템
└─ prometheus              // 모니터링 시스템
```

2. 계층형 아키텍처 구조
각 마이크로서비스는 DDD 기반의 계층형 아키텍처로 구성되어 있습니다.
``` text
com.trillionares.tryit.[service-name]
├─ application             // 비즈니스 처리 계층
│  ├─ dto                 // 서비스 간 데이터 전달 객체
│  └─ service             // 비즈니스 로직 처리
│
├─ domain                 // 핵심 비즈니스 규칙 계층
│  ├─ model              // 도메인 모델/엔티티
│  └─ repository         // 레포지토리 인터페이스
│
├─ infrastructure        // 인프라스트럭처 계층
│  ├─ config            // 설정 클래스
│  └─ persistence       // 영속성 관리
│
└─ presentation         // 프레젠테이션 계층
    ├─ controller       // 컨트롤러
    └─ dto             // API 요청/응답 DTO
```
