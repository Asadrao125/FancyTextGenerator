package com.stylish.fancy.text.generator.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.kaelli.niceratingbar.NiceRatingBar
import com.stylish.fancy.text.generator.R
import com.stylish.fancy.text.generator.databinding.ActivityHomeBinding
import com.stylish.fancy.text.generator.fragments.DashboardFragment
import com.stylish.fancy.text.generator.fragments.StylishFragment
import com.stylish.fancy.text.generator.utils.GlobalFunction

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    lateinit var toolbarTitle: TextView
    private lateinit var globalFunction: GlobalFunction
    lateinit var icBackOrMenu: ImageView
    private lateinit var icMenu: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        icMenu = findViewById(R.id.icMenu)
        icBackOrMenu.visibility = View.GONE
        globalFunction = GlobalFunction(this)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.text = getString(R.string.home_title)

        changeFragment(DashboardFragment())
        binding.chipNavBar.setItemSelected(R.id.homeMenu, true)

        binding.chipNavBar.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                var fragment: Fragment? = null
                fragment = if (id == R.id.homeMenu) {
                    toolbarTitle.text = getString(R.string.home_title)
                    DashboardFragment()
                } else {
                    toolbarTitle.text = getString(R.string.stylish_text)
                    StylishFragment()
                }
                changeFragment(fragment)
            }
        })
    }

    private fun showStatusPopup() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popup_layout, null)
        val popupWindow = PopupWindow(view, 500, ConstraintLayout.LayoutParams.WRAP_CONTENT, true)
        popupWindow.showAsDropDown(icMenu, 0, 0)
        val shareApp = view.findViewById<LinearLayout>(R.id.shareApp)
        val privacyPolicy = view.findViewById<LinearLayout>(R.id.privacyPolicy)
        val checkUpdate = view.findViewById<LinearLayout>(R.id.checkUpdate)
        val feedback = view.findViewById<LinearLayout>(R.id.feedback)
        val moreApps = view.findViewById<LinearLayout>(R.id.moreApps)
        val rateApp = view.findViewById<LinearLayout>(R.id.rateUs)
        shareApp.setOnClickListener {
            globalFunction.shareApp()
            popupWindow.dismiss()
        }
        privacyPolicy.setOnClickListener {
            globalFunction.intentBrowser(getString(R.string.privacy_policy_link))
            popupWindow.dismiss()
        }
        checkUpdate.setOnClickListener {
            globalFunction.intentBrowser(getString(R.string.appLink))
            popupWindow.dismiss()
        }
        feedback.setOnClickListener {
            globalFunction.sendToGmail()
            popupWindow.dismiss()
        }
        rateApp.setOnClickListener {
            globalFunction.intentBrowser(getString(R.string.appLink))
            popupWindow.dismiss()
        }
        moreApps.setOnClickListener { popupWindow.dismiss() }
    }

    private fun changeFragment(fragment: Fragment?) {
        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showDialog() {
        val rating = floatArrayOf(0f)
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_exit, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.window?.attributes!!.windowAnimations = R.style.DialogAnimation
        val tvExit = dialogView.findViewById<TextView>(R.id.tvExit)
        val tvRateUs = dialogView.findViewById<TextView>(R.id.tvRateUs)
        val niceRatingBar = dialogView.findViewById<NiceRatingBar>(R.id.niceRatingBar)
        tvExit.setOnClickListener {
            alertDialog.dismiss()
            finishAffinity()
        }
        niceRatingBar.setOnRatingChangedListener { starRating -> rating[0] = starRating }
        tvRateUs.setOnClickListener {
            if (rating[0] == 0f) {
                Toast.makeText(this, "Please select stars for rating", Toast.LENGTH_SHORT).show()
            } else if (rating[0] > 0 && rating[0] < 4) {
                globalFunction.sendToGmail()
            } else {
                globalFunction.intentBrowser(getString(R.string.appLink))
                alertDialog.dismiss()
            }
        }
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    override fun onBackPressed() {
        showDialog()
    }
}