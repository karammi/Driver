package ir.brn.presentation.mapper.enums

import ir.brn.domain.model.enums.UserType
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.enums.UserTypeView
import javax.inject.Inject

class UserTypeViewMapper @Inject constructor() : Mapper<UserTypeView, UserType> {

    override fun mapToView(type: UserType): UserTypeView {
        return UserTypeView.valueOf(type.type)
    }

    override fun mapFromView(view: UserTypeView): UserType {
        return UserType.valueOf(view.type)
    }
}