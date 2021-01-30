package com.fghilmany.kuliahyuk.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fghilmany.kuliahyuk.R
import com.fghilmany.kuliahyuk.ui.alternative.AlternativeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    companion object{
        const val ID_ALTERNATIVE = "id_alternative"
    }

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = intent.extras
        if (extras != null){
            val id = extras.getString(ID_ALTERNATIVE)
            if (id != null) {
                viewModel.setId(id.toInt())
                setText(id)
                setRecyclerViewValue(id)
                btn_delete_alternative.setOnClickListener {
                    showDialogAndDelete()
                }
                btn_edit_alternative.setOnClickListener {
                    val i = Intent(this, AlternativeActivity::class.java)
                    i.putExtra(AlternativeActivity.isEdit, true)
                    i.putExtra(AlternativeActivity.ID_ALTERNATIVE, id.toInt())
                    startActivity(i)
                }

            }
        }

    }

    private fun showDialogAndDelete() {
        MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
            .setTitle("Hapus Alternative?")
            .setMessage("Apakah kamu akan menghapus alternative ini?")
            .setIcon(R.mipmap.ic_launcher)
            .setPositiveButton("Hapus"){dialogInterface, _ ->
                viewModel.deleteAlternativeValue()
                viewModel.deletePreferenceValue()
                viewModel.deleteResult()
                finish()
                dialogInterface.dismiss()
            }
            .setNegativeButton("batal"){dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .show()
    }

    private fun setText(id: String) {
        viewModel.gerResult().observe(this, Observer {

            for (i in it) {
                if (i.id == id.toInt()) {
                    tv_title.text = i.name_university
                    break
                }
            }
        })
    }

    private fun setRecyclerViewValue(id: String) {
        val detailAdapter = DetailAdapter {}
        viewModel.getCriteria().observe(this, Observer {
            detailAdapter.setCriteria(it)
        })
        viewModel.getAlternativeValueById().observe(this, Observer {
            detailAdapter.setResult(it)
            detailAdapter.notifyDataSetChanged()

        })

        with(rv_detail) {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            setHasFixedSize(true)
            adapter = detailAdapter
        }
    }


}