package com.dudnyk.projectwithmaterialdesign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dudnyk.projectwithmaterialdesign.Data.User
import com.dudnyk.projectwithmaterialdesign.Preferences.UserPreferences
import com.dudnyk.projectwithmaterialdesign.databinding.FragmentProfileBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileFragment : Fragment() {
    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var toolbar: MaterialToolbar
    private lateinit var user: User
    private lateinit var fab: FloatingActionButton
    private lateinit var userPreferences: UserPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)

        setUpToolBar(R.string.profile)
        initObjects()
        initView()

        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab = activity?.findViewById(R.id.fab)!!
        fab.show()
    }

    override fun onResume() {
        setUpToolBar(R.string.profile)
        initObjects()
        initView()
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (userPreferences.isLoggedIn()) {
            fab.hide()
        }
    }

    companion object {
        const val TAG  = "FRAGMENT_PROFILE"
        fun newInstance() = ProfileFragment()
    }


    private fun setUpToolBar(title: Int) {
        toolbar = activity?.findViewById(R.id.my_toolbar)!!
        toolbar.setTitle(title)
    }

    private fun initObjects() {
        userPreferences = this.context?.let { UserPreferences(it) }!!
        //TODO change logic to either:
        // - redirecting to login activity instead of showing a fake account
        // - showing another fragment with "Log in" message
        user = userPreferences.getCurrentUser()
    }

    private fun initView() {
        //TODO set user image
        profileBinding.profileImg.setImageResource(user.resId)
        profileBinding.profileName.text = user.name
        profileBinding.profileEmail.text = user.email
    }
}