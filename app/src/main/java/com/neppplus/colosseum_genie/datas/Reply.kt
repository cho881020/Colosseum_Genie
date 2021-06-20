package com.neppplus.colosseum_genie.datas

import org.json.JSONObject

class Reply {

    var id = 0
    var content = ""

    lateinit var selectedSide : Side

    var writerNickname = ""

    var likeCount = 0
    var dislikeCount = 0

    var myLike = false
    var myDislike = false


    companion object {

        fun getReplyFromJson(jsonObj : JSONObject) : Reply {
            val resultReply = Reply()

            resultReply.id = jsonObj.getInt("id")
            resultReply.content = jsonObj.getString("content")

            resultReply.selectedSide = Side.getSideFromJson(jsonObj.getJSONObject("selected_side"))

            resultReply.writerNickname = jsonObj.getJSONObject("user").getString("nick_name")

            resultReply.likeCount = jsonObj.getInt("like_count")
            resultReply.dislikeCount = jsonObj.getInt("dislike_count")

            resultReply.myLike = jsonObj.getBoolean("my_like")
            resultReply.myDislike = jsonObj.getBoolean("my_dislike")

            return resultReply
        }

    }

}