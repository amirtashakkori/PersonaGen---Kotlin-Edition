package com.example.personagen.Main

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.personagen.Data.Model.User
import com.example.personagen.R
import com.example.personagen.UserInfo.UserInfoActivity
import com.squareup.picasso.Picasso

class UserAdapter(
    val c: Context,
    val users: List<User>,
    val listener: ChangeListener
): RecyclerView.Adapter<UserAdapter.item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): item {
        val view = LayoutInflater.from(c).inflate(R.layout.item_user, parent, false)
        return item(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: item, position: Int) {
        holder.bindUsers(users.get(position))
    }

    inner class item(itemView: View):RecyclerView.ViewHolder(itemView){
        private val userImg: ImageView = itemView.findViewById(R.id.userImg)
        private val userNameTv: TextView = itemView.findViewById(R.id.userNameTv)
        private val userPhoneTv: TextView = itemView.findViewById(R.id.userPhoneTv)
        private val userNatTv: TextView = itemView.findViewById(R.id.userNatTv)
        private val userNatImg: ImageView = itemView.findViewById(R.id.userNatImg)
        private val moreBtn: ImageView = itemView.findViewById(R.id.moreBtn)

        fun bindUsers(user: User){
            Picasso.get().load(user.picture.large).into(userImg)
            val name = "${user.name.first} ${user.name.last}"
            userNameTv.text = name
            userPhoneTv.text = user.phone
            userNatTv.text = user.nat
            userNatImg.setImageResource(getNatFlag(user.nat))

            moreBtn.setOnClickListener { view ->
                val popupMenu = PopupMenu(c, view, Gravity.END, 0, R.style.popUpMenuStyle)
                popupMenu.menuInflater.inflate(R.menu.item_more_option, popupMenu.menu)
                popupMenu.show()

                popupMenu.setOnMenuItemClickListener { item ->
                    if (item.itemId == R.id.deleteBtn) {
                        listener.onDelete(user)
                        true
                    } else {
                        false
                    }
                }
            }

            itemView.setOnClickListener {
                val intent = Intent(c, UserInfoActivity::class.java).apply {
                    putExtra("user", user)
                    putExtra("deleteBtnVisibility", true)
                }
                c.startActivity(intent)
            }

        }

    }

    interface ChangeListener {
        fun onDelete(user: User)
    }

    private fun getNatFlag(nat: String): Int {
        return when (nat) {
            "US" -> R.drawable.ic_flag_us
            "GB" -> R.drawable.ic_flag_uk
            "AU" -> R.drawable.ic_flag_au
            "CA" -> R.drawable.ic_flag_ca
            "CH" -> R.drawable.ic_flag_ch
            "DE" -> R.drawable.ic_flag_de
            "ES" -> R.drawable.ic_flag_es
            "TR" -> R.drawable.ic_flag_tr
            "UA" -> R.drawable.ic_flag_ua
            "FR" -> R.drawable.ic_flag_fr
            "IR" -> R.drawable.ic_flag_ir
            else -> R.drawable.ic_flag_nl
        }
    }

}