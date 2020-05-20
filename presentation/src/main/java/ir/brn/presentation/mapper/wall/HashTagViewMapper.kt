package ir.brn.presentation.mapper.wall

import ir.brn.domain.model.wall.HashTag
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.wall.HashTagView
import javax.inject.Inject

class HashTagViewMapper @Inject constructor() : Mapper<HashTagView, HashTag> {
    override fun mapToView(type: HashTag): HashTagView {
        return HashTagView(type.id,
                type.text)
    }

    override fun mapFromView(view: HashTagView): HashTag {
        return HashTag(view.id,
                view.text)
    }
}