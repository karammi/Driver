package ir.brn.driver.myLoads

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wdullaer.materialdatetimepicker.Utils
import ir.brn.driver.R
import ir.brn.driver.ext.inflate
import ir.brn.driver.util.Utility
import javax.inject.Inject

class MyLoadAdapter @Inject constructor()
    : ListAdapter<Trip, MyLoadAdapter.LoadViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadViewHolder {
        val itemView = parent.inflate(R.layout.item_load)
        return LoadViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LoadViewHolder, position: Int) {
        val load = getItem(position)
        holder.mTitle.text = Utils.convertDigitsToPersian(load.loadingTime?.let { Utility.getPersianDate(it) })

        load.loadingAddress?.city?.name?.let { holder.mSourceAddress.text = it }
        load.dischargingAddress?.city?.name?.let { holder.mDestinationAddress.text = it }
        holder.mTruckTypeValue.text = Utils.convertDigitsToPersian(load.truck?.name + " " + load.carrier?.name)
        holder.mLoadNumber.text = load.tripIdentity

        if (load.routes?.size!! > 0) {
            load.routes?.get(0)?.let { holder.mSourceFullAddress.text = it?.startAddress?.address }
            load.routes?.get(0)?.endAddress?.address.let { holder.mDestinationFullAddress.text = it }
        }
        if (load.selected!!) {
            holder.mShowDetails.visibility = View.VISIBLE
            holder.mShowMore.setImageResource(R.drawable.ic_expand_less)
        } else {
            holder.mShowDetails.visibility = View.GONE
            holder.mShowMore.setImageResource(R.drawable.ic_expand_more)
        }

        holder.mMainLoadCardView.setOnClickListener {
            if (!load.selected!!) {
                holder.mShowDetails.visibility = View.VISIBLE
                holder.mShowMore.setImageResource(R.drawable.ic_expand_less)
                holder.sideBar.setBackgroundResource(R.color.baron_grayLight)
                load.selected = true
                load.seen = true
            } else {
                holder.mShowDetails.visibility = View.GONE
                holder.mShowMore.setImageResource(R.drawable.ic_expand_more)
                load.selected = false
            }
        }

        holder.mShowMore.setOnClickListener {
            if (!load.selected!!) {
                holder.mShowDetails.visibility = View.VISIBLE
                holder.mShowMore.setImageResource(R.drawable.ic_expand_less)
                holder.sideBar.setBackgroundResource(R.color.baron_grayLight)
                load.selected = true
                load.seen = true
            } else {
                holder.mShowDetails.visibility = View.GONE
                holder.mShowMore.setImageResource(R.drawable.ic_expand_more)
                load.selected = false
            }
        }
        if (load.seen!!)
            holder.sideBar.setBackgroundResource(R.color.baron_grayLight)
        else
            holder.sideBar.setBackgroundResource(R.color.baron_red)

    }



    class LoadViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTitle: TextView = view.findViewById(R.id.tvTitle)
        var mSourceAddress: TextView = view.findViewById(R.id.tvSourceAddress)
        var mDestinationAddress: TextView = view.findViewById(R.id.tvDestinationAddress)
        var mTruckTypeValue: TextView = view.findViewById(R.id.tvTruckTypeValue)
        var mSourceFullAddress: TextView = view.findViewById(R.id.tvSourceFullAddress)
        var mDestinationFullAddress: TextView = view.findViewById(R.id.tvDestinationFullAddress)
        var mShowDetails: LinearLayout = view.findViewById(R.id.llShowDetails)
        var mLoadNumber: TextView = view.findViewById(R.id.tvLoadNumber)
        var mShowMore: ImageView = view.findViewById(R.id.imgShowMore)
        var sideBar: View = view.findViewById(R.id.sideBar)
        var mAcceptLoad: LinearLayout = view.findViewById(R.id.llAcceptLoad)
        var mReserveLoad: LinearLayout = view.findViewById(R.id.llReserveLoad)
        var mUnSuitableLoad: LinearLayout = view.findViewById(R.id.llUnSuitableLoad)
        var mMainLoadCardView :LinearLayout = view.findViewById(R.id.llMainLoadCardView)

    }
}


val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Trip>() {
    override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
        return oldItem == newItem
    }
}