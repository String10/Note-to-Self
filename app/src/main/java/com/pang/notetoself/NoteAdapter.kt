package com.pang.notetoself

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val mainActivity: MainActivity,
    private val noteList: List<Note>
): RecyclerView.Adapter<NoteAdapter.ListItemHolder>() {
    inner class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        internal var title = view.findViewById<TextView>(R.id.textViewTitle)
//        internal var description = view.findViewById<TextView>(R.id.textViewDescription)
//        internal var status = view.findViewById<TextView>(R.id.textViewStatus)
        internal var todo = view.findViewById<CheckBox>(R.id.todo)

        init {
            view.isClickable = true
            view.setOnClickListener(this)

        }

        override fun onClick(view: View?) {
            mainActivity.showNote(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ListItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return ListItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ListItemHolder, position: Int) {
        val note = noteList[position]
        holder.title.text = note.title
        holder.todo.setOnCheckedChangeListener { compoundButton, isChecked ->
            note.done = isChecked
            if (isChecked) {
                holder.itemView.setBackgroundColor(Color.GRAY)
            } else {
                holder.itemView.setBackgroundColor(Color.WHITE)
            }
        }

        holder.todo.isChecked = note.done

//        holder.description.text = if(note.description!!.length > 15) note.description!!.substring(0, 15) else note.description
//        when {
//            note.idea -> holder.status.text = mainActivity.resources.getString(R.string.idea_text)

//            note.important -> holder.status.text = mainActivity.resources.getString(R.string.important_text)

//            note.todo -> holder.status.text = mainActivity.resources.getString(R.string.todo_text)
//        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}