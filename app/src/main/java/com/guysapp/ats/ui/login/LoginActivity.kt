package com.guysapp.ats.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.guysapp.ats.R
import com.guysapp.ats.data.Repository
import com.guysapp.ats.databinding.ActivityLoginBinding
import com.guysapp.ats.extensions.goToActivity
import com.guysapp.ats.ui.base.BaseActivity
import com.guysapp.ats.ui.home.HomeActivity

class LoginActivity : BaseActivity(),View.OnClickListener {


    var mBinding : ActivityLoginBinding? = null
    var repository : Repository? = null
    val loginViewModel : LoginViewModel by viewModels{
        LoginViewModelFactory(repository = repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)

        init()
        showProgress()
        loginResult()

    }

    private fun showProgress() {
        loginViewModel.IsProgressBarVisible.observe(this) {
            if (it) mBinding?.progressBar?.visibility = View.VISIBLE
            else mBinding?.progressBar?.visibility = View.INVISIBLE
        }
    }

    private fun loginResult() {
        loginViewModel.response.observe(this) {
            if (it){
                this.goToActivity(HomeActivity::class)
            }
            else Toast.makeText(this, "Try again with correct username & password", Toast.LENGTH_LONG).show()
        }
    }


    fun init(){
        mBinding?.btLogin?.setOnClickListener(this)
        repository = Repository()
    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.bt_login->{
                val username = mBinding?.etLoginUsername?.text.toString()
                val password = mBinding?.etLoginPassword?.text.toString()
                if (username.isNotEmpty() && password.isNotEmpty()){
                    loginViewModel.doLogin(username,password)
                }
                else Toast.makeText(this,getString(R.string.error_msg_login),Toast.LENGTH_LONG).show()
            }
        }
    }
}