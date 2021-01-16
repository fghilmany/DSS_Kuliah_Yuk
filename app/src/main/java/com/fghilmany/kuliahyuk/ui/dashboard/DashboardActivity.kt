package com.fghilmany.kuliahyuk.ui.dashboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fghilmany.kuliahyuk.R
import com.fghilmany.kuliahyuk.core.data.source.local.entity.ResultEntity
import com.fghilmany.kuliahyuk.ui.alternative.AlternativeActivity
import com.fghilmany.kuliahyuk.ui.criteria.CriteriaActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

@Suppress("LABEL_NAME_CLASH")
class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModel()
    private val dashboardAdapter = DashboardAdapter{}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        viewModel.getResult().observe(this, Observer {
            if (it.isEmpty()) return@Observer
                dashboardAdapter.setResult(it)
                dashboardAdapter.notifyDataSetChanged()

        })

        with(rv_dashboard){
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            setHasFixedSize(true)
            adapter = dashboardAdapter
        }

        btn_add_criteria.setOnClickListener {
            startActivity(Intent(this, CriteriaActivity::class.java))
        }

        btn_add_university.setOnClickListener {
            startActivityForResult(Intent(this, AlternativeActivity::class.java), 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0){
            if (resultCode == Activity.RESULT_OK){
                getAndInsertItem()
            }
        }
    }

    private fun getAndInsertItem(){
        viewModel.deleteResult()
        viewModel.getPreferenceValue().observe(this, Observer {
            var countPreferenceValue = 0.0
            for (i in it) countPreferenceValue += i.preferenceValue

            val index = it.size
            for (i in  it.indices){

                viewModel.countVVector(it[i].preferenceValue, countPreferenceValue)
                viewModel.insertResult(ResultEntity(
                    id = it[i].id,
                    name_university = it[i].nameAlternative,
                    result = viewModel.vectorValue()
                ))

                Timber.tag("Nilai V").e(viewModel.vectorValue().toString())

            }

        })
    }

}