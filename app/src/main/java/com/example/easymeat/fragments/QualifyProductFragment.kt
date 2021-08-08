package com.example.easymeat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.easymeat.R
import com.example.easymeat.models.Product
import com.example.easymeat.viewmodels.ViewModelQualifyProduct
import kotlinx.android.synthetic.main.fragment_qualify_product.*


class QualifyProductFragment : Fragment(), View.OnClickListener {

    private lateinit var itemProduct: Product
    private val vm by lazy { ViewModelProviders.of(this).get(ViewModelQualifyProduct::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qualify_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewStar1.setOnClickListener(this)
        imageViewStar2.setOnClickListener(this)
        imageViewStar3.setOnClickListener(this)
        imageViewStar4.setOnClickListener(this)
        imageViewStar5.setOnClickListener(this)

        btnQualifyProduct.setOnClickListener(this)

        itemProduct = arguments?.getSerializable("productItem") as Product
        textViewQualify.text = itemProduct.qualify.toString()

        vm.progressBar.observe(viewLifecycleOwner, Observer {
            progressBarQualifyProduct.visibility = if (it) View.VISIBLE else View.GONE
        })

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imageViewStar1 -> {
                imageViewStar1.setImageResource(R.drawable.ic_selected_star)
                imageViewStar2.setImageResource(R.drawable.ic_unselected_star)
                imageViewStar3.setImageResource(R.drawable.ic_unselected_star)
                imageViewStar4.setImageResource(R.drawable.ic_unselected_star)
                imageViewStar5.setImageResource(R.drawable.ic_unselected_star)
                textViewQualify.text = 1.0.toString()
            }
            R.id.imageViewStar2 -> {
                imageViewStar1.setImageResource(R.drawable.ic_selected_star)
                imageViewStar2.setImageResource(R.drawable.ic_selected_star)
                imageViewStar3.setImageResource(R.drawable.ic_unselected_star)
                imageViewStar4.setImageResource(R.drawable.ic_unselected_star)
                imageViewStar5.setImageResource(R.drawable.ic_unselected_star)
                textViewQualify.text = 2.0.toString()

            }
            R.id.imageViewStar3 -> {
                imageViewStar1.setImageResource(R.drawable.ic_selected_star)
                imageViewStar2.setImageResource(R.drawable.ic_selected_star)
                imageViewStar3.setImageResource(R.drawable.ic_selected_star)
                imageViewStar4.setImageResource(R.drawable.ic_unselected_star)
                imageViewStar5.setImageResource(R.drawable.ic_unselected_star)
                textViewQualify.text = 3.0.toString()

            }
            R.id.imageViewStar4 -> {
                imageViewStar1.setImageResource(R.drawable.ic_selected_star)
                imageViewStar2.setImageResource(R.drawable.ic_selected_star)
                imageViewStar3.setImageResource(R.drawable.ic_selected_star)
                imageViewStar4.setImageResource(R.drawable.ic_selected_star)
                imageViewStar5.setImageResource(R.drawable.ic_unselected_star)
                textViewQualify.text = 4.0.toString()

            }

            R.id.imageViewStar5 -> {
                imageViewStar1.setImageResource(R.drawable.ic_selected_star)
                imageViewStar2.setImageResource(R.drawable.ic_selected_star)
                imageViewStar3.setImageResource(R.drawable.ic_selected_star)
                imageViewStar4.setImageResource(R.drawable.ic_selected_star)
                imageViewStar5.setImageResource(R.drawable.ic_selected_star)
                textViewQualify.text = 5.0.toString()

            }
            R.id.btnQualifyProduct -> {
                vm.qualifyProduct(
                        arguments?.getString("shopProduct")!!,
                        itemProduct.id,
                        textViewQualify.text.toString().toDouble(),
                        requireContext()
                )
            }
        }
    }

}