package com.example.myfirstwords
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter(
    private val wordItemLayout: Int,
    private val onItemClicked: (WordList) -> Unit) :
    RecyclerView.Adapter<WordListAdapter.ViewHolder>() {

    private var wordList: List<WordList>? = null

    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val item = holder.item
        wordList?.let {
            val word = it[listPosition]
            item.text = word.wordSaved
            holder.itemView.setOnClickListener {
                onItemClicked(word)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(wordItemLayout, parent, false)
        return ViewHolder(view)
    }
    fun setWordList(words: List<WordList>) {
        wordList = words
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return if (wordList == null) 0 else wordList!!.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: TextView = itemView.findViewById(R.id.wordRow)
    }
}