package com.neppplus.colosseum_genie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.colosseum_genie.datas.Topic
import com.neppplus.colosseum_genie.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mTopicList = ArrayList<Topic>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        getTopicListFromServer()

    }


    fun getTopicListFromServer() {

//        서버에서 주제 목록을 받아오자. => /v2/main_info => ServerUtil에 기능 추가 필요.

        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.Companion.JsonReponseHandler {
            override fun onResponse(jsonObj: JSONObject) {

//                서버에서 주제 목록을 받아온 상황.

                val dataObj = jsonObj.getJSONObject("data")
                val topicsArr = dataObj.getJSONArray("topics")

//                topicsArr 안에 있는 여러개의 주제 들을 반복적으로 파싱. => for문 활용.
//                배열에 15개 주제 : index -> 0 ~ 14 까지.

                for ( index   in 0 until topicsArr.length() ) {

//                    index위치에 맞는 주제들을 Topic클래스 형태로 변환.



//                    mTopicList에 추가.

                }


            }

        })

    }

}