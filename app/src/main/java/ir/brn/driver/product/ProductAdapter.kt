package ir.brn.driver.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wdullaer.materialdatetimepicker.Utils
import ir.brn.driver.R
import ir.brn.driver.ext.inflate
import ir.brn.driver.model.product.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter : ListAdapter<Product, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(product: Product) {
            view.tvProductTitle.text = Utils.convertDigitsToPersian(product.title)
            view.tvProductPrice.text = Utils.convertDigitsToPersian("${product.price} تومان ")
            view.tvProductDescription.text = Utils.convertDigitsToPersian(product.description)
            view.tvIntro.text = Utils.convertDigitsToPersian(product.introduction)
            Glide.with(view.context)
                    .load(product.pictureFilename)
                    .into(view.ivProduct)
        }
    }
}


val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}