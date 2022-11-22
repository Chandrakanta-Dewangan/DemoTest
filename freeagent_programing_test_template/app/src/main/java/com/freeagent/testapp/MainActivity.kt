package com.freeagent.testapp
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity(), ActionMode.Callback {

    private var selectedPostItems: MutableList<PostItem> = mutableListOf()
    private var actionMode: ActionMode? = null
    private lateinit var adapter: PostsAdapter
    private var tracker: SelectionTracker<PostItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val postsRecyclerView: RecyclerView = findViewById(R.id.postsRecyclerView)
        postsRecyclerView.isNestedScrollingEnabled = false
        postsRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val postItems: MutableList<PostItem> = mutableListOf()
        postItems.add(PostItem("Lee Minho", R.drawable.leeminho))
        postItems.add(PostItem("Lee Jong Suk", R.drawable.leejongsuk))
        postItems.add(PostItem("Cha Eun Woo", R.drawable.chaeunwoo))
        postItems.add(PostItem("Seo Kang Joon", R.drawable.seokangjoon))
        postItems.add(PostItem("Kim Soo Hyun", R.drawable.kimsoohyun))
        postItems.add(PostItem("Park Seo Joon", R.drawable.parkseojoon))
        postItems.add(PostItem("Seo In Guk", R.drawable.seoinguk))
        postItems.add(PostItem("Ji Chang Wook", R.drawable.jichangwook))
        postItems.add(PostItem("Yoo Seung Ho", R.drawable.yooseungho))
        postItems.add(PostItem("Lee Seung Gi", R.drawable.leeseunggi))

        adapter = PostsAdapter(this, postItems)
        postsRecyclerView.adapter = adapter

        tracker = SelectionTracker.Builder<PostItem>(
            "mySelection",
            postsRecyclerView,
            MyItemKeyProvider(adapter),
            MyItemDetailsLookup(postsRecyclerView),
            StorageStrategy.createParcelableStorage(PostItem::class.java)
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        adapter.tracker = tracker

        tracker?.addObserver(object : SelectionTracker.SelectionObserver<PostItem>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    tracker?.let {
                        selectedPostItems = it.selection.toMutableList()
                        if (selectedPostItems.isEmpty()) {
                            actionMode?.finish()
                        } else {
                            if (actionMode == null) actionMode =
                                startSupportActionMode(this@MainActivity)
                            actionMode?.title =
                                "${selectedPostItems.size}"
                        }
                    }
                }
            })
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_view_delete -> {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra ("A_STRING", selectedPostItems.toString())
                intent.putParcelableArrayListExtra("postItem",ArrayList( selectedPostItems))
                startActivity(intent)

              /*  Toast.makeText(
                    this,
                    selectedPostItems.toString(),
                    Toast.LENGTH_LONG
                ).show()*/
            }
        }
        return true
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.let {
            val inflater: MenuInflater = it.menuInflater
            inflater.inflate(R.menu.action_mode_menu, menu)
            return true
        }
        return false
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        adapter.tracker?.clearSelection()
        adapter.notifyDataSetChanged()
        actionMode = null
    }
}
/*import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.freeagent.testapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //https://apilayer.com/account
//API key = 1c1tIVQqcEzv5fr0D9qbJ7u1JJ4oXVvL
    //https://data.fixer.io/api/latest
    //    ? access_key = API_KEY
    private var tracker: SelectionTracker<PostItem>? = null
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        /*if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<RatesListFragment>(R.id.fragment_container_view)
            }
        }*/

        val postsRecyclerView: RecyclerView = findViewById(R.id.postsRecyclerView)
        postsRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val postItems: MutableList<PostItem> = mutableListOf()
        postItems.add(PostItem("Lee Minho", R.drawable.leeminho))
        postItems.add(PostItem("Lee Jong Suk", R.drawable.leejongsuk))
        postItems.add(PostItem("Cha Eun Woo", R.drawable.chaeunwoo))
        postItems.add(PostItem("Seo Kang Joon", R.drawable.seokangjoon))
        postItems.add(PostItem("Kim Soo Hyun", R.drawable.kimsoohyun))
        postItems.add(PostItem("Park Seo Joon", R.drawable.parkseojoon))
        postItems.add(PostItem("Seo In Guk", R.drawable.seoinguk))
        postItems.add(PostItem("Ji Chang Wook", R.drawable.jichangwook))
        postItems.add(PostItem("Yoo Seung Ho", R.drawable.yooseungho))
        postItems.add(PostItem("Lee Seung Gi", R.drawable.leeseunggi)

        postsRecyclerView.adapter = PostsAdapter(this, postItems)
        tracker = SelectionTracker.Builder<PostItem>(
            "mySelection",
            postsRecyclerView,
            MyItemKeyProvider(adapter),
            MyItemDetailsLookup(postsRecyclerView),
            StorageStrategy.createParcelableStorage(PostItem::class.java)
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        adapter.tracker = tracker

        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    tracker?.let {
                        selectedPostItems = it.selection.toMutableList()
                        if (selectedPostItems.isEmpty()) {
                            actionMode?.finish()
                        } else {
                            if (actionMode == null) actionMode =
                                startSupportActionMode(this@MainActivity)
                            actionMode?.title =
                                "${selectedPostItems.size}"
                        }
                    }
                }
            })
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_view_delete -> {
                Toast.makeText(
                    this,
                    selectedPostItems.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return true
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.let {
            val inflater: MenuInflater = it.menuInflater
            inflater.inflate(R.menu.action_mode_menu, menu)
            return true
        }
        return false
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        adapter.tracker?.clearSelection()
        adapter.notifyDataSetChanged()
        actionMode = null
    }
    }
}*/