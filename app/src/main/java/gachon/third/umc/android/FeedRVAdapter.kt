package gachon.third.umc.android

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.databinding.FeedRvItemBinding
import gachon.third.umc.android.databinding.StoryRvItemBinding

class FeedRVAdapter (private val FeedList: ArrayList<FeedData>): RecyclerView.Adapter<FeedRVAdapter.DataViewHolder>(){

    // ViewHolder 객체
    inner class DataViewHolder(private val viewBinding: FeedRvItemBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(data: FeedData){
            viewBinding.imgFeedProfile.setImageResource(data.profile_icon)
            viewBinding.tvFeedName.text = data.profile_name
            viewBinding.imgFeedPhoto.setImageResource(data.feed_photo)
            viewBinding.tvFeedTotalLike.text = data.total_like
            viewBinding.tvFeedComment.text = data.comment_content
            viewBinding.tvFeedTotalComment.text = data.total_comment
            viewBinding.tvFeedPostDay.text = data.post_day

            val textData: String = viewBinding.tvFeedComment.text.toString()

            val builder = SpannableStringBuilder(textData)

            builder.setSpan(StyleSpan(Typeface.BOLD),0,data.profile_name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            viewBinding.tvFeedComment.text = builder
        }
    }

    // ViewHolder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = FeedRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return DataViewHolder(viewBinding)
    }

    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(FeedList[position])
    }

    // 표현할 Item의 총 갯수
    override fun getItemCount(): Int = FeedList.size

}