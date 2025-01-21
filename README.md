<h1>Try it</h1>
<div  align="center">
  <img width="24%" src="https://github.com/user-attachments/assets/b2992947-940a-40b5-a424-4f8005d95f71" alt="LOGO">
</div>
</br>
<h3 align="center">Spring 단기심화 2기 Ch.3 프로젝트</h3>

- **팀 명 :**  연봉1조
- **프로젝트 명 :** Try it
- **프로젝트 기간 :** 2025.01.02 - 2025.01.27
- **한줄 소개 :** 새로운 제품을 출시하는 기업과 체험하고 싶은 사용자를 연결하는 선착순 체험단 플랫폼
- **팀원 :** 손예지(리더), 신영한(부리더), 김주한, 하남규, 조승아
- **팀노션 링크 :** [💁🏻 노션 링크](https://teamsparta.notion.site/1-1-1672dc3ef51480ceb16ff07841605e7b)
- **배포 링크 :** [📮 링크](https://github.com/HERE-TRILLIONAIRES/try-and-review)

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
| :---: | :---: | :---: |---------| :---: |
| <img alt="예지" src="https://ca.slack-edge.com/T06B9PCLY1E-U07QEFTESGP-e2b23afd15a7-512" height="100" width="100"> | <img alt="영한" src="https://ca.slack-edge.com/T06B9PCLY1E-U07S875EUBV-g9ae29003de3-512" height="100" width="100"> | <img alt="주한" src="https://ca.slack-edge.com/T06B9PCLY1E-U06QUQQ5UU8-f0cd5f6b2a58-512" height="100" width="100"> |    <img alt="남규" src="https://ca.slack-edge.com/T06B9PCLY1E-U07RKNHN003-db789eae7cdc-512" height="100" width="100">     | <img alt="승아" src="https://ca.slack-edge.com/T06B9PCLY1E-U07QQLBVDDJ-1001a73273bb-512" height="100" width="100"> |
| [handyejee](https://github.com/handyejee) | [syhan7516](https://github.com/syhan7516) | [Hany-Kim](https://github.com/Hany-Kim) |     [Namgyu11](https://github.com/Namgyu11)    | [hiimsajo](https://github.com/hiimsajo) | 
|<p align="left">- 알림기능 <br/>- 아키텍쳐 설계 및 초기구성<br/>- 일정관리</p>|<p align="left">- 리뷰기능<br/>- 통계기능</p>|<p align="left">- 상품, 이미지 업로드 기능<br/>- 체험단 신청기능<br/>- AWS 배포</p>|<p align="left">- 체험단 모집<br/>- 추가예정<br/>- 추가예정</p>|<p align="left">- 사용자 기능<br/>- JWT 기반 인증 기능<br/>- Gateway 설정</p>|

## 📺 프로젝트 설계
### API 명세서</br>
<img alt="" src="https://github.com/user-attachments/assets/b3b42806-0558-4764-98ab-35a2a1400e46"  width="800">

### 테이블 명세서</br>
<img alt="" src="https://github.com/user-attachments/assets/b3b42806-0558-4764-98ab-35a2a1400e46"  width="800">

### ERD</br>
<img alt="" src="https://github.com/user-attachments/assets/0afea585-b5d6-4c56-a7a2-7341f1075f67"  width="800">

### 인프라 설계도</br>
<img alt="" src="https://github.com/user-attachments/assets/7f469c41-4999-444f-94cf-96441c8f1e43"  width="800">

### 카프카 구조도</br>
<img alt="" src="https://github.com/user-attachments/assets/346a7fe1-f7a3-4bc4-abb4-13b28b3a8f66"  width="800">

### WBS 일정관리</br>
<img alt="" src="https://github.com/user-attachments/assets/b3b42806-0558-4764-98ab-35a2a1400e46"  width="800">


## ➡️ User flow
<img width="2800" alt="https://github.com/user-attachments/assets/cd22c0d1-8f4a-44e7-aea0-35a53ebd0f64">

</br>

## Tech Stack
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
- Microservices Architecture
- Layered Architecture + DDD
```text
C:.
│  .gitignore
│  .gitmodules
│  docker-compose.yml
│  pull_request_template.md
│  README.md
│  structure.txt
│  
├─.github
│  ├─ISSUE_TEMPLATE
│  │      feature_request.md
│  │      
│  └─workflows
│          auth-task-revision1.json
│          aws.yml
│          config-task-revision1.json
│          gateway-task-revision1.json
│          image-manage-task-revision1.json
│          notification-task-revision1.json
│          product-task-revision1.json
│          review-task-revision1.json
│          server-task-revision1.json
│          statistics-task-revision1.json
│          trial-task-revision1.json
│          
├─.idea
│      compiler.xml
│      workspace.xml
│      
├─com.trillionares.tryit.auth
│  │  .gitattributes
│  │  .gitignore
│  │  build.gradle
│  │  Dockerfile
│  │  gradlew
│  │  gradlew.bat
│  │  settings.gradle
│  │  
│  ├─.gradle
│  │  │  file-system.probe
│  │  │  
│  │  ├─8.11.1
│  │  │  │  gc.properties
│  │  │  │  
│  │  │  ├─checksums
│  │  │  │      checksums.lock
│  │  │  │      
│  │  │  ├─executionHistory
│  │  │  │      executionHistory.bin
│  │  │  │      executionHistory.lock
│  │  │  │      
│  │  │  ├─expanded
│  │  │  ├─fileChanges
│  │  │  │      last-build.bin
│  │  │  │      
│  │  │  ├─fileHashes
│  │  │  │      fileHashes.bin
│  │  │  │      fileHashes.lock
│  │  │  │      resourceHashesCache.bin
│  │  │  │      
│  │  │  └─vcsMetadata
│  │  ├─buildOutputCleanup
│  │  │      buildOutputCleanup.lock
│  │  │      cache.properties
│  │  │      outputFiles.bin
│  │  │      
│  │  └─vcs-1
│  │          gc.properties
│  │          
│  ├─build
│  │  ├─classes
│  │  │  └─java
│  │  │      └─main
│  │  │          └─com
│  │  │              └─trillionares
│  │  │                  └─tryit
│  │  │                      └─auth
│  │  │                          │  AuthApplication.class
│  │  │                          │  
│  │  │                          ├─application
│  │  │                          │  ├─dto
│  │  │                          │  │      InfoByUsernameResponseDto.class
│  │  │                          │  │      
│  │  │                          │  └─service
│  │  │                          │          AuthService.class
│  │  │                          │          UserService.class
│  │  │                          │          
│  │  │                          ├─domain
│  │  │                          │  ├─common
│  │  │                          │  │  └─base
│  │  │                          │  │          BaseEntity.class
│  │  │                          │  │          QBaseEntity.class
│  │  │                          │  │          
│  │  │                          │  ├─model
│  │  │                          │  │      QUser.class
│  │  │                          │  │      Role.class
│  │  │                          │  │      User$UserBuilder.class
│  │  │                          │  │      User.class
│  │  │                          │  │      
│  │  │                          │  └─repository
│  │  │                          │          UserRepository.class
│  │  │                          │          UserRepositoryCustom.class
│  │  │                          │          UserRepositoryCustomImpl.class
│  │  │                          │          
│  │  │                          ├─infrastructure
│  │  │                          │  └─config
│  │  │                          │      │  AuditorAwareImpl.class
│  │  │                          │      │  CustomAuthenticationProvider.class
│  │  │                          │      │  CustomUserDetails.class
│  │  │                          │      │  CustomUserDetailsService.class
│  │  │                          │      │  JpaConfig.class
│  │  │                          │      │  QueryDslConfig.class
│  │  │                          │      │  SecurityConfig.class
│  │  │                          │      │  
│  │  │                          │      └─jwt
│  │  │                          │          │  JwtUtil.class
│  │  │                          │          │  SecretKeyGenerator.class
│  │  │                          │          │  
│  │  │                          │          └─filter
│  │  │                          │                  JwtAuthorizationFilter.class
│  │  │                          │                  
│  │  │                          ├─libs
│  │  │                          │  └─exception
│  │  │                          │          CustomAuthenticationException.class
│  │  │                          │          ErrorCode.class
│  │  │                          │          ErrorResponse$ErrorResponseBuilder.class
│  │  │                          │          ErrorResponse$FieldError$FieldErrorBuilder.class
│  │  │                          │          ErrorResponse$FieldError.class
│  │  │                          │          ErrorResponse.class
│  │  │                          │          ExceptionConverter.class
│  │  │                          │          GlobalException.class
│  │  │                          │          GlobalExceptionHandler.class
│  │  │                          │          
│  │  │                          └─presentation
│  │  │                              ├─controller
│  │  │                              │      AuthController.class
│  │  │                              │      UserController.class
│  │  │                              │      
│  │  │                              └─dto
│  │  │                                  │  BaseResponse$BaseResponseBuilder.class
│  │  │                                  │  BaseResponse.class
│  │  │                                  │  
│  │  │                                  ├─requestDto
│  │  │                                  │      PasswordUpdateReqDto.class
│  │  │                                  │      SignInRequestDto.class
│  │  │                                  │      SignUpRequestDto.class
│  │  │                                  │      UserInfoUpdateReqDto.class
│  │  │                                  │      
│  │  │                                  └─responseDto
│  │  │                                          SignInResponseDto.class
│  │  │                                          UserResponseDto.class
│  │  │                                          
│  │  ├─generated
│  │  │  └─sources
│  │  │      ├─annotationProcessor
│  │  │      │  └─java
│  │  │      │      └─main
│  │  │      │          └─com
│  │  │      │              └─trillionares
│  │  │      │                  └─tryit
│  │  │      │                      └─auth
│  │  │      │                          └─domain
│  │  │      │                              ├─common
│  │  │      │                              │  └─base
│  │  │      │                              │          QBaseEntity.java
│  │  │      │                              │          
│  │  │      │                              └─model
│  │  │      │                                      QUser.java
│  │  │      │                                      
│  │  │      └─headers
│  │  │          └─java
│  │  │              └─main
│  │  ├─reports
│  │  │  └─problems
│  │  │          problems-report.html
│  │  │          
│  │  ├─resources
│  │  │  └─main
│  │  │          application.yml
│  │  │          
│  │  └─tmp
│  │      └─compileJava
│  │              previous-compilation-data.bin
│  │              
│  ├─gradle
│  │  └─wrapper
│  │          gradle-wrapper.jar
│  │          gradle-wrapper.properties
│  │          
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─trillionares
│      │  │          └─tryit
│      │  │              └─auth
│      │  │                  │  AuthApplication.java
│      │  │                  │  
│      │  │                  ├─application
│      │  │                  │  ├─dto
│      │  │                  │  │      InfoByUsernameResponseDto.java
│      │  │                  │  │      
│      │  │                  │  └─service
│      │  │                  │          AuthService.java
│      │  │                  │          UserService.java
│      │  │                  │          
│      │  │                  ├─domain
│      │  │                  │  ├─common
│      │  │                  │  │  └─base
│      │  │                  │  │          BaseEntity.java
│      │  │                  │  │          
│      │  │                  │  ├─model
│      │  │                  │  │      Role.java
│      │  │                  │  │      User.java
│      │  │                  │  │      
│      │  │                  │  └─repository
│      │  │                  │          UserRepository.java
│      │  │                  │          
│      │  │                  ├─infrastructure
│      │  │                  │  └─config
│      │  │                  │      │  AuditorAwareImpl.java
│      │  │                  │      │  CustomAuthenticationProvider.java
│      │  │                  │      │  CustomUserDetails.java
│      │  │                  │      │  CustomUserDetailsService.java
│      │  │                  │      │  JpaConfig.java
│      │  │                  │      │  SecurityConfig.java
│      │  │                  │      │  
│      │  │                  │      └─jwt
│      │  │                  │          │  JwtUtil.java
│      │  │                  │          │  SecretKeyGenerator.java
│      │  │                  │          │  
│      │  │                  │          └─filter
│      │  │                  │                  JwtAuthorizationFilter.java
│      │  │                  │                  
│      │  │                  ├─libs
│      │  │                  │  └─exception
│      │  │                  │          CustomAuthenticationException.java
│      │  │                  │          ErrorCode.java
│      │  │                  │          ErrorResponse.java
│      │  │                  │          ExceptionConverter.java
│      │  │                  │          GlobalException.java
│      │  │                  │          GlobalExceptionHandler.java
│      │  │                  │          
│      │  │                  └─presentation
│      │  │                      ├─controller
│      │  │                      │      AuthController.java
│      │  │                      │      UserController.java
│      │  │                      │      
│      │  │                      └─dto
│      │  │                          │  BaseResponse.java
│      │  │                          │  
│      │  │                          ├─requestDto
│      │  │                          │      PasswordUpdateReqDto.java
│      │  │                          │      SignInRequestDto.java
│      │  │                          │      SignUpRequestDto.java
│      │  │                          │      UserInfoUpdateReqDto.java
│      │  │                          │      
│      │  │                          └─responseDto
│      │  │                                  SignInResponseDto.java
│      │  │                                  UserResponseDto.java
│      │  │                                  
│      │  └─resources
│      │          application.yml
│      │          
│      └─test
│          └─java
│              └─com
│                  └─trillionares
│                      └─tryit
│                          └─auth
│                                  AuthApplicationTests.java
│                                  
├─com.trillionares.tryit.config
│  │  .gitattributes
│  │  .gitignore
│  │  build.gradle
│  │  Dockerfile
│  │  gradlew
│  │  gradlew.bat
│  │  settings.gradle
│  │  
│  ├─.gradle
│  │  │  file-system.probe
│  │  │  
│  │  ├─8.11.1
│  │  │  │  gc.properties
│  │  │  │  
│  │  │  ├─checksums
│  │  │  │      checksums.lock
│  │  │  │      
│  │  │  ├─executionHistory
│  │  │  │      executionHistory.bin
│  │  │  │      executionHistory.lock
│  │  │  │      
│  │  │  ├─expanded
│  │  │  ├─fileChanges
│  │  │  │      last-build.bin
│  │  │  │      
│  │  │  ├─fileHashes
│  │  │  │      fileHashes.bin
│  │  │  │      fileHashes.lock
│  │  │  │      resourceHashesCache.bin
│  │  │  │      
│  │  │  └─vcsMetadata
│  │  ├─buildOutputCleanup
│  │  │      buildOutputCleanup.lock
│  │  │      cache.properties
│  │  │      outputFiles.bin
│  │  │      
│  │  └─vcs-1
│  │          gc.properties
│  │          
│  ├─build
│  │  ├─classes
│  │  │  └─java
│  │  │      └─main
│  │  │          └─com
│  │  │              └─trillionares
│  │  │                  └─tryit
│  │  │                      └─config
│  │  │                              ConfigApplication.class
│  │  │                              
│  │  ├─generated
│  │  │  └─sources
│  │  │      ├─annotationProcessor
│  │  │      │  └─java
│  │  │      │      └─main
│  │  │      └─headers
│  │  │          └─java
│  │  │              └─main
│  │  ├─resources
│  │  │  └─main
│  │  │      │  application.yml
│  │  │      │  
│  │  │      └─config-repo
│  │  │              auth-service.yml
│  │  │              image-manage-service.yml
│  │  │              notification-service.yml
│  │  │              product-service.yml
│  │  │              review-service.yml
│  │  │              statistics-service.yml
│  │  │              trial-service.yml
│  │  │              
│  │  └─tmp
│  │      └─compileJava
│  │              previous-compilation-data.bin
│  │              
│  ├─gradle
│  │  └─wrapper
│  │          gradle-wrapper.jar
│  │          gradle-wrapper.properties
│  │          
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─trillionares
│      │  │          └─tryit
│      │  │              └─config
│      │  │                      ConfigApplication.java
│      │  │                      
│      │  └─resources
│      │      │  application.yml
│      │      │  
│      │      └─config-repo
│      │              auth-service.yml
│      │              image-manage-service.yml
│      │              notification-service.yml
│      │              product-service.yml
│      │              review-service.yml
│      │              statistics-service.yml
│      │              trial-service.yml
│      │              
│      └─test
│          └─java
│              └─com
│                  └─trillionares
│                      └─tryit
│                          └─config
│                                  ConfigApplicationTests.java
│                                  
├─com.trillionares.tryit.gateway
│  │  .gitattributes
│  │  .gitignore
│  │  build.gradle
│  │  Dockerfile
│  │  gradlew
│  │  gradlew.bat
│  │  settings.gradle
│  │  
│  ├─.gradle
│  │  │  file-system.probe
│  │  │  
│  │  ├─8.11.1
│  │  │  │  gc.properties
│  │  │  │  
│  │  │  ├─checksums
│  │  │  │      checksums.lock
│  │  │  │      
│  │  │  ├─executionHistory
│  │  │  │      executionHistory.bin
│  │  │  │      executionHistory.lock
│  │  │  │      
│  │  │  ├─expanded
│  │  │  ├─fileChanges
│  │  │  │      last-build.bin
│  │  │  │      
│  │  │  ├─fileHashes
│  │  │  │      fileHashes.bin
│  │  │  │      fileHashes.lock
│  │  │  │      resourceHashesCache.bin
│  │  │  │      
│  │  │  └─vcsMetadata
│  │  ├─buildOutputCleanup
│  │  │      buildOutputCleanup.lock
│  │  │      cache.properties
│  │  │      outputFiles.bin
│  │  │      
│  │  └─vcs-1
│  │          gc.properties
│  │          
│  ├─build
│  │  ├─classes
│  │  │  └─java
│  │  │      └─main
│  │  │          └─com
│  │  │              └─trillionares
│  │  │                  └─tryit
│  │  │                      └─gateway
│  │  │                          │  GatewayApplication.class
│  │  │                          │  
│  │  │                          ├─config
│  │  │                          │      GatewayConfig.class
│  │  │                          │      
│  │  │                          ├─filter
│  │  │                          │      JwtAuthFilter.class
│  │  │                          │      
│  │  │                          └─jwt
│  │  │                                  JwtUtil.class
│  │  │                                  
│  │  ├─generated
│  │  │  └─sources
│  │  │      ├─annotationProcessor
│  │  │      │  └─java
│  │  │      │      └─main
│  │  │      └─headers
│  │  │          └─java
│  │  │              └─main
│  │  ├─resources
│  │  │  └─main
│  │  │          application.yml
│  │  │          
│  │  └─tmp
│  │      └─compileJava
│  │              previous-compilation-data.bin
│  │              
│  ├─gradle
│  │  └─wrapper
│  │          gradle-wrapper.jar
│  │          gradle-wrapper.properties
│  │          
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─trillionares
│      │  │          └─tryit
│      │  │              └─gateway
│      │  │                  │  GatewayApplication.java
│      │  │                  │  
│      │  │                  ├─config
│      │  │                  │      GatewayConfig.java
│      │  │                  │      
│      │  │                  ├─filter
│      │  │                  │      JwtAuthFilter.java
│      │  │                  │      
│      │  │                  └─jwt
│      │  │                          JwtUtil.java
│      │  │                          
│      │  └─resources
│      │          application.yml
│      │          
│      └─test
│          └─java
│              └─com
│                  └─trillionares
│                      └─tryit
│                          └─gateway
│                                  GatewayApplicationTests.java
│                                  
├─com.trillionares.tryit.image-manage
│  │  .gitattributes
│  │  .gitignore
│  │  build.gradle
│  │  Dockerfile
│  │  gradlew
│  │  gradlew.bat
│  │  settings.gradle
│  │  
│  ├─.gradle
│  │  ├─8.11.1
│  │  │  │  gc.properties
│  │  │  │  
│  │  │  ├─checksums
│  │  │  │      checksums.lock
│  │  │  │      
│  │  │  ├─executionHistory
│  │  │  │      executionHistory.lock
│  │  │  │      
│  │  │  ├─expanded
│  │  │  ├─fileChanges
│  │  │  │      last-build.bin
│  │  │  │      
│  │  │  ├─fileHashes
│  │  │  │      fileHashes.bin
│  │  │  │      fileHashes.lock
│  │  │  │      
│  │  │  └─vcsMetadata
│  │  ├─buildOutputCleanup
│  │  │      buildOutputCleanup.lock
│  │  │      cache.properties
│  │  │      
│  │  └─vcs-1
│  │          gc.properties
│  │          
│  ├─gradle
│  │  └─wrapper
│  │          gradle-wrapper.jar
│  │          gradle-wrapper.properties
│  │          
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─trillionares
│      │  │          └─tryit
│      │  │              └─image_manage
│      │  │                  │  ImageManageApplication.java
│      │  │                  │  
│      │  │                  ├─domain
│      │  │                  │  ├─common
│      │  │                  │  │  ├─base
│      │  │                  │  │  │      BaseEntity.java
│      │  │                  │  │  │      
│      │  │                  │  │  ├─json
│      │  │                  │  │  │      JsonUtils.java
│      │  │                  │  │  │      
│      │  │                  │  │  └─message
│      │  │                  │  │          ImageMessage.java
│      │  │                  │  │          S3Message.java
│      │  │                  │  │          
│      │  │                  │  ├─model
│      │  │                  │  │  └─productImage
│      │  │                  │  │          ProductImage.java
│      │  │                  │  │          
│      │  │                  │  ├─repository
│      │  │                  │  │      ProductImageRepository.java
│      │  │                  │  │      
│      │  │                  │  └─service
│      │  │                  │          ImageEndpoint.java
│      │  │                  │          ImageService.java
│      │  │                  │          S3ImageService.java
│      │  │                  │          
│      │  │                  ├─infrastructure
│      │  │                  │  ├─config
│      │  │                  │  │      JpaConfig.java
│      │  │                  │  │      S3Config.java
│      │  │                  │  │      
│      │  │                  │  └─messaging
│      │  │                  │      └─kafka
│      │  │                  │              ConsumerApplicationKafkaConfig.java
│      │  │                  │              ProducerApplicationKafkaConfig.java
│      │  │                  │              
│      │  │                  └─presentation
│      │  │                      ├─controller
│      │  │                      │      ImageController.java
│      │  │                      │      S3ImageController.java
│      │  │                      │      
│      │  │                      ├─dto
│      │  │                      │      BaseResponseDto.java
│      │  │                      │      ImageIdResponseDto.java
│      │  │                      │      ImageInfoResquestDto.java
│      │  │                      │      ImageUrlDto.java
│      │  │                      │      ProductIdAndProductImageIdDto.java
│      │  │                      │      
│      │  │                      └─exception
│      │  │                              ImageNotFoundException.java
│      │  │                              ImageUrlNotFoundException.java
│      │  │                              RequestException.java
│      │  │                              S3Exception.java
│      │  │                              
│      │  └─resources
│      │          application.yml
│      │          
│      └─test
│          └─java
│              └─com
│                  └─trillionares
│                      └─tryit
│                          └─image_manage
│                                  ImageManageApplicationTests.java
│                                  
├─com.trillionares.tryit.notification
│  │  .gitattributes
│  │  .gitignore
│  │  build.gradle
│  │  Dockerfile
│  │  gradlew
│  │  gradlew.bat
│  │  settings.gradle
│  │  
│  ├─.gradle
│  │  ├─8.11.1
│  │  │  │  gc.properties
│  │  │  │  
│  │  │  ├─checksums
│  │  │  │      checksums.lock
│  │  │  │      
│  │  │  ├─executionHistory
│  │  │  │      executionHistory.lock
│  │  │  │      
│  │  │  ├─expanded
│  │  │  ├─fileChanges
│  │  │  │      last-build.bin
│  │  │  │      
│  │  │  ├─fileHashes
│  │  │  │      fileHashes.lock
│  │  │  │      
│  │  │  └─vcsMetadata
│  │  ├─buildOutputCleanup
│  │  │      buildOutputCleanup.lock
│  │  │      cache.properties
│  │  │      
│  │  └─vcs-1
│  │          gc.properties
│  │          
│  ├─build
│  │  └─reports
│  │      └─problems
│  │              problems-report.html
│  │              
│  ├─gradle
│  │  └─wrapper
│  │          gradle-wrapper.jar
│  │          gradle-wrapper.properties
│  │          
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─trillionares
│      │  │          └─tryit
│      │  │              └─notification
│      │  │                  │  NotificationApplication.java
│      │  │                  │  
│      │  │                  ├─application
│      │  │                  │  ├─dto
│      │  │                  │  │  ├─request
│      │  │                  │  │  │      GetNotificationRequest.java
│      │  │                  │  │  │      
│      │  │                  │  │  ├─response
│      │  │                  │  │  │      NotificationResponse.java
│      │  │                  │  │  │      
│      │  │                  │  │  └─slack
│      │  │                  │  │          SlackMessage.java
│      │  │                  │  │          SlackNotificationSender.java
│      │  │                  │  │          
│      │  │                  │  └─service
│      │  │                  │          NotificationRoleValidation.java
│      │  │                  │          NotificationService.java
│      │  │                  │          
│      │  │                  ├─domain
│      │  │                  │  ├─common
│      │  │                  │  │  └─base
│      │  │                  │  │          BaseEntity.java
│      │  │                  │  │          
│      │  │                  │  ├─model
│      │  │                  │  │      Notification.java
│      │  │                  │  │      NotificationStatus.java
│      │  │                  │  │      
│      │  │                  │  └─repository
│      │  │                  │          NotificationRepositoryCustom.java
│      │  │                  │          NotificationRepositoryImpl.java
│      │  │                  │          
│      │  │                  ├─infrastructure
│      │  │                  │  ├─config
│      │  │                  │  │      JpaConfig.java
│      │  │                  │  │      QueryDslConfig.java
│      │  │                  │  │      RestTemplateConfig.java
│      │  │                  │  │      
│      │  │                  │  ├─messaging
│      │  │                  │  │  ├─event
│      │  │                  │  │  │      EventSerializer.java
│      │  │                  │  │  │      KafkaMessage.java
│      │  │                  │  │  │      SubmissionEventConsumer.java
│      │  │                  │  │  │      SubmissionKafkaEvent.java
│      │  │                  │  │  │      
│      │  │                  │  │  └─kafka
│      │  │                  │  │          NotificationTopic.java
│      │  │                  │  │          
│      │  │                  │  └─persistence
│      │  │                  │          NotificationRepository.java
│      │  │                  │          
│      │  │                  ├─libs
│      │  │                  │  ├─client
│      │  │                  │  │  ├─auth
│      │  │                  │  │  │      AuthClient.java
│      │  │                  │  │  │      FeignUserIdResponseDto.java
│      │  │                  │  │  │      FeignUsernameResponseDto.java
│      │  │                  │  │  │      
│      │  │                  │  │  └─config
│      │  │                  │  │          InternalFeignClientConfig.java
│      │  │                  │  │          RequestHeaderProvider.java
│      │  │                  │  │          WebFeignClientConfig.java
│      │  │                  │  │          
│      │  │                  │  └─exception
│      │  │                  │          ErrorCode.java
│      │  │                  │          ErrorResponse.java
│      │  │                  │          ExceptionConverter.java
│      │  │                  │          GlobalException.java
│      │  │                  │          GlobalExceptionHandler.java
│      │  │                  │          
│      │  │                  └─presentation
│      │  │                      ├─controller
│      │  │                      │      NotificationController.java
│      │  │                      │      
│      │  │                      └─dto
│      │  │                              BaseResponse.java
│      │  │                              
│      │  └─resources
│      │          application.yml
│      │          
│      └─test
│          ├─http
│          │      notification.http
│          │      
│          ├─java
│          │  └─com
│          │      └─trillionares
│          │          └─tryit
│          │              └─notification
│          │                      KafkaTestConfig.java
│          │                      NotificationApplicationTests.java
│          │                      NotificationServiceTest.java
│          │                      NotificationTest.java
│          │                      
│          └─resources
│                  application.yml
│                  
├─com.trillionares.tryit.product
│  │  .gitattributes
│  │  .gitignore
│  │  build.gradle
│  │  Dockerfile
│  │  gradlew
│  │  gradlew.bat
│  │  settings.gradle
│  │  
│  ├─.gradle
│  │  ├─8.11.1
│  │  │  │  gc.properties
│  │  │  │  
│  │  │  ├─checksums
│  │  │  │      checksums.lock
│  │  │  │      
│  │  │  ├─executionHistory
│  │  │  │      executionHistory.lock
│  │  │  │      
│  │  │  ├─expanded
│  │  │  ├─fileChanges
│  │  │  │      last-build.bin
│  │  │  │      
│  │  │  ├─fileHashes
│  │  │  │      fileHashes.lock
│  │  │  │      
│  │  │  └─vcsMetadata
│  │  ├─buildOutputCleanup
│  │  │      buildOutputCleanup.lock
│  │  │      cache.properties
│  │  │      
│  │  └─vcs-1
│  │          gc.properties
│  │          
│  ├─gradle
│  │  └─wrapper
│  │          gradle-wrapper.jar
│  │          gradle-wrapper.properties
│  │          
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─trillionares
│      │  │          └─tryit
│      │  │              └─product
│      │  │                  │  ProductApplication.java
│      │  │                  │  
│      │  │                  ├─application
│      │  │                  │  └─service
│      │  │                  │          CategoryService.java
│      │  │                  │          ProductEndpoint.java
│      │  │                  │          ProductService.java
│      │  │                  │          RecruitmentEndpoint.java
│      │  │                  │          RecruitmentService.java
│      │  │                  │          
│      │  │                  ├─domain
│      │  │                  │  ├─client
│      │  │                  │  │      AuthClient.java
│      │  │                  │  │      ImageClient.java
│      │  │                  │  │      
│      │  │                  │  ├─common
│      │  │                  │  │  ├─base
│      │  │                  │  │  │      BaseEntity.java
│      │  │                  │  │  │      
│      │  │                  │  │  ├─json
│      │  │                  │  │  │      JsonUtils.java
│      │  │                  │  │  │      
│      │  │                  │  │  └─message
│      │  │                  │  │          CategoryMessage.java
│      │  │                  │  │          ProductMessage.java
│      │  │                  │  │          
│      │  │                  │  ├─model
│      │  │                  │  │  ├─category
│      │  │                  │  │  │      Category.java
│      │  │                  │  │  │      ProductCategory.java
│      │  │                  │  │  │      
│      │  │                  │  │  ├─product
│      │  │                  │  │  │      Product.java
│      │  │                  │  │  │      
│      │  │                  │  │  └─recruitment
│      │  │                  │  │      │  Recruitment.java
│      │  │                  │  │      │  
│      │  │                  │  │      └─type
│      │  │                  │  │              RecruitmentStatus.java
│      │  │                  │  │              
│      │  │                  │  └─repository
│      │  │                  │          CategoryRepository.java
│      │  │                  │          CustomRecruitmentRepository.java
│      │  │                  │          CustomRecruitmentRepositoryImpl.java
│      │  │                  │          ProductCategoryRepository.java
│      │  │                  │          ProductRepository.java
│      │  │                  │          ProductRepositoryCustom.java
│      │  │                  │          RecruitmentRepository.java
│      │  │                  │          
│      │  │                  ├─infrastructure
│      │  │                  │  ├─config
│      │  │                  │  │      FeignConfig.java
│      │  │                  │  │      JpaConfig.java
│      │  │                  │  │      QueryDslConfig.java
│      │  │                  │  │      
│      │  │                  │  └─messaging
│      │  │                  │      └─kafka
│      │  │                  │              ConsumerApplicationKafkaConfig.java
│      │  │                  │              ProducerApplicationKafkaConfig.java
│      │  │                  │              
│      │  │                  └─presentation
│      │  │                      ├─controller
│      │  │                      │      CategoryController.java
│      │  │                      │      ProductController.java
│      │  │                      │      RecruitmentController.java
│      │  │                      │      
│      │  │                      ├─dto
│      │  │                      │  │  InfoByUsernameResponseDto.java
│      │  │                      │  │  ProductIdAndProductImageIdDto.java
│      │  │                      │  │  RecruitmentExistAndStatusDto.java
│      │  │                      │  │  UserResponseDto.java
│      │  │                      │  │  
│      │  │                      │  ├─common
│      │  │                      │  │  ├─base
│      │  │                      │  │  │      BaseResponseDto.java
│      │  │                      │  │  │      
│      │  │                      │  │  └─kafka
│      │  │                      │  │          KafkaMessage.java
│      │  │                      │  │          RecruitmentSubmissionResponseDto.java
│      │  │                      │  │          SubmissionToRecruitmentRequestDto.java
│      │  │                      │  │          
│      │  │                      │  ├─productImage
│      │  │                      │  │      ImageIdResponseDto.java
│      │  │                      │  │      ImageInfoResquestDto.java
│      │  │                      │  │      ImageUrlDto.java
│      │  │                      │  │      
│      │  │                      │  ├─request
│      │  │                      │  │      CategoryInfoRequestDto.java
│      │  │                      │  │      CreateRecruitmentRequest.java
│      │  │                      │  │      ProductInfoRequestDto.java
│      │  │                      │  │      UpdateRecruitmentRequest.java
│      │  │                      │  │      UpdateRecruitmentStatusRequest.java
│      │  │                      │  │      
│      │  │                      │  └─response
│      │  │                      │          CategoryIdResponseDto.java
│      │  │                      │          GetRecruitmentResponse.java
│      │  │                      │          ProductIdResponseDto.java
│      │  │                      │          ProductInfoResponseDto.java
│      │  │                      │          RecruitmentIdResponse.java
│      │  │                      │          UpdateRecruitmentStatusResponse.java
│      │  │                      │          
│      │  │                      └─exception
│      │  │                              CategoryNotFoundException.java
│      │  │                              CreateProductMainImageIdException.java
│      │  │                              CreateProductMainImageUrlException.java
│      │  │                              ProductMainImageNotFoundException.java
│      │  │                              ProductNotFoundException.java
│      │  │                              
│      │  └─resources
│      │          application.yml
│      │          
│      └─test
│          ├─java
│          │  └─com
│          │      └─trillionares
│          │          └─tryit
│          │              └─product
│          │                  │  ProductApplicationTests.java
│          │                  │  
│          │                  └─application
│          │                      └─service
│          │                              ProductServiceTest.java
│          │                              
│          └─resources
│                  application-test.yml
│                  
├─com.trillionares.tryit.review
│  │  .gitattributes
│  │  .gitignore
│  │  build.gradle
│  │  Dockerfile
│  │  gradlew
│  │  gradlew.bat
│  │  settings.gradle
│  │  
│  ├─.gradle
│  │  ├─8.11.1
│  │  │  │  gc.properties
│  │  │  │  
│  │  │  ├─checksums
│  │  │  │      checksums.lock
│  │  │  │      
│  │  │  ├─executionHistory
│  │  │  │      executionHistory.lock
│  │  │  │      
│  │  │  ├─expanded
│  │  │  ├─fileChanges
│  │  │  │      last-build.bin
│  │  │  │      
│  │  │  ├─fileHashes
│  │  │  │      fileHashes.lock
│  │  │  │      
│  │  │  └─vcsMetadata
│  │  ├─buildOutputCleanup
│  │  │      buildOutputCleanup.lock
│  │  │      cache.properties
│  │  │      
│  │  └─vcs-1
│  │          gc.properties
│  │          
│  ├─gradle
│  │  └─wrapper
│  │          gradle-wrapper.jar
│  │          gradle-wrapper.properties
│  │          
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─trillionares
│      │  │          └─tryit
│      │  │              └─review
│      │  │                  │  ReviewApplication.java
│      │  │                  │  
│      │  │                  ├─application
│      │  │                  │  ├─dto
│      │  │                  │  │  ├─request
│      │  │                  │  │  │      ReviewCreateRequestDto.java
│      │  │                  │  │  │      ReviewUpdateRequestDto.java
│      │  │                  │  │  │      
│      │  │                  │  │  └─response
│      │  │                  │  │          ReviewCreateResponseDto.java
│      │  │                  │  │          ReviewDeleteResponseDto.java
│      │  │                  │  │          ReviewGetResponseDto.java
│      │  │                  │  │          ReviewGetUserByUsernameResponseDto.java
│      │  │                  │  │          ReviewIsSelectedResponseDto.java
│      │  │                  │  │          ReviewSubmitResponseDto.java
│      │  │                  │  │          ReviewUpdateResponseDto.java
│      │  │                  │  │          
│      │  │                  │  └─service
│      │  │                  │          ReviewService.java
│      │  │                  │          
│      │  │                  ├─domain
│      │  │                  │  ├─client
│      │  │                  │  │      AuthClient.java
│      │  │                  │  │      TrialClient.java
│      │  │                  │  │      
│      │  │                  │  ├─common
│      │  │                  │  │  └─base
│      │  │                  │  │          BaseEntity.java
│      │  │                  │  │          
│      │  │                  │  ├─model
│      │  │                  │  │      Review.java
│      │  │                  │  │      
│      │  │                  │  ├─repository
│      │  │                  │  │      ReviewRepository.java
│      │  │                  │  │      
│      │  │                  │  └─service
│      │  │                  │          ReviewValidation.java
│      │  │                  │          
│      │  │                  ├─infrastructure
│      │  │                  │  └─persistence
│      │  │                  │          ReviewRepositoryImpl.java
│      │  │                  │          
│      │  │                  ├─libs
│      │  │                  │  └─exception
│      │  │                  │          ErrorCode.java
│      │  │                  │          ErrorResponse.java
│      │  │                  │          ExceptionConverter.java
│      │  │                  │          GlobalException.java
│      │  │                  │          GlobalExceptionHandler.java
│      │  │                  │          
│      │  │                  └─presentation
│      │  │                      ├─controller
│      │  │                      │      ReviewController.java
│      │  │                      │      
│      │  │                      └─dto
│      │  │                              BaseResponse.java
│      │  │                              ReviewCreateRequest.java
│      │  │                              ReviewUpdateRequest.java
│      │  │                              
│      │  └─resources
│      │          application.yml
│      │          
│      └─test
│          └─java
│              └─com
│                  └─trillionares
│                      └─tryit
│                          └─review
│                                  ReviewApplicationTests.java
│                                  
├─com.trillionares.tryit.server
│  │  .gitattributes
│  │  .gitignore
│  │  build.gradle
│  │  Dockerfile
│  │  gradlew
│  │  gradlew.bat
│  │  settings.gradle
│  │  
│  ├─.gradle
│  │  │  file-system.probe
│  │  │  
│  │  ├─8.11.1
│  │  │  │  gc.properties
│  │  │  │  
│  │  │  ├─checksums
│  │  │  │      checksums.lock
│  │  │  │      
│  │  │  ├─executionHistory
│  │  │  │      executionHistory.bin
│  │  │  │      executionHistory.lock
│  │  │  │      
│  │  │  ├─expanded
│  │  │  ├─fileChanges
│  │  │  │      last-build.bin
│  │  │  │      
│  │  │  ├─fileHashes
│  │  │  │      fileHashes.bin
│  │  │  │      fileHashes.lock
│  │  │  │      resourceHashesCache.bin
│  │  │  │      
│  │  │  └─vcsMetadata
│  │  ├─buildOutputCleanup
│  │  │      buildOutputCleanup.lock
│  │  │      cache.properties
│  │  │      outputFiles.bin
│  │  │      
│  │  └─vcs-1
│  │          gc.properties
│  │          
│  ├─build
│  │  ├─classes
│  │  │  └─java
│  │  │      └─main
│  │  │          └─com
│  │  │              └─trillionares
│  │  │                  └─tryit
│  │  │                      └─server
│  │  │                              ServerApplication.class
│  │  │                              
│  │  ├─generated
│  │  │  └─sources
│  │  │      ├─annotationProcessor
│  │  │      │  └─java
│  │  │      │      └─main
│  │  │      └─headers
│  │  │          └─java
│  │  │              └─main
│  │  ├─resources
│  │  │  └─main
│  │  │          application.yml
│  │  │          
│  │  └─tmp
│  │      └─compileJava
│  │              previous-compilation-data.bin
│  │              
│  ├─gradle
│  │  └─wrapper
│  │          gradle-wrapper.jar
│  │          gradle-wrapper.properties
│  │          
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─trillionares
│      │  │          └─tryit
│      │  │              └─server
│      │  │                      ServerApplication.java
│      │  │                      
│      │  └─resources
│      │          application.yml
│      │          
│      └─test
│          └─java
│              └─com
│                  └─trillionares
│                      └─tryit
│                          └─server
│                                  ServerApplicationTests.java
│                                  
├─com.trillionares.tryit.statistics
│  │  .gitattributes
│  │  .gitignore
│  │  build.gradle
│  │  Dockerfile
│  │  gradlew
│  │  gradlew.bat
│  │  settings.gradle
│  │  
│  ├─.gradle
│  │  ├─8.11.1
│  │  │  │  gc.properties
│  │  │  │  
│  │  │  ├─checksums
│  │  │  │      checksums.lock
│  │  │  │      
│  │  │  ├─executionHistory
│  │  │  │      executionHistory.lock
│  │  │  │      
│  │  │  ├─expanded
│  │  │  ├─fileChanges
│  │  │  │      last-build.bin
│  │  │  │      
│  │  │  ├─fileHashes
│  │  │  │      fileHashes.lock
│  │  │  │      
│  │  │  └─vcsMetadata
│  │  ├─buildOutputCleanup
│  │  │      buildOutputCleanup.lock
│  │  │      cache.properties
│  │  │      
│  │  └─vcs-1
│  │          gc.properties
│  │          
│  ├─gradle
│  │  └─wrapper
│  │          gradle-wrapper.jar
│  │          gradle-wrapper.properties
│  │          
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─trillionares
│      │  │          └─tryit
│      │  │              └─statistics
│      │  │                  │  StatisticsApplication.java
│      │  │                  │  
│      │  │                  ├─application
│      │  │                  │  ├─dto
│      │  │                  │  │  ├─request
│      │  │                  │  │  │      StatisticsCreateRequestDto.java
│      │  │                  │  │  │      
│      │  │                  │  │  └─response
│      │  │                  │  │          StatisticsCreateResponseDto.java
│      │  │                  │  │          StatisticsGetResponseDto.java
│      │  │                  │  │          StatisticsGetUserByUsernameResponseDto.java
│      │  │                  │  │          
│      │  │                  │  └─service
│      │  │                  │          StatisticsService.java
│      │  │                  │          
│      │  │                  ├─domain
│      │  │                  │  ├─client
│      │  │                  │  │      AuthClient.java
│      │  │                  │  │      
│      │  │                  │  ├─common
│      │  │                  │  │  └─base
│      │  │                  │  │          BaseEntity.java
│      │  │                  │  │          
│      │  │                  │  ├─model
│      │  │                  │  │      Statistics.java
│      │  │                  │  │      
│      │  │                  │  ├─respository
│      │  │                  │  │      StatisticsRepository.java
│      │  │                  │  │      
│      │  │                  │  └─service
│      │  │                  │          StatisticsValidation.java
│      │  │                  │          
│      │  │                  ├─infrastructure
│      │  │                  │  └─persistence
│      │  │                  │          StatisticsRepositoryImpl.java
│      │  │                  │          
│      │  │                  ├─libs
│      │  │                  │  └─exception
│      │  │                  │          ErrorCode.java
│      │  │                  │          ErrorResponse.java
│      │  │                  │          ExceptionConverter.java
│      │  │                  │          GlobalException.java
│      │  │                  │          GlobalExceptionHandler.java
│      │  │                  │          
│      │  │                  └─presentation
│      │  │                      ├─controller
│      │  │                      │      StatisticsController.java
│      │  │                      │      
│      │  │                      └─dto
│      │  │                              BaseResponse.java
│      │  │                              StatisticsCreateRequest.java
│      │  │                              
│      │  └─resources
│      │          application.yml
│      │          
│      └─test
│          └─java
│              └─com
│                  └─trillionares
│                      └─tryit
│                          └─statistics
│                                  StatisticsApplicationTests.java
│                                  
├─com.trillionares.tryit.trial
│  │  .gitattributes
│  │  .gitignore
│  │  build.gradle
│  │  Dockerfile
│  │  gradlew
│  │  gradlew.bat
│  │  settings.gradle
│  │  
│  ├─.gradle
│  │  ├─8.11.1
│  │  │  │  gc.properties
│  │  │  │  
│  │  │  ├─checksums
│  │  │  │      checksums.lock
│  │  │  │      
│  │  │  ├─executionHistory
│  │  │  │      executionHistory.lock
│  │  │  │      
│  │  │  ├─expanded
│  │  │  ├─fileChanges
│  │  │  │      last-build.bin
│  │  │  │      
│  │  │  ├─fileHashes
│  │  │  │      fileHashes.lock
│  │  │  │      
│  │  │  └─vcsMetadata
│  │  ├─buildOutputCleanup
│  │  │      buildOutputCleanup.lock
│  │  │      cache.properties
│  │  │      
│  │  └─vcs-1
│  │          gc.properties
│  │          
│  ├─gradle
│  │  └─wrapper
│  │          gradle-wrapper.jar
│  │          gradle-wrapper.properties
│  │          
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─trillionares
│      │  │          └─tryit
│      │  │              └─trial
│      │  │                  │  TrialApplication.java
│      │  │                  │  
│      │  │                  └─jhtest
│      │  │                      ├─domain
│      │  │                      │  ├─client
│      │  │                      │  │      AuthClient.java
│      │  │                      │  │      RecruitmentClient.java
│      │  │                      │  │      
│      │  │                      │  ├─common
│      │  │                      │  │  ├─base
│      │  │                      │  │  │      BaseEntity.java
│      │  │                      │  │  │      
│      │  │                      │  │  └─json
│      │  │                      │  │          JsonUtils.java
│      │  │                      │  │          
│      │  │                      │  ├─model
│      │  │                      │  │  │  Trial.java
│      │  │                      │  │  │  
│      │  │                      │  │  └─type
│      │  │                      │  │          SubmissionStatus.java
│      │  │                      │  │          
│      │  │                      │  ├─repository
│      │  │                      │  │      TrialRepository.java
│      │  │                      │  │      
│      │  │                      │  └─service
│      │  │                      │          TrialEndpoint.java
│      │  │                      │          TrialService.java
│      │  │                      │          
│      │  │                      ├─infrastructure
│      │  │                      │  ├─config
│      │  │                      │  │      FeignConfig.java
│      │  │                      │  │      JpaConfig.java
│      │  │                      │  │      
│      │  │                      │  └─messaging
│      │  │                      │      └─kafka
│      │  │                      │              ConsumerApplicationKafkaConfig.java
│      │  │                      │              ProducerApplicationKafkaConfig.java
│      │  │                      │              
│      │  │                      └─presentation
│      │  │                          ├─controller
│      │  │                          │      trialController.java
│      │  │                          │      
│      │  │                          └─dto
│      │  │                              │  InfoByUsernameResponseDto.java
│      │  │                              │  RecruitmentExistAndStatusDto.java
│      │  │                              │  SendNotificationDto.java
│      │  │                              │  SendRecruitmentDto.java
│      │  │                              │  SubmissionIdAndStatusResponseDto.java
│      │  │                              │  TrialIdResponseDto.java
│      │  │                              │  TrialInfoRequestDto.java
│      │  │                              │  UpdateStatusDto.java
│      │  │                              │  UserResponseDto.java
│      │  │                              │  
│      │  │                              ├─common
│      │  │                              │  ├─base
│      │  │                              │  │      BaseResponseDto.java
│      │  │                              │  │      
│      │  │                              │  └─kafka
│      │  │                              │          KafkaMessage.java
│      │  │                              │          
│      │  │                              └─trial
│      │  │                                      TrialInfoResponseDto.java
│      │  │                                      
│      │  └─resources
│      │          application.yml
│      │          
│      └─test
│          └─java
│              └─com
│                  └─trillionares
│                      └─tryit
│                          └─trial
│                                  TrialApplicationTests.java
│                                  
├─environment
│  │  .env
│  │  
│  └─init-scripts
│          init-sql.sql
│          
├─loki
│      loki-config.yml
│      
└─prometheus
        prometheus.yml

```
