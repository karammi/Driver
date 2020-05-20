package ir.brn.driver.tweet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.wdullaer.materialdatetimepicker.Utils
import dagger.android.support.AndroidSupportInjection
import ir.brn.driver.R
import ir.brn.driver.ext.get
import ir.brn.driver.injection.ViewModelFactory
import ir.brn.driver.mapper.wall.WallViewMapper
import ir.brn.driver.model.wall.Wall
import ir.brn.driver.user.afterTextChanged
import ir.brn.presentation.WallViewModel
import ir.brn.presentation.state.Resource
import ir.brn.presentation.state.ResourceState
import kotlinx.android.synthetic.main.fragment_add_tweet.*
import javax.inject.Inject

class AddTweet : Fragment() {

    private lateinit var wallViewModel: WallViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var wallViewMapper: WallViewMapper

    var tempWall: Wall? = null
    var type: Int? = 1

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_tweet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        type = arguments?.getInt("type", 1) as Int

        if (type == 2) {
            tempWall = arguments?.getSerializable("wall") as Wall?
            etTweet.setText(tempWall?.body.toString())
        }

        wallViewModel = ViewModelProviders.of(this, viewModelFactory).get()
        wallViewModel.getLiveDataDone().observe(this, addEditTweetObserver)
        bTweet.setOnClickListener {
            var currentWall = Wall(body = etTweet.text.toString())
            if (type == 1) {
                wallViewModel.addWall(wallViewMapper.mapFromView(currentWall))
            } else {
                currentWall.id = tempWall?.id
                wallViewModel.editWall(wallViewMapper.mapFromView(currentWall))
            }
        }
        compose_toolbar_cancel_button.setOnClickListener { findNavController().popBackStack() }
        etTweet.afterTextChanged {
            tvCharacterCount.text = Utils.convertDigitsToPersian(it.length.toString())
        }
    }

    private var addEditTweetObserver = Observer<Resource<Boolean>> { resource ->
        when (resource.status) {
            ResourceState.LOADING -> {

            }
            ResourceState.SUCCESS -> {
                findNavController().popBackStack()
            }
            ResourceState.ERROR -> {
                Toast.makeText(activity?.applicationContext, "خطا در ثبت پیام شما رخ داده شده است", Toast.LENGTH_SHORT).show()
            }

        }
    }


}