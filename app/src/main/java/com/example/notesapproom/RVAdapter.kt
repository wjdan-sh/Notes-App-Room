package com.example.notesapproom

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*


class RVAdapter(
    private val activity: MainActivity, private val messages: List<Notes>): RecyclerView.Adapter<RVAdapter.MessageViewHolder>() {

    class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.MessageViewHolder {
        return RVAdapter.MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RVAdapter.MessageViewHolder, position: Int) {
        val Note = messages[position]

        holder.itemView.apply {
            tvMessage.text = Note.note
            if(position%2==0){llitem.setBackgroundColor(Color.parseColor("#A5D1D6"))}
            EditNote.setOnClickListener {
                activity.Dialog(Note)
            }

            DeleteNote.setOnClickListener {
                activity.delete(Note)
            }
        }
    }

    override fun getItemCount() = messages.size
}
