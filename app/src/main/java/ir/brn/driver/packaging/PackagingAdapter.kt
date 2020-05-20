package ir.brn.driver.packaging

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.brn.driver.R
import ir.brn.driver.ext.inflate
import ir.brn.driver.model.core.Package
import javax.inject.Inject

class PackagingAdapter @Inject constructor() : ListAdapter<Package, PackagingAdapter.PackagingViewHolder>(DIFF_PACKAGE_CALLBACK) {
    var packageListener: PackageListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackagingViewHolder {
        return PackagingViewHolder(parent.inflate(R.layout.item_package))
    }

    override fun onBindViewHolder(holder: PackagingViewHolder, position: Int) {
        val currentPackage = getItem(position)
        holder.packagingTitle.text = currentPackage.name
        holder.cvPackage.setOnClickListener {
            packageListener?.onSelectedPackage(currentPackage)
        }
    }

    inner class PackagingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var packagingTitle: TextView = view.findViewById(R.id.tvPackageTitle)
        var cvPackage: CardView = view.findViewById(R.id.cvPackage)
    }
}

val DIFF_PACKAGE_CALLBACK = object : DiffUtil.ItemCallback<Package>() {
    override fun areItemsTheSame(oldItem: Package, newItem: Package): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Package, newItem: Package): Boolean {
        return oldItem == newItem
    }
}