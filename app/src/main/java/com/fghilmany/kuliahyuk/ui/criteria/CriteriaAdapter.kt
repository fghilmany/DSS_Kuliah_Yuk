package com.fghilmany.kuliahyuk.ui.criteria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fghilmany.kuliahyuk.R
import com.fghilmany.kuliahyuk.core.data.source.local.entity.CriteriaEntity
import kotlinx.android.synthetic.main.item_criteria.view.*


class CriteriaAdapter(private val listener: (CriteriaEntity) -> (Unit)) :
    RecyclerView.Adapter<CriteriaAdapter.CriteriaViewHolder>() {

    private var listCriteria = ArrayList<CriteriaEntity>()

    fun setCriteria(criteria: List<CriteriaEntity>?) {
        if (criteria == null) return
        listCriteria.clear()
        listCriteria.addAll(criteria)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CriteriaViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_criteria, parent, false)
        return CriteriaViewHolder(view)
    }

    override fun getItemCount(): Int = listCriteria.size

    override fun onBindViewHolder(holder: CriteriaViewHolder, position: Int) {
        val criteria = listCriteria[position]
        holder.bind(criteria, listener)
    }

    class CriteriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(criteria: CriteriaEntity, listener: (CriteriaEntity) -> Unit) {
            with(itemView) {
                tv_name_criteria.text = criteria.name
                tv_unit_criteria.text = criteria.unit
                tv_type_criteria.text = criteria.type
                tv_weight_criteria.text = criteria.weight.toInt().toString()

                setOnClickListener {
                    listener(criteria)
                }
            }
        }

    }

}