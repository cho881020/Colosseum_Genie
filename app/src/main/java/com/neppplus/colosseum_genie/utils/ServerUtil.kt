package com.neppplus.colosseum_genie.utils

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class ServerUtil {

//    어떤 내용? => 서버 연결 전담.

    companion object {


//        모든 기능의 기본이 되는 주소.
        val BASE_URL = "http://54.180.52.26"

//        로그인 하는 기능

        fun postRequestLogin(email : String, pw : String) {

//            서버에 입력받은 email, pw 전달 => 로그인 기능 POST /user 로 전달. => 요청 (Request) 실행.
//            라이브러리 (OkHttp) 활용해서 짜보자.

//            http://54.180.52.26/user + POST + 파라미터들 첨부.

//            호스트주소 + 기능주소 결합
            val urlString = "${BASE_URL}/user"

//            POST방식 => 파라미터를 폼데이터 (폼바디) 에 담아주자.

            val formData = FormBody.Builder()
                    .add("email", email)
                    .add("password", pw)
                    .build()


//            어디로 => 어떻게 => 어떤 데이터를 들고 가는지를 모두 종합해둔, Request 변수 생성.

            val request = Request.Builder()
                    .url(urlString)
                    .post(formData)
                    .build()

//            클라이언트로써의 동작 : Request 요청 실행. => OkHttp라이브러리 지원.

            val client = OkHttpClient()

//            실제로 서버에 요청 날리기.
            client.newCall(request)


        }


    }

}