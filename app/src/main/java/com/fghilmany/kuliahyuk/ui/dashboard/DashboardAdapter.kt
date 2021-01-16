package com.fghilmany.kuliahyuk.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fghilmany.kuliahyuk.R
import com.fghilmany.kuliahyuk.core.data.source.local.entity.ResultEntity
import kotlinx.android.synthetic.main.item_dashboard.view.*


class DashboardAdapter(private val listener: (ResultEntity) -> (Unit)) :
    RecyclerView.Adapter<DashboardAdapter.CriteriaViewHolder>() {

    private var listResult = ArrayList<ResultEntity>()

    fun setResult(result: List<ResultEntity>?) {
        if (result == null) return
        listResult.clear()
        listResult.addAll(result)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CriteriaViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard, parent, false)
        return CriteriaViewHolder(view)
    }

    override fun getItemCount(): Int = listResult.size

    override fun onBindViewHolder(holder: CriteriaViewHolder, position: Int) {
        val result = listResult[position]
        holder.bind(result, listener, position+1)
    }

    class CriteriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
        fun bind(result: ResultEntity, listener: (ResultEntity) -> Unit, position: Int) {
            with(itemView) {
                tv_result.text = "Hasil Hitungan: "+result.result
                tv_position_item.text = position.toString()
                if (position <= 3){
                    //iv_position.setBackgroundResource(R.drawable.ic_circle)
                    iv_position.setImageDrawable(resources.getDrawable(R.drawable.ic_crown))
                }else{
                    iv_position.setImageDrawable(resources.getDrawable(R.drawable.ic_circle))
                }
                tv_university_name.text = result.name_university
            }
        }

    }

}