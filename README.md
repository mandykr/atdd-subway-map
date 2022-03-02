<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-6.14.15-blue">
  <img alt="node" src="https://img.shields.io/badge/node-14.18.2-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
</p>

<br>

# 지하철 노선도 미션
## 🚀 1단계 - 노선 관리 기능 구현
```
Feature: 지하철 노선 관리 기능

  Scenario: 지하철 노선 생성
    When 지하철 노선 생성을 요청 하면
    Then 지하철 노선 생성이 성공한다.
  
  Scenario: 지하철 노선 목록 조회
    Given 지하철 노선 생성을 요청 하고
    Given 새로운 지하철 노선 생성을 요청 하고
    When 지하철 노선 목록 조회를 요청 하면
    Then 두 노선이 포함된 지하철 노선 목록을 응답받는다
    
  Scenario: 지하철 노선 조회
    Given 지하철 노선 생성을 요청 하고
    When 생성한 지하철 노선 조회를 요청 하면
    Then 생성한 지하철 노선을 응답받는다
    
  Scenario: 지하철 노선 수정
    Given 지하철 노선 생성을 요청 하고
    When 지하철 노선의 정보 수정을 요청 하면
    Then 지하철 노선의 정보 수정은 성공한다.
    
  Scenario: 지하철 노선 삭제
    Given 지하철 노선 생성을 요청 하고
    When 생성한 지하철 노선 삭제를 요청 하면
    Then 생성한 지하철 노선 삭제가 성공한다.
```
<br>

## 🚀 2단계 - 인수 테스트 리팩터링
```
Feature: 지하철역 관리 기능
...  
  Scenario: 중복이름으로 지하철역 생성
    Given 지하철역 생성을 요청 하고
    When 같은 이름으로 지하철역 생성을 요청 하면
    Then 지하철역 생성이 실패한다.
```
```
Feature: 지하철 노선 관리 기능
...  
  Scenario: 중복이름으로 지하철 노선 생성
    Given 지하철 노선 생성을 요청 하고
    When 같은 이름으로 지하철 노선 생성을 요청 하면
    Then 지하철 노선 생성이 실패한다.
```
<br>

## 🚀 3단계 - 지하철 구간 관리
**구간 등록 기능**
- [x] 지하철 노선에 구간을 등록하는 기능을 구현
- [x] 새로운 구간의 상행역은 해당 노선에 등록되어있는 하행 종점역이어야 한다.
- [x] 새로운 구간의 하행역은 해당 노선에 등록되어있는 역일 수 없다.
- [x] 새로운 구간 등록시 위 조건에 부합하지 않는 경우 에러 처리한다.

**구간 제거 기능**
- [x] 지하철 노선에 구간을 제거하는 기능 구현
- [x] 지하철 노선에 등록된 역(하행 종점역)만 제거할 수 있다. 즉, 마지막 구간만 제거할 수 있다.
- [x] 지하철 노선에 상행 종점역과 하행 종점역만 있는 경우(구간이 1개인 경우) 역을 삭제할 수 없다.
- [x] 새로운 구간 제거시 위 조건에 부합하지 않는 경우 에러 처리한다.
