package com.guysapp.ats.ui.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.guysapp.ats.R
import com.guysapp.ats.databinding.FragmentFeedbackBinding

class FeedbackFragment : Fragment(),View.OnClickListener{

    var mBinding : FragmentFeedbackBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFeedbackBinding.inflate(inflater,container,false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init(){
        mBinding?.btFeedbackSubmit?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.bt_feedback_submit->{
                var validate = validateContent(mBinding?.etSubject?.text.toString(),mBinding?.etMessage?.text.toString())

                if (validate){
                    Toast.makeText(requireContext(),getString(R.string.successful_submitted),Toast.LENGTH_SHORT).show()
                    mBinding?.etSubject?.text?.clear()
                    mBinding?.etMessage?.text?.clear()
                }
            }
        }
    }

    private fun validateContent(subject: String, message: String): Boolean {

        if (subject.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.err_subject),Toast.LENGTH_SHORT).show()
            return false
        }

        if (message.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.err_message),Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}