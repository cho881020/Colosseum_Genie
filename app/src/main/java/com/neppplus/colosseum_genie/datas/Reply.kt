package com.neppplus.colosseum_genie.datas

import org.json.JSONObject

class Reply {

    var id = 0
    var content = ""

    lateinit var selectedSide : Side

    var writerNickname = ""

    companion object {

        fun getReplyFromJson(jsonObj : JSONObject) : Reply {
            val resultReply = Reply()

            resultReply.id = jsonObj.getInt("id")
            resultReply.content = jsonObj.getString("content")

            resultReply.selectedSide = Side.getSideFromJson(jsonObj.getJSONObject("selected_side"))

            resultReply.writerNickname = jsonObj.getJSONObject("user").getString("nick_name")

            return resultReply
        }

    }

}