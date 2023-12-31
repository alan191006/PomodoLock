package vce.nhs.pomodolock.fragments.SettingsFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import vce.nhs.pomodolock.R
import vce.nhs.pomodolock.databinding.FragmentSettingBinding
import vce.nhs.pomodolock.databinding.FragmentSignupBinding
import vce.nhs.pomodolock.fragments.ProfileFragments.LoginFragment

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root

    }


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        visible = true

        val userInfoButton: TextView = view.findViewById(R.id.UserInfoButton)
        userInfoButton.setOnClickListener {
            // Code when the UserInfoButton is clicked
        }

        val editProfileButton: AppCompatButton = view.findViewById(R.id.EditProfileButton)
        editProfileButton.setOnClickListener {
            if (isLoggedIn()) {
                // User is logged in, navigate to the edit profile page
                // Implement the logic to navigate to the edit profile page here
            } else {
                // User is not logged in, navigate to the login page
                val loginFragment = LoginFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, loginFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        val switch1: Switch = view.findViewById(R.id.Switch1)
        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // Switch 1 is checked
            } else {
                // Switch 1 is unchecked
            }
        }

        val switch2: Switch = view.findViewById(R.id.Switch2)
        switch2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // Switch 2 is checked
            } else {
                // Switch 2 is unchecked
            }
        }

        val switch3: Switch = view.findViewById(R.id.Switch3)
        switch3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // Switch 3 is checked
            } else {
                // Switch 3 is unchecked
            }
        }

        val settingField1: RelativeLayout = view.findViewById(R.id.SettingField1)
        settingField1.setOnClickListener {
            // Code when the RelativeLayout is clicked
        }

        val settingField2: RelativeLayout = view.findViewById(R.id.SettingField2)
        settingField2.setOnClickListener {
            // Code when the RelativeLayout is clicked
        }

        val settingField3: RelativeLayout = view.findViewById(R.id.SettingField3)
        settingField3.setOnClickListener {
            // Code when the RelativeLayout is clicked
        }
    }

    private fun isLoggedIn(): Boolean {
        return false
    }

    // Full screen (Hide action + top bar)

    private val hideHandler = Handler(Looper.myLooper()!!)
    private var fullscreenContentControls: View? = null

    @Suppress("InlinedApi")
    private val hidePart2Runnable = Runnable {
        val flags =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        activity?.window?.decorView?.systemUiVisibility = flags
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }
    private val showPart2Runnable = Runnable {
        fullscreenContentControls?.visibility = View.VISIBLE
    }
    private var visible: Boolean = false
    private val hideRunnable = Runnable { hide() }

    @SuppressLint("ClickableViewAccessibility")
    private val delayHideTouchListener = View.OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        delayedHide(100)
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        activity?.window?.decorView?.systemUiVisibility = 0
        show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun toggle() {
        if (visible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        fullscreenContentControls?.visibility = View.GONE
        visible = false
        hideHandler.removeCallbacks(showPart2Runnable)
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    @Suppress("InlinedApi")
    private fun show() {
        visible = true
        hideHandler.removeCallbacks(hidePart2Runnable)
        hideHandler.postDelayed(showPart2Runnable, UI_ANIMATION_DELAY.toLong())
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    companion object {
        private const val AUTO_HIDE = true
        private const val AUTO_HIDE_DELAY_MILLIS = 3000
        private const val UI_ANIMATION_DELAY = 300
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}