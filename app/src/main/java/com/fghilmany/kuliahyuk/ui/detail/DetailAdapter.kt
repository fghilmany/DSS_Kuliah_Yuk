package com.fghilmany.kuliahyuk.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fghilmany.kuliahyuk.R
import com.fghilmany.kuliahyuk.core.data.source.local.entity.AlternativeValueEntity
import com.fghilmany.kuliahyuk.core.data.source.local.entity.CriteriaEntity
import kotlinx.android.synthetic.main.item_detail.view.*


class DetailAdapter(private val listener: (AlternativeValueEntity) -> (Unit)) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private var listResult = ArrayList<AlternativeValueEntity>()
    private var listCriteria = ArrayList<CriteriaEntity>()

    fun setResult(result: List<AlternativeValueEntity>?) {
        if (result == null) return
        listResult.clear()
        listResult.addAll(result)
    }

    fun setCriteria(result: List<CriteriaEntity>?) {
        if (result == null) return
        listCriteria.clear()
        listCriteria.addAll(result)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int = listResult.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val result = listResult[position]
        val criteria = listCriteria[position]
        holder.bind(result, listener, criteria)
    }

    class DetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
        fun bind(result: AlternativeValueEntity,
                 listener: (AlternativeValueEntity) -> Unit,
                 criteria: CriteriaEntity
        ) {
            with(itemView) {
                tv_value.text = result.value.toString()
                tv_criteria_name.text = "${ criteria.name } :"
                tv_criteria_unit.text = criteria.unit
                tv_weight_criteria.text = criteria.weight.toInt().toString()
            }
        }

    }

}