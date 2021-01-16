package com.fghilmany.kuliahyuk.ui.criteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fghilmany.kuliahyuk.R
import com.fghilmany.kuliahyuk.core.data.source.local.entity.CriteriaEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_criteria.*
import org.koin.android.viewmodel.ext.android.viewModel

class CriteriaActivity : AppCompatActivity() {

    private val viewModel: CriteriaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criteria)

        val criteriaAdapter = CriteriaAdapter{
            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setTitle("Hapus Kriteria")
                .setMessage("Apakah kamu akan menghapus kriteria \"${it.name}\" ?")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("Hapus"){dialogInterface, _ ->
                    viewModel.setParameter(it.id)
                    viewModel.deleteCriteriaById()
                    dialogInterface.dismiss()
                }
                .setNegativeButton("batal"){dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .show()
        }
        viewModel.getListCriteria().observe(this, Observer {
            criteriaAdapter.setCriteria(it)
            criteriaAdapter.notifyDataSetChanged()
        })
        with(rv_criteria){
            layoutManager = LinearLayoutManager(this@CriteriaActivity)
            setHasFixedSize(true)
            adapter = criteriaAdapter
        }

        btn_criteria.setOnClickListener {

            val criteriaName = edt_criteria.text.toString()
            val criteriaUnit = edt_unit.text.toString()
            val criteriaWeight = edt_weight.text.toString()

            if (criteriaName.isEmpty()){
                edt_criteria.error = "Nama tidak boleh kosong"
                edt_criteria.requestFocus()
                return@setOnClickListener
            }

            if (criteriaUnit.isEmpty()){
                edt_unit.error = "Satuan tidak boleh kosong"
                edt_unit.requestFocus()
                return@setOnClickListener
            }

            if (criteriaWeight.isEmpty()){
                edt_weight.error = "Bobot tidak boleh kosong"
                edt_weight.requestFocus()
                return@setOnClickListener
            }

            if (criteriaWeight.toInt() > 5 || criteriaWeight.toInt() <= 1){
                edt_weight.error = "Bobot Maksimal 5 dan Minimal 1"
                edt_weight.requestFocus()
                return@setOnClickListener
            }

            viewModel.insertCriteria(
                CriteriaEntity(
                    name = criteriaName,
                    unit = criteriaUnit,
                    weight = criteriaWeight.toDouble(),
                    type = spinner_type.selectedItem.toString()
                )
            )
        }
    }
}