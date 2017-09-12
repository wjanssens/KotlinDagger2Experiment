package com.robotsandpencils.kotlindaggerexperiement.presentation.main

import com.robotsandpencils.kotlindaggerexperiement.R
import com.robotsandpencils.kotlindaggerexperiement.app.db.User
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_user.view.*

class UserItem(internal val user: User) : Item<ViewHolder>(user.uid.toLong()) {

    override fun getLayout(): Int {
        return R.layout.item_user
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val view = viewHolder.itemView

        view.firstName.text = user.firstName
        view.lastName.text = user.lastName
        view.idNumber.text = user.uid.toString()
    }
}