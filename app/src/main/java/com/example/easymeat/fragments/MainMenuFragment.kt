package com.example.easymeat.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.easymeat.R
import kotlinx.android.synthetic.main.fragment_main_menu.*

class MainMenuFragment : Fragment(), View.OnClickListener {

    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = findNavController()

        btnBuyer.setOnClickListener(this)
        btnSeller.setOnClickListener(this)
        btnExit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id){
            btnBuyer.id -> navigator.navigate(R.id.action_mainMenuFragment_to_buyerFragment)
            btnSeller.id -> navigator.navigate(R.id.action_mainMenuFragment_to_sellerFragment)
            btnExit.id -> {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Deseas salir de EasyMeat?")
                builder.setPositiveButton("Si") { _, _ ->
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.addCategory(Intent.CATEGORY_HOME)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                builder.setNegativeButton("Cancelar") { _, _ -> }
                builder.create()
                builder.show()
            }
        }

    }



}