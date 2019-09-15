package com.example.shopbacktest.ui.userDetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.shopbacktest.R
import com.example.shopbacktest.base.fragment.BaseFragment
import com.example.shopbacktest.ui.Users.UsersFragment
import com.example.shopbacktest.util.fetchCircleImage
import com.example.shopbacktest.util.fetchImage
import kotlinx.android.synthetic.main.detail_user_fragment.*
import kotlinx.android.synthetic.main.detail_user_fragment.txt_badge
import kotlinx.android.synthetic.main.detail_user_fragment.txt_login
import com.example.shopbacktest.MainActivity


class DetailUserFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.detail_user_fragment

    private lateinit var viewModel:  DetailUserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_close.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailUserViewModel::class.java)

        viewModel.getResult().observe(viewLifecycleOwner, Observer {
            it?.apply {
                txt_name.text = name
                txt_login.text = login
                txt_badge.visibility = if (badge) View.VISIBLE else View.GONE
                txt_location.text = location
                txt_link.text = blog
                fetchCircleImage(requireContext(),avatarUrl,img_avatar)
                if (bio == null)
                    img_bio.visibility = View.GONE
                else
                    fetchImage(requireContext(), bio, img_bio)
            }
        })

        viewModel.getError().observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it!!, Toast.LENGTH_LONG).show()
        })

        arguments?.let {
            viewModel.getUser(it.getString(UsersFragment.LOGIN)!!)
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).supportActionBar!!.show()
    }

}
