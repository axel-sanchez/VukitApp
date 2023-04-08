package com.example.vukitapp.presentation.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vukitapp.R
import com.example.vukitapp.data.model.Category
import com.example.vukitapp.data.model.Product
import com.example.vukitapp.helpers.hide
import com.example.vukitapp.helpers.show

/**
 * @author Axel Sanchez
 */
class CategoriesAdapter(
    private var categories: List<Category?>,
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categories[position]

        item?.let { category ->
            with(holder){
                setAdapterProducts(category.products, this)
                itemView.setOnClickListener {
                    if (rvColapsable.isVisible){
                        rvColapsable.hide()
                        ivArrow.setBackgroundResource(R.drawable.ic_arrow_bottom_white)
                    }
                    else {
                        rvColapsable.show()
                        ivArrow.setBackgroundResource(R.drawable.ic_arrow_top_white)
                    }
                }
                itemView.setBackgroundColor(Color.parseColor(category.color))
                tvCategory.text = category.name
            }
        }
    }

    private fun setAdapterProducts(products: MutableList<Product?>, viewHolder: ViewHolder) {
        val recipeAdapter = ProductsAdapter(products)
        with(viewHolder.rvColapsable) {
            layoutManager = LinearLayoutManager(context)
            adapter = recipeAdapter
        }
    }

    override fun getItemCount(): Int = categories.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivArrow: ImageView = view.findViewById(R.id.ivArrow)
        val tvCategory: TextView = view.findViewById(R.id.tvCategory)
        val rvColapsable: RecyclerView = view.findViewById(R.id.rvColapsable)
    }
}