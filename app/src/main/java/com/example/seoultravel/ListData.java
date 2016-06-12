package com.example.seoultravel;

/**
 * Created by 성열 on 2016-06-13.
 */
//Member의 정보를 저장하기 위한 클래스.....
public class ListData {

    String content;    //이름 저장
    String subject;   //제목 저장
    int imgId;      //국기 이미지의 리소스 아이디

    public ListData(String subject, String content, int imgId) {
        // TODO Auto-generated constructor stub
        //생성자함수로 전달받은 Member의 정보를 멤버변수에 저장..
        this.content= content;
        this.subject=subject;
        this.imgId=imgId;
    }

    //이 아래는 getter , setter 메소드듭입니다.
    //OOP(객체 지향 프로그래밍)은 클래스 객체 외부에서 직접 멤버변수에 접근하는 것을 지양합니다.
    //객체의 멤버변수 제어는 객체 스스로 하도록 해서 재사용성을 높인 방법이죠.
    //getter, setter 멤버 메소드들은 그 목적으로 만들어 진 것이죠.

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public int getImgId() {
        return imgId;
    }


}