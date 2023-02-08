package gachon.third.umc.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.Constants.StoryViewType
import gachon.third.umc.android.databinding.ProfileStoryAddHlBinding
import gachon.third.umc.android.databinding.ProfileStoryHl1Binding
import gachon.third.umc.android.databinding.StoryRvAddItemBinding
import gachon.third.umc.android.databinding.StoryRvItemBinding

class StoryRVAdapter (private val storyList: ArrayList<StoryData>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    interface OnItemClickListener{
        fun onItemClick(v:View, data: StoryData, pos : Int)
    }
    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    // ViewHolder 객체
    inner class DataViewHolder1(private val viewBinding: StoryRvAddItemBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: StoryData){
            viewBinding.imgStoryProfile.setImageResource(item.profileImg)
            viewBinding.tvStoryName.text = item.name

        }
    }
    inner class DataViewHolder2(private val viewBinding: StoryRvItemBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: StoryData){
            viewBinding.imgStoryProfile.setImageResource(item.profileImg)
            viewBinding.tvStoryName.text = item.name

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, item, pos)
                }
            }
        }
    }
    inner class DataViewHolder3(private val viewBinding: ProfileStoryAddHlBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: StoryData){
            viewBinding.btnAddHl.setImageResource(item.profileImg)
            viewBinding.tvStoryName.text = item.name
        }
    }
    inner class DataViewHolder4(private val viewBinding: ProfileStoryHl1Binding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: StoryData){
            viewBinding.imgProfileSrc.setImageResource(item.profileImg)
            viewBinding.tvStoryName.text = item.name
        }
    }

    // ViewHolder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == StoryViewType.add) {
            val viewBinding =
                StoryRvAddItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
            return DataViewHolder1(viewBinding)
        }
        else if(viewType == StoryViewType.story) {
            val viewBinding =
                StoryRvItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
            return DataViewHolder2(viewBinding)
        }
        else if(viewType == StoryViewType.profileStoryAdd) {
            val viewBinding =
                ProfileStoryAddHlBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
            return DataViewHolder3(viewBinding)
        }
        val viewBinding =
            ProfileStoryHl1Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return DataViewHolder4(viewBinding)
    }

    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(storyList[position].viewType){
            StoryViewType.add -> {
                (holder as DataViewHolder1).bind(storyList[position])
            }
            StoryViewType.story -> {
                (holder as DataViewHolder2).bind(storyList[position])
            }
            StoryViewType.profileStoryAdd -> {
                (holder as DataViewHolder3).bind(storyList[position])
            }
            StoryViewType.profileStory -> {
                (holder as DataViewHolder4).bind(storyList[position])
            }
        }
    }

    // 표현할 Item의 총 갯수
    override fun getItemCount(): Int = storyList.size

    override fun getItemViewType(position: Int): Int {
        return storyList[position].viewType
    }


}