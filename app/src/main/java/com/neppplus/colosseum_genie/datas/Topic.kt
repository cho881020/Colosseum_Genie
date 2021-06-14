package com.neppplus.colosseum_genie.datas

import org.json.JSONObject
import java.io.Serializable

class Topic : Serializable {

    var id = 0 // 나중에 Int 가 들어올것이라는 명시.
    var title = "" // 나중에 String이 들어올것이라는 명시.
    var imageURL = ""

    val sides = ArrayList<Side>()

    companion object {

//        적당한 JSONObject 하나를 넣어주면 => Topic 형태로 변환해주는 함수 작성.

        fun getTopicDataFromJson(jsonObj : JSONObject) : Topic {

            val resultTopic = Topic()

            resultTopic.id = jsonObj.getInt("id")
            resultTopic.title = jsonObj.getString("title")
            resultTopic.imageURL = jsonObj.getString("img_url")

            val sidesArr = jsonObj.getJSONArray("sides")

            for (i   in 0 until sidesArr.length()) {

//                토픽마다 하위정보로 달린 => 선택진영을 파싱.
                val sideObj = sidesArr.getJSONObject(i)
                val side = Side.getSideFromJson(sideObj)

                resultTopic.sides.add(side)

            }

            return resultTopic

        }

    }

}