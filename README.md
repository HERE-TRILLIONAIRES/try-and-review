<h1><img src="https://github.com/user-attachments/assets/e6ed9501-3146-449e-a6c1-f76236d2b4d4" alt="icon" width="65" height="65" />     Try-it</h1>

<div  align="center">
  <img width="50%" src="https://github.com/user-attachments/assets/0d2b125d-4a4f-4c75-80ef-04c7ddea53ee" alt="LOGO">
</div>
</br>
<h3 align="center"> Try-it 선착순 체험단 플랫폼</h3>

`Try-it` 서비스는 **대규모 트래픽 환경**에서도 안정적으로 작동하는 **MSA 기반**의 **선착순** 체험단 플랫폼 입니다.
새로운 제품을 출시하는 기업과 체험하고 싶은 사용자를 **연결하는 서비스**를 만들었습니다. </br>

---

- **팀 명 :**  연봉1조
- **프로젝트 명 :** Try-it
- **프로젝트 기간 :** 2025.01.02 - 2025.01.27
- **팀원 :** 손예지(리더), 신영한(부리더), 김주한, 하남규, 조승아
- **팀노션 링크 :** [💁🏻 노션 링크](https://teamsparta.notion.site/1-1-1672dc3ef51480ceb16ff07841605e7b)
- **배포 링크 :** [📮 배포 링크](http://gateway-lb-1883933206.ap-northeast-2.elb.amazonaws.com)

<br>

## 🥅 프로젝트 목표
1. **대규모 트래픽이 예상되는 인기 체험단 모집 시 신청자 대기열을 안정적으로 관리합니다.**

2. **Kafka 를활용해 체험단 상태변경을 실시간으로 감지하고 이벤트 기반으로 알림을 발행하는 시스템 구축합니다.**

3. **자주 조회되는 정보들은 캐싱을 통해 데이터베이스의 부하를 감소시킵니다.**


<br>

## 🏢 인프라 아키텍처 & 적용 기술


### 🗺️ 아키텍처 설계도
<img alt="" src="https://github.com/user-attachments/assets/7f469c41-4999-444f-94cf-96441c8f1e43"  width="800">


위 아키텍처는 **MSA 기반의 이커머스 서비스** 구조를 나타냅니다.  
각 모듈은 Redis, Kafka를 통해 통신하며, Docker로 컨테이너화되어 CI/CD를 통해 자동 배포됩니다.
<br>

### 🥽 적용 기술

**🔗Infrastructure**

<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"><img src="https://img.shields.io/badge/Amazon Web Services-232F3E?style=for-the-badge&logo=Amazon Web Services&logoColor=white"><img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white"><img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=Amazon RDS&logoColor=white"><img src="https://img.shields.io/badge/Amazon ECS-FF9900?style=for-the-badge&logo=Amazon ECS&logoColor=white">

**💻Backend**

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"><img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"><img src="https://img.shields.io/badge/Redis-FF4438?style=for-the-badge&logo=Redis&logoColor=white"><img src="https://img.shields.io/badge/Apache Kafka-231F20?style=for-the-badge&logo=Apache Kafka&logoColor=white"><img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=PostgreSQL&logoColor=white"><img src="https://img.shields.io/badge/Zipkin-FF9E0F?style=for-the-badge&logoColor=white"><img src="https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=Grafana&logoColor=white">

**🛠Tools**

<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"><img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white">
<img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=GitHub Actions&logoColor=white"><img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white">


<details>
<summary><b>📦 적용 기술 상세보기</b></summary>

### 💾 **데이터베이스 및 캐싱**
1. **Redis**
- **적용 위치**: 캐시 서버
- **사용 이유**: 인증과 권한 확인으로 호출이 빈번하게 일어나는 사용자 조회 성능 향상, 회원정보 수정 시 데이터 자동 삭제.

---

### 📬 **메시징 시스템**
1. **Apache Kafka**
- **적용 위치**: 서비스 간 비동기 통신
- **사용 이유**: 대규모 메시지 처리를 위한 안정적 메시징 큐 구현.
- **구체적 역할**: 체험단 신청 시 재고 차감, 체험단 신청의 결과를 사용자에게 슬랙 메세지로 알림.

---

### 🌐 **인프라 및 배포**
1. **Docker**
- **적용 위치**: 모든 서비스 컨테이너화
- **사용 이유**: 환경 이식성과 배포 속도 개선.

2. **Github Actions**
- **적용 위치**: CI/CD 파이프라인
- **사용 이유**: 자동화된 코드 품질 검사와 배포 구현.

3. **Prometheus & Grafana**
- **적용 위치**: 실시간 모니터링
- **사용 이유**: 주요 메트릭(트래픽, CPU 사용량 등) 수집 및 시각화로 장애 발생 시 빠른 대응 가능.

</details>

<details>
  <summary><b> 👉기술 스택 테이블 확인하기기 </summary>

| **항목** | **설명** |
| --- | --- |
| **Redis** | 메모리에서 직접 데이터를 가져오기 때문에 조회 속도가 빨라서 응답속도와 데이터베이스에 쌓이는 부하를 줄이고자 사용<br>MSA에서 서비스간 느슨한 결합과 확장성을 위해 사용<br>한정된 인원이 당첨이 보장될 수 있도록 Redis Pub/Sub 및 분산락 사용 |
| **Kafka** | 분산 메시지 큐 시스템으로 대량의 데이터 처리에도 수평확장을 통해 처리량을 유지하는데 적합<br>이벤트 중심 아키텍처로 높은 성능으로 높은 처리량을 제공<br>메시지를 로그 형식으로 저장하며, 데이터 손실을 방지하고 장애 상황에서도 데이터를 안전하게 관리할 기능을 제공<br>비동기 메시징으로 MSA 서비스간 느슨한 결합을 제공 |
| **QueryDSL** | BooleanBuilder를 사용해 조건을 쉽게 추가 또는 제거하여 동적 쿼리를 작성할 때 조건을 유연하게 사용할 수 있음<br>이외에도 페이징, 정렬등 쿼리 기능 제공 |
| **Slack API** | 슬랙 API는 실시간 알림을 위해 Webhook과 다양한 SDK를 통해 손쉬운 시스템 통합이 가능<br>REST API와 WebSocket 기반의 실시간 통신 지원의 장점 |
| **PostgreSQL** | ACID 트랜잭션을 기본적으로 지원하고, 다양한 데이터 타입(배열, JSON, HSTORE, UUID, XML 등)을 기본적으로 제공하며, 오픈소스이고 상용 라이선스 비용이 없어서 비용 효율성이 좋음 |
| **AWS S3** | AWS에서 제공하는 정적서버로 정적 파일을 안전하게 관리할 수 있음<br>타 서비스와 연동이 쉬움<br>클라우드상에서 확장과, 내구성, 고가용성, 비용, 보안 등 다양한 기능 제공 |
| **ECR + ECS** | 컨테이너화된 애플리케이션의 배포와 관리를 간소화하기 위해 사용<br>EC2와 달리 별도의 인프라 관리 없이 애플리케이션 실행 가능 |
| **Github Action** | Github과 연계해 다양한 편의 기능 제공<br>CI / CD를 자동화하며 branch의 변경사항이 있을때, 자동으로 감지해 배포로직을 수행하며 휴먼 에러를 줄일 수 있다. |
| **Zipkin** | MSA에서는 하나의 요청이 여러 서비스에 걸쳐 처리되기 때문에, 요청이 어디서 얼마나 시간이 걸렸는지 확인하기 어려운데, Zipkin은 요청의 시작부터 끝까지의 경로를 시각적으로 보여주고 서비스에서의 처리 시간(지연 시간)을 수집하고 분석할 수 있어서 사용하게 됨 |
| **Grafana** | 모니터링을 통해 서버의 성능을 모니터링하고, 개발자에게 Slack과 연동해 알림을 제공할 수 있어 서버의 처리량이 급증할때 대비할 수 있음. |
| **Open Feign** | 서비스간 동기 통신이 필요한 경우에 사용 |

</details>



<br>

## ✨ 주요 기능

### **Kafka 기반 체험단 모집 - 신청 - 알림 이벤트 처리**
- **Kafka를 통해 체험단 신청 이벤트를 비동기로 처리하여 시스템 부하 분산**

<details>
  <summary>신청자 정보와 체험단 정보를 포함한 이벤트 메시지 발행 및 구독</summary>

- 기존 단일 파티션, 단일 머신으로 요청량이 증가함에 따라 처리 오류율을 보임
    - 이에 파티션 수를 늘리고, 머신 2대로 처리 성능을 개선

  </details>

<details>
  <summary> 사용자별 Slack Webhook을 통한 실시간 알림 발송 </summary>

- Kafka Consumer를 통한 체험단 상태 변경 이벤트(신청/당첨/낙첨/리뷰) 구독 및 처리
    - 알림 발송 실패 시 자동 재시도 로직 구현 및 중복 메시지 검증을 통한 알림 무결성 보장
    - <img alt="image" src="https://github.com/user-attachments/assets/c0fad59f-3bce-4ff3-bf32-3f209fd2c40e" width="700">

</details>

---

### **Redis 캐싱 기반 사용자 정보 조회**
- TTL 주기를 짧게 갱신해서 TTI를 줄이고, 리소스 활용을 효율적으로 개선하여 사용자 조회 성능 향상
- 캐싱을 적용하여 데이터베이스에 직접 가지 않고 빠르게 조회 가능
<details>
  <summary> 캐싱 적용 후 응답시간이 458ms에서 40ms로 약 **91.26% 감소** </summary>

- 관련 내용: [👥사용자 조회 시 Redis 적용](https://www.notion.so/teamsparta/Redis-2b2b14a48c404da489b1dece8d11398c)

</details> 

---

### **Redis TTL 기반 체험단 모집 상태 관리**
- **모집 상태 자동화**: 모집 시작 및 종료 시점을 자동으로 관리하도록 Redis TTL 기능 활용해 모집 상태 관리 최적화 ****
- **시스템 부하 감소:** Polling 방식을 통해 TTL 만료 키만 확인함으로써 불필요한 DB 조회를 방지, Redis 메모리 기반 조회로 빠른 처리 속도 유지
- **실시간성 확보:** Redis TTL 만료 이벤트를 활용하여 모집 상태 변경 작업을 지연 없이 수행, 모집 시작/종료 상태 전환의 정확도와 신뢰성 향상

---

### **상품 관리 관련된 개선**
- **Kafka 기반 상품 등록 API 개선**
    - 이미지 데이터를 DB에 저장하는 데 전체 API 수행시간의 약 **12%**를 차지.
    - Front가 있다는 가정하에 이미지 업로드 수행시간을 API에서 분리한다면, 이미지 데이터를 DB에 저장하는 데 전체 API 수행시간의 약 **29%**를 차지하게 됨.

      <details>
      <summary>이미지 데이터를 DB에 저장하는 로직 분리 전, API 수행시간</summary>

      <img alt="image" src="https://github.com/user-attachments/assets/0f5af0e8-8087-4401-85a6-c6efd1a04248" width="700">

      </details>

    - 기존 이미지 데이터를 DB에 저장하는 로직은 동기적으로 처리되고 있어, 이를 Kafka 기반의 **비동기적 처리 방식으로 변경**해 분리.

      → **전체 API 수행시간 평균 약 14% 향상.**

      <details>
      <summary>이미지 데이터를 DB에 저장하는 로직 분리 후, API 수행시간</summary>

      <img alt="image" src="https://github.com/user-attachments/assets/6076a3e2-0e5b-4cd5-886a-d8e949a5fa11" width="700">

      </details>

      <img alt="image" src="https://github.com/user-attachments/assets/fedde110-5969-4eb6-810c-95b7f5468582" width="700">

- **Redis TTL을 통한 캐싱 기반 상품 정보 조회**
    - 상품 정보 조회 시 자주 조회되는 상품의 경우 **조회 성능 개선** (배포 중/배포 후 테스트)


---

### **QueryDSL 기반의 페이징 및 정렬 기능 구현**

- 모든 서비스 목록 조회기능에 동적으로 조회하고 페이징 처리 적용
- 알림 서비스 기준 알림전송상태, 사용자 ID, 기간을 동적 검색조건으로 사용하여 페이징된 알림 목록을 조회
- 상품 서비스 기준 등록된 시점, 등록한 사람을 동적 검색조건으로 사용하여 페이징된 상품 목록을 조회

<br>

## 🎯기술적 의사결정
- [Long vs UUID](https://www.notion.so/teamsparta/Long-vs-UUID-9b6d255d98e34ea88e87ee38d793f214)
- [final 키워드 사용](https://www.notion.so/teamsparta/final-62b6fb5bc10d4a2d9f6594d2e0af9568)
- [모집 상태 관리 기술적 의사결정: Redis TTL 기반 접근](https://www.notion.so/teamsparta/Redis-TTL-4212db09208f4c97aa049f6193c26313)
- [이미지 처리시 비동기 도입 이유](https://www.notion.so/teamsparta/3d5cbce944d54ffb9468953e08ad3d0a)
- [사용자 조회 시 Redis 적용](https://www.notion.so/teamsparta/Redis-2b2b14a48c404da489b1dece8d11398c)

<br>

## 🚀트러블 슈팅
- [@Valid, @Validated 예외 처리](https://www.notion.so/teamsparta/Valid-Validated-1d94ef6b0af542b1a93f30b98e84412a)
- [Checked Exception 처리 개선](https://www.notion.so/teamsparta/Checked-Exception-caa55024912f4dc68c6c67a14b72e302)
- [MultipartFile을 FeignClient로 전송하기](https://www.notion.so/teamsparta/MultipartFile-FeignClient-7df1bd0ba0ab46be80510e51c13ac130)
- [배포 & CI/CD](https://www.notion.so/teamsparta/CI-CD-176776321f3047b5bdb8a7648af77c7d)
- [Kafka 파티션 수 늘리기](https://www.notion.so/teamsparta/Kafka-4453325ff3254da58790fe0db0a238e8)

<br>

## 📺 프로젝트 설계

<details>
  <summary> 🔗카프카 구조도 </summary>
  <img alt="kafka structure" src="https://github.com/user-attachments/assets/346a7fe1-f7a3-4bc4-abb4-13b28b3a8f66" width="600">
</details>

<details>
  <summary> 🔗ERD </summary>
  <img alt="erd" src="https://github.com/user-attachments/assets/0afea585-b5d6-4c56-a7a2-7341f1075f67" width="600">
</details>

<details>
  <summary>🔗테이블 명세서</summary>
  <a href="https://docs.google.com/spreadsheets/d/1Tw1WcOjfr9_PxjtonXc4OV4tweI1FBCfwA45Lor7rYE/edit?gid=91016624#gid=91016624">➡️ 확인하기</a>
</details>

<details>
  <summary>🔗API 명세서</summary>
  <a href="https://docs.google.com/spreadsheets/d/1Tw1WcOjfr9_PxjtonXc4OV4tweI1FBCfwA45Lor7rYE/edit?gid=1840269664#gid=1840269664">➡️ 확인하기</a>
</details>

<details>
  <summary> 🔗User Flow </summary>
  <img alt="User flow" src="https://github.com/user-attachments/assets/246f0e0a-5232-4ea5-bc06-0d8307e144b1" width="600">
</details>

<details>
  <summary>🔗WBS 일정관리</summary>
  <a href="https://docs.google.com/spreadsheets/d/1Tw1WcOjfr9_PxjtonXc4OV4tweI1FBCfwA45Lor7rYE/edit?gid=2096235861#gid=2096235861">➡️ 확인하기</a>
  <img alt="wbs" src="https://github.com/user-attachments/assets/8259f1a5-7ced-43b8-bed5-8fb24526480d" width="600">
</details>

<br>

## 🤝 역할 분담 및 협업 방식
| 손예지<br> | 신영한<br> | 김주한<br> | 하남규<br> | 조승아<br> |
| :---: | :---: | :---: |:---:| :---: |
| <img alt="예지" src="https://ca.slack-edge.com/T06B9PCLY1E-U07QEFTESGP-e2b23afd15a7-512" height="100" width="100"> | <img alt="영한" src="https://ca.slack-edge.com/T06B9PCLY1E-U07S875EUBV-g9ae29003de3-512" height="100" width="100"> | <img alt="주한" src="https://ca.slack-edge.com/T06B9PCLY1E-U06QUQQ5UU8-f0cd5f6b2a58-512" height="100" width="100"> | <img alt="남규" src="https://ca.slack-edge.com/T06B9PCLY1E-U07RKNHN003-db789eae7cdc-512" height="100" width="100"> | <img alt="승아" src="https://ca.slack-edge.com/T06B9PCLY1E-U07QQLBVDDJ-1001a73273bb-512" height="100" width="100"> |
| [😼handyejee](https://github.com/handyejee) | [😼syhan7516](https://github.com/syhan7516) | [😼Hany-Kim](https://github.com/Hany-Kim) | [😼Namgyu11](https://github.com/Namgyu11) | [😼hiimsajo](https://github.com/hiimsajo) | 
|<p align="left"> 🐥리더 <br/>- 알림기능 <br/>- 아키텍쳐 설계 및 초기구성<br/>- 일정관리</p>|<p align="left">🐥부리더 <br/>- 리뷰기능<br/>- 통계기능<br/><br/></p>|<p align="left">🐥팀원 <br/>- 상품, 이미지 업로드 기능<br/>- 체험단 신청기능<br/>- AWS 배포</p>|<p align="left">🐥팀원 <br/>- 체험단 모집<br/>- 모집상태 관리<br/><br/></p>|<p align="left">🐥팀원 <br/>- 사용자 기능<br/>- JWT 기반 인증 기능<br/>- Gateway 설정<br/>  </p>|


### 📌**Ground Rule**

**문제 발생 시 즉시 공유**

- 문제가 발생하면 팀원들에게 빠르게 상황을 공유하여 협력 해결.

**목표를 위한 노력**

- WBS 일정관리를 통해 매일 목표는 매일 달성 할 수 있도록 노력하기.

**사실과 근거에 기반한 피드백**

- 서로 존중하며 감정이 아닌 사실과 근거에 기반안 피드백 주기.
- 질문이나 배워가는 점 또한 피드백이 될 수 있음.

<br>

## 🌲 Git
### Branch
- `main` : 서비스 운영 브랜치입니다.
- `develop` : 개발 환경 브랜치입니다. 개별적으로 작업했던 내용을 합치고 검토합니다.
- `feat/(서비스명)/...` : (서비스이름) 서비스별 세부 브랜치입니다.
- `fix/(서비스명)...` : (서비스이름) 서비스별 수정되는 작업들을 위한 브랜치입니다.
- `deploy/...` : CI/CD를 위한 브랜치 입니다.

### Commit & Pull-Request Message
|    Division    | Message |
|:--------------:| :--- |
|   "feat: ~ "   | 새로운 기능 추가 |
|   "fix: ~ "    | 버그 수정 |
| "refactor: ~ " | 기능이나 성능 개선 및 수정 |
|   "add: ~ "    | 파일이나 코드 추가 |
|  "remove: ~ "  | 파일을 삭제만 한 경우 |

<br>

## 📁 프로젝트 파일 구조

1. **마이크로서비스** 아키텍처 구조<br>
   프로젝트는 **Microservices Architecture와 DDD(Domain-Driven Design) 기반**의 Layered Architecture를 적용하여 구성되어 있습니다.
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

2. **계층형** 아키텍처 구조<br>
   각 마이크로서비스는 **DDD 기반의 계층형** 아키텍처로 구성되어 있습니다.
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
<br>

## 👣 성과 및 회고

### 잘된 점

- **일정 관리가 잘 이루어짐**
    - WBS로 일정관리를 한 덕분에 한눈에 팀원들의 진행 상황이 파악됨
    - 목표 달성까지 얼마나 남았는지 확인이 가능

---

### 아쉬운 점

- **제목**
    - 내용
---

### 향후 계획

- **제목**
    - 내용

