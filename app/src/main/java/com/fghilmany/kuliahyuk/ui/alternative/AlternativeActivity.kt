package com.fghilmany.kuliahyuk.ui.alternative

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fghilmany.kuliahyuk.R
import com.fghilmany.kuliahyuk.core.data.source.local.entity.*
import kotlinx.android.synthetic.main.activity_alternative.*
import kotlinx.android.synthetic.main.item_input_criteria.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class AlternativeActivity : AppCompatActivity() {

    companion object{
        const val isEdit = "is_edit"
        const val ID_ALTERNATIVE = "id_alternative"
    }

    private val viewModel: AlternativeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alternative)

        val inputAdapter = InputCriteriaAdapter()
        val extras = intent.extras
        if (extras != null){
            inputAdapter.setEditState(extras.getBoolean(isEdit))
            viewModel.setId(extras.getInt(ID_ALTERNATIVE))
            viewModel.getCriteria().observe(this, Observer {criteria ->
                inputAdapter.setCriteria(criteria)
                viewModel.getAlternativeValueById().observe(this, Observer { alternativeValue ->
                    inputAdapter.setSizeData(alternativeValue.size)
                    inputAdapter.setAlternativeValue(alternativeValue)
                    inputAdapter.notifyDataSetChanged()
                })

                btn_add_alternative.setOnClickListener {
                    viewModel.insertAlternative(
                        AlternativeEntity(
                            name = edt_alternative.text.toString()                    )
                    )
                    viewModel.getAlternative().observe(this, Observer {

                        var countWeights = 0.0
                        for (i in criteria){
                            countWeights += i.weight
                        }

                        var valuePreference = 1.0

                        for (i in criteria.indices){
                            val view: View = rv_item_criteria.getChildAt(i)
                            val alternativeValue = view.edt_input_criteria

                            viewModel.scaleWeights(criteria[i].weight, countWeights)
                            val normalizeWeight= viewModel.eachWeight()
                            viewModel.countSVector(alternativeValue.text.toString().toDouble(), normalizeWeight)
                            viewModel.setState(criteria[i].type)
                            valuePreference *= viewModel.preferenceValue()

                            Timber.tag("Normalisasai Bobot").e(normalizeWeight.toString())

                            viewModel.insertAlternativeValue(
                                AlternativeValueEntity(
                                    idAlternative = extras.getInt(ID_ALTERNATIVE),
                                    idCriteria = criteria[i].id,
                                    value = alternativeValue.text.toString().toInt()
                                )
                            )
                        }
                        Timber.tag("Nilai Preferensi").e(valuePreference.toString())

                        viewModel.insertPreferenceValue(
                            PreferenceValueEntity(
                                id = extras.getInt(ID_ALTERNATIVE),
                                nameAlternative = edt_alternative.text.toString(),
                                preferenceValue = valuePreference
                            )
                        )

                        viewModel.deleteResult()
                    })
                    Toast.makeText(this, "Berhasil Mengedit Sekolah/Universitas", Toast.LENGTH_SHORT).show()
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            })

            viewModel.gerResult().observe(this, Observer {

                for (i in it) {
                    if (i.id == extras.getInt(ID_ALTERNATIVE)) {
                        edt_alternative.setText(i.name_university)
                        break
                    }
                }
            })


        }else{
            viewModel.getCriteria().observe(this, Observer {criteria ->
                inputAdapter.setCriteria(criteria)
                inputAdapter.notifyDataSetChanged()

                btn_add_alternative.setOnClickListener {
                    viewModel.insertAlternative(
                        AlternativeEntity(
                            name = edt_alternative.text.toString()                    )
                    )
                    viewModel.getAlternative().observe(this, Observer {
                        val id = if (it.isEmpty()) it.size
                        else{
                            it.last().id+1
                        }

                        var countWeights = 0.0
                        for (i in criteria){
                            countWeights += i.weight
                        }

                        var valuePreference = 1.0

                        for (i in criteria.indices){
                            val view: View = rv_item_criteria.getChildAt(i)
                            val alternativeValue = view.edt_input_criteria

                            viewModel.scaleWeights(criteria[i].weight, countWeights)
                            val normalizeWeight= viewModel.eachWeight()
                            viewModel.countSVector(alternativeValue.text.toString().toDouble(), normalizeWeight)
                            viewModel.setState(criteria[i].type)
                            valuePreference *= viewModel.preferenceValue()

                            Timber.tag("Normalisasai Bobot").e(normalizeWeight.toString())

                            viewModel.insertAlternativeValue(
                                AlternativeValueEntity(
                                    idAlternative = id,
                                    idCriteria = criteria[i].id,
                                    value = alternativeValue.text.toString().toInt()
                                )
                            )
                        }
                        Timber.tag("Nilai Preferensi").e(valuePreference.toString())

                        viewModel.insertPreferenceValue(
                            PreferenceValueEntity(
                                id = id,
                                nameAlternative = edt_alternative.text.toString(),
                                preferenceValue = valuePreference
                            )
                        )

                        viewModel.deleteResult()
                    })
                    Toast.makeText(this, "Berhasil Menambahkan Sekolah/Universitas Baru", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
        }
        with(rv_item_criteria){
            layoutManager = LinearLayoutManager(this@AlternativeActivity)
            setHasFixedSize(true)
            adapter = inputAdapter
        }
    }
}