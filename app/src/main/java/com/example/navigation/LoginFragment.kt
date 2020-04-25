package com.example.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        btn_submit.setOnClickListener {
            viewModel.authenticate(edt_username.text.toString(), edt_pass.text.toString())
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            viewModel.refuseAuthentication()
            navController.popBackStack(R.id.mainFragment, false)
        }
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            when (it){
                LoginViewModel.AuthenticationState.AUTHENTICATED ->{navController.popBackStack()}
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION ->{
                    showErrorMessage()
                }
            }
        })
    }

    private fun showErrorMessage() {
        Snackbar.make(this!!.view!!, "invalid user or password", Snackbar.LENGTH_LONG)
    }

    public fun abc(){

    }

    public fun xyz(){

    }
}
