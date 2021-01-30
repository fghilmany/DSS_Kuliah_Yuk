package com.fghilmany.kuliahyuk.ui.alternative

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fghilmany.kuliahyuk.R
import com.fghilmany.kuliahyuk.core.data.source.local.entity.AlternativeValueEntity
import com.fghilmany.kuliahyuk.core.data.source.local.entity.CriteriaEntity
import kotlinx.android.synthetic.main.item_input_criteria.view.*
import timber.log.Timber


class InputCriteriaAdapter:
    RecyclerView.Adapter<InputCriteriaAdapter.CriteriaViewHolder>() {

    private var listCriteria = ArrayList<CriteriaEntity>()
    private var listAlternativeValue = ArrayList<AlternativeValueEntity>()

    private var isEdit = false
    private var indexSize = 0

    fun setEditState(isEdit: Boolean){
        this.isEdit = isEdit
    }

    fun setSizeData(indexSize: Int){
        this.indexSize = indexSize
    }

    fun setCriteria(criteria: List<CriteriaEntity>?) {
        if (criteria == null) return
        listCriteria.clear()
        listCriteria.addAll(criteria)
    }

    fun setAlternativeValue(alternativeValue: List<AlternativeValueEntity>?) {
        if (alternativeValue == null) return
        listAlternativeValue.clear()
        listAlternativeValue.addAll(alternativeValue)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CriteriaViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_input_criteria, parent, false)
        return CriteriaViewHolder(view)
    }

    override fun getItemCount(): Int = listCriteria.size

    override fun onBindViewHolder(holder: CriteriaViewHolder, position: Int) {
        val criteria = listCriteria[position]
        if (isEdit){
            Timber.tag("cekPositionSize").e("${indexSize-1}")
            Timber.tag("cekPosition").e("$position")
            val alternativeValue = listAlternativeValue[position]
            if (position > indexSize-1){
                holder.bindEditNullValue(criteria)
            }else{
                holder.bindEdit(criteria, alternativeValue)
            }
        }else{
            holder.bind(criteria)
        }
    }

    class CriteriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(criteria: CriteriaEntity) {
            with(itemView) {
                outlinedTextFieldUsername.hint = criteria.name
                tv_unit_criteria.text = criteria.unit
            }
        }

        fun bindEdit(criteria: CriteriaEntity, alternativeValue: AlternativeValueEntity){
            with(itemView) {
                edt_input_criteria.setText(alternativeValue.value.toString())
                tv_unit_criteria.text = criteria.unit
            }
        }

        fun bindEditNullValue(criteria: CriteriaEntity){
            with(itemView) {
                edt_input_criteria.setText("0")
                tv_unit_criteria.text = criteria.unit
            }
        }

    }
}

