package com.jasson.example.s5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieSelectionListener {

    private val movieList = ArrayList<Footage>()
    private var customAdapter: CustomAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMovies()
        my_recycler_view.layoutManager = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
        customAdapter = CustomAdapter(movieList, this)
        my_recycler_view.adapter = customAdapter

    }

    private fun loadMovies(){
        movieList.add(Footage("Avengers Endgame","| Action, Adventure, Drama |","After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.", "endgame","Movie"))
        movieList.add(Footage("Alien","| Horror, Sci-Fi |","After a space merchant vessel receives an unknown transmission as a distress call, one of the crew is attacked by a mysterious life form and they soon realize that its life cycle has merely begun.", "alien","Movie"))
        movieList.add(Footage("Nightmare on Elm Street 3","| Fantasy, Horror |","A psychiatrist familiar with knife-wielding dream demon Freddy Krueger helps teens at a mental hospital battle the killer who is invading their dreams.","elm_street","Movie" ))
        movieList.add(Footage("The Fourth Kind","| Horror, Mystery, Sci-Fi |","A thriller involving an ongoing unsolved mystery in Alaska, where one town has seen an extraordinary number of unexplained disappearances during the past 40 years and there are accusations of a federal cover up.", "fourth_kind","Movie"))
        movieList.add(Footage("Friday the 13th 3","| Horror, Thriller |","Having revived from his wounds, Jason Voorhees takes refuge at a cabin near Crystal Lake. As a group of co-eds arrive for their vacation, Jason continues his killing spree.", "friday","Movie"))
        movieList.add(Footage("It","| Horror |","In the summer of 1989, a group of bullied kids band together to destroy a shape-shifting monster, which disguises itself as a clown and preys on the children of Derry, their small Maine town.", "it","Movie"))
        movieList.add(Footage("Watchmen","| Action, Drama, Mystery |","Set in an alternate history where masked vigilantes are treated as outlaws, Watchmen embraces the nostalgia of the original groundbreaking graphic novel of the same name, while attempting to break new ground of its own.", "watchmen","Movie"))
        movieList.add(Footage("John Wick","| Action, Crime, Thriller |","An ex-hit-man comes out of retirement to track down the gangsters that killed his dog and took everything from him.","wick","Movie"))
        movieList.add(Footage("X Men: Days of future past","| Action, Adventure, Sci-Fi |","The X-Men send Wolverine to the past in a desperate effort to change history and prevent an event that results in doom for both humans and mutants.", "xmen","Movie"))
        movieList.add(Footage("30 Monedas","| Drama, Fantasy, Horror |","An exiled priest tries to escape his demons while living in a remote village in Spain.","monedas","TV Series"))
        movieList.add(Footage("Hunters","| Crime, Drama, Mystery |","In 1977, in New York City, a troubled young Jewish man bent on revenge is taken in by a secret group of Nazi hunters fighting a clandestine war against the cabal of high-ranking Nazi officials in hiding who work to create the Fourth Reich.","hunters","TV Series"))
        movieList.add(Footage("The Mandalorian","| Action, Adventure, Sci-Fi |","The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic.","mando","TV Series"))

    }

    override fun onMovieSelected(movieIndex: Int) {
        val movieSelected = movieList[movieIndex]
        val detailIntent = Intent(this, MovieDetailActivityActivity::class.java)
        detailIntent.putExtra("movie",movieSelected)
        startActivity(detailIntent)
    }


}

