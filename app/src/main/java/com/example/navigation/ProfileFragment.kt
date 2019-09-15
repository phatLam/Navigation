package com.example.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    val args: ProfileFragmentArgs by navArgs()
    private val viewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profile = args.profile
        Toast.makeText(context, " argument = " + profile, Toast.LENGTH_LONG).show()
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            when(it){
                LoginViewModel.AuthenticationState.AUTHENTICATED ->{showMessage()}
                LoginViewModel.AuthenticationState.UNAUTHENTICATED ->{findNavController().navigate(R.id.loginFragment)}
            }
        })
    }

    private fun showMessage(){
        txt_welcome.text = " welcome " + viewModel.username
    }

}
