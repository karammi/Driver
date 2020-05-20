package ir.brn.driver.mapper.wall

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.model.wall.HashTag
import ir.brn.presentation.model.wall.HashTagView
import javax.inject.Inject

class HashTagViewMapper @Inject constructor() : ViewMapper<HashTagView, HashTag> {

    override fun mapToView(presentation: HashTagView): HashTag {
        return HashTag(presentation.id,
                presentation.text)
    }

    fun mapFromView(hashTag: HashTag): HashTagView {
        return HashTagView(hashTag.id,
                hashTag.text)
    }
}