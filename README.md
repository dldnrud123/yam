
<h1>YAM</h1>
<p>학생들에게 식단표를 공유해주는 어플리케이션입니다.</p>

![test_video_gif](https://github.com/dldnrud123/yam/assets/49900271/2dd80c0f-4e44-49c1-8e5b-2f1f19f546c1)

<hr>

<h3><Frontend></h3>
<p>Android Studio</p>

<h3><Backend></h3>
* 메인
JAVA(Mealtest_01)

* 스케쥴링 작업
- parsing_task
- notification_task 

<h3><DB></h3>
ORACLE 11g
  
<h3><SERVER></h3>
OS : UBUNTU

<hr>


* 주요기능

서버에서 java scheduler를 이용하여 일주일 마다 해당 사이트의 내용을 파싱하여 DB에 저장 후,
클라이언트에서 내용을 볼수 있도록 하였습니다.

* 선호도기능
  
학생들이 하트를 눌러 선호도체크를 할수 있고, 선호하는 학생 수를 볼 수 있게 하였습니다.
또 선호도를 통해 일정 선호도를 갱신할 경우 학생들이 추천메뉴 알림셋팅을 켜놓을 경우 추천메뉴 알림을 받을 수 있도록 하였습니다.

* 식단알림기능
  
![fcm_push1](https://github.com/dldnrud123/yam/assets/49900271/118de1c5-b64e-4d94-943b-4a0e827c741b)
![fcm_push2](https://github.com/dldnrud123/yam/assets/49900271/9a72ff27-de3c-4d63-a42b-27d6c9d6878e)

FCM(Firebase Cloud Messaging) 기능을 이용하여 학생들의 원하는 시간대의 식단의 알림을 on/off 할수 있도록 설정 하였습니다.

* 커뮤니케이션 기능
학생들이 하루 식단에대해 자유롭게 대화할 수 있도록 게시판 기능을 추가 했고, 조식/중식/석식 별로 볼수 있도록 정렬기능을 추가하였습니다.
 
-- 배포 및 운영 --
데스크탑으로 포트포워딩하여 배포하였고, 약 두달간 실운영 하였습니다.


읽어봐 주셔서 감사합니다 :D
