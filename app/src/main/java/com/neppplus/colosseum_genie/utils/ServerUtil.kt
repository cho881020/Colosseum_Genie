package com.neppplus.colosseum_genie.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//    어떤 내용? => 서버 연결 전담.

    companion object {

        interface JsonReponseHandler {
            fun onResponse(jsonObj : JSONObject)
        }


//        모든 기능의 기본이 되는 주소.
        val BASE_URL = "http://54.180.52.26"

//        로그인 하는 기능

        fun postRequestLogin(email : String, pw : String, handler : JsonReponseHandler?) {

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

//            실제로 서버에 요청 날리기. => 갔다 와서는 뭘 한건지?
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버에 연결 자체를 실패한 경우.
//                    서버 마비, 인터넷 단선.
                }

                override fun onResponse(call: Call, response: Response) {

//                    로그인 성공, 로그인 실패 (연결 성공 -> 검사 실패)
//                    응답이 돌아온 경우.


//                    응답 본문을 String 으로 저장.
                    val bodyString = response.body!!.string()

//                    bodyString 변수에는 한글이 깨져있다. => JSONObject로 변환하면, 한글 정상 처리.
                    val jsonObj = JSONObject(bodyString)

                    Log.d("응답본문", jsonObj.toString())

//                    handler 변수가 null이 아니라면, (실체가 있다면)
//                     그 내부에 적힌 내용 실행.

                    handler?.onResponse(jsonObj)

                }

            })


        }


//        회원가입 하는 기능

        fun putRequestSignUp(email : String, pw : String, nick: String, handler : JsonReponseHandler?) {

//            서버에 입력받은 email, pw, nick 전달 => 회원가입 기능 PUT /user 로 전달. => 요청 (Request) 실행.
//            라이브러리 (OkHttp) 활용해서 짜보자.

//            http://54.180.52.26/user + PUT + 파라미터들 첨부.

//            호스트주소 + 기능주소 결합
            val urlString = "${BASE_URL}/user"

//            PUT방식 => 파라미터를 폼데이터 (폼바디) 에 담아주자.

            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nick)
                .build()


//            어디로 => 어떻게 => 어떤 데이터를 들고 가는지를 모두 종합해둔, Request 변수 생성.

            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()

//            클라이언트로써의 동작 : Request 요청 실행. => OkHttp라이브러리 지원.

            val client = OkHttpClient()

//            실제로 서버에 요청 날리기. => 갔다 와서는 뭘 한건지?
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버에 연결 자체를 실패한 경우.
//                    서버 마비, 인터넷 단선.
                }

                override fun onResponse(call: Call, response: Response) {

//                    로그인 성공, 로그인 실패 (연결 성공 -> 검사 실패)
//                    응답이 돌아온 경우.


//                    응답 본문을 String 으로 저장.
                    val bodyString = response.body!!.string()

//                    bodyString 변수에는 한글이 깨져있다. => JSONObject로 변환하면, 한글 정상 처리.
                    val jsonObj = JSONObject(bodyString)

                    Log.d("응답본문", jsonObj.toString())

//                    handler 변수가 null이 아니라면, (실체가 있다면)
//                     그 내부에 적힌 내용 실행.

                    handler?.onResponse(jsonObj)

                }

            })


        }


//        이메일 / 닉네임 중복 확인 기능

        fun getRequestDuplCheck(type: String, value: String, handler: JsonReponseHandler?) {

//            어디로? + 어떤 데이터? 같이 명시하자.
//            URL 적으면 + 파라미터 첨부도 같이. => 보조 도구 (Builder)

            val urlBuilder = "${BASE_URL}/user_check".toHttpUrlOrNull()!!.newBuilder()
            urlBuilder.addEncodedQueryParameter("type", type)
            urlBuilder.addEncodedQueryParameter("value", value)

            val urlString = urlBuilder.build().toString()

            Log.d("완성된URL", urlString)


            val request = Request.Builder()
                .url(urlString)
                .get()
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())

                    handler?.onResponse(jsonObj)

                }

            })



        }


//        진행중인 주제 목록 확인 기능

        fun getRequestMainInfo(context: Context, handler: JsonReponseHandler?) {

//            어디로? + 어떤 데이터? 같이 명시하자.
//            URL 적으면 + 파라미터 첨부도 같이. => 보조 도구 (Builder)

            val urlBuilder = "${BASE_URL}/v2/main_info".toHttpUrlOrNull()!!.newBuilder()
//            urlBuilder.addEncodedQueryParameter("type", type)
//            urlBuilder.addEncodedQueryParameter("value", value)

            val urlString = urlBuilder.build().toString()

            Log.d("완성된URL", urlString)


            val request = Request.Builder()
                    .url(urlString)
                    .get()
                    .header("X-Http-Token", ContextUtil.getToken(context))
                    .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())

                    handler?.onResponse(jsonObj)

                }

            })



        }


//        원하는 주제 상세현황 확인 기능

        fun getRequestTopicDetail(context: Context, topicId: Int, handler: JsonReponseHandler?) {

//            어디로? + 어떤 데이터? 같이 명시하자.
//            URL 적으면 + 파라미터 첨부도 같이. => 보조 도구 (Builder)

            val urlBuilder = "${BASE_URL}/topic".toHttpUrlOrNull()!!.newBuilder()
            urlBuilder.addEncodedPathSegment(topicId.toString())
            urlBuilder.addEncodedQueryParameter("order_type", "NEW")
//            urlBuilder.addEncodedQueryParameter("value", value)

            val urlString = urlBuilder.build().toString()

            Log.d("완성된URL", urlString)


            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token", ContextUtil.getToken(context))
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())

                    handler?.onResponse(jsonObj)

                }

            })



        }

//        특정 진영에 투표하기

        fun postRequestVote(context: Context, sideId : Int, handler : JsonReponseHandler?) {


            val urlString = "${BASE_URL}/topic_vote"

            val formData = FormBody.Builder()
                    .add("side_id", sideId.toString())
                    .build()


            val request = Request.Builder()
                    .url(urlString)
                    .post(formData)
                    .header("X-Http-Token", ContextUtil.getToken(context))
                    .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버에 연결 자체를 실패한 경우.
//                    서버 마비, 인터넷 단선.
                }

                override fun onResponse(call: Call, response: Response) {

//                    로그인 성공, 로그인 실패 (연결 성공 -> 검사 실패)
//                    응답이 돌아온 경우.


//                    응답 본문을 String 으로 저장.
                    val bodyString = response.body!!.string()

//                    bodyString 변수에는 한글이 깨져있다. => JSONObject로 변환하면, 한글 정상 처리.
                    val jsonObj = JSONObject(bodyString)

                    Log.d("응답본문", jsonObj.toString())

//                    handler 변수가 null이 아니라면, (실체가 있다면)
//                     그 내부에 적힌 내용 실행.

                    handler?.onResponse(jsonObj)

                }

            })


        }


    }

}