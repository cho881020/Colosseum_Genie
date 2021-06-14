package com.neppplus.colosseum_genie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.neppplus.colosseum_genie.datas.Topic
import kotlinx.android.synthetic.main.activity_view_topic_detail.*

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var mTopic : Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mTopic = intent.getSerializableExtra("topic") as Topic

        topicTitleTxt.text = mTopic.title
        Glide.with(mContext).load(mTopic.imageURL).into(topicImg)

    }

}