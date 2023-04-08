package com.example.vukitapp.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.vukitapp.R
import com.example.vukitapp.data.model.Product
import com.example.vukitapp.helpers.hide
import com.example.vukitapp.helpers.show

/**
 * @author Axel Sanchez
 */
class ProductsAdapter(
    private var products: List<Product?>,
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.products_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = products[position]

        item?.let { product ->
            with(holder){
                tvProduct.text = product.name

                tvTitle.text = product.alias
                tvPrice.text = product.price_type
                if (product.description.isNullOrEmpty()) tvDescription.visibility = View.GONE
                else tvDescription.text = product.description

                //ivImage.load(product.image)

                itemView.setOnClickListener {
                    if (clColapsable.isVisible){
                        clColapsable.hide()
                        ivArrow.setBackgroundResource(R.drawable.ic_arrow_bottom_black)
                    }
                    else {
                        clColapsable.show()
                        ivArrow.setBackgroundResource(R.drawable.ic_arrow_top_black)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = products.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivArrow: ImageView = view.findViewById(R.id.ivArrow)
        val tvProduct: TextView = view.findViewById(R.id.tvProduct)
        val clColapsable: ConstraintLayout = view.findViewById(R.id.clColapsable)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val ivImage: ImageView = view.findViewById(R.id.ivImage)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    }
}