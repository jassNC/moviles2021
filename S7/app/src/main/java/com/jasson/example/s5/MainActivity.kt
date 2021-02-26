package com.jasson.example.s5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jasson.example.s5.database.LocalDataBase
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), MovieSelectionListener {

    private val movieList = ArrayList<Footage>()
    private val movieList2 = ArrayList<Footage>()
    private var customAdapter: CustomAdapter?=null
    private var deletedFootage: Footage?=null
    private var deletedFootageIndex: Int?=null

    companion object{
        var footageList = ArrayList<Footage>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMovies()
        getAllFootage()

        my_recycler_view.layoutManager = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
        customAdapter = CustomAdapter(movieList, this)
        my_recycler_view.adapter = customAdapter
        addBtn.setOnClickListener {
            addMovie()
            customAdapter!!.notifyDataSetChanged()
        }


    }

    private fun loadMovies(){
        movieList2.add(Footage(getString(R.string.endgameTitle),"| ${getString(R.string.action)} ${getString(R.string.adventure)} |",getString(R.string.endgame), "endgame",getString(R.string.movie),"https://www.imdb.com/title/tt4154796/","https://terrigen-cdn-dev.marvel.com/content/prod/1x/ae_digital_packshot.jpg"))
        movieList2.add(Footage(getString(R.string.alienTitle),"| ${getString(R.string.horror)} ${getString(R.string.scifi)} |",getString(R.string.alien), "alien",getString(R.string.movie),"https://www.imdb.com/title/tt0078748/","https://pics.filmaffinity.com/Alien_el_octavo_pasajero-657278575-mmed.jpg"))
        movieList2.add(Footage(getString(R.string.elmTitle),"| ${getString(R.string.fantasy)} ${getString(R.string.horror)} |",getString(R.string.elm_street),"elm_street",getString(R.string.movie),"https://www.imdb.com/title/tt0093629/","https://upload.wikimedia.org/wikipedia/en/7/7f/A_Nightmare_on_Elm_Street_3_Dream_Warriors.jpg"))
        movieList2.add(Footage(getString(R.string.fourthTitle),"| ${getString(R.string.horror)} ${getString(R.string.scifi)} |",getString(R.string.fourth_kind), "fourth_kind",getString(R.string.movie),"https://www.imdb.com/title/tt1220198/","https://pm1.narvii.com/6791/430318dea7063b14612f7cb816128878506c4063v2_hq.jpg"))
        movieList2.add(Footage(getString(R.string.fridayTitle),"| ${getString(R.string.horror)} ${getString(R.string.thriller)} |",getString(R.string.friday), "friday",getString(R.string.movie),"https://www.imdb.com/title/tt0083972/","https://i.pinimg.com/originals/97/c9/05/97c905884294ed455f8d6511f5e2bc85.jpg"))
        movieList2.add(Footage(getString(R.string.itTitle),"| ${getString(R.string.horror)} |",getString(R.string.it), "it",getString(R.string.movie),"https://www.imdb.com/title/tt1396484/","https://pics.filmaffinity.com/It-787119144-large.jpg"))
        movieList2.add(Footage(getString(R.string.watchmenTitle),"| ${getString(R.string.action)} ${getString(R.string.drama)} |",getString(R.string.watchmen), "watchmen",getString(R.string.movie),"https://www.imdb.com/title/tt0409459/?ref_=nv_sr_srsg_3","https://pics.filmaffinity.com/Watchmen-337424304-mmed.jpg"))
        movieList2.add(Footage(getString(R.string.watchmenTitle),"| ${getString(R.string.action)} ${getString(R.string.crime)} ${getString(R.string.thriller)} |",getString(R.string.wick),"wick",getString(R.string.movie),"https://www.imdb.com/title/tt2911666/?ref_=nv_sr_srsg_0","https://is2-ssl.mzstatic.com/image/thumb/Video/v4/c6/f5/0c/c6f50c35-324d-0e29-b5f6-882246a1e0f3/pr_source.lsr/320x0w.png"))
        movieList2.add(Footage(getString(R.string.xmenTitle),"| ${getString(R.string.adventure)} ${getString(R.string.scifi)} |",getString(R.string.xmen), "xmen",getString(R.string.movie),"https://www.imdb.com/title/tt1877832/?ref_=nv_sr_srsg_0","https://redactovision.files.wordpress.com/2014/05/x-men-days-of-future-past-poster-a.jpg"))
        movieList2.add(Footage(getString(R.string.monedasTitle),"| ${getString(R.string.drama)} ${getString(R.string.fantasy)} ${getString(R.string.horror)} |",getString(R.string.monedas),"monedas",getString(R.string.tvSeries),"https://www.imdb.com/title/tt9764386/?ref_=nv_sr_srsg_0","https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/30-monedas-eduard-fernandez-fotogramas-1605537679.jpg"))
        movieList2.add(Footage(getString(R.string.huntersTitle),"| ${getString(R.string.crime)} ${getString(R.string.drama)} |",getString(R.string.hunters),"hunters",getString(R.string.tvSeries),"https://www.imdb.com/title/tt7456722/?ref_=nv_sr_srsg_0","https://pics.filmaffinity.com/Hunters_Serie_de_TV-261359907-large.jpg"))
        movieList2.add(Footage(getString(R.string.mandoTitle),"| ${getString(R.string.adventure)} ${getString(R.string.scifi)} |",getString(R.string.mando),"mando",getString(R.string.tvSeries),"https://www.imdb.com/title/tt8111088/?ref_=nv_sr_srsg_0","https://pics.filmaffinity.com/The_MandalorianSeriedeTV-526462730-large.jpg"))
        movieList2.add(Footage(getString(R.string.ashTitle),"| ${getString(R.string.horror)} |",getString(R.string.ash),"ash",getString(R.string.movie),"https://www.imdb.com/title/tt0083907/?ref_=nv_sr_srsg_0","https://images-na.ssl-images-amazon.com/images/I/51pUr3DLs9L._AC_SY445_.jpg"))
        movieList2.add(Footage(getString(R.string.boysTitle),"| ${getString(R.string.action)} ${getString(R.string.crime)} |",getString(R.string.boys),"boys",getString(R.string.tvSeries),"https://www.imdb.com/title/tt1190634/?ref_=fn_al_tt_1","https://as.com/meristation/imagenes/2020/08/05/noticias/1596628368_372018_1596628747_sumario_normal.jpg"))
        movieList2.add(Footage(getString(R.string.terrorTitle),"| ${getString(R.string.adventure)} ${getString(R.string.crime)} |",getString(R.string.terror),"terror",getString(R.string.tvSeries),"https://www.imdb.com/title/tt2708480/?ref_=nv_sr_srsg_0","https://2.bp.blogspot.com/-w1jb0_-8zZA/WsKBCplTNrI/AAAAAAABMV4/VasPX1-SGkI39kaT72U4HLxd_arISWpaQCLcBGAs/s640/the-terror-jared-harris-amc.jpg"))
        movieList2.add(Footage(getString(R.string.crowTitle),"| ${getString(R.string.action)} ${getString(R.string.drama)} ${getString(R.string.fantasy)} |",getString(R.string.crow),"crow",getString(R.string.movie),"https://www.imdb.com/title/tt0109506/?ref_=fn_al_tt_1","https://m.media-amazon.com/images/M/MV5BM2Y4ZGVhZjItNjU0OC00MDk1LWI4ZTktYTgwMWJkNDE5OTcxXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UY1200_CR89,0,630,1200_AL_.jpg"))
        movieList2.add(Footage(getString(R.string.outsiderTitle),"| ${getString(R.string.crime)} ${getString(R.string.drama)} ${getString(R.string.fantasy)} |",getString(R.string.outsider),"outsider",getString(R.string.tvSeries),"https://www.imdb.com/title/tt8550800/?ref_=hm_tpks_tt_7_pd_tp1_cp","https://images-na.ssl-images-amazon.com/images/I/81brH91wqvL.jpg"))
    }


    override fun onMovieSelected(movieIndex: Int) {
        val movieSelected = movieList[movieIndex]
        val detailIntent = Intent(this, MovieDetailActivityActivity::class.java)
        detailIntent.putExtra("movie",movieSelected)
        startActivity(detailIntent)
    }

    override fun onMovieSelectedForDelete(movieIndex: Int) {

        deleteFootage(footageList[movieIndex])

        /*deletedFootageIndex = movieIndex;
        deletedFootage = movieList[movieIndex]
        movieList.removeAt(movieIndex)
        customAdapter!!.notifyDataSetChanged()
        showSnackBar("Footage deleted")
        */
    }

    fun showToast(message: String){
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext,message,duration)
        toast.show()
    }

    fun addMovie(){
        try {
            val n = movieList2[0].title
            //movieList.add(movieList2[0])
            insertFootage(movieList2[0])
            movieList2.removeAt(0)
            showToast("$n added corectly")
        }catch (ex: Exception){
            showToast("No new movies to add")
        }
    }

    fun showSnackBar(message: String){
        val mySnackbar = Snackbar.make(findViewById(R.id.my_coordinator),message,Snackbar.LENGTH_SHORT)
        mySnackbar.setAction("Undo") {
            movieList.add(deletedFootageIndex!!,deletedFootage!!)
            deletedFootageIndex = null
            deletedFootage = null
            customAdapter!!.notifyDataSetChanged()
        }
        mySnackbar.show()
    }

    private fun insertFootage(footage: Footage){
        Thread(Runnable {
            LocalDataBase.getInstance(applicationContext).footageDao.insertFootage(footage)
            getAllFootage()
        }).start()

    }
    private fun deleteFootage(footage: Footage){
        Thread(Runnable {
            LocalDataBase.getInstance(applicationContext).footageDao.deleteFootage(footage)
            getAllFootage()
        }).start()
    }
    private fun getAllFootage(){
        Thread(Runnable {
            footageList = LocalDataBase.getInstance(applicationContext).footageDao.getAllFootage().toMutableList() as ArrayList<Footage>
            runOnUiThread{
                customAdapter?.updateFootage(footageList)
            }
        }).start()
    }
}

