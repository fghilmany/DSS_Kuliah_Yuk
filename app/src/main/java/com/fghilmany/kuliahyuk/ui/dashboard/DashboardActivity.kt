package com.fghilmany.kuliahyuk.ui.dashboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fghilmany.kuliahyuk.R
import com.fghilmany.kuliahyuk.core.data.source.local.entity.ResultEntity
import com.fghilmany.kuliahyuk.ui.alternative.AlternativeActivity
import com.fghilmany.kuliahyuk.ui.criteria.CriteriaActivity
import com.fghilmany.kuliahyuk.ui.detail.DetailActivity
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

@Suppress("LABEL_NAME_CLASH")
class DashboardActivity : AppCompatActivity() {

    private val mAppUnitId: String by lazy {

        getString(R.string.admob_id)
    }

    private val mInterstitialAdUnitId: String by lazy {

        "ca-app-pub-3940256099942544/1033173712"
    }

    private lateinit var mInterstitialAd: InterstitialAd

    private val viewModel: DashboardViewModel by viewModel()
    private val dashboardAdapter = DashboardAdapter{
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra(DetailActivity.ID_ALTERNATIVE, it.id.toString())
        Timber.tag("cekId").e(it.id.toString())
        startActivity(i)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        MobileAds.initialize(this)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        mInterstitialAd = InterstitialAd(this)

        initializeInterstitialAd(mAppUnitId)

        loadInterstitialAd(mInterstitialAdUnitId)

        eventsAd()

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
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Timber.d("The interstitial ad wasn't loaded yet.")
                startActivity(Intent(this, CriteriaActivity::class.java))
            }
            runAdEventsCriteria()
        }



        btn_add_university.setOnClickListener {
            viewModel.getCriteria().observe(this, Observer {
                if (it.isEmpty()){
                    Toast.makeText(this, "Kriteria Kosong!", Toast.LENGTH_SHORT).show()
                    return@Observer
                }
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    Timber.d( "The interstitial ad wasn't loaded yet.")
                    startActivity(Intent(this, AlternativeActivity::class.java))
                }
                runAdEventsAlternative()
//                startActivityForResult(Intent(this, AlternativeActivity::class.java), 0)
            })

        }
    }
    private fun runAdEventsCriteria() {

        mInterstitialAd.adListener = object : AdListener() {

            // If user clicks on the ad and then presses the back, s/he is directed to DetailActivity.
            override fun onAdClicked() {
                super.onAdOpened()
                mInterstitialAd.adListener.onAdClosed()
            }

            // If user closes the ad, s/he is directed to DetailActivity.
            override fun onAdClosed() {
                startActivity(Intent(this@DashboardActivity, CriteriaActivity::class.java))
            }
        }
    }

    private fun runAdEventsAlternative() {

        mInterstitialAd.adListener = object : AdListener() {

            // If user clicks on the ad and then presses the back, s/he is directed to DetailActivity.
            override fun onAdClicked() {
                super.onAdOpened()
                mInterstitialAd.adListener.onAdClosed()
            }

            // If user closes the ad, s/he is directed to DetailActivity.
            override fun onAdClosed() {
                startActivity(Intent(this@DashboardActivity, AlternativeActivity::class.java))
            }
        }
    }

    private fun initializeInterstitialAd(appUnitId: String) {

        MobileAds.initialize(this, appUnitId)

    }

    private fun loadInterstitialAd(interstitialAdUnitId: String) {

        mInterstitialAd.adUnitId = interstitialAdUnitId
        mInterstitialAd.loadAd(AdRequest.Builder()
            .addTestDevice("F4F2D9E4B25736B32A5AAF149315E92D")
            .build())
    }

    private fun eventsAd() {
        adView.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                adView.visibility = View.GONE
            }
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

    override fun onResume() {
        super.onResume()
        getAndInsertItem()
        Timber.tag("onResume").e("true")
    }

}