package com.neppplus.colosseum_genie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.colosseum_genie.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {

//            입력한 이메일 / 비밀번호가 뭔지 변수에 저장.
            val inputEmail = emailEdt.text.toString()
            val inputPw = passwordEdt.text.toString()


//            서버에 실제 회원이 맞는지 확인 요청. (Request)
            ServerUtil.postRequestLogin(inputEmail, inputPw)

        }

    }

    override fun setValues() {

    }


}