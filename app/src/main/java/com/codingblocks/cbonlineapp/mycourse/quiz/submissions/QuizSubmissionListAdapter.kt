package com.codingblocks.cbonlineapp.mycourse.quiz.submissions

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingblocks.cbonlineapp.R
import com.codingblocks.cbonlineapp.util.extensions.formatDate
import com.codingblocks.cbonlineapp.util.extensions.sameAndEqual
import com.codingblocks.onlineapi.models.QuizAttempt
import kotlinx.android.synthetic.main.quiz_attempt_list.view.*

class QuizSubmissionListAdapter : ListAdapter<QuizAttempt, QuizSubmissionListAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_quiz_attempt, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: QuizAttempt) = with(itemView) {
            numberTv.text = (adapterPosition + 1).toString()
            statusTv.text = item.status
            dateTv.text = formatDate(item.createdAt!!)
            if (item.result?.score != null)
                scoreTv.text = item.result?.score.toString()
            else
                scoreTv.text = "N/A"
            setOnClickListener {
                //                clickListner(attempt)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<QuizAttempt>() {
        override fun areItemsTheSame(oldItem: QuizAttempt, newItem: QuizAttempt): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuizAttempt, newItem: QuizAttempt): Boolean {
            return oldItem.sameAndEqual(newItem)
        }
    }
}
