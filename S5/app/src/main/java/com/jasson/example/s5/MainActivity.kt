package com.jasson.example.s5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), MovieSelectionListener {

    private val movieList = ArrayList<Footage>()
    private val movieList2 = ArrayList<Footage>()
    private var customAdapter: CustomAdapter?=null
    private var deletedFootage: Footage?=null
    private var deletedFootageIndex: Int?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMovies()
        my_recycler_view.layoutManager = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
        customAdapter = CustomAdapter(movieList, this)
        my_recycler_view.adapter = customAdapter
        addBtn.setOnClickListener {
            addMovie()
            customAdapter!!.notifyDataSetChanged()
        }


    }

    private fun loadMovies(){
        movieList.add(Footage("Avengers Endgame","| Action, Adventure, Drama |","After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.", "endgame","Movie","https://www.imdb.com/title/tt4154796/"))
        movieList.add(Footage("Alien","| Horror, Sci-Fi |","After a space merchant vessel receives an unknown transmission as a distress call, one of the crew is attacked by a mysterious life form and they soon realize that its life cycle has merely begun.", "alien","Movie","https://www.imdb.com/title/tt0078748/"))
        movieList.add(Footage("Nightmare on Elm Street 3","| Fantasy, Horror |","A psychiatrist familiar with knife-wielding dream demon Freddy Krueger helps teens at a mental hospital battle the killer who is invading their dreams.","elm_street","Movie","https://www.imdb.com/title/tt0093629/"))
        movieList.add(Footage("The Fourth Kind","| Horror, Mystery, Sci-Fi |","A thriller involving an ongoing unsolved mystery in Alaska, where one town has seen an extraordinary number of unexplained disappearances during the past 40 years and there are accusations of a federal cover up.", "fourth_kind","Movie","https://www.imdb.com/title/tt1220198/"))
        movieList.add(Footage("Friday the 13th 3","| Horror, Thriller |","Having revived from his wounds, Jason Voorhees takes refuge at a cabin near Crystal Lake. As a group of co-eds arrive for their vacation, Jason continues his killing spree.", "friday","Movie","https://www.imdb.com/title/tt0083972/"))
        movieList.add(Footage("It","| Horror |","In the summer of 1989, a group of bullied kids band together to destroy a shape-shifting monster, which disguises itself as a clown and preys on the children of Derry, their small Maine town.", "it","Movie","https://www.imdb.com/title/tt1396484/"))
        movieList.add(Footage("Watchmen","| Action, Drama, Mystery |","Set in an alternate history where masked vigilantes are treated as outlaws, Watchmen embraces the nostalgia of the original groundbreaking graphic novel of the same name, while attempting to break new ground of its own.", "watchmen","Movie","https://www.imdb.com/title/tt0409459/?ref_=nv_sr_srsg_3"))
        movieList.add(Footage("John Wick","| Action, Crime, Thriller |","An ex-hit-man comes out of retirement to track down the gangsters that killed his dog and took everything from him.","wick","Movie","https://www.imdb.com/title/tt2911666/?ref_=nv_sr_srsg_0"))
        movieList.add(Footage("X Men: Days of future past","| Action, Adventure, Sci-Fi |","The X-Men send Wolverine to the past in a desperate effort to change history and prevent an event that results in doom for both humans and mutants.", "xmen","Movie","https://www.imdb.com/title/tt1877832/?ref_=nv_sr_srsg_0"))
        movieList.add(Footage("30 Monedas","| Drama, Fantasy, Horror |","An exiled priest tries to escape his demons while living in a remote village in Spain.","monedas","TV Series","https://www.imdb.com/title/tt9764386/?ref_=nv_sr_srsg_0"))
        movieList.add(Footage("Hunters","| Crime, Drama, Mystery |","In 1977, in New York City, a troubled young Jewish man bent on revenge is taken in by a secret group of Nazi hunters fighting a clandestine war against the cabal of high-ranking Nazi officials in hiding who work to create the Fourth Reich.","hunters","TV Series","https://www.imdb.com/title/tt7456722/?ref_=nv_sr_srsg_0"))
        movieList.add(Footage("The Mandalorian","| Action, Adventure, Sci-Fi |","The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic.","mando","TV Series","https://www.imdb.com/title/tt8111088/?ref_=nv_sr_srsg_0"))
        movieList2.add(Footage("Evil Dead","| Horror |","Five friends travel to a cabin in the woods, where they unknowingly release flesh-possessing demons.","ash","Movie","https://www.imdb.com/title/tt0083907/?ref_=nv_sr_srsg_0"))
        movieList2.add(Footage("The Boys","| Action, Comedy, Crime |","A group of vigilantes set out to take down corrupt superheroes who abuse their superpowers.","boys","TV Series","https://www.imdb.com/title/tt1190634/?ref_=fn_al_tt_1"))
        movieList2.add(Footage("The Terror","| Adventure, Drama, History |","Supernatural, semihistorical, horror anthology series, where each season is inspired by a different infamous or mysterious real life historical tragedy.","terror","TV Series","https://www.imdb.com/title/tt2708480/?ref_=nv_sr_srsg_0"))
        movieList2.add(Footage("The Crow","| Action, Drama, Fantasy |","A man brutally murdered comes back to life as an undead avenger of his and his fiancée's murder.","crow","Movie","https://www.imdb.com/title/tt0109506/?ref_=fn_al_tt_1"))
        movieList2.add(Footage("The Outsider","| Crime, Drama, Fantasy |","Investigators are confounded over an unspeakable crime that's been committed.","outsider","TV Series","https://www.imdb.com/title/tt8550800/?ref_=hm_tpks_tt_7_pd_tp1_cp"))
    }

    override fun onMovieSelected(movieIndex: Int) {
        val movieSelected = movieList[movieIndex]
        val detailIntent = Intent(this, MovieDetailActivityActivity::class.java)
        detailIntent.putExtra("movie",movieSelected)
        startActivity(detailIntent)
    }

    override fun onMovieSelectedForDelete(movieIndex: Int) {
        deletedFootageIndex = movieIndex;
        deletedFootage = movieList[movieIndex]
        movieList.removeAt(movieIndex)
        customAdapter!!.notifyDataSetChanged()
        showSnackBar("Footage deleted")
    }

    fun showToast(message: String){
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext,message,duration)
        toast.show()
    }

    fun addMovie(){
        try {
            val n = movieList2[0].title
            movieList.add(movieList2[0])
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


}

