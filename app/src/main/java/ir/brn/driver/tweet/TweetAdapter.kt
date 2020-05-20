package ir.brn.driver.tweet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wdullaer.materialdatetimepicker.Utils
import ir.brn.driver.R
import ir.brn.driver.model.wall.Wall
import kotlinx.android.synthetic.main.item_wall_left.view.*
import kotlinx.android.synthetic.main.item_wall_left.view.ivDisLike
import kotlinx.android.synthetic.main.item_wall_left.view.ivLoveTweet
import kotlinx.android.synthetic.main.item_wall_left.view.ivProfileImage
import kotlinx.android.synthetic.main.item_wall_left.view.tvBodyD
import kotlinx.android.synthetic.main.item_wall_left.view.tvCreatedAtD
import kotlinx.android.synthetic.main.item_wall_left.view.tvLikes
import kotlinx.android.synthetic.main.item_wall_left.view.tvRetweets
import kotlinx.android.synthetic.main.item_wall_left.view.tvScreenName
import kotlinx.android.synthetic.main.item_wall_left.view.tvUserName
import kotlinx.android.synthetic.main.item_wall_right.view.*
import javax.inject.Inject

class WallAdapter @Inject constructor() : ListAdapter<Wall, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    var tweetListener: TweetListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_wall_right, parent, false)
                RightWallViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_wall_left, parent, false)
                LeftWallViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 -> (holder as RightWallViewHolder).bind(getItem(position))
            else -> (holder as LeftWallViewHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).owner == true)
            1
        else
            2
    }


    inner class RightWallViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {

        fun bind(wall: Wall) {
            mView.tvUserName.text = Utils.convertDigitsToPersian(wall.user?.name)
            mView.tvScreenName.text = Utils.convertDigitsToPersian("${wall.truckType?.name
                    ?: ""} ${wall.carrierType?.name ?: ""}").trim()
            mView.tvBodyD.text = Utils.convertDigitsToPersian(wall.body)
            mView.tvCreatedAtD.text = Utils.convertDigitsToPersian(wall.createdAtShamsi)
            mView.tvLikes.text = Utils.convertDigitsToPersian(wall.like.toString())
            mView.tvRetweets.text = Utils.convertDigitsToPersian(wall.disLike.toString())
            mView.ivProfileImage.setColorFilter(ContextCompat.getColor(mView.context, R.color.baron_blueMediumLight))
            mView.ivLoveTweet.setOnClickListener {
                wall.id?.let { it1 -> tweetListener?.likeTweet(it1) }
            }
            mView.ivDisLike.setOnClickListener {
                wall?.id?.let { it ->
                    tweetListener?.disLikeTweet(it)
                }
            }
            mView.ivDelete.setOnClickListener {
                wall?.id?.let { it ->
                    tweetListener?.deleteTweet(it)
                }
            }
            mView.ivEdit.setOnClickListener {
                wall?.let { it ->
                    tweetListener?.editTweet(it)
                }
            }
        }
    }

    inner class LeftWallViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(wall: Wall) {
            mView.tvUserName.text = Utils.convertDigitsToPersian(wall.user?.name)
            mView.tvScreenName.text = Utils.convertDigitsToPersian("${wall.truckType?.name
                    ?: ""} ${wall.carrierType?.name ?: ""}").trim()
            mView.tvBodyD.text = Utils.convertDigitsToPersian(wall.body)
            mView.tvCreatedAtD.text = Utils.convertDigitsToPersian(wall.createdAtShamsi)
            mView.tvLikes.text = Utils.convertDigitsToPersian(wall.like.toString())
            mView.tvRetweets.text = Utils.convertDigitsToPersian(wall.disLike.toString())
            mView.ivProfileImage.setColorFilter(ContextCompat.getColor(mView.context, R.color.baron_grayLight))
            mView.ivLoveTweet.setOnClickListener {
                wall.id?.let { it1 -> tweetListener?.likeTweet(it1) }
            }
            mView.ivDisLike.setOnClickListener {
                wall?.id?.let { it ->
                    tweetListener?.disLikeTweet(it)
                }
            }

        }
    }
}

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Wall>() {
    override fun areItemsTheSame(oldItem: Wall, newItem: Wall): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Wall, newItem: Wall): Boolean {
        return oldItem == newItem
    }
}