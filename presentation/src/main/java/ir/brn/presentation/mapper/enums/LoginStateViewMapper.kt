package ir.brn.presentation.mapper.enums

import ir.brn.domain.model.enums.LoginState
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.enums.LoginStateView
import javax.inject.Inject

class LoginStateViewMapper @Inject constructor() : Mapper<LoginStateView, LoginState> {

    override fun mapToView(type: LoginState): LoginStateView {
        return LoginStateView.valueOf(type.value)

    }

    override fun mapFromView(view: LoginStateView): LoginState {
        return LoginState.valueOf(view.value)
    }
}