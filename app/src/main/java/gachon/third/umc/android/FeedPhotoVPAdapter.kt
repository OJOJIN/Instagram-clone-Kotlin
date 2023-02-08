package gachon.third.umc.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//class FeedPhotoVPAdapter(private val photoUrlList: List<String>): RecyclerView.Adapter<FeedPhotoVPAdapter.FeedPhotoViewHolder>() {
//    inner class FeedPhotoViewHolder(private val viewBinding: ItemPhotoBinding): RecyclerView.ViewHolder(viewBinding.root) {
//        fun bind(photoUrl: String){
//            GlideApp.with(viewBinding.root)
//                .load(photoUrl)
//                .centerInside()
//                .into(viewBinding.ivPhoto)
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedPhotoViewHolder {
//        val viewBinding = ItemPhotoBinding.inflate(LayoutInflater.form(parent.context), parent, false)
//        return FeedPhotoViewHolder(viewBinding)
//    }
//
//    override fun onBindViewHolder(holder: FeedPhotoViewHolder, position: Int) {
//        holder.bind(photoUrlList[holder.adapterPosition])
//    }
//
//    override fun getItemCount(): Int = photoUrlList.size
//
//}