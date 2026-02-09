package com.example.telcoapp.recharge

import android.Manifest
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
//import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.telcoapp.R
import com.example.telcoapp.databinding.ActivityPointTransferBinding
import com.sixdee.cvm.sdk

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

class PointTransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPointTransferBinding
    private val PERMISSION_REQUEST_CODE = 100

    private val contactPickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val contactUri = result.data?.data ?: return@registerForActivityResult
            val cursor = contentResolver.query(
                contactUri,
                arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                null,
                null,
                null
            )
            cursor?.use {
                if (it.moveToFirst()) {
                    val phoneNumber = it.getString(0)
                    binding.phoneNumberInput.setText(phoneNumber)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityPointTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //sdk.initialiseSDK(this)
        setupContactsRecyclerView()
        setupContactsButton()
        setupProceedButton()
        setupBackButton()
        setUpEditTexts()
    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupContactsRecyclerView() {
        val contacts = listOf(
            Contact(1, "Budi Santoso", "+6281234567890"),
            Contact(2, "Andi Pratama", "+6281390213344"),
            Contact(3, "Rizky Ramadhan", "+6285777712020"),
            Contact(4, "Fitri Handayani", "+6281355559090")
        )

        val adapter = ContactAdapter(contacts) { contact ->
            binding.phoneNumberInput.setText(contact.phoneNumber)
        }

        binding.contactsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PointTransferActivity, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
    }

    private fun setupContactsButton() {
        binding.contactsButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                openContactPicker()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun openContactPicker() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        contactPickerLauncher.launch(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openContactPicker()
            }
        }
    }

    private fun setupProceedButton() {
        binding.proceedButton.setOnClickListener {
            val phoneNumber = binding.phoneNumberInput.text.toString()
            val points = binding.pointsInput.text.toString()

            if (phoneNumber.isEmpty()) {
                binding.phoneNumberInput.error = "Please enter phone number"
                return@setOnClickListener
            }

            if (points.isEmpty()) {
                binding.pointsInput.error = "Please enter points"
                return@setOnClickListener
            }

           // showVerificationDialog()
            val msg = "Recharge of Rp ${binding.pointsInput.text} successfully completed for ${binding.phoneNumberInput.text}."
            Log.d("RECHARGE_MSG", "setupProceedButton: $msg")
            sdk.sendEvent("RECHARGE",msg )
            showSuccessDialog()
        }
    }





    private fun shareTransferDetails() {
        val phoneNumber = binding.phoneNumberInput.text.toString()
        val points = binding.pointsInput.text.toString()
        val shareText = "I have successfully transferred $points points to $phoneNumber"

        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(intent, "Share Transfer"))
    }

    private fun setUpEditTexts(){
        setEditTextBorder(binding.phoneNumberInput)
        setEditTextBorder(binding.pointsInput)
    }
    private fun setEditTextBorder(editText: EditText){
        val drawable = editText.background.mutate() as GradientDrawable
        editText.addTextChangedListener {
            checkForAllValues()
            if (!it.isNullOrEmpty()){
                drawable.setStroke(1.dp, Color.GREEN)
            }else{
                drawable.setStroke(1.dp, Color.RED)
            }
        }
    }
    private fun checkForAllValues(){
        if (!binding.phoneNumberInput.text.isNullOrEmpty() && !binding.pointsInput.text.isNullOrEmpty()) {
         //   binding.proceedButton.setBackgroundResource(R.drawable.bg_button_proceed)
        } else {
          //  binding.proceedButton.setBackgroundResource(R.drawable.bg_button_proceed_inactive)
        }
    }

    private fun showSuccessDialog() {
        val d = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        d.setContentView(R.layout.dialog_order_success)
        val rechargeText =  d.findViewById<TextView?>(R.id.titleLine1)
        val done = d.findViewById<ImageButton?>(R.id.btnSuccessDone)
        rechargeText?.text = "Recharge"
        if (done != null) done.setOnClickListener(View.OnClickListener { v: View? ->
            d.dismiss()
            finish()
        })

        // Prepare entrance
        val root = d.findViewById<View?>(android.R.id.content)
        if (root != null) {
            root.setAlpha(0f)
            root.setTranslationY(24f)
        }
        // Hero zoom-in
        val hero = d.findViewById<ImageView?>(R.id.successHero)
        if (hero != null) {
            hero.setScaleX(0.85f)
            hero.setScaleY(0.85f)
            hero.setAlpha(0f)
        }
        // Texts & CTA
        val title1 = d.findViewById<TextView?>(R.id.titleLine1)
        val title2 = d.findViewById<TextView?>(R.id.titleLine2)
        val subtitle = d.findViewById<TextView?>(R.id.successSubtitle)
        if (title1 != null) {
            title1.setAlpha(0f)
            title1.setTranslationY(16f)
        }
        if (title2 != null) {
            title2.setAlpha(0f)
            title2.setTranslationY(20f)
        }
        if (subtitle != null) {
            subtitle.setAlpha(0f)
            subtitle.setTranslationY(24f)
        }
        if (done != null) {
            done.setScaleX(0.7f)
            done.setScaleY(0.7f)
            done.setAlpha(0f)
        }

        d.show()

        if (root != null) {
            val set = AnimatorSet()
            set.setInterpolator(AccelerateDecelerateInterpolator())
            set.setDuration(280)
            set.playTogether(
                ObjectAnimator.ofFloat<View?>(root, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat<View?>(root, View.TRANSLATION_Y, 24f, 0f)
            )
            set.start()
        }

        if (hero != null) {
            val zoom = AnimatorSet()
            zoom.setInterpolator(OvershootInterpolator(1.0f))
            zoom.setStartDelay(180)
            zoom.setDuration(700)
            zoom.playTogether(
                ObjectAnimator.ofFloat<View?>(hero, View.SCALE_X, 0.85f, 1f),
                ObjectAnimator.ofFloat<View?>(hero, View.SCALE_Y, 0.85f, 1f),
                ObjectAnimator.ofFloat<View?>(hero, View.ALPHA, 0f, 1f)
            )
            zoom.start()
        }

        if (title1 != null) {
            val s = AnimatorSet()
            s.setInterpolator(AccelerateDecelerateInterpolator())
            s.setStartDelay(260)
            s.setDuration(380)
            s.playTogether(
                ObjectAnimator.ofFloat<View?>(title1, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat<View?>(title1, View.TRANSLATION_Y, 16f, 0f)
            )
            s.start()
        }
        if (title2 != null) {
            val s = AnimatorSet()
            s.setInterpolator(AccelerateDecelerateInterpolator())
            s.setStartDelay(340)
            s.setDuration(380)
            s.playTogether(
                ObjectAnimator.ofFloat<View?>(title2, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat<View?>(title2, View.TRANSLATION_Y, 20f, 0f)
            )
            s.start()
        }
        if (subtitle != null) {
            val s = AnimatorSet()
            s.setInterpolator(AccelerateDecelerateInterpolator())
            s.setStartDelay(420)
            s.setDuration(320)
            s.playTogether(
                ObjectAnimator.ofFloat<View?>(subtitle, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat<View?>(subtitle, View.TRANSLATION_Y, 24f, 0f)
            )
            s.start()
        }
        if (done != null) {
            val s = AnimatorSet()
            s.setInterpolator(OvershootInterpolator(1.1f))
            s.setStartDelay(500)
            s.setDuration(380)
            s.playTogether(
                ObjectAnimator.ofFloat<View?>(done, View.SCALE_X, 0.7f, 1f),
                ObjectAnimator.ofFloat<View?>(done, View.SCALE_Y, 0.7f, 1f),
                ObjectAnimator.ofFloat<View?>(done, View.ALPHA, 0f, 1f)
            )
            s.start()
        }
    }
}
