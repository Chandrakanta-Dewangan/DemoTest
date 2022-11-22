package com.freeagent.testapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.freeagent.testapp.databinding.ItemViewCurrencyBinding

class RatesListAdapter : RecyclerView.Adapter<RatesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemViewCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0 //TODO: Replace this with the actual count
    }

    class ViewHolder(private val binding: ItemViewCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
/*class MemesAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Meme, MemesAdapter.MyViewHolder>(MyDiffUtil) {

    companion object MyDiffUtil : DiffUtil.ItemCallback<Meme>() {
        override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Meme, newItem: Meme): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meme: Meme?) {
            Glide.with(binding.imageView)
                .load(meme?.url)
                .into(binding.imageView)

            binding.memeName.text = meme?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val meme = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(meme)
        }
        holder.bind(meme)
    }

    class OnClickListener(val clickListener: (meme: Meme) -> Unit) {
        fun onClick(meme: Meme) = clickListener(meme)
    }
}*/
